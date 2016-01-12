import static spark.Spark.*;
import spark.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sparktest {

    static final String DB_URL = "jdbc:mysql://localhost/CSV";
    static final String USER = "root";
    static final String PASS = "root";


    public static void main(String[] args) {

        staticFileLocation("/www");

        CarIgnition carignit = new CarIgnition();
        TireReplace tirereplace = new TireReplace();
        MaxTemp maxtemp = new MaxTemp();
        ArrayList<BigDecimal> ids = new ArrayList<BigDecimal>();
        ArrayList<Integer> total = new ArrayList<Integer>();
        ArrayList<AngularConnectionsResultObject> connectionsList = new ArrayList<AngularConnectionsResultObject>();

        Statement stmt;
        Connection conn;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String query = "SELECT UnitID, COUNT(*) FROM CONNECTIONS WHERE Value = 1 GROUP BY UnitID";
            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();

            while(resultSet.next()){
                ids.add(resultSet.getBigDecimal("UnitID"));
                total.add(resultSet.getInt("COUNT(*)"));
                connectionsList.add(new AngularConnectionsResultObject(resultSet.getBigDecimal("UnitID"),resultSet.getInt("COUNT(*)")));
                //System.out.println(ids);
            }

            get("/getIds","application/json", (request, response) -> {
                AngularResultObject angularResultObject = new AngularResultObject();
                angularResultObject.setResultObject(ids);
                return angularResultObject;
            },new GsonTransformer());

            get("/getTotal","application/json", (request, response) -> {
                AngularResultObject angularResultObject = new AngularResultObject();
                angularResultObject.setResultObject(connectionsList);
                return angularResultObject;
            },new GsonTransformer());

            get("/getIgnitions","application/json", (request, response) -> {
                AngularResultObject angularResultObject = new AngularResultObject();
                angularResultObject.setResultObject(carignit.getCars());
                return angularResultObject;
            },new GsonTransformer());

            get("/getSpeeds","application/json", (request, response) -> {
                AngularResultObject angularResultObject = new AngularResultObject();
                angularResultObject.setResultObject(tirereplace.getSpeeds());
                return angularResultObject;
            },new GsonTransformer());

            get("/getTemps","application/json", (request, response) -> {
                AngularResultObject angularResultObject = new AngularResultObject();
                angularResultObject.setResultObject(maxtemp.getTemp());
                return angularResultObject;
            },new GsonTransformer());


        } catch(Exception e) {
            System.out.println(e.getMessage());
            /*try {
                if(null != stmt) {
                    stmt.close();
                }
                if(null != conn) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }*/
        }
    }
}