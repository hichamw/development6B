import static spark.Spark.*;
import spark.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarIgnition {

    static final String DB_URL = "jdbc:mysql://localhost/CSV";
    static final String USER = "root";
    static final String PASS = "root";


    ArrayList<BigDecimal> unitids = new ArrayList<BigDecimal>();
    ArrayList<Integer> ignitions = new ArrayList<Integer>();
    ArrayList<AngularConnectionsResultObject> connectionsList2 = new ArrayList<AngularConnectionsResultObject>();
    Connection conn;
    Statement stmt;
    

    public ArrayList<AngularConnectionsResultObject> getCars() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT UnitId , COUNT(*) FROM `EVENTS` WHERE Port = 'Ignition' AND Value =1 GROUP BY UnitId";
            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                unitids.add(resultSet.getBigDecimal("UnitID"));
                ignitions.add(resultSet.getInt("COUNT(*)"));
                connectionsList2.add(new AngularConnectionsResultObject(resultSet.getBigDecimal("UnitID"), resultSet.getInt("COUNT(*)")));
                //System.out.println(ids);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return connectionsList2;
    }

}
