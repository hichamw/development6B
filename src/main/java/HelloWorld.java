/**
 * Created by Administrator on 17-5-2016.
 */
import org.apache.log4j.BasicConfigurator;

import static spark.Spark.get;

public class HelloWorld {
    public static void main(String[] args){
        BasicConfigurator.configure();
        get("/", (req, res) -> "Hello World!");
    }
}
