import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 import java.util.ArrayList;
 
 public class Meal {
 
    int totalCases;
    Scanner input;
    TestCase[] caseArray;
    int diners; 
    PrintWriter outputFile;
    
    public Meal(File inputFile) throws FileNotFoundException {
        input = new Scanner(inputFile);
        totalCases = input.nextInt();
        caseArray = new TestCase[totalCases]; 
        buildCaseArray();
    }
    
    private void buildCaseArray() {
        for(int i = 0; i < totalCases; i++){
            parseCase(i);
        }
        
    }
 
    private void parseCase(int i) {
        diners = input.nextInt();
        ArrayList<Integer> dinerPlates = new ArrayList<Integer>();
        for (int k = 0; k < diners; k++){
            dinerPlates.add(input.nextInt());
        }
        TestCase c = new TestCase(diners, dinerPlates);
        caseArray[i] = c;
    }
 
    public void evaluate() throws FileNotFoundException {
        outputFile = new PrintWriter("pancakes.out");
        for (int i=0; i < caseArray.length; i++){
            
                
            outputFile.println("Case #" + (i+1) + ": " + 
                    caseArray[i].evaluateCase());
        }
        outputFile.close();
        
    }
 
 }
