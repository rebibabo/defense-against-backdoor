
 package durazom.util;
 
 import java.io.IOException;
 import java.util.AbstractList;
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class Handler {
 
     public static void run(String inputFile, String outputFile, Case thisCase)
             throws IOException {
 
         run(inputFile, outputFile, thisCase, 1);
 
     }
 
     public static void run(String inputFile, String outputFile, Case thisCase,
             int linesPerCase) throws IOException {
 
         List<String> cases = Reader.readFile(inputFile, linesPerCase);
         List<String> outputLines = new ArrayList<>();
 
         if (cases.size() > 0) {
             cases.stream().map((c) -> thisCase.solve(c)).forEach((solved) -> {
                 outputLines.add(solved);
             });
         } else {
             outputLines.add(thisCase.solve(""));
         }
 
         Writer.write(outputFile, outputLines);
     }
 
     public static void run(String inputFile, String outputFile, Case thisCase,
             boolean determineCasesAutomatically) throws IOException {
 
         if (determineCasesAutomatically) {
 
             List<String> cases = new ArrayList<>();
             List<String> outputLines = new ArrayList<>();
             do {
 
                 cases = Reader.readFile(inputFile, 1);
                 
                 if( cases.size() == 0 ){ break; }
 
                 Integer followingLines = Integer.parseInt((cases.get(0).split(" "))[1]);
 
                 cases.addAll(Reader.readFile(inputFile, followingLines));
 
                 String multipleLineCase = "";
 
                 multipleLineCase = cases.stream().map((a) -> a + "\n").reduce(multipleLineCase, String::concat);
 
                 outputLines.add(thisCase.solve(multipleLineCase));
                 
 
             } while( cases.size() > 0);
             
             Writer.write(outputFile, outputLines);
 
         } else {
 
             run(inputFile, outputFile, thisCase, 1);
 
         }
 
         Reader.readFile(inputFile, 1);
 
     }
 
 }
