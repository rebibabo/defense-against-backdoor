
 package durazom.bathroomstalls;
 
 import durazom.tidynumbers.*;
 import durazom.util.Handler;
 import java.io.IOException;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 
 public class BathroomStallsProgram {
     
         public static void main(String[] args){
         
             try {
                 String inputFile = "C-small-1-attempt0.in";
                 String outputFile = "bathOutput.txt";
                 Handler.run(inputFile, outputFile, new BathroomStallsCase());
             } catch (IOException ex) {
                 Logger.getLogger(BathroomStallsProgram.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         }
     
     
 }
