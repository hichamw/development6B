import static spark.Spark.*;
import java.sql.*;

public class Sparktest {

    static final String DB_URL = "jdbc:mysql://localhost/CSV";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {

        Connection conn;
        Statement stmt;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            get("/", (req, res) -> "Connecting to MySQL database");

            stmt = conn.createStatement();
            String query = "SELECT UnitID FROM CONNECTIONS";
            stmt.execute(query);
            ResultSet resultSet = stmt.getResultSet();

            while(resultSet.next()){
                resultSet.getBigDecimal("UnitID");
                get("/unitids", (req, res) -> resultSet.getBigDecimal("UnitID"));
            }


        } catch(Exception e) {
            System.out.println(e.getMessage());

            /*
            try {
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