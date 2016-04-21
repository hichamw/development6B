import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 21-4-2016.
 */
public class AdditionTest {

    @Test
    public void testCalc() throws Exception {
        int value1 = 1;
        int value2 = 2;
        assertEquals(3, Addition.calc(value1,value2));
    }
}