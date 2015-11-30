import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by hicham on 25-11-2015.
 */
public class DatabaseConnection {
    public static void dbConnection() {
        String url = "jdbc:mysql://localhost/";
        String dbName = "gpstest";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM positions");
            while (res.next()) {
                long rdx = res.getLong("rdx");
                long rdy = res.getLong("rdy");
                System.out.println(rdx + "\t" + rdy);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
