import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
    final String problem = "D";
    
 
    final String filename = problem + "-small-attempt0";
    
    
 
    final int mod = 1000000007;
 
    public void solve() throws Exception {
        int R = iread(), C = iread();
        out.write(stupid(R, C, true, 7) + "");
    }
 
    public long smart(int R, int C) {
        return 0;
    }
 
    int[] f_field;
    int f_RC, f_R, f_C;
    boolean f_repeat;
    int f_mask;
    int f_ans;
 
    public long stupid(int R, int C, boolean repeat, int mask) {
        f_field = new int[R * C];
        f_RC = R * C;
        f_R = R;
        f_C = C;
        f_repeat = repeat;
        f_mask = mask;
        f_ans = 0;
        rec(0);
        return f_ans;
    }
 
    int[] dx = new int[] { 1, 0, -1, 0 };
    int[] dy = new int[] { 0, 1, 0, -1 };
 
    public boolean check(int x, int y) {
        if (y < 0 || y >= f_R)
            return true;
        if (x == f_C) {
            x = 0;
            if (!f_repeat)
                return true;
        }
        if (x == -1) {
            x = f_C - 1;
            if (!f_repeat)
                return true;
        }
        int f = f_field[x + y * f_C];
        if (f == 0)
            return true;
        int min = 0, max = 0;
        for (int i = 0; i < 4; i++) {
            int x1 = x + dx[i], y1 = y + dy[i];
            if (y1 < 0 || y1 >= f_R)
                continue;
            if (x1 == f_C) {
                x1 = 0;
                if (!f_repeat)
                    return true;
            }
            if (x1 == -1) {
                x1 = f_C - 1;
                if (!f_repeat)
                    return true;
            }
            int f2 = f_field[x1 + y1 * f_C];
            if (f2 == 0)
                max++;
            else if (f2 == f) {
                min++;
                max++;
            }
        }
        return f >= min && f <= max;
    }
 
    public void rec(int num) {
        if (num == f_RC) {
            f_ans++;
            return;
        }
        int x = num % f_C, y = num / f_C;
        for (int i = 1; i <= 3; i++)
            if ((f_mask & (1 << (i - 1))) != 0) {
                f_field[num] = i;
                if (check(x, y) && check(x + 1, y) && check(x - 1, y)
                        && check(x, y + 1) && check(x, y - 1))
                    rec(num + 1);
                f_field[num] = 0;
            }
    }
 
    public void solve_gcj() throws Exception {
        int tests = iread();
        for (int test = 1; test <= tests; test++) {
            out.write("Case #" + test + ": ");
            solve();
            out.write("\n");
        }
    }
 
    public void run() {
        try {
            
            
            in = new BufferedReader(new FileReader(filename + ".in"));
            out = new BufferedWriter(new FileWriter(filename + ".out"));
            solve_gcj();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
 
    public int iread() throws Exception {
        return Integer.parseInt(readword());
    }
 
    public double dread() throws Exception {
        return Double.parseDouble(readword());
    }
 
    public long lread() throws Exception {
        return Long.parseLong(readword());
    }
 
    BufferedReader in;
 
    BufferedWriter out;
 
    public String readword() throws IOException {
        StringBuilder b = new StringBuilder();
        int c;
        c = in.read();
        while (c >= 0 && c <= ' ')
            c = in.read();
        if (c < 0)
            return "";
        while (c > ' ') {
            b.append((char) c);
            c = in.read();
        }
        return b.toString();
    }
 
    public static void main(String[] args) {
        try {
            Locale.setDefault(Locale.US);
        } catch (Exception e) {
 
        }
        new Thread(new Main()).start();
        
    }
 }