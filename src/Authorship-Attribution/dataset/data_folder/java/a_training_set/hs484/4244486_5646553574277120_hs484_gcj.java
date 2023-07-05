package gcj.R1C_2015.C;
 
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
 
 
        if (true) { filename = "C-small-attempt0.in"; }
 
 
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
 
    int solveSmall(int C, int[] d, int V) {
        int D = d.length;
 
        boolean[] possible = new boolean[V+1];
        possible[0] = true;
        for (int coin = 0; coin < D; coin++) {
            int v = d[coin];
            for (int i = V; i - v >= 0; i--) {
                if (possible[i-v]) possible[i] = true;
            }
        }
 
        int ans = 0;
        for (int tar = 0; tar <= V; tar++) {
            if (!possible[tar]) {
                ans++;
                int v = tar;
                for (int i = V; i - v >= 0; i--) {
                    if (possible[i-v]) possible[i] = true;
                }
            }
        }
        return ans;
    }
    int solveLarge(int C, int[] d, int V) {
        return 42;
    }
    void solve(Scanner sc, PrintWriter fout) {
        int C = sc.nextInt();
        int D = sc.nextInt();
        int[] d = new int[D];
        int V = sc.nextInt();
        for (int i = 0; i < D; i++) {
            d[i] = sc.nextInt();
        }
        if (C == 1 && D <= 5 && V <= 30) {
            int ans = solveSmall(C, d, V);
            fout.println(ans);
            return;
        }
        else {
            int ans = solveLarge(C, d, V);
            fout.println(ans);
            return;
        }
 
    }
 }
