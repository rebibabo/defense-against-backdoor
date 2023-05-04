package roundq;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 
 public class Zad1 {
    
    private int t;
    
    private int[] maxS;
    
    private String[] sString;
    
    private int[][] sArray;
    
    public void readInput() throws FileNotFoundException {
        Scanner s = new Scanner(new File("zad1.in"));
        
        t = s.nextInt();
        
        maxS = new int[t];
        sString = new String[t];
        sArray = new int[t][];
        
        for (int i = 0; i < t; i++) {
            maxS[i] = s.nextInt();
            sString[i] = s.next();
        }
        
        s.close();
    }
    
    private int solveCase(int caseNum) {
        int friendsNeeded = 0;
        int clamping = 0;
        
        for (int i = 0; i <= maxS[caseNum]; i++) {
            int n = sString[caseNum].charAt(i) - '0';
            
            if (i > clamping) {
                int diff = i - clamping;
                friendsNeeded += diff;
                clamping += diff;
            }
            
            clamping += n;
        }
        
        return friendsNeeded;
    }
    
    public void solveAndPrintOutput() {
        
        for (int i = 0; i < t; i++) {
            System.out.println("Case #" + (i+1) + ": " + solveCase(i));
        }
    }
 
    public static void main(String[] args) {
 
        Zad1 z = new Zad1();
        try {
            z.readInput();
            z.solveAndPrintOutput();
            
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        
        
 
 
    }
 
 }
