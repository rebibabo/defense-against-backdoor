package gcj2016.r2.c;
 
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
 
 
        if (true) { filename = "C-small-attempt0.in"; }
 
 
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
        int R = sc.nextInt();
        int C = sc.nextInt();
        int N = R * C;
        char[][] t = new char[R][C];
        int[] pr = new int[2 * (R+C)];
        for (int i = 0; i < pr.length; i++) pr[i] = sc.nextInt();
        
        
        out.println();
        for (int set = 0; set < (1 << N); set++) {
            for (int i = 0; i < N; i++) 
                if ((set >> i & 1) == 1) 
                    t[i/C][i%C] = '/';
                else
                    t[i/C][i%C] = '\\';
            if (check(t, pr)) {
                for (int i = 0; i < R; i++) out.println(new String(t[i]));
                return;
            }
        }
        out.println("IMPOSSIBLE");
    }
    
    boolean check(char[][] t, int[] pr) {
        for (int i = 0; i < pr.length; i += 2) {
 
            if (run(t, pr[i]) != pr[i+1]) return false;
        }
        return true;
    }
    
    int run(char[][] t, int init) {
        int R = t.length;
        int C = t[0].length;
        int r = 0, c = 0, d = 0;
        if (init <= C) {  d = 1; r = 0; c = init - 1; }
        else if (init <= C + R) { d = 0; r = (init - C - 1); c = C - 1; }
        else if (init <= C + R + C) { d = 3; r = R - 1; c = C - (init - R - C); }
        else { d = 2; c = 0; r = R - (init - C - R - C); }
        while (0 <= r && r < R && 0 <= c && c < C) {
            if (t[r][c] == '/' && d == 0) { d = 1; r++; }
            else if (t[r][c] == '/' && d == 1) { d = 0; c--; }
            else if (t[r][c] == '/' && d == 2) { d = 3; r--; }
            else if (t[r][c] == '/' && d == 3) { d = 2; c++; }
            else if (t[r][c] == '\\' && d == 0) { d = 3; r--; }
            else if (t[r][c] == '\\' && d == 1) { d = 2; c++; }
            else if (t[r][c] == '\\' && d == 2) { d = 1; r++; }
            else if (t[r][c] == '\\' && d == 3) { d = 0; c--; }
        }
 
        if (r < 0) { return c+1; }
        if (c >= C) { return C + r+1; }
        if (r >= R) { return C + R + (C - c); }
        if (c < 0) { return C + R + C + (R - r); }
        return -1;
    }
    
 }
