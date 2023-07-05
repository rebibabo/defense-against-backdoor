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
            int p[] = new int[n];
            int st = 1;
            for (int i = 0; i < n; i++) {
                p[i] = in.nextInt();
                st *= 3;
            }
            int ret = 100;
            for (int m = st - 1; m >= 0; m--) {
                int tm = m;
                int mp = 0;
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    int d = tm % 3;
                    int tp = p[i] / (d + 1) 
                            + (p[i] % (d + 1) == 0 ? 0 : 1);
                    mp = tp > mp ? tp : mp;
                    sum += d;
                    tm /= 3;
                }
                sum += mp;
                ret = sum < ret ? sum : ret;
            }
            System.out.println("Case #" + t + ": " + ret);
        }
    }
 
 }
