package wonton.interfaces;

import wonton.Data;
import wonton.exceptions.DoesNotExistsInModelException;

import java.util.List;

public interface IService {
    public int create(List<Data> data);
    public int update(int id, List<Data> data);
    public int find();
    public int find(String... columns) throws DoesNotExistsInModelException;
    public int get(int id);
    public boolean delete(int id);
}
