package gcj.R1C_2015.B;
 
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
 
 
        if (true) { filename = "B-small-attempt0.in"; }
 
 
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
 
 
    int[] getKeyboard(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int k = (s.charAt(i) - 'A');
            count[k]++;
        }
        return count;
    }
 
    int getOverlap(String s) {
        int n = s.length();
        int ma = 0;
        for (int i = 1; i < n; i++) {
            if (s.substring(0, i).equals(s.substring(n - i, n))) {
                ma = i;
            }
        }
        return ma;
    }
 
    double solve2(int[] keyboard, int K, String target, int S) {
        int L = target.length();
 
        int overlap = getOverlap(target);
        if (S < L) {
            return 0.0;
        }
 
        double e1 = 1.0;
        for (int i = 0; i < target.length(); i++) {
            int k = target.charAt(i) - 'A';
            double p = 1.0 * keyboard[k] / K;
            if (keyboard[k] == 0) return 0;
            e1 *= p;
        }
 
        int ma = 1 + (S - L) / (L - overlap);
        tr(target, S, ma);
 
        double e = e1 * (S - L + 1);
        return (ma - e);
    }
 
    void solve(Scanner sc, PrintWriter fout) {
        int K = sc.nextInt();
        int L = sc.nextInt();
        int S = sc.nextInt();
        int[] keyboard = getKeyboard(sc.next());
        String target = sc.next(); 
        double ans = solve2(keyboard, K, target, S);
        fout.printf("%.10f\n", ans);
 
    }
 }
