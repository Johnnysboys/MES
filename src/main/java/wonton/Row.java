package wonton;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Data> dataList;
    private final int  id;

    public Row(int id){
        this.dataList = new ArrayList<>();
        this.id = id;
    }

    public void add (Data data) {
        this.dataList.add(data);
    }
    public Data get(int index){
        return this.dataList.get(index);
    }
    public Data get(String field){
        for(Data data : dataList){
            if(data.getField().equals(field)) return data;
        }
        return null;
    }
    public List<Data> getData() {
        return this.dataList;
    }
}
