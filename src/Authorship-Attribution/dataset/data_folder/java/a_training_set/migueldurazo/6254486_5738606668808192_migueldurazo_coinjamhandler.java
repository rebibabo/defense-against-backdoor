package durazom.codejam.coinjam;
 
 
 import durazom.codejam.pancakerevenge.*;
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
 
 
 
 public class CoinjamHandler {
 
     public static void main(String[] args) {
 
         String input = "coinjam small.txt";
         String output = "output.txt";
         
         CoinjamHandler sc = new CoinjamHandler();
         try {
             sc.solve(input, output);
         } catch (IOException ex) {
             Logger.getLogger(CoinjamHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
 
     }
 
     public void solve(String input, String output) throws FileNotFoundException, IOException {
         
         List<CoinjamCase> testCases = CoinjamReader.readCases(
                 new BufferedReader(new FileReader(input)));
         
         testCases.stream().forEach(CoinjamCase::solve);
         
         CoinjamWriter.writeCases(testCases, 
                 new BufferedWriter(new FileWriter(output)));
 
         
     }
 
 }
