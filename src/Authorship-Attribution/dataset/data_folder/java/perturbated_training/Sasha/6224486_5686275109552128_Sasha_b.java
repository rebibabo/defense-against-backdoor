import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class B {
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            int n = in.nextInt();
            int[] p = new int[n];
 
            int max = 0;
            for (int i = 0; i < n; i++) {
                p[i] = in.nextInt();
                if (p[i] > max) {
                    max = p[i];
                }
            }
 
            int res = max;
            for (int d = 1; d < max; d++) {
                int res1 = d;
                for (int i = 0; i < n; i++) {
                    res1 += p[i] / d - 1;
                    if (p[i] % d != 0)
                        res1++;
                }
 
                if (res1 < res) {
                    res = res1;
                }
            }
            out.println("Case #" + test + ": " + res);
        }
    }
 }
