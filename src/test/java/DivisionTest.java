import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 22-4-2016.
 */
public class DivisionTest {

    @Test
    public void testCalc() throws Exception {
        int value1 = 10;
        int value2 = 2;
        assertEquals(5, Division.calc(value1,value2));
    }
}