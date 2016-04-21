import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 21-4-2016.
 */
public class SubtractionTest {

    @Test
    public void testCalc() throws Exception {
        int value1 = 5;
        int value2 = 2;
        assertEquals(3, Subtraction.calc(value1,value2));
    }
}