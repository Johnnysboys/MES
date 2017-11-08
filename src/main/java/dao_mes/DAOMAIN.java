package dao_mes;

import dto_mes.OrderDTO;
import models.ordersModel;
import wonton.Data;
import wonton.Row;
import wonton.Wonton;
import wonton.connections.PGConnection;
import wonton.service.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DAOMAIN {
    public static void main(String[] args) throws SQLException {
        Random random = new Random();
        int low=5;
        int high=20;

        Wonton wonton;
        wonton = new Wonton(new PGConnection("admin_dhk", "admin_dhk", "code1234", "51.254.143.91"));
        Service caller = wonton.createService(new ordersModel());

        List<Row> list2= caller.find();
        for (Row r:list2){
            caller.update(
                    (Integer)r.get("id").getData(),
                    new ArrayList<Data>(){{
                add(new Data("quantity",(float) random.nextInt(high-low)+low));
            }
            });
        }
    }
}
