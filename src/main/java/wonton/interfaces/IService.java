package wonton.interfaces;

import wonton.Data;
import wonton.Parameter;
import wonton.Row;
import wonton.exceptions.DoesNotExistsInModelException;

import java.util.List;

public interface IService {
    public boolean create(List<Data> data);
    public boolean update(int id, List<Data> data);
    public List<Row> find();
    public List<Row> find(String... columns) throws DoesNotExistsInModelException;
    public List<Row> find(Parameter... parameter) throws DoesNotExistsInModelException;
    public Row get(int id);
    public boolean delete(int id);
}
