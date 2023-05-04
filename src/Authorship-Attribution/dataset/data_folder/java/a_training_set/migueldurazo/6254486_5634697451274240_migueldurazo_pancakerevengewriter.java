package durazom.codejam.pancakerevenge;
 
 import durazom.codejam.countingsheep.*;
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import durazom.codejam.storecredit.*;
 import java.io.BufferedWriter;
 import java.io.IOException;
 import java.util.List;
 import java.util.stream.Collectors;
 
 
 
 public class PancakeRevengeWriter {
 
     public static void writeCases(List<PancakeRevengeCase> cases, BufferedWriter writer) throws IOException {
 
         int counter = 1;
         for (PancakeRevengeCase testCase : cases) {
 
             if (testCase.isSolved()) {
 
                 writer.write("Case #" + counter + ": "+
                         testCase.getFlips()+"\n");
                             
             }
 
             counter++;
         }
         writer.close();
 
     }
 
 }
