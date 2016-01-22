import static spark.Spark.*;
import spark.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Sparktest {

    static final String DB_URL = "jdbc:mysql://localhost/CSV";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {

        staticFileLocation("/www");

        ArrayList<BigDecimal> ids = new ArrayList<BigDecimal>();
        Connection conn;
        Statement stmt;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String query = "SELECT UnitID FROM CONNECTIONS";
            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();
            //testcomment
            //testcomment2
            while(resultSet.next()){
                ids.add(resultSet.getBigDecimal("UnitID"));
            }

            get("/getIds","application/json", (request, response) -> {
                AngularResultObject angularResultObject = new AngularResultObject();
                angularResultObject.setResultObject(ids);
                return angularResultObject;
            },new GsonTransformer());

            get("/test", (request, response) -> {
                //return new ModelAndView(ids, )
                return "<html><body>Hoi</body></html>";
            });

            //get("/", (req, res) -> ids);

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