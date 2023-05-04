
 package durazom.cruisecontrol;
 
 import durazom.fizzbuzz.*;
 import durazom.util.Handler;
 import java.io.IOException;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 
 public class CruiseControlProgram {
     
     public static void main(String[] args){
         
         try {
             String inputFile = "A-small-attempt0.in";
             String outputFile = "cruiseout.txt";
             Handler.run(inputFile, outputFile, new CruiseControlCase(), true);
         } catch (IOException ex) {
             Logger.getLogger(CruiseControlProgram.class.getName()).log(Level.SEVERE, null, ex);
         }
         
     }
     
 }
