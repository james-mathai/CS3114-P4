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
        record = new Record("test", 5);
    }


    /**
     * Tests Record class methods.
     */
    public void testRecord() {
        assertEquals("test", record.key());
        assertEquals(5, record.vertex());

        record.setKey("new");
        record.setVertex(10);

        assertEquals("new", record.key());
        assertEquals(10, record.vertex());
    }
}
