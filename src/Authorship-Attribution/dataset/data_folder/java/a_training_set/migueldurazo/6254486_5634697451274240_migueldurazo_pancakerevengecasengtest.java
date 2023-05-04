
 package durazom.codejam.pancakerevenge;
 
 import static org.testng.Assert.*;
 import org.testng.annotations.AfterClass;
 import org.testng.annotations.AfterMethod;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.BeforeMethod;
 import org.testng.annotations.Test;
 
 
 public class PancakeRevengeCaseNGTest {
 
     public PancakeRevengeCaseNGTest() {
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
         
         testCase("+-+-+--++-", 8);
         testCase("+++++", 0);
         testCase("", 0);
         testCase("----------", 1);
         testCase("-+", 1);
         testCase("+-", 2);
         testCase("+++", 0);
         testCase("--+-", 3);
 
     }
 
     public void testCase(String pancakes, long expected) {
 
         PancakeRevengeCase instance = new PancakeRevengeCase();
 
         instance.setPancakeTower(pancakes);
 
         if (instance.solve()) {
 
             long result = instance.getFlips();
 
             assertEquals(result, expected);
 
         }
 
     }
 
 }
