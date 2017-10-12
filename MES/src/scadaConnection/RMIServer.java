package scadaConnection;

import dto.OrderINFO;
import mes.AbstractMES;
import java.rmi.RemoteException;

public class RMIServer extends AbstractMES{

    public RMIServer() throws RemoteException {
        super();
    }

    @Override
    public void alertPlanted(OrderINFO orderINFO) throws RemoteException {

    }

    @Override
    public void alertDiscarded(OrderINFO orderINFO) throws RemoteException {

    }

    @Override
    public void alertHarvest(OrderINFO orderINFO) throws RemoteException {

    }
}
