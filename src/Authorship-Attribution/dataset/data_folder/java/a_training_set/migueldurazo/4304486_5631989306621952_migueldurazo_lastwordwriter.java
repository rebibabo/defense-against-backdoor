package durazom.codejam.lastword;
 
 import durazom.codejam.countingsheep.*;
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import durazom.codejam.storecredit.*;
 import java.io.BufferedWriter;
 import java.io.IOException;
 import java.util.List;
 import java.util.stream.Collectors;
 
 
 
 public class LastWordWriter {
 
     public static void writeCases(List<LastWordCase> cases, BufferedWriter writer) throws IOException {
 
         int counter = 1;
         for (LastWordCase testCase : cases) {
 
             if (testCase.isSolved()) {
 
                 writer.write("Case #" + counter + ": "+
                         testCase.getLastWord()+"\n");
                 
                 
                 
             }
 
             counter++;
         }
         writer.close();
 
     }
 
 }
