package r2015.round1b;
 
 import java.io.PrintStream;
 import java.util.Scanner;
 
 import common.OfflineSolution;
 
 public class R2015_1B_B extends OfflineSolution {
    
    private int r;
    
    private int c;
    
    private int m;
    
    private int unhappinaes;
    
    private int minUnahappines;
 
    @Override
    public void readCase(Scanner in) {
        r = in.nextInt();
        c = in.nextInt();
        m = in.nextInt();
        
    }
    
    private boolean countOk(int a) {
        int nn = 0;
        
        for (int i = 0; i < r * c; i++) {
            if ((a & (1 << i)) != 0) {
                nn ++;
            }
        }
        return nn == m;
    }
    
    private int unhappiness(int n) {
        int unhap = 0;
        int t;
        
        if (!countOk(n)) {
            return Integer.MAX_VALUE;
        }
 
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c - 1; j++) {
                t = i * c + j;
                unhap += ((n & (1 << (t))) != 0 && (n & (1 << (t + 1))) != 0) ? 1 : 0;
            }
        }
        
        
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r - 1; j++) {
                t = j * c + i;
                unhap += ((((n & (1 << t)) != 0) && (n & (1 << (t + c))) != 0)) ? 1 : 0;
 
            }
        }
        
        return unhap;
    }
 
    @Override
    public void solveCase() {
        minUnahappines = Integer.MAX_VALUE;
        
        for (int i = 0; i < (1 << (r * c)); i++) {
            minUnahappines = Math.min(minUnahappines, unhappiness(i));
        }
    }
 
    @Override
    public void printOutput(PrintStream out) {
        out.println(minUnahappines);
    }
 
 }
