import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
 public class RankAndFile {
 
    int totalCases;
    Scanner input;
    TestCase[] caseArray;
    int[] heightArray;
    int result;
    int n;
    int height;
    
    public RankAndFile(File inputFile) throws FileNotFoundException {
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
        heightArray = new int[2500];
        for (int j = 0; j < ((2*(n*n)) - n); j++){
            height = input.nextInt();
            heightArray[height] += 1;
        }
        TestCase c = new TestCase(n, heightArray);
        caseArray[i] = c;
        
    }
 
    public void evaluate() {
        for (int i=0; i < caseArray.length; i++){
            System.out.println("Case #" + (i+1) + ": " + 
                    caseArray[i].evaluateCase());
        }
        
    }
 
 }
