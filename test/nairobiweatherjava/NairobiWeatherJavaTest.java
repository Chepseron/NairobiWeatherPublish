/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nairobiweatherjava;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anonymous
 */
public class NairobiWeatherJavaTest {
    
    public NairobiWeatherJavaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class NairobiWeatherJava.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        NairobiWeatherJava.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class NairobiWeatherJava.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        NairobiWeatherJava instance = new NairobiWeatherJava();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CheckWeather method, of class NairobiWeatherJava.
     */
    @Test
    public void testCheckWeather() {
        System.out.println("CheckWeather");
        NairobiWeatherJava instance = new NairobiWeatherJava();
        String expResult = "";
        String result = instance.CheckWeather();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
