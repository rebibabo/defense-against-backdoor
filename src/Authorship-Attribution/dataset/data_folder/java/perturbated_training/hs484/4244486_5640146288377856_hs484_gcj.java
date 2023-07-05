package gcj.R1C_2015.A;
 
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
 
    int[] decode(int s) {
        int[] res = new int[C];
        for (int i = 0; i < C; i++) {
            res[i] = s % 3;
            s /= 3;
        }
        return res;
    }
    int encode(int[] state) {
        int h = 0;
        for (int i = 0; i < C; i++) {
            h = h * 3 + state[C - 1 - i];
        }
        return h;
    }
    int hard(int[] state) {
        int zero = 0;
        int one = 0;
        for (int i = 0; i < C; i++) {
            if (state[i] == 0) zero++;
            if (state[i] == 1) one++;
        }
        if (one > W) return -999;
        if (one > 0) {
            int a = 0;
            int b = C - 1;
            while (a < C && state[a] != 1) a++;
            while (b >= 0 && state[b] != 1) b--;
            int len = b - a + 1;
            if (len > W) return -999;
            for (int i = a; i <= b; i++) if (state[i] == 2) return -999;
 
            if (len == W) {
                int un = 0;
                for (int i = a; i <= b; i++) {
                    if (state[i] == 0) un++;
                }
                return un;
            }
        }
        if (zero == 0) return -999;
        return -2;
    }
    int[] memo = new int[60000];
    int solveSmall(int s) {
        if (memo[s] != -1) return memo[s];
        int[] state = decode(s);
 
        int h = hard(state);
        if (h != -2) {
            if (C == 4) tr(state, h);
            return memo[s] = h;
        }
        int res = 100000;
        for (int i = 0; i < C; i++) if (state[i] == 0) {
            int[] next1 = state.clone();
            int[] next2 = state.clone();
            next1[i] = 1;
            next2[i] = 2;
            int here = 1 + Math.max(solveSmall(encode(next1)),
                                solveSmall(encode(next2)));
            res = Math.min(res, here);
        }
        if (C == 4) tr(state, res);
        return memo[s] = res;
    }
 
 
    int R, C, W;
    void solve(Scanner sc, PrintWriter fout) {
        R = sc.nextInt();
        C = sc.nextInt();
        W = sc.nextInt();
        if (R == 1) {
            for (int i = 0; i < memo.length; i++) {
                memo[i] = -1;
            }
            int ans = solveSmall(0);
            fout.println(ans);
        }
    }
 }
