package scadaConnection;

import dao.OrderDAO;
import dto.OrderINFO;
import dto.OrderStatus;
import mes.AbstractMES;
import java.rmi.RemoteException;

public class RMIServer extends AbstractMES{

    public RMIServer() throws RemoteException {
        super();
    }

    @Override
    public void alertPlanted(OrderINFO orderINFO) throws RemoteException {
        OrderDAO.get().getOrder(orderINFO.getOrderID()).setStatus(OrderStatus.IN_PRODUCTION);
    }

    @Override
    public void alertDiscarded(OrderINFO orderINFO) throws RemoteException {

    }

    @Override
    public void alertHarvest(OrderINFO orderINFO) throws RemoteException {

    }
}
