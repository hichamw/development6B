import java.lang.reflect.Array;

/**
 * Created by hicham on 24-11-2015.
 */
public class mainConverter {
    public static void main(String [] args)  {
        convert(158112.767266254, 380429.127648613);
        DatabaseConnection.dbConnection();
    }

    public static void convert(double x, double y){
        long  startTime = System.currentTimeMillis();
        int rd_x_base = 155000;
        int rd_y_base = 463000;
        double wgs84_latitude_base = 52.15517440;
        double wgs84_longitude_base = 5.38720621;

        double calc_lat = 0;
        double calc_long = 0;

        double[][] Kpq = new double[6][5];
        double[][] Lpq = new double[6][5];
        Kpq[0][1]                    =     3235.65389;
        Kpq[2][0]                    =      -32.58297;
        Kpq[0][2]                    =       -0.24750;
        Kpq[2][1]                    =       -0.84978;
        Kpq[0][3]                    =       -0.06550;
        Kpq[2][2]                    =       -0.01709;
        Kpq[1][0]                    =       -0.00738;
        Kpq[4][0]                    =        0.00530;
        Kpq[2][3]                    =       -0.00039;
        Kpq[4][1]                    =        0.00033;
        Kpq[1][1]                    =       -0.00012;
        Lpq[1][0]                    =     5260.52916;
        Lpq[1][1]                    =      105.94684;
        Lpq[1][2]                    =        2.45656;
        Lpq[3][0]                    =       -0.81885;
        Lpq[1][3]                    =        0.05594;
        Lpq[3][1]                    =       -0.05607;
        Lpq[0][1]                    =        0.01199;
        Lpq[3][2]                    =       -0.00256;
        Lpq[1][4]                    =        0.00128;
        Lpq[0][2]                    =        0.00022;
        Lpq[2][0]                    =       -0.00022;
        Lpq[5][0]                    =        0.00026;
        double d_latitude                  = (x - rd_x_base) * 0.00001;         // dX = (X - X0) 10^5
        double d_longitude                  = (y - rd_y_base) * 0.00001;

        int pmax = 5;
        int qmax = 4;
        for(int p = 0; p <= pmax; p++)
        {
            for(int q = 0; q <= qmax; q++)
            {
                if(Kpq[p][q] != 0) {
                    calc_lat += Kpq[p][q] * Math.pow(d_latitude, p) * Math.pow(d_longitude, q);
                }
                if(Lpq[p][q] != 0) {
                    calc_long += Lpq[p][q] * Math.pow(d_latitude, p) * Math.pow(d_longitude, q);
                }
            }

        }
        double wgs84_latitude  = wgs84_latitude_base + (calc_lat / 3600);
        double wgs84_longitude  = wgs84_longitude_base + (calc_long / 3600);

        System.out.println("lat: " + wgs84_latitude + " long: " + wgs84_longitude);
        long endTime = System.currentTimeMillis();
        System.out.println("It took: " + (endTime - startTime) + " milliseconds");
    }
}
