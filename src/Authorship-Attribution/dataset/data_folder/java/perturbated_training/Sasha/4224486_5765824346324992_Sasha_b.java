import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class B {
 
    private static long count(long time, long[] m, long n) {
        long count = 0;
        long cur = 0;
        for (int i = 0; i < m.length; i++) {
            count += time / m[i];
            if (time % m[i] == 0) {
                cur++;
            } else {
                count++;
            }
        }
 
        if (count >= n) {
            return -2;
        }
        if (count + cur < n) {
            return -1;
        }
 
        return count;
    }
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
 
            int b = in.nextInt();
            long n = in.nextInt();
 
            long[] m = new long[b];
            for (int i = 0; i < b; i++) {
                m[i] = in.nextLong();
            }
 
            long max = m[0];
            for (int i = 1; i < b; i++) {
                if (m[i] > max) {
                    max = m[i];
                }
            }
 
            long l = 0, r = n * max / b;
 
            int pos = 0;
            while (true) {
                long time = (l + r) / 2;
                long count = count(time, m, n);
 
                if (count >= 0) {
                    for (int i = 0; i < b; i++) {
                        if (time % m[i] == 0) {
                            count++;
                            if (count == n) {
                                pos = i;
                                break;
                            }
                        }
                    }
                    break;
                } else if (count == -1) {
                    l = time + 1;
                } else if (count == -2) {
                    r = time - 1;
                }
 
            }
 
            out.println("Case #" + test + ": " + (pos + 1));
        }
    }
 }
