package gcj2017.r2.a;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class GCJsmall {
 
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
 
        GCJsmall obj = new GCJsmall();
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
        int P = sc.nextInt();
        int G[] = new int[N];
        for (int i = 0; i < N; i++) G[i] = sc.nextInt();
        
        int[] B = new int[P];
        for (int i = 0; i < N; i++) B[G[i] % P]++;
        
        int ans = 0;
        if (P == 2) {
            ans += B[0];
            ans += (B[1]+1)/2;
        }
        if (P == 3) {
            ans += B[0];
            int x = Math.min(B[1], B[2]);
            ans += x;
            B[1] -= x;
            B[2] -= x;
            ans += (B[1] + 2) / 3;
            ans += (B[2] + 2) / 3;
        }
        if (P == 4) {
            ans = -1;
        }
        out.println(ans);
    }
    
 }
