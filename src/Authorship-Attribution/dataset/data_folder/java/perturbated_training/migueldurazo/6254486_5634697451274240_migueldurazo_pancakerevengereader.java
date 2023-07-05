package durazom.codejam.pancakerevenge;
 
 import durazom.codejam.countingsheep.*;
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import durazom.codejam.storecredit.*;
 import durazom.codejam.util.Util;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 
 
 
 public class PancakeRevengeReader {
 
     public static List<PancakeRevengeCase> readCases(BufferedReader reader) throws IOException {
 
         List<PancakeRevengeCase> cases = new ArrayList<>();
 
         int numberOfCases = Util.readInteger(reader);
         for (int i = 0; i < numberOfCases; i++) {
 
             String tower = reader.readLine();
             
             PancakeRevengeCase testCase = new PancakeRevengeCase();
             
             testCase.setPancakeTower(tower);
 
             cases.add(testCase);
 
         }
 
         reader.close();
         return cases;
 
     }
 
 }
