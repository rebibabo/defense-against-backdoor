package durazom.codejam.countingsheep;
 
 
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.List;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 
 
 public class CountingSheepHandler {
 
     public static void main(String[] args) {
 
         String input = "countingSheepSmall.in";
         String output = "output.txt";
         
         CountingSheepHandler sc = new CountingSheepHandler();
         try {
             sc.solve(input, output);
         } catch (IOException ex) {
             Logger.getLogger(CountingSheepHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
 
     }
 
     public void solve(String input, String output) throws FileNotFoundException, IOException {
         
         List<CountingSheepCase> testCases = CountingSheepReader.readCases(
                 new BufferedReader(new FileReader(input)));
         
         testCases.stream().forEach(CountingSheepCase::solve);
         
         CountingSheepWriter.writeCases(testCases, 
                 new BufferedWriter(new FileWriter(output)));
 
         
     }
 
 }
