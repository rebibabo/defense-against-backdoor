package durazom.codejam.coinjam;
 
 import durazom.codejam.pancakerevenge.*;
 import durazom.codejam.countingsheep.*;
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import durazom.codejam.storecredit.*;
 import durazom.codejam.util.Util;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 
 
 
 public class CoinjamReader {
 
     public static List<CoinjamCase> readCases(BufferedReader reader) throws IOException {
 
         List<CoinjamCase> cases = new ArrayList<>();
 
         int numberOfCases = Util.readInteger(reader);
         for (int i = 0; i < numberOfCases; i++) {
 
             List<Integer> input = Util.readIntegerList(reader, " ");
             
             int length = input.get(0);
             int coinsNeeded = input.get(1);
             
             
             CoinjamCase testCase = new CoinjamCase();
             
             testCase.setLength(length);
             testCase.setCoinjamsNeeded(coinsNeeded);
 
             cases.add(testCase);
 
         }
 
         reader.close();
         return cases;
 
     }
 
 }
