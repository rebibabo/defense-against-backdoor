
 package durazom.codejam.countingsheep;
 
 import java.util.Set;
 import static org.testng.Assert.*;
 import org.testng.annotations.AfterClass;
 import org.testng.annotations.AfterMethod;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.BeforeMethod;
 import org.testng.annotations.Test;
 
 
 public class CountingSheepCaseNGTest {
 
     public CountingSheepCaseNGTest() {
     }
 
     @BeforeClass
     public static void setUpClass() throws Exception {
     }
 
     @AfterClass
     public static void tearDownClass() throws Exception {
     }
 
     @BeforeMethod
     public void setUpMethod() throws Exception {
     }
 
     @AfterMethod
     public void tearDownMethod() throws Exception {
     }
 
     
     @Test
     public void testSolve() {
         System.out.println("solve");
         
         testCase(0, 0, true);
         testCase(1, 10, false);
         testCase(2, 90, false);
         testCase(11, 110, false);
         testCase(1692, 5076, false);
         testCase(1000000, 9000000, false);
         
         
     }
 
     public void testCase(long initial, long expected, boolean insomnia) {
 
         CountingSheepCase instance = new CountingSheepCase();
 
         instance.setInitial(initial);
 
         if (instance.solve()) {
 
             if (instance.isInsomnia()) {
 
                 assertEquals(true, insomnia);
 
             } else {
 
                 long result = instance.getFallsSleep();
 
                 assertEquals(result, expected);
 
             }
 
         }
 
     }
 
 }
