import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 import org.omg.PortableServer.ForwardRequestHelper;
 
 public class C {
 
    private static final int[][] matrix = new int[][] { { 1, 2, 3, 4 },
            { 2, -1, 4, -3 }, { 3, -4, -1, 2 }, { 4, 3, -2, -1 } };
 
    private static class Complex {
        int x;
 
        final static String s = "ijk";
 
        public Complex() {
            x = 1;
        }
 
        void mult(char c) {
            int m = matrix[Math.abs(x) - 1][s.indexOf(c) + 1];
            x = (x > 0) ? m : -m;
        }
 
        void pow(long n) {
            if (Math.abs(x) == 1) {
                if ((x == -1) && (n % 2 == 0)) {
                    x = 1;
                }
            } else {
                if (n % 2 == 1) {
                    if ((n - 1) % 4 != 0) {
                        x = -x;
                    }
                } else {
                    x = (n % 4 == 0) ? 1 : -1;
                }
            }
 
        }
    }
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            long L = in.nextLong();
            long X = in.nextLong();
            String s = in.next();
 
            Complex complex = new Complex();
            for (int i = 0; i < L; i++) {
                complex.mult(s.charAt(i));
            }
            complex.pow(X);
            boolean possible = true;
 
            if (complex.x != -1) {
                possible = false;
            } else {
 
                boolean foundI = false, foundJ = false;
 
                int pos = 0;
                long p1;
 
                complex.x = 1;
                for (p1 = 1; p1 <= Math.min(8 * L, L * X - 2); p1++) {
                    complex.mult(s.charAt(pos));
                    pos++;
                    if (pos == L) {
                        pos = 0;
                    }
                    if (complex.x == 2) {
                        foundI = true;
                        break;
                    }
                }
 
                if (foundI) {
                    complex.x = 1;
                    for (long p2 = p1 + 1; p2 <= Math
                            .min(p1 + 8 * L, L * X - 1); p2++) {
                        complex.mult(s.charAt(pos));
                        pos++;
                        if (pos == L) {
                            pos = 0;
                        }
                        if (complex.x == 3) {
                            foundJ = true;
                            break;
                        }
                    }
 
                }
                possible = foundI & foundJ;
 
            }
            out.println("Case #" + test + ": " + (possible ? "YES" : "NO"));
        }
    }
 }
