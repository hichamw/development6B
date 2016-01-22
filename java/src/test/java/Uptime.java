import java.math.BigDecimal;
import java.sql.*;
import java.text.*;
import java.util.*;


/**
 * Created by hicham on 8-12-2015.
 */
public class Uptime {
    private final static String url = "jdbc:mysql://localhost/";
    private final static String dbName = "CSV";
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String userName = "root";
    private final static String password = "root";
    static final int SECONDS_IN_DAY = 86400;


        //Get userid's from server, and put the vehicles it in a list
        ArrayList<BigDecimal> vehicleList = new ArrayList<>();
        Array vehicleList2;
        ArrayList<Long> DateList = new ArrayList<>();
        ArrayList<Long> IgnitionList = new ArrayList();
        double result = 0.0;
        int vehicleSelected = 14100042;
        int vehicleSelected2 = 14120026 ;
        ArrayList<AngularConnectionsResultObject> uptimeList = new ArrayList<>();

    public ArrayList<AngularConnectionsResultObject> getUptime(){
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT DISTINCT UnitId FROM EVENTS");
            while (res.next()) {
                BigDecimal vehicle = res.getBigDecimal("UnitID");
                vehicleList.add(vehicle);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        for(int x = 0; x < vehicleList.size(); x++){
            vehicleList.
        }*/

        // TODO User selects vehicle

        //Additional data is requested from server about the selected vehicle
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            BigDecimal[] vehicles = new BigDecimal[vehicleList.size()];
            vehicleList.toArray(vehicles);
            for(BigDecimal i:vehicles) {
                //System.out.println(i);

                PreparedStatement st = conn.prepareStatement("SELECT DateTime,Value FROM EVENTS where UnitId = '" + i + "'group by DateTime order by DateTime");
                ResultSet res = st.executeQuery();
                while (res.next()) {
                    DateList.add(res.getTimestamp("DateTime").getTime());
                    IgnitionList.add((long) res.getInt("Value"));
                    uptimeList.add(new AngularConnectionsResultObject(i, res.getInt("Value")));
                }

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
            for(BigDecimal i:vehicles){
                //System.out.println("Het kastje van het voertuig met id: " + i + " heeft in totaal " + df.format(percentageOfDay) + "% aangestaan");
            }
            //System.out.println(vehicleList);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return uptimeList;
    }
}
