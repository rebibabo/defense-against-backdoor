import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class LastWord {
 
    int totalCases;
    Scanner input;
    TestCase[] caseArray;
    int result;
    String word;
    PrintWriter writer = new PrintWriter("LastWord.out");
    
    public LastWord(File inputFile) throws FileNotFoundException {
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
        word = input.next();
        TestCase c = new TestCase(word);
        caseArray[i] = c;
    }
 
    public void evaluate() {
        for (int i=0; i < caseArray.length; i++){
            writer.println("Case #" + (i+1) + ": " + 
                    caseArray[i].evaluateCase());
        }
        
        writer.close();
        
    }
 
 }
