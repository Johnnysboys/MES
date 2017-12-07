import models.LogModel;
import models.newModel;
import models.ordersModel;
import models.testModel;
import wonton.Data;
import wonton.Parameter;
import wonton.Row;
import wonton.connections.PGConnection;
import wonton.exceptions.DoesNotExistsInModelException;
import wonton.service.Service;
import wonton.Wonton;
import wonton.types.Operators;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestORM {
    public static void main(String[] args) {

        try {
            Wonton wonton = new Wonton(new PGConnection("admin_dhk", "admin_dhk", "code1234", "51.254.143.91"));
            Service logService = wonton.createService(new LogModel());
            Service testService = wonton.createService(new testModel());
            Service newService = wonton.createService(new newModel());
            Service ordersService = wonton.createService(new ordersModel());
//            newService.create(new ArrayList<Data>(){{
//                add(new Data("int", 2));
//            }});
//            List<Row> list = ordersService.find("id");
//            for(Row row : list){
//                for(Data data : row.getData()){
//                    System.out.println(data.getField());
//                }
//            }
//            for(Row row : logService.find(new Parameter("id", Operators.EQ, 12))){
//                for(Data data : row.getData()){
//                    System.out.println(data.toString());
//                }
//            };
//            newService.delete(1);
//            newService.find();
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
//            testService.update(1, new ArrayList<Data>() {{
//                add(new Data<>("strings", "thai mad"));
//            }});
            Parameter[] parameters = {
                    new Parameter("id", Operators.EQ, 1),
                    new Parameter("order", Operators.EQ, 12)
            };

            testService.update(new ArrayList<Data>(){{
                add(new Data<>("strings", "thai mad"));
            }}, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
