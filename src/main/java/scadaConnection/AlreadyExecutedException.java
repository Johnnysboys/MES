package scadaConnection;

import java.rmi.AlreadyBoundException;

public class AlreadyExecutedException extends Exception {
    public AlreadyExecutedException(){
        super();
    }
    public AlreadyExecutedException(String message){
        super(message);
    }
}
