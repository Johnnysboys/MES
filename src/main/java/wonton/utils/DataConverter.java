package wonton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataConverter {
    public static List<Data> covertResultSetToData(ResultSet rs, Model model) {
        ArrayList<Data> list = new ArrayList<>();
        try {
            while(rs.next()) {
                for (Definition definition : model.getDefinitions()) {
                    list.add(new Data(definition.getFieldName(), rs.getObject(definition.getFieldName())));
                }
            }
        } catch (SQLException e) {
            // We don't care if it does not exists the result set... Naaaaah fuck that shit..
        }
        return list;
    }
}
