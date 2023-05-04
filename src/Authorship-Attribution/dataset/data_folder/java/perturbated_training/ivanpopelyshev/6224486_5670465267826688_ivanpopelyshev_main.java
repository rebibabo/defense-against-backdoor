import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
    final String problem = "C";
 
 
     final String filename= problem+"-small-attempt0";
 
    
 
    public void solve() throws Exception {
        int L = iread();
        long X = lread();
        String ss = readword();
        int MAX = 10000;
        int n = MAX / L < X ? MAX : (int) (L * X);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int j = (int) (i % L);
            switch (ss.charAt(j)) {
            case '1':
                a[i] = 1;
                break;
            case 'i':
                a[i] = 2;
                break;
            case 'j':
                a[i] = 3;
                break;
            case 'k':
                a[i] = 4;
                break;
            }
        }
        int s = 1;
        for (int i = 0; i < L; i++) {
            s = mul(s, a[i]);
        }
        if (pow(s, X) != -1) {
            out.write("NO");
            return;
        }
        s = 1;
        int step = 0;
        for (int i = 0; i < n; i++) {
            s = mul(s, a[i]);
            if (step == 0) {
                if (s == 2) {
                    step++;
                    s = 1;
                }
            } else {
                if (s == 3) {
                    step++;
                    break;
                }
            }
        }
        if (step == 2) {
            out.write("YES");
        } else {
            out.write("NO");
        }
    }
 
    int[][] mul = new int[][] { new int[] { 1, 2, 3, 4 },
            new int[] { 2, -1, 4, -3 }, new int[] { 3, -4, -1, 2 },
            new int[] { 4, 3, -2, -1 } };
 
    int mul(int x, int y) {
        int ans = 1;
        if (x < 0) {
            ans = -ans;
            x = -x;
        }
        if (y < 0) {
            ans = -ans;
            y = -y;
        }
        return ans * mul[x - 1][y - 1];
    }
 
    int pow(int x, long k) {
        int res = 1;
        while (k > 0) {
            if (k % 2 == 1)
                res = mul(res, x);
            x = mul(x, x);
            k /= 2;
        }
        return res;
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