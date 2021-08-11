package sportsfacility;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Haseong Kim
 */
public class AdvisorControllerIT {
    
    public AdvisorControllerIT() {
    }
    /**
     * Test of updateCustomerInfo method, of class AdvisorController.
     * @param custid
     * @param name
     * @param mbship
     */
    Boolean testUpdateCustomerInfo(int custid, String name, int mbship) {
        System.out.println("updateCustomerInfo");
        AdvisorController instance = new AdvisorController(custid, false);
        boolean result = instance.updateCustomerInfo(custid, name, mbship);
        return result;
    }
    
    @Test
    public void testMain() {
        assertEquals(testUpdateCustomerInfo(1, "John Smith", 0), true);
        assertEquals(testUpdateCustomerInfo(3, "Paul Brown", 2), true);
        assertEquals(testUpdateCustomerInfo(5, "Katie Wilson", 1), true);
        assertEquals(testUpdateCustomerInfo(0, "", 0), false);
        assertEquals(testUpdateCustomerInfo(1, "", 1), false);
    }
}
