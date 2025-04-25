import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {


    @Test
    public void test1(){
        String expectedData = "Java";
        String actualData = "Java";

        assertEquals(expectedData, actualData);
        System.out.println("Test 1 passed");
    }

    @Test
    public void test2(){
        assertTrue("Java".contains("a")); // PASS
        assertFalse("DataBase".contains("J")); // PASS
        System.out.println("Test 2 passed!");

        assertTrue("JDBC".equals("jdbc")); // FAILED
        System.out.println("Test 2 passed!");

        // When assertion fails at any point in the test method, it doesn't execute the remaining codes
        // This is called HARD ASSERTION.

    }

    @Test
    public void test3(){
        // Java Assertion => also works like HARD ASSERTION

        assert("DataBase".contains("D"));
        assert("Java".contains("D"));
        System.out.println("Test 3 passed!");
    }


    @Test
    public void test4(){

        assertNotEquals("Selenium", "Java");
        System.out.println("Test 4 passed!");

        String expectedData = "Java";
        String actualData = "Java";

        assertNotEquals(expectedData, actualData, "Both values are equal!");


    }
}
