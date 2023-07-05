import java.io.File;
 import java.io.FileNotFoundException;
 
 
 
 
 
 
 
 
 
 public class LastWordTester {
 
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(args[0]);
        LastWord t = new LastWord(inputFile);
        t.evaluate();
    }
 
 }
