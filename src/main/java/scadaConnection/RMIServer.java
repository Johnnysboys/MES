package scadaConnection;
import GUI.GUIController;
import dao_mes.OrderDAO;
import dto_mes.OrderDTO;
import dto.OrderINFO;
import dto_mes.OrderStatus;
import mes.RMI_Constants;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer extends AbstractMES {

    public RMIServer() throws RemoteException {
        super();
        //Uses shared constants from the MESCADAPI.jar library.
        Registry registry;
        try{
            registry=LocateRegistry.getRegistry(RMI_Constants.MES_PORT);

        }catch(Exception e){
            registry = LocateRegistry.createRegistry(RMI_Constants.MES_PORT);
        }
        System.out.println("Registry created.");
        registry.rebind(RMI_Constants.MES_OBJECTNAME, this);
        System.out.println("Server was bound.");
    }

    @Override
    public void alertPlanted(String orderID) throws RemoteException {
        //Starts new thread which sends commands to the OrderDAO.
        //It is possible that the rmi server starts threads implicitly, but it should always start a new thread.
        new Thread(()->{
            System.out.println("Alert planted was registered.");
            OrderDTO temp =OrderDAO.get().getOrder(orderID);
            temp.addPlanted(1);
            OrderDAO.get().updateOrder(temp);
            System.out.println("Order updated after alert planted.");
        }).start();
    }

    /**
     * @param orderID
     * @throws RemoteException
     */
    @Override
    public void alertDiscarded(String orderID) throws RemoteException {
        new Thread(() -> {
            System.out.println("alertDiscarded was registered.");
                OrderDTO temp =OrderDAO.get().getOrder(orderID);
                temp.addDiscarded(1);
                OrderDAO.get().updateOrder(temp);
            System.out.println("Order updated after discarded was registered.");
        }).start();
    }
    /**
     * @param orderID
     * @throws RemoteException
     */
    @Override
    public void alertHarvest(String orderID) throws RemoteException {
        new Thread(()-> {
            System.out.println("alertHarvesting was registered.");
            OrderDTO temp=OrderDAO.get().getOrder(orderID);
            temp.addHarvested(1);
            OrderDAO.get().updateOrder(temp);
            System.out.println("Order has been updated after harvesting.");
        }).start();
    }

    public void executeOrder(OrderDTO orderDTO) throws RemoteException, ExceedsCapacityException, AlreadyExecutedException {
        //Method evaluates which status the order is in, and if it is to be executed, it executes it using the
        //method in the super class, AbstractMES, found in MESCADAPI.jar
        System.out.println("Executing order");

        if(orderDTO.getStatus().equals(OrderStatus.UNSCHEDULED)){
            orderDTO.setStatus(OrderStatus.SCHEDULED);
            super.executeOrder(new OrderINFO(orderDTO.getArticleNumber(),orderDTO.getToBePlanted(),orderDTO.getOrderNumber()));
            orderDTO.setStatus(OrderStatus.IN_PRODUCTION);
            System.out.println("Order is executed");
        }else{
            throw new AlreadyExecutedException("Order ID: "+orderDTO.getOrderNumber()+" is already executed.");
        }
    }
}
