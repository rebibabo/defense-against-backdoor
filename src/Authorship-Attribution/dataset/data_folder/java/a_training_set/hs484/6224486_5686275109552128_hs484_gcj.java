package gcj.Qual_2015.B;
 
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
 
    void solve(Scanner sc, PrintWriter fout) {
        int n = sc.nextInt();
        int[] P = new int[n];
        for (int i = 0; i < n; i++) P[i] = sc.nextInt();
 
        int best = Integer.MAX_VALUE;
        for (int i = 1; i < 1024; i++) {
            int cur = doit(P.clone(), i);
            best = Math.min(best, cur);
        }
        fout.println(best);
    }
    int doit(int[] P, int initialMaxSize) {
        int operation = 0;
        for (int x : P) {
            operation += (x - 1) / initialMaxSize;
        }
        return initialMaxSize + operation;
    }
 }
