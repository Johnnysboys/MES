package ORM;


import ORM.interfaces.IConnection;

public class Wonton {
    private IConnection connection;
    public Wonton(IConnection connection){
        this.connection = connection;
    }
    public Service createService(Model model){
        return new Service(model, this.connection);
    }
}