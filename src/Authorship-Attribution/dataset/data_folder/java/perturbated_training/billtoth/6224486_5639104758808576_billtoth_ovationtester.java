import java.io.File;
 import java.io.FileNotFoundException;
 
 
 
 
 
 
 
 
 
 public class OvationTester {
 
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(args[0]);
        StandingOvation s = new StandingOvation(inputFile);
        s.evaluate();
    }
 
 }
