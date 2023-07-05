package durazom.codejam.pancakerevenge;
 
 
 import durazom.codejam.countingsheep.*;
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
 
 
 
 public class PancakeRevengeHandler {
 
     public static void main(String[] args) {
 
         String input = "revengesmall.in";
         String output = "output.txt";
         
         PancakeRevengeHandler sc = new PancakeRevengeHandler();
         try {
             sc.solve(input, output);
         } catch (IOException ex) {
             Logger.getLogger(PancakeRevengeHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
 
     }
 
     public void solve(String input, String output) throws FileNotFoundException, IOException {
         
         List<PancakeRevengeCase> testCases = PancakeRevengeReader.readCases(
                 new BufferedReader(new FileReader(input)));
         
         testCases.stream().forEach(PancakeRevengeCase::solve);
         
         PancakeRevengeWriter.writeCases(testCases, 
                 new BufferedWriter(new FileWriter(output)));
 
         
     }
 
 }
