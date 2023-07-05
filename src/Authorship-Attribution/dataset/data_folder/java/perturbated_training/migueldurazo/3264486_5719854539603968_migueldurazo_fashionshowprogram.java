
 package durazom.fashionshow;
 
 import durazom.bathroomstalls.*;
 import durazom.tidynumbers.*;
 import durazom.util.Handler;
 import java.io.IOException;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 
 public class FashionShowProgram {
     
         public static void main(String[] args){
         
             try {
                 String inputFile = "D-small-attempt0.in";
                 String outputFile = "fashionOutput.txt";
                 Handler.run(inputFile, outputFile, new FashionShowCase(),true);
             } catch (IOException ex) {
                 Logger.getLogger(FashionShowProgram.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         }
     
     
 }
