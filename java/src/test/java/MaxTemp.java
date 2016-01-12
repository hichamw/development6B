import static spark.Spark.*;
import spark.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaxTemp {

    static final String DB_URL = "jdbc:mysql://localhost/CSV";
    static final String USER = "root";
    static final String PASS = "root";

    ArrayList<BigDecimal> unitids = new ArrayList<BigDecimal>();
    ArrayList<Integer> temps = new ArrayList<Integer>();
    ArrayList<AngularConnectionsResultObject> tempList = new ArrayList<AngularConnectionsResultObject>();
    Connection conn;
    Statement stmt;

    public ArrayList<AngularConnectionsResultObject> getTemp() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String query = "SELECT UnitId, AVG(Max) FROM MONITORING WHERE TYPE = 'Hardware/ProcessorTemperature' GROUP BY UnitId";
            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();

            while (resultSet.next()) {
                unitids.add(resultSet.getBigDecimal("UnitID"));
                temps.add(resultSet.getInt("AVG(MAX)"));
                tempList.add(new AngularConnectionsResultObject(resultSet.getBigDecimal("UnitID"), resultSet.getInt("AVG(MAX)")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return tempList;
    }
}
