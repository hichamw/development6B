import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

import static spark.Spark.*;

// added dashboard class
// changed port to 9090
// added public folder

public class Dashboard {

    static final String DB_URL = "jdbc:mysql://localhost/CSV";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //spark.Spark.port(8080);
        spark.Spark.staticFileLocation("/www");

        spark.Spark.get("/dashboard", (request, response) -> {
            return "index";
        });

        spark.Spark.get("/dashboard", (request, response) -> {
            //return new ModelAndView(ids, )
            return "index";
        });


        spark.Spark.get("/getIds", "application/json", (request, response) -> {
            AngularResultObject angularResultObject = new AngularResultObject();
            angularResultObject.setResultObject(getIds("SELECT UnitID FROM CONNECTIONS"));
            return angularResultObject;
        }, new GsonTransformer());

    }

    private static ArrayList<BigDecimal> getIds(String query) {
        ArrayList<BigDecimal> ids = new ArrayList<BigDecimal>();
        Connection conn;
        Statement stmt;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                ids.add(resultSet.getBigDecimal("UnitID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }
}
