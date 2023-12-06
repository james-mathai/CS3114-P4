import student.TestCase;

/**
 * Test class for Handle class.
 * 
 * @author Vansh Parikh (vparikh33)
 * @author James Mathai (jmathai)
 * 
 * @version December 2023
 */
public class HandleTest extends TestCase {
    private Handle handle;
    
    /**
     * Set up method for HandleTest class.
     */
    public void setUp() {
        handle = new Handle("test");
    }
    
    /**
     * Tests Handle class methods.
     */
    public void testHandle() {
        assertEquals(handle.getName(), "test");
        assertEquals(handle.getLength(), 4);
    }
}
