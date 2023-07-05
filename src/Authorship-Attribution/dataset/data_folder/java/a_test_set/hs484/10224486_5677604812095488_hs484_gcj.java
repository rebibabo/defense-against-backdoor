package gcj2016.r2.b;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class GCJ {
 
    public static void tr(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }
 
    public static void main(String[] args) throws Throwable {
        long start = System.currentTimeMillis();
 
        String pkg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
        String dir = "src/" + pkg;
 
        String filename = "";
 
 
        if (true) { filename = "B-small-attempt2.in"; }
 
 
        Scanner sc = null;
        try {
            sc = new Scanner(new File(dir + "/" + filename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter fout = new PrintWriter(new File(dir + "/" + filename + ".res"));
 
        GCJ obj = new GCJ();
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            fout.write(String.format("Case #%d: ", (t + 1)));
            obj.solve(sc, fout);
            fout.flush();
        }
        fout.flush();
        fout.close();
        long end = System.currentTimeMillis();
        tr((end - start) + "ms");
    }
    
    void solve(Scanner sc, PrintWriter out) {
        int N = sc.nextInt();
        int K = sc.nextInt();
        double[] P = new double[N];
        for (int i = 0; i < N; i++) P[i] = sc.nextDouble();
        Arrays.sort(P);
        double ans = naive(P, K);
        double[] PP = new double[K];
        for (int i = 0; i < K/2; i++) {
            PP[i] = P[i];
            PP[K-1-i] = P[N - 1 - i];
        }
        out.printf("%.8f%n", ans);
    }
    
    double naive(double[] P, int K) {
        int N = P.length;
        double best = 0;
        double[] PP = new double[K];
        int p = (1 << K) - 1;
        do {
            int sp = 0;
            for (int i = 0; i < N; i++) {
                if ((p >> i & 1) == 1) {
                    PP[sp++] = P[i];
                }
            }
            double ans = calc(PP);
            best = Math.max(best, ans);
        } while( (p = next_combination(p)) < (1 << N) );
        return best;
    }
    
    static int next_combination(int p) {
        int lsb = p & -p;
        int rem = p + lsb;
        int rit = rem & ~p;
        return rem | (((rit / lsb) >> 1) - 1);
    }
    
    double calc(double[] P) {
        int N = P.length;
        double[][] dp = new double[N+1][N+1];
        dp[0][0] = 1.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i+1][j+1] += dp[i][j] * P[i];
                dp[i+1][j]   += dp[i][j] * (1.0 - P[i]);
            }
        }
        return dp[N][N/2];
    }
    
 }
