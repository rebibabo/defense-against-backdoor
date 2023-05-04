package abtric.qualification;
 
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.List;
 
 import abtric.utility.Writer;
 
 public class FractilesSimple {
    public static void main(String[] args) {
 
         String stringPath = "D-small-attempt0.in";
        
        try {
            final List<String> inputFile = Files
                    .readAllLines(FileSystems.getDefault().getPath("in\\Fractiles", stringPath));
            
            final int T = Integer.parseInt(inputFile.get(0));
            String[] results = new String[T];
            for (int i = 0; i < T; i++) {
                
 
                
 
                
                
                final int S = Integer.parseInt(inputFile.get(i + 1).split(" ")[2]);
                results[i] = "1";
                for (int j = 2; j <= S; j++) {
                    results[i] += " " + j;
                }
            }
 
             Writer.write("out\\Fractiles-small.out", results);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
