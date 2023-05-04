package durazom.codejam.lastword;
 
 
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
 
 
 
 public class LastWordHandler {
 
     public static void main(String[] args) {
 
         String input = "lastwordsmall.in";
         String output = "output.txt";
         
         LastWordHandler sc = new LastWordHandler();
         try {
             sc.solve(input, output);
         } catch (IOException ex) {
             Logger.getLogger(LastWordHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
 
     }
 
     public void solve(String input, String output) throws FileNotFoundException, IOException {
         
         List<LastWordCase> testCases = LastWordReader.readCases(
                 new BufferedReader(new FileReader(input)));
         
         testCases.stream().forEach(LastWordCase::solve);
         
         LastWordWriter.writeCases(testCases, 
                 new BufferedWriter(new FileWriter(output)));
 
         
     }
 
 }
