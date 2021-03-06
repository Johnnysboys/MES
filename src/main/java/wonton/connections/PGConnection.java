package wonton.connections;

import wonton.Data;
import wonton.Row;
import wonton.utils.DataConverter;
import wonton.Model;
import wonton.interfaces.IConnection;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class PGConnection implements IConnection {
    private final String URL;
    private Properties properties = new Properties();
    private Connection connection;
    private Statement statement;

    public PGConnection(String db, String user, String password, String host) throws SQLException {
        this(db, user, password, host, "5432");
    }

    public PGConnection(String db, String user, String password, String host, String port) throws SQLException {
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        this.URL = "jdbc:postgresql://" + host + ":" + port + "/" + db;
        this.connection = DriverManager.getConnection(this.URL, properties);
    }

    @Override
    public List<Row> queryModel(String query, Model model) {
        try {
            this.statement = connection.createStatement();
            ResultSet resultSet = this.statement.executeQuery(query);
            List data = DataConverter.covertResultSetToData(resultSet, model);
            resultSet.close();
            statement.close();
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void querySql(String query) throws SQLException {
        this.statement = connection.createStatement();
        this.statement.executeQuery(query);
        statement.close();
    }

    @Override
    public ResultSet queryData(String query, List<Data> _data) {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            for (Data data : _data) {
                int index = _data.indexOf(data) + 1;
                if (data.getData() instanceof String)   ps.setString(index, (String) data.getData());
                if (data.getData() instanceof Boolean)  ps.setBoolean(index, (Boolean) data.getData());
                if (data.getData() instanceof Integer)  ps.setInt(index, (Integer) data.getData());
                if (data.getData() instanceof Double)   ps.setDouble(index, (Double) data.getData());
                if (data.getData() instanceof Float)    ps.setFloat(index, (Float) data.getData());
                if (data.getData() instanceof Date)     ps.setDate(index, (Date) data.getData());
            }
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
