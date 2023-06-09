package gcj2016.qual.a;
 
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
        long n = sc.nextLong();
        boolean[] ap = new boolean[10];
        int ds = 0;
        int MAX_STEP = 1000;
        for (int step = 0; step < MAX_STEP; step++) {
            long xn = (step + 1) * n;
            for (char c : String.valueOf(xn).toCharArray()) {
                c -= '0';
                if (!ap[c]) { 
                    ap[c] = true;
                    ds++;
                }
                if (ds == 10) {
                    out.println(xn);
                    return;
                }
            }
        }
        out.println("INSOMNIA");
    }
    
 }
