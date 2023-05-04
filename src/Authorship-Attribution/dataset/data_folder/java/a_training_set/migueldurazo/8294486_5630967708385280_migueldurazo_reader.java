
 package durazom.util;
 
 import java.io.BufferedReader;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class Reader {
 
     private static int lineCount = 0;
     
     public static void reset() throws FileNotFoundException, IOException {
         lineCount = 0;
     }
     
     public static List<String> readFile(String inputFile, int linesToRead) throws FileNotFoundException, IOException {
 
         List<String> cases = new ArrayList<String>();
 
         if( linesToRead == 0 ) return cases;
         
         if (inputFile != null) {
 
             BufferedReader reader = new BufferedReader(new FileReader(inputFile));
 
             String line = "";
 
             
             String lines = "";
             
             int linesRead = 0;
             if( lineCount > 0 ){
                 while ((linesRead++) < lineCount && (line = reader.readLine()) != null  ){
                     int x = 2;
                 }
             }
             
             linesRead = 0;
             while ((line = reader.readLine()) != null && linesRead< linesToRead  ) {
                 lineCount++;
                 linesRead++;
                 lines += lines.length()>0?"\n"+line:line;
                 if( linesRead%linesToRead == 0 ){
                     cases.add(lines);
                     lines = "";
                 }
             }
 
             reader.close();
             
         }
 
         return cases;
 
     }
 
 }
