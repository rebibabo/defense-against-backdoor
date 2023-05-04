
 package durazom.util;
 
 import java.io.BufferedReader;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class Reader {
 
     public static List<String> readFile(String inputFile, int linesPerCase) throws FileNotFoundException, IOException {
 
         List<String> cases = new ArrayList<String>();
 
         if (inputFile != null) {
 
             BufferedReader reader = new BufferedReader(new FileReader(inputFile));
 
             String line = "";
 
             int lineCount = 0;
             String lines = "";
             while ((line = reader.readLine()) != null) {
                 lineCount++;
                 lines += lines.length()>0?"\n"+line:line;
                 if( lineCount%linesPerCase == 0 ){
                     cases.add(lines);
                     lines = "";
                 }
             }
 
             reader.close();
             
         }
 
         return cases;
 
     }
 
 }
