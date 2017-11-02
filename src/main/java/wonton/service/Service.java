package wonton.service;

import wonton.*;
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
    public boolean create(List<Data> data) {
        try {
            this.model.validateData(data);
            this.connection.queryData(SQLConstructor.insert(this.model, data), data);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(int id, List<Data> data) {
        try {
            this.model.validateData(data);
            String query = SQLConstructor.update(model, data, id);
            System.out.println(query);
            this.connection.queryData(query, data);
            return true;
        } catch (DoesNotExistsInModelException e) {
            e.printStackTrace();
        } catch (IsNotAnAllowedValueException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Row> find() {
     try{
        String query = SQLConstructor.select(this.model);
         return this.connection.queryModel(query, this.model);
    } catch (Exception e){
        e.printStackTrace();
    }
        return null;
    }

    @Override
    public List<Row> find(String... columns) throws DoesNotExistsInModelException {
        try{
            for(String column : columns) {
                if (!this.model.hasColumn(column)) throw new DoesNotExistsInModelException("Column " + column +" does not exists");
            }
            String query = SQLConstructor.select(this.model, columns);
            return this.connection.queryModel(query, this.model);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Row get(int id) {
        try{
            ArrayList parameters = new ArrayList<Parameter>(){{
                add(new Parameter<>("id", Operators.EQ, id));
            }};
            String query = SQLConstructor.select(this.model, parameters);

            return this.connection.queryModel(query, this.model).get(0);
        } catch (Exception e){

        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        String query = SQLConstructor.delete(this.model, id);
        System.out.printf(query);
        this.connection.querySql(query);
        return false;
    }

}
