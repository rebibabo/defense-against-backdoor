package durazom.codejam.countingsheep;
 
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import durazom.codejam.storecredit.*;
 import java.io.BufferedWriter;
 import java.io.IOException;
 import java.util.List;
 import java.util.stream.Collectors;
 
 
 
 public class CountingSheepWriter {
 
     public static void writeCases(List<CountingSheepCase> cases, BufferedWriter writer) throws IOException {
 
         int counter = 1;
         for (CountingSheepCase testCase : cases) {
 
             if (testCase.isSolved()) {
 
                 if( testCase.isInsomnia() ){
                 
                     writer.write("Case #" + counter + ": "+
                         "INSOMNIA\n");
                     
                 }else{
                 
                 writer.write("Case #" + counter + ": "+
                         testCase.getFallsSleep()+"\n");
                 }
                 
             }
 
             counter++;
         }
         writer.close();
 
     }
 
 }
