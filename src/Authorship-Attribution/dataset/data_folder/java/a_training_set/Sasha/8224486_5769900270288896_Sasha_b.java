import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class B {
 
    private static int unhappiness(boolean[][] ap) {
        int u = 0;
 
        for (int i = 0; i < ap.length - 1; i++) {
            for (int j = 0; j < ap[i].length - 1; j++) {
                if (ap[i][j] && ap[i + 1][j]) {
                    u++;
                }
                if (ap[i][j] && ap[i][j + 1]) {
                    u++;
                }
            }
        }
        return u;
    }
 
    private static void build(boolean[][] ap, int i, int j, int n) {
        if (n == 0) {
            int u = unhappiness(ap);
            if (res == -1 || u < res) {
                res = u;
            }
            return;
        }
        if (j == ap[i].length - 1) {
            j = 0;
            i++;
        }
 
        if (i == ap.length - 1) {
            return;
        }
        
        build(ap, i, j+1, n);
        
        ap[i][j] = true;
        build(ap, i, j+1, n-1);
        ap[i][j] = false;
    }
 
    private static int res;
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            int r = in.nextInt();
            int c = in.nextInt();
 
            int n = in.nextInt();
 
            boolean[][] ap = new boolean[r + 1][c + 1];
 
            res = -1;
            build(ap, 0, 0, n);
 
            out.println("Case #" + test + ": " + res);
        }
 
        in.close();
        out.close();
    }
 }
