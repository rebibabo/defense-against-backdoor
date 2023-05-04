package gcj2016.qual.d;
 
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
 
 
        if (true) { filename = "D-small-attempt0.in"; }
 
 
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
            fout.write(String.format("Case #%d:", (t + 1)));
            obj.solve(sc, fout);
            fout.flush();
        }
        fout.flush();
        fout.close();
        long end = System.currentTimeMillis();
        tr((end - start) + "ms");
    }
    
    long[] kc = new long[101];
    void solve(Scanner sc, PrintWriter out) {
        int K = sc.nextInt();
        int C = sc.nextInt();
        int S = sc.nextInt();
        kc[0] = 1;
        for (int c = 1; c <= C; c++) {
            kc[c] = kc[c-1] * K;
        }
        if (S == K) {
            for (int i = 0; i < S; i++) {
                long index = 0;
                for (int c = 0; c < C; c++) {
                    index += kc[c] * i;
                }
                out.print(" " + (1 + index));
            }
            out.println();
            return;
        }
        out.println();
        
    }
    
 }
