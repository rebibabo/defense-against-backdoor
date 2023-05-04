import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
 public class Pancakes {
 
    int totalCases;
    Scanner input;
    TestCase[] caseArray;
    int result;
    String stack;
    
    public Pancakes(File inputFile) throws FileNotFoundException {
        input = new Scanner(inputFile);
        totalCases = input.nextInt();
        input.nextLine();
        caseArray = new TestCase[totalCases]; 
        
        buildCaseArray();
        
    }
    
    private void buildCaseArray() {
        for(int i = 0; i < totalCases; i++){
            parseCase(i);
        }
        
    }
 
    private void parseCase(int i) {
        stack = input.nextLine();
        
        TestCase c = new TestCase(stack);
        caseArray[i] = c;
    }
 
    public void evaluate() {
        for (int i = 0; i < caseArray.length; i++){
            System.out.println("Case #" + (i+1) + ": " + 
                    caseArray[i].evaluateCase());
        }
        
    }
 
 }
