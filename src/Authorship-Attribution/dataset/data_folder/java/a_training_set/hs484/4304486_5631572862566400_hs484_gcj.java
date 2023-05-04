package gcj2016.r1a.c;
 
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
 
 
        if (true) { filename = "C-small-attempt2.in"; }
 
 
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
        int n = sc.nextInt();
        int[] next = new int[n];
        for (int i = 0; i < n; i++) next[i] = sc.nextInt() - 1;
        
        int extra = 0;
        int maxCycle = 0;
        boolean[] useExtra = new boolean[n];
        for (int start = 0; start < n; start++) {
            int[] vis = new int[3000];
            int cur = start; 
            for (int age = 1; ; age++) {
                cur = next[cur];
                if (vis[cur] != 0) {
                    int len = age - vis[cur];
                    maxCycle = Math.max(maxCycle, len);
                    if (len == 2 && !useExtra[cur]) {
                        extra += len + hige(next, cur);
                        useExtra[cur] = true;
                        useExtra[next[cur]] = true;
                    }
                    break;
                }
                vis[cur] = age;
            }
        }
        tr();
        
        int ans = Math.max(maxCycle, extra);
        out.println(ans);
    }
    
    int hige(int[] next, int end) {
        int n = next.length;
        int g1 = end;
        int g2 = next[end];
        
        int max1 = 0;
        int max2 = 0;
        for (int p = 0; p < next.length; p++) {
            boolean[] vis = new boolean[n];
            int cur = p;
            for (int age = 0; ; age++) {
                if (cur == g1) { max1 = Math.max(max1, age); break; }
                if (cur == g2) { max2 = Math.max(max2, age); break; }
                if (vis[cur]) break;
                vis[cur] = true;
                cur = next[cur];
            }
        }
        return max1 + max2;
    }
    
 }
