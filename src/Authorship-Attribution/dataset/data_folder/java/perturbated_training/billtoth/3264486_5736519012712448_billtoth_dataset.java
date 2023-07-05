import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class Dataset {
 
    int totalCases;
    Scanner input;
    Stovetop[] caseArray;
    PrintWriter writer = new PrintWriter("OversizedFlipper.out");
    
    public Dataset(File inputFile) throws FileNotFoundException {
        input = new Scanner(inputFile);
        totalCases = input.nextInt();
        input.nextLine();
        buildCaseArray();
    }
    
    private void buildCaseArray() {
        caseArray = new Stovetop[totalCases];
        for(int i = 0; i < totalCases; i++){
            Stovetop s = new Stovetop(input.next(), input.nextInt());
            caseArray[i] = s;
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