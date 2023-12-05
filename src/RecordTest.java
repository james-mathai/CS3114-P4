import student.TestCase;

/**
 * Test class for Record class.
 * 
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class RecordTest extends TestCase {
    private Handle handle;
    private Record record;
    
    /**
     * Set up method for RecordTest class.
     */
    public void setUp() {
        handle = new Handle("test");
        record = new Record(handle, 5);
    }
    
    /**
     * Tests Record class methods.
     */
    public void testRecord() {
        assertEquals(record.getHandle(), handle);
        assertEquals(record.getVertex(), 5);
        
        Handle temp = new Handle("new");
        record.setHandle(temp);
        record.setVertex(10);
        
        assertEquals(record.getHandle(), temp);
        assertEquals(record.getVertex(), 10);
    }
}
