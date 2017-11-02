package ORM.exceptions;

public class DoesNotExistsInModelException extends Exception {
    public DoesNotExistsInModelException(){
        super("Field does not exists in the model");
    }

    public DoesNotExistsInModelException(String s) {
        super(s);
    }
}
