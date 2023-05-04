package common;
 
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public abstract class OfflineSolution extends Solution {
    
    protected int t;
    
    
    public void readNumberCases(Scanner in) {
        t = in.nextInt();
    }
    
    public abstract void readCase(Scanner in);
    
    
    public abstract void solveCase();
    
    
    public abstract void printOutput(PrintStream out);
    
    
    public int numberCases() {
        return t;
    }
 
    @Override
    public void solve(Scanner in, PrintStream out) {
 
        readNumberCases(in);
        for (int i = 0; i < numberCases(); i++) {
            printCaseLabel(i, out);
            readCase(in);
            solveCase();
            printOutput(out);
        }
 
    }
 
 }
