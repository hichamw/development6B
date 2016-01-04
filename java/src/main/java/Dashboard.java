import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

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
        spark.Spark.staticFileLocation("/public");



        //pages
        spark.Spark.get("/", (req, res) -> {
            Map<String, Object> page = new HashMap<>();
            page.put("title", "Dashboard");
            return new ModelAndView(page, "dashboard.ftl");
        }, new FreeMarkerEngine());

        spark.Spark.get("/dashboard", (req, res) -> {
            Map<String, Object> page = new HashMap<>();
            page.put("title", "Dashboard");
            return new ModelAndView(page, "dashboard.ftl");
        }, new FreeMarkerEngine());

        spark.Spark.get("/rapporten/overview", (request, response) -> {
            Map<String, Object> page = new HashMap<>();
            page.put("title", "Rapporten - overview");
            return new ModelAndView(page, "rapporten/overview.ftl");
        }, new FreeMarkerEngine());

        spark.Spark.get("/rapporten/create", (request, response) -> {
            Map<String, Object> page = new HashMap<>();
            page.put("title", "Rapporten - create");
            return new ModelAndView(page, "rapporten/create.ftl");
        }, new FreeMarkerEngine());

        //data
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
