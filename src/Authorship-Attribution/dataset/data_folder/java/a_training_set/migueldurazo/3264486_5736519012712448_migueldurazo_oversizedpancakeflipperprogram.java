
 package durazom.oversizedpancakeflipper;
 
 import durazom.primepalindrome.*;
 import durazom.fizzbuzz.FizzBuzzCase;
 import durazom.fizzbuzz.FizzBuzzProgram;
 import durazom.util.Handler;
 import java.io.IOException;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 
 public class OversizedPancakeFlipperProgram {
     
         public static void main(String[] args){
         
             try {
                 String inputFile = "A-small-attempt0.in";
                 String outputFile = "oversizedOuput";
                 Handler.run(inputFile, outputFile, new OversizedPancakeFlipperCase());
             } catch (IOException ex) {
                 Logger.getLogger(FizzBuzzProgram.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         }
     
     
 }
