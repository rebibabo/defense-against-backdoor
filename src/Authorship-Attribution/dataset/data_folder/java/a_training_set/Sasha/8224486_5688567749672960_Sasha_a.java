import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class A {
 
    public static long reverse(int a) {
        long b = 0;
        while (a != 0) {
            b = b * 10 + (a % 10);
            a /= 10;
        }
        return b;
    }
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        long[] res = new long[1000001];
        res[1] = 1;
        for (int i = 1; i < 1000000; i++) {
            long m = res[i] + 1;
            if (res[i + 1] == 0 || res[i + 1] > m) {
                res[i + 1] = m;
            }
 
            if (i % 10 != 0) {
                int r = (int) reverse(i);
                if (r > i && r <= 1000000) {
                    if (res[r] == 0 || res[r] > m) {
                        res[r] = m;
                    }
                }
            }
 
        }
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            long n = in.nextLong();
 
            out.println("Case #" + test + ": " + res[(int) n]);
        }
 
        in.close();
        out.close();
    }
 }
