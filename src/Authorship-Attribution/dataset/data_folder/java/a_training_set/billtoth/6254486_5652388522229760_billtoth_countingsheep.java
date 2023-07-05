import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
 public class CountingSheep {
 
    int totalCases;
    Scanner input;
    TestCase[] caseArray;
    int result;
    int n;
    int lastNum;
    
    public CountingSheep(File inputFile) throws FileNotFoundException {
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
        n = input.nextInt();
        TestCase c = new TestCase(n);
        caseArray[i] = c;
    }
 
    public void evaluate() {
        for (int i = 0; i < caseArray.length; i++){
            lastNum = caseArray[i].evaluateCase();
            if (lastNum == 0){
                System.out.println("Case #" + (i + 1) + ": INSOMNIA");
            }
            else{
                System.out.println("Case #" + (i + 1) + ": " + 
                        lastNum);
            }
            
        }
        
    }
 
 }
