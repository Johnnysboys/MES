package wonton;


import wonton.interfaces.IConnection;
import wonton.service.Service;

public class Wonton {
    private IConnection connection;
    public Wonton(IConnection connection){
        this.connection = connection;
    }
    public Service createService(Model model){
        return new Service(model, this.connection);
    }
}