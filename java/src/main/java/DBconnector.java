/**
 * Created by mrtn on 18-11-15.
 */

import java.sql.*;

public class DBconnector {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/CSV";

    static final String USER = "root";
    static final String PASS = "root";

    //Get Statement werkt nog niet!!

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connect to database..");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Create statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT `UnitId` FROM `EVENTS` WHERE DateTime = \"2015-03-10 07:19:55\"";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int unitid = rs.getInt("UnitId");
                System.out.println(unitid);
            }

            rs.close();
            stmt.close();
            conn.close();

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
