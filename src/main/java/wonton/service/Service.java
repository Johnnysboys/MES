package wonton.service;

import wonton.Data;
import wonton.Model;
import wonton.Parameter;
import wonton.SQLConstructor;
import wonton.exceptions.DoesNotExistsInModelException;
import wonton.exceptions.IsNotAnAllowedValueException;
import wonton.interfaces.IConnection;
import wonton.interfaces.IService;
import wonton.types.Operators;

import java.util.ArrayList;
import java.util.List;

public class Service implements IService {
    private IConnection connection;
    private Model model;

    public Service(Model model, IConnection connection){
        this.model = model;
        this.connection = connection;
        connection.querySql(SQLConstructor.createTable(this.model));
    }

    @Override
    public int create(List<Data> data) {
        try {
            this.model.validateData(data);
            connection.queryData(SQLConstructor.insert(this.model, data), data);
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(int id, List<Data> data) {
        try {
            this.model.validateData(data);
            String query = SQLConstructor.update(model, data, id);
            System.out.println(query);
            this.connection.queryData(query, data);
        } catch (DoesNotExistsInModelException e) {
            e.printStackTrace();
        } catch (IsNotAnAllowedValueException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public int find() {
     try{
        String query = SQLConstructor.select(this.model);
        for(Data data : this.connection.queryModel(query, this.model)){
            System.out.println(data.getField() + " " + data.getData());
        }
    } catch (Exception e){
        e.printStackTrace();
    }
        return 0;
    }

    @Override
    public int find(String... columns) throws DoesNotExistsInModelException {
        try{
            for(String column : columns) {
                if (!this.model.hasColumn(column)) throw new DoesNotExistsInModelException("Column " + column +" does not exists");
            }
            String query = SQLConstructor.select(this.model, columns);
            for(Data data : this.connection.queryModel(query, this.model)){
                System.out.println(data.getField() + " " + data.getData());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int get(int id) {
        try{
            ArrayList parameters = new ArrayList<Parameter>(){{
                add(new Parameter<>("id", Operators.EQ, id));
            }};
            String query = SQLConstructor.select(this.model, parameters);
            for(Data data : this.connection.queryModel(query, this.model)){
                System.out.println(data.getField() + " " + data.getData());
            }
        } catch (Exception e){

        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        String query = SQLConstructor.delete(this.model, id);
        System.out.printf(query);
        this.connection.querySql(query);
        return false;
    }

}
