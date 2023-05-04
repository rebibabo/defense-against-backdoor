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
        int N = sc.nextInt();
        boolean[][] t = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            char[] cs = sc.next().toCharArray();
            for (int j = 0; j < N; j++) {
                t[i][j] = cs[j] == '1';
            }
        }
        int X = 0;
        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) if (!t[i][j]) X++;
        
        int[] rs = new int[X];
        int[] cs = new int[X];
        {
            int x = 0;
            for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) if (!t[i][j]) {
                rs[x] = i;
                cs[x] = j;
                x++;
            }
        }
        int ans = X;
        for (int set = 0; set < (1 << X); set++) {
            for (int i = 0; i < X; i++) if ((set >> i & 1) == 1) { t[rs[i]][cs[i]] = true; }
            if (check(t)) {
                ans = Math.min(ans, Integer.bitCount(set));
            }
            for (int i = 0; i < X; i++) if ((set >> i & 1) == 1) { t[rs[i]][cs[i]] = false; }
        }
        out.println(ans);
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
