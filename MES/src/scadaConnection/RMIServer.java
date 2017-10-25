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
    public void alertPlanted(OrderINFO orderINFO) throws RemoteException {
        OrderDAO.get().getOrder(orderINFO.getOrderID()).addPlanted(1);
    }

    /**
     * @param orderINFO
     * @throws RemoteException
     */
    @Override
    public void alertDiscarded(OrderINFO orderINFO) throws RemoteException {
        OrderDAO.get().getOrder(orderINFO.getOrderID()).addDiscarded(1);
    }
    /**
     * @param orderINFO
     * @throws RemoteException
     */
    @Override
    public void alertHarvest(OrderINFO orderINFO) throws RemoteException {
        OrderDAO.get().getOrder(orderINFO.getOrderID()).addHarvested(1);
    }

    public void executeOrder(OrderDTO orderDTO) throws ExceedsCapacityException {
        super.executeOrder(new OrderINFO(orderDTO.getArticleNumber(),orderDTO.getQuantity(),orderDTO.getOrderNumber()));
    }
}
