package gcj.Qual_2015.A;
 
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
 
 
        if (true) { filename = "A-small-attempt0.in"; }
 
 
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
        int[] S = new int[n + 1];
        char[] sss = sc.next().toCharArray();
        for (int i = 0; i < sss.length; i++) {
            S[i] = sss[i] - '0';
        }
 
        int lo = -1;
        int hi = 1024;
        while (hi - lo > 1) {
            int mid = (hi + lo) / 2;
            if (check(S, mid)) hi = mid; else lo = mid;
        }
        fout.println(hi);
    }
    boolean check(int[] S, int alreadyStanding) {
        for (int i = 0; i < S.length; i++) {
            if (i <= alreadyStanding) {
                alreadyStanding += S[i];
            } else {
                return false;
            }
        }
        return true;
    }
 }
