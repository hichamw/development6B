import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by mrtn on 10-12-15.
 */
public class GsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }


}
