package wonton;

public class Data<T> {
    private T data;
    private String field;

    public Data(String field, T data) {
        this.field = field;
        this.data = data;
    }



    public String getField() {
        return this.field;
    }
    public T getData() {
        return data;
    }


}
