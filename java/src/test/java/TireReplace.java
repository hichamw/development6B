import static spark.Spark.*;
import spark.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*Get car speeds for tire replacement*/

public class TireReplace {

    static final String DB_URL = "jdbc:mysql://localhost/CSV";
    static final String USER = "root";
    static final String PASS = "root";

    ArrayList<BigDecimal> unitids = new ArrayList<BigDecimal>();
    ArrayList<Integer> speeds = new ArrayList<Integer>();
    ArrayList<AngularConnectionsResultObject> speedList = new ArrayList<AngularConnectionsResultObject>();
    Connection conn;
    Statement stmt;

    public ArrayList<AngularConnectionsResultObject> getSpeeds() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT UnitID, COUNT(*) FROM POSITIONS WHERE Speed >130 GROUP BY UnitId ORDER BY COUNT(*)";
            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                unitids.add(resultSet.getBigDecimal("UnitID"));
                speeds.add(resultSet.getInt("COUNT(*)"));
                speedList.add(new AngularConnectionsResultObject(resultSet.getBigDecimal("UnitID"), resultSet.getInt("COUNT(*)")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return speedList;
    }


}
