package ORM.Exceptions;

public class NotEnumTypeException extends Exception{

        public NotEnumTypeException() {
            super("The data type is not an enum, therefore you cant add allowedValues");
        }
    }