import Models.LogModel;
import Models.newModel;
import Models.testModel;
import wonton.connections.PGConnection;
import wonton.service.Service;
import wonton.Wonton;

import java.sql.SQLException;

public class TestORM {
    public static void main(String[] args) {

        try {
            Wonton wonton = new Wonton(new PGConnection("admin_dhk", "admin_dhk", "code1234", "51.254.143.91"));
            Service logService = wonton.createService(new LogModel());
            Service testService = wonton.createService(new testModel());
            Service newService = wonton.createService(new newModel());
//            newService.create(new ArrayList<Data>(){{
//                add(new Data("int", 2));
//            }});
//            newService.delete(1);
            newService.find();
//            newService.get(4);
//            newService.update()
//
//            logService.create(new ArrayList<Data>() {{
//                add(new Data<>("log_type", "error"));
//                add(new Data<>("text", "new error"));
//            }});
//
//            testService.create(new ArrayList<Data>() {{
//                add(new Data<>("strings", "this is a string"));
//                add(new Data<>("int", 3));
//            }});
//            logService.find();
//            testService.find("id");
//            logService.get(4);
//            testService.delete(3);
//            testService.update(3, new ArrayList<Data>() {{
//                add(new Data<>("strings", "this is a string"));
//                add(new Data<>("int", 2));
//            }});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
