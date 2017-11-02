package scadaConnection;

import dao.OrderDAO;
import dto.OrderDTO;
import dto.OrderINFO;
import dto.OrderStatus;
import mes.AbstractMES;
import mes.ExceedsCapacityException;
import mes.IMESServer;
import mes.RMI_Constants;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;

public class RMIServer extends AbstractMES{

    public RMIServer() throws RemoteException {
        super();
        try {
            Registry registry = LocateRegistry.createRegistry(RMI_Constants.MES_PORT);
            IMESServer mes = this;
            registry.bind(RMI_Constants.MES_OBJECTNAME, mes);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alertPlanted(String orderID) throws RemoteException {
        new Thread(()->OrderDAO.get().getOrder(orderID).addPlanted(1)).start();
    }

    /**
     * @param orderID
     * @throws RemoteException
     */
    @Override
    public void alertDiscarded(String orderID) throws RemoteException {
        new Thread(()->OrderDAO.get().getOrder(orderID).addDiscarded(1)).start();
    }
    /**
     * @param orderID
     * @throws RemoteException
     */
    @Override
    public void alertHarvest(String orderID) throws RemoteException {
        new Thread(()->OrderDAO.get().getOrder(orderID).addHarvested(1)).start();
    }

    public void executeOrder(OrderDTO orderDTO) throws ExceedsCapacityException, RemoteException {
        super.executeOrder(new OrderINFO(orderDTO.getArticleNumber(),orderDTO.getQuantity(),orderDTO.getOrderNumber()));
    }
}