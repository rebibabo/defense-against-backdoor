import java.io.File;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class C {
 
    private static int search2(int cur, int[] f, boolean[] inCircle) {
        inCircle[cur] = true;
 
        int max = 0;
        for (int i = 0; i < f.length; i++) {
            if (!inCircle[i] && f[i] == cur) {
                max = Math.max(max, search2(i, f, inCircle) + 1);
            }
        }
        return max;
    }
 
    private static int search(int start, int cur, int[] f, int len, boolean[] inCircle) {
        if (f[cur] == start) {
            return len;
        }
 
        if (inCircle[f[cur]]) {
            return 0;
        }
        inCircle[cur] = true;
        int r = search(start, f[cur], f, len + 1, inCircle);
        inCircle[cur] = false;
 
        return r;
    }
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
 
        for (int test = 1; test <= T; test++) {
            int n = in.nextInt();
            int[] f = new int[n];
 
            for (int i = 0; i < n; i++) {
                f[i] = in.nextInt() - 1;
            }
            boolean[] inCircle = new boolean[n];
 
            int r1 = 0, r2 = 0;
 
            for (int i = 0; i < n; i++) {
                if (!inCircle[i]) {
                    if (f[f[i]] == i) {
                        inCircle[i] = true;
                        inCircle[f[i]] = true;
                        r2 += 2 + search2(i, f, inCircle) + search2(f[i], f, inCircle);
                    } else {
                        r1 = Math.max(search(i, i, f, 1, inCircle), r1);
                    }
                }
 
            }
 
            out.printf("Case #%d: %d\n", test, Math.max(r1, r2));
 
        }
 
    }
 }
