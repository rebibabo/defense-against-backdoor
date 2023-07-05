import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class Dataset {
 
    int totalCases;
    Road[] caseArray;
    public static Scanner input;
    PrintWriter writer = new PrintWriter("AnnieSpeed.out");
    
    public Dataset(File inputFile) throws FileNotFoundException {
        input = new Scanner(inputFile);
        totalCases = input.nextInt();
        buildCaseArray();
    }
    
    private void buildCaseArray() {
        caseArray = new Road[totalCases];
        for(int i = 0; i < totalCases; i++){
            Road r = new Road(input, input.nextDouble(), input.nextInt());
            caseArray[i] = r;
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