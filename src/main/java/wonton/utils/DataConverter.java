package wonton.utils;

import com.sun.org.apache.xpath.internal.SourceTree;
import wonton.Data;
import wonton.Definition;
import wonton.Model;
import wonton.Row;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataConverter {
    public static List<Row> covertResultSetToData(ResultSet rs, Model model) {
        ArrayList<Row> list = new ArrayList<>();

        try {
            while(rs.next()) {
                Row row = new Row(rs.getInt("id"));
                ResultSetMetaData md = rs.getMetaData();
                list.add(row);
                row.add(new Data("id", rs.getInt("id")));
                for(int i = 1; i < md.getColumnCount(); i++){
                    row.add(new Data(md.getColumnName(i), rs.getObject(i)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // We don't care if it does not exists the result set... Naaaaah fuck that shit..
        }
        return list;
    }
}
