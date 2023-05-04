package gcj2016.r2.a;
 
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
    
    static final String IMPOSSIBLE = "IMPOSSIBLE";
    
    void solve(Scanner sc, PrintWriter out) {
        int N = sc.nextInt();
        int R = sc.nextInt();
        int P = sc.nextInt();
        int S = sc.nextInt();
        
        char[] t = new char[1 << N];
        int p = 0;
        for (int i = 0; i < R; i++) t[p++] = 'R';
        for (int i = 0; i < P; i++) t[p++] = 'P';
        for (int i = 0; i < S; i++) t[p++] = 'S';
        Arrays.sort(t);
        do {
            tr(new String(t));
            if (check(t)) {
                out.println(new String(t));
                return;
            }
        } while (nextPermutation(t));
        out.println(IMPOSSIBLE);
        return;
    }
    
    boolean check(char[] t) {
        char[] work = t.clone();
        int p = t.length;
        int np = 0;
        while (p > 1) {
            for (int i = 0; i < p; i += 2) {
                if (work[i] == work[i+1]) return false;
                work[np++] = win(work[i], work[i+1]);
            }
            p = np;
            np = 0;
        }
        return true;
    }
    
    char win(char c1, char c2) {
        if (c1 == c2) return c1;
        if (c1 == 'R' && c2 == 'P') return c2;
        if (c1 == 'P' && c2 == 'S') return c2;
        if (c1 == 'S' && c2 == 'R') return c2;
        return c1;
    }
    
    static boolean nextPermutation(char[] is) {
        int n = is.length;
        for (int i = n - 1; i > 0; i--) {
            if (is[i - 1] < is[i]) {
                int j = n;
                while (is[i - 1] >= is[--j])
                    ;
                swap(is, i - 1, j);
                rev(is, i, n);
                return true;
            }
        }
        rev(is, 0, n);
        return false;
    }
 
    static void swap(char[] is, int i, int j) {
        char t = is[i]; is[i] = is[j]; is[j] = t;
    }
 
    static void rev(char[] is, int s, int t) {
        while (s < --t) swap(is, s++, t);
    }
    
 }
