package scadaConnection;
import dao_mes.OrderDAO;
import dto_mes.OrderDTO;
import dto.OrderINFO;
import dto_mes.OrderStatus;
import mes.AbstractMES;
import mes.RMI_Constants;
import mes.ExceedsCapacityException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer extends AbstractMES{

    public RMIServer() throws RemoteException {
        super();
        //Uses shared constants from the MESCADAPI.jar library.
        Registry registry = LocateRegistry.createRegistry(RMI_Constants.MES_PORT);
        registry.rebind(RMI_Constants.MES_OBJECTNAME, this);

    }

    @Override
    public void alertPlanted(String orderID) throws RemoteException {
        //Starts new thread which sends commands to the OrderDAO.
        //It is possible that the rmi server starts threads implicitly, but it should always start a new thread.
        new Thread(()->{
            OrderDTO temp =OrderDAO.get().getOrder(orderID);
            temp.addPlanted(1);
            OrderDAO.get().updateOrder(temp);
        }).start();
    }

    /**
     * @param orderID
     * @throws RemoteException
     */
    @Override
    public void alertDiscarded(String orderID) throws RemoteException {
        new Thread(() -> {
                OrderDTO temp =OrderDAO.get().getOrder(orderID);
                temp.addDiscarded(1);
                OrderDAO.get().updateOrder(temp);
        }).start();
    }
    /**
     * @param orderID
     * @throws RemoteException
     */
    @Override
    public void alertHarvest(String orderID) throws RemoteException {
        new Thread(()-> {
            OrderDTO temp =OrderDAO.get().getOrder(orderID);
            temp.addHarvested(1);
            OrderDAO.get().updateOrder(temp);
        }).start();
    }

    public void executeOrder(OrderDTO orderDTO) throws RemoteException, ExceedsCapacityException {
        //Method evaluates which status the order is in, and if it is to be executed, it executes it using the
        //method in the super class, AbstractMES, found in MESCADAPI.jar
        if(orderDTO.getStatus().equals(OrderStatus.UNSCHEDULED)){
            orderDTO.setStatus(OrderStatus.SCHEDULED);
            super.executeOrder(new OrderINFO(orderDTO.getArticleNumber(),orderDTO.getToBePlanted(),orderDTO.getOrderNumber()));
            orderDTO.setStatus(OrderStatus.IN_PRODUCTION);
        }
    }
}
