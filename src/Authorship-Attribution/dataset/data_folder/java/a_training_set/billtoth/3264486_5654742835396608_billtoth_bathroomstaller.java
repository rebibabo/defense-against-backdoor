import java.io.File;
 import java.io.FileNotFoundException;
 
 
 
 
 
 
 
 
 
 public class BathroomStaller {
 
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(args[0]);
        Dataset d = new Dataset(inputFile);
        d.evaluate();
 
    }
 
 }
