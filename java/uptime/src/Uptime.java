import java.sql.*;
import java.text.*;
import java.util.*;


/**
 * Created by hicham on 8-12-2015.
 */
public class Uptime {
    private final static String url = "jdbc:mysql://localhost/";
    private final static String dbName = "csv";
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String userName = "root";
    private final static String password = "";
    static final int SECONDS_IN_DAY = 86400;

    public static void main(String[] args){
        //Get userid's from server, and put the vehicles it in a list
        HashSet<Long> vehicleList = new HashSet<>();
        ArrayList<Long> DateList = new ArrayList<>();
        ArrayList<Long> IgnitionList = new ArrayList();
        double result = 0.0;
        int vehicleSelected = 14100042;
        int vehicleSelected2 = 14120026 ;

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT DISTINCT UnitId FROM events");
            while (res.next()) {
                Long vehicle = res.getLong("UnitId");
                vehicleList.add(vehicle);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO User selects vehicle

        //Additional data is requested from server about the selected vehicle
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT DateTime,Value FROM events where UnitId = '" + vehicleSelected + "' group by DateTime order by DateTime");
            while (res.next()) {
                DateList.add(res.getTimestamp("DateTime").getTime());
                IgnitionList.add((long) res.getInt("Value"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        HashSet<String> UniqueDays = new HashSet<>();

        for (int i=0;i<DateList.size();i++){
            UniqueDays.add(sdf.format(DateList.get(i)));
        }

        for (int x = 0; x < DateList.size(); x++) {
            for (int y = 0; y < IgnitionList.size(); y++) {
                if (sdf.format(DateList.get(x)).equals(sdf.format(DateList.get(x + y))) && IgnitionList.get(x + y) == 0 ) {
                    result += (DateList.get(x + y) - DateList.get(x)) / 1000;
                }
            }
            break;
        }

        double percentageOfDay =  (result/(SECONDS_IN_DAY* UniqueDays.size()))*100;
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Het kastje van het voertuig met id: " + vehicleSelected2 + " heeft in totaal " + df.format(percentageOfDay) + "% aangestaan");
    }
}
