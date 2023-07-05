import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class Dataset {
 
    int totalCases;
    Scanner input;
    Bathroom[] caseArray;
    PrintWriter writer = new PrintWriter("BathroomStaller.out");
    
    public Dataset(File inputFile) throws FileNotFoundException {
        input = new Scanner(inputFile);
        totalCases = input.nextInt();
        buildCaseArray();
    }
    
    private void buildCaseArray() {
        caseArray = new Bathroom[totalCases];
        for(int i = 0; i < totalCases; i++){
            Bathroom b = new Bathroom(input.nextInt(), input.nextInt());
            caseArray[i] = b;
        }
    }
 
    public void evaluate() {
        for(int i = 0; i < totalCases; i++){
            writer.println("Case #" + (i+1) + ": " + 
                    caseArray[i].evaluate());
        }
        writer.close();
    }
 }