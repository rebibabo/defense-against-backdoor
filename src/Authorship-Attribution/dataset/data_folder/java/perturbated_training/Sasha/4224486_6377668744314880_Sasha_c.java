import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class C {
 
    private static boolean isInside(long[] x, long[] y, boolean[] cut, int cur) {
        long d = 0, v = 0;
 
        for (int i1 = 0; i1 < x.length; i1++) {
            for (int i2 = i1 + 1; i2 < x.length; i2++) {
                for (int i3 = i2 + 1; i3 < x.length; i3++) {
                    if (i1 != cur && i2 != cur && i3 != cur && !cut[i1]
                            && !cut[i2] && !cut[i3]) {
                        long x0 = x[cur];
                        long y0 = y[cur];
                        long x1 = x[i1];
                        long y1 = y[i1];
                        long x2 = x[i2];
                        long y2 = y[i2];
                        long x3 = x[i3];
                        long y3 = y[i3];
 
                        d++;
 
                        long d1 = (x1 - x0) * (y2 - y1) - (x2 - x1) * (y1 - y0);
                        long d2 = (x2 - x0) * (y3 - y2) - (x3 - x2) * (y2 - y0);
                        long d3 = (x3 - x0) * (y1 - y3) - (x1 - x3) * (y3 - y0);
 
                        if (d1 == 0 || d2 == 0 || d3 == 0) {
                            v++;
                        }
 
                        if (d1 > 0 && d2 > 0 && d3 > 0) {
                            return true;
                        }
 
                        if (d1 < 0 && d2 < 0 && d3 < 0) {
                            return true;
                        }
 
                    }
 
                }
            }
 
        }
        
        if (d == 1) {
            return false;
        }
        if (d == v) {
            return true;
        }
        return false;
    }
 
    private static void find(long[] x, long[] y, boolean[] cut, int numCut,
            int cur, int pos, long[] res) {
        if (numCut >= res[cur]) {
            return;
        }
 
        if (pos == x.length) {
            if (!isInside(x, y, cut, cur)) {
                res[cur] = numCut;
            }
            return;
        }
        find(x, y, cut, numCut, cur, pos + 1, res);
 
        if (pos != cur) {
            cut[pos] = true;
            find(x, y, cut, numCut + 1, cur, pos + 1, res);
            cut[pos] = false;
        }
 
    }
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            int n = in.nextInt();
            long[] x = new long[n];
            long[] y = new long[n];
            long[] res = new long[n];
            boolean[] cut = new boolean[n];
 
            for (int i = 0; i < n; i++) {
                x[i] = in.nextLong();
                y[i] = in.nextLong();
            }
 
            for (int i = 0; i < n; i++) {
                res[i] = n - 1;
                find(x, y, cut, 0, i, 0, res);
            }
 
            out.println("Case #" + test + ":");
            for (int i = 0; i < n; i++) {
                out.println(res[i]);
            }
        }
    }
 }
