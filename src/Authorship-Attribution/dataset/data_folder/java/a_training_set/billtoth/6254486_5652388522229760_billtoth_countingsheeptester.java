import java.io.File;
 import java.io.FileNotFoundException;
 
 
 
 
 
 
 
 
 
 public class CountingSheepTester {
 
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(args[0]);
        CountingSheep t = new CountingSheep(inputFile);
        t.evaluate();
    }
 
 }
