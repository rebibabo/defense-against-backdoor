import java.io.File;
 import java.io.FileNotFoundException;
 
 
 
 
 
 
 
 
 
 public class PancakeTester {
 
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(args[0]);
        Pancakes p = new Pancakes(inputFile);
        p.evaluate();
    }
 
 }
