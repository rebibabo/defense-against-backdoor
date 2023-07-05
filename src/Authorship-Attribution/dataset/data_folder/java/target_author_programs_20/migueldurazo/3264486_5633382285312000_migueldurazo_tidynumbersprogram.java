
 package durazom.tidynumbers;
 
 import durazom.util.Handler;
 import java.io.IOException;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 
 public class TidyNumbersProgram {
     
         public static void main(String[] args){
         
             try {
                 String inputFile = "B-small-attempt0.in";
                 String outputFile = "tidyOutput.txt";
                 Handler.run(inputFile, outputFile, new TidyNumbersCase());
             } catch (IOException ex) {
                 Logger.getLogger(TidyNumbersProgram.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         }
     
     
 }
