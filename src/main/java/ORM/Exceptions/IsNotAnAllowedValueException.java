package ORM.Exceptions;

public class IsNotAnAllowedValueException extends Exception{
    public IsNotAnAllowedValueException() {
        super("The input value is not in the allowed values for choosen field");
    }
    public IsNotAnAllowedValueException(String ex){
        super(ex);
    }
}
