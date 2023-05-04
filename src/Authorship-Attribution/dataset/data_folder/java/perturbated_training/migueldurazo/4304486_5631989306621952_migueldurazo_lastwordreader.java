package durazom.codejam.lastword;
 
 import durazom.codejam.countingsheep.*;
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import durazom.codejam.storecredit.*;
 import durazom.codejam.util.Util;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 
 
 
 public class LastWordReader {
 
     public static List<LastWordCase> readCases(BufferedReader reader) throws IOException {
 
         List<LastWordCase> cases = new ArrayList<>();
 
         int numberOfCases = Util.readInteger(reader);
         for (int i = 0; i < numberOfCases; i++) {
 
             String word = reader.readLine();
             
             LastWordCase testCase = new LastWordCase();
             
             testCase.setInput(word);
             
 
             cases.add(testCase);
 
         }
 
         reader.close();
         return cases;
 
     }
 
 }
