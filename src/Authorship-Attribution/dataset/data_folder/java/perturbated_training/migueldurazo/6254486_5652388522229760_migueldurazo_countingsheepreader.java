package durazom.codejam.countingsheep;
 
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import durazom.codejam.storecredit.*;
 import durazom.codejam.util.Util;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 
 
 
 public class CountingSheepReader {
 
     public static List<CountingSheepCase> readCases(BufferedReader reader) throws IOException {
 
         List<CountingSheepCase> cases = new ArrayList<>();
 
         int numberOfCases = Util.readInteger(reader);
         for (int i = 0; i < numberOfCases; i++) {
 
             int initialNumber = Util.readInteger(reader);
             
             CountingSheepCase testCase = new CountingSheepCase();
             
             testCase.setInitial(initialNumber);
 
             cases.add(testCase);
 
         }
 
         reader.close();
         return cases;
 
     }
 
 }
