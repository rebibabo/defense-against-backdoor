package r2015.round1b;
 
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Scanner;
 
 import common.OfflineSolution;
 
 public class R2015_1B_C extends OfflineSolution {
    
    private int n;
    
    private int[] d;
    
    private int[] h;
    
    private int[] m;
    
    private int encounters;
 
    @Override
    public void readCase(Scanner in) {
        n = in.nextInt();
        d = new int[n];
        h = new int[n];
        m = new int[n];
        
        for (int i = 0; i < n; i++) {
            d[i] = in.nextInt();
            h[i] = in.nextInt();
            m[i] = in.nextInt();
        }
        
    }
    
    private double timeTo360(int a) {
        return ((double)m[a]) * d[a] / 360;
    }
    
    private int f(int a, int b) {
        int enc = 0;
        
        double timeB = timeTo360(b);
        double timeA = timeTo360(a);
        
        if (d[a] < d[b]) {
            if (timeB > timeA) {
                return 1 + (int)Math.floor((timeB - timeA) / m[a]);
            } else {
                return 0;
            }
        } else {
            if (timeB > timeA + m[a]) {
                return 1;
            } else {
                return 0;
            }
        }
    }
 
    @Override
    public void solveCase() {
        encounters = Integer.MAX_VALUE;
        
        if (n == 1) {
            if (d[0] == 0) {
                encounters = h[0];
            } else {
                encounters = 0;
            }
 
        } else {
            
            if (d[0] > d[1]) {
                if (f(0, 1) > 0 || f(1, 0) > 1) {
                    encounters = 1;
                } else {
                    encounters = 0;
                }
            } else {
                if (f(0, 1) > 1 || f(1, 0) > 0) {
                    encounters = 1;
                } else {
                    encounters = 0;
                }
            }
            
        }
    }
 
    @Override
    public void printOutput(PrintStream out) {
        out.println(encounters);
    }
 
 }
