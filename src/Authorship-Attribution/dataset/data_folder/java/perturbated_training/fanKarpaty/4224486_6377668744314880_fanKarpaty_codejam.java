import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 public class Codejam {
    public static long[] equat(long x1, long y1, long x2, long y2) {
        long[] ret = new long[3];
        if (x1 == x2) {
            ret[0] = 1;
            ret[2] = - x1;
            return ret;
        }
        if (y1 == y2) {
            ret[1] = 1;
            ret[2] = -y1;
            return ret;
        }
        ret[0] = y2 - y1;
        ret[1] = x1 - x2;
        ret[2] = (y2 - y1) * (-x1) + y1 * (x2 - x1);
        return ret;
    }
    public static void main(String[] args) {
        
        
        Scanner in = null;
        try {
            in = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e1) {
        }
        try {
            System.setOut(new PrintStream(new File("output.txt")));
        } catch (FileNotFoundException e) {
        }
        
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int n = in.nextInt();
            long x[] = new long[n];
            long y[] = new long[n];
            for (int i = 0; i < n; i++) {
                x[i] = in.nextLong();
                y[i] = in.nextLong();
            }
            System.out.println("Case #" + t + ":");
            
            for (int i = 0; i < n; i++) {
                int ret = Math.max(n - 3, 0);
                for (int j = 0; j < n; j++) {
                    if (i == j)continue;
                    long[] eq = equat(x[i], y[i], x[j], y[j]);
                    int mi = 0;
                    int ma = 0;
                    for (int k = 0; k < n; k++) {
                        if (k == i || k == j)continue;
                        long tmp = eq[0] * x[k] + eq[1] * y[k] + eq[2];
                        if (tmp < 0)mi++;
                        else if (tmp > 0)ma++;
                    }
                    if (mi > ma) mi = ma;
                    ret = Math.min(mi, ret);
                    
                }
                System.out.println(ret);
            }
        }
    }
 }
