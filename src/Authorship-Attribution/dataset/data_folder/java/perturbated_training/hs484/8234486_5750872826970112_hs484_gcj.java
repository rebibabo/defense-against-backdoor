package gcj.R2_2015.B;
 
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
 
 
        if (true) { filename = "B-small-attempt4.in"; }
 
 
        Scanner sc = null;
        try {
            sc = new Scanner(new File(dir + "/" + filename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter fout = new PrintWriter(new File(dir + "/" + filename + ".res"));
 
        GCJ obj = new GCJ();
        int TNO = sc.nextInt();
        for (int tno = 0; tno < TNO; tno++) {
            fout.write(String.format("Case #%d: ", (tno + 1)));
            obj.solve(sc, fout);
            fout.flush();
        }
        fout.flush();
        fout.close();
        long end = System.currentTimeMillis();
        tr((end - start) + "ms");
    }
 
    double solve1(double V, double X, double R0, double C0) {
        if (Math.abs(X - C0) > EPS) return -1;
        double t = V / R0;
        return t;
    }
 
    static final double EPS = 1e-6;
    int N;
    double[] R, C;
    double V, X;
    void solve(Scanner sc, PrintWriter fout) {
        N = sc.nextInt();
        V = sc.nextDouble() * 1000;
        X = sc.nextDouble() * 1000;
        R = new double[N];
        C = new double[N];
        for (int i = 0; i < N; i++) {
            R[i] = sc.nextDouble() * 1000;
            C[i] = sc.nextDouble() * 1000;
        }
 
        if (N == 1) {
            double ans = solve1(V, X, R[0], C[0]);
            if (ans < -0.5) {
                fout.println("IMPOSSIBLE");
            } else {
                fout.printf("%.10f\n", ans);
            }
            return;
        }
        if (N == 2 && Math.abs(C[1] - C[0]) < EPS) {
            double ans = solve1(V, X, R[0] + R[1], C[0]);
            if (ans < -0.5) {
                fout.println("IMPOSSIBLE");
            } else {
                fout.printf("%.10f\n", ans);
            }
            return;
        }
 
        double t0 = V * (X - C[1]) / (R[0] * (C[0] - C[1]));
        double t1 = V * (X - C[0]) / (R[1] * (C[1] - C[0]));
 
 
 
 
 
        double ans = -1;
             if ((X - C[1]) * (C[0] - C[1]) < -EPS) ans = -1;
        else if ((X - C[0]) * (C[1] - C[0]) < -EPS) ans = -1;
        else {
            ans = Math.max(t0, t1);
        }
 
        if (ans < -0.5) {
            fout.println("IMPOSSIBLE");
        } else {
            fout.printf("%.10f\n", ans);
        }
    }
 }
