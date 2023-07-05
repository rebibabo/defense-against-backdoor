package durazom.codejam.coinjam;
 
 import durazom.codejam.pancakerevenge.*;
 import durazom.codejam.countingsheep.*;
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import durazom.codejam.storecredit.*;
 import java.io.BufferedWriter;
 import java.io.IOException;
 import java.util.List;
 import java.util.stream.Collectors;
 
 
 
 public class CoinjamWriter {
 
     public static void writeCases(List<CoinjamCase> cases, BufferedWriter writer) throws IOException {
 
         int counter = 1;
         for (CoinjamCase testCase : cases) {
 
             if (testCase.isSolved()) {
 
                 writer.write("Case #" + counter + ": "+
                         "\n");
                 
                 List<Coinjam> coins = testCase.getCoinjamsFound();
                 
                 for( Coinjam coin : coins ){
                     writer.write(coin+"\n");
                 }
                             
             }
 
             counter++;
         }
         writer.close();
 
     }
 
 }
