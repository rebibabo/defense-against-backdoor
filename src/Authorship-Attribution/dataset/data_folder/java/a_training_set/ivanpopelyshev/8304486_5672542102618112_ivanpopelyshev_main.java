import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
    final String problem = "A";
    
 
    final String filename = problem + "-small-attempt0";
 
    
    
 
    public void solve() throws Exception {
        char[] starts = readword().toCharArray();
        n = starts.length;
 
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++)
            fact[i] = fact[i - 1] * i;
 
        Thing start = new Thing();
        for (int i = 0; i < n; i++)
            start.x[i] = starts[i] - '0';
        start.calcSum();
        if (start.sum > n) {
            out.write("1");
            return;
        }
 
        long ans = 0;
 
        q = new Thing[10000];
        qlen = 1;
        q[0] = start;
 
        single = new HashSet<Integer>();
        single.add(start.hashCode());
 
        for (int qcur = 0; qcur < qlen; qcur++) {
            Thing c = q[qcur];
            c.calcSum();
            int m = 0;
            for (int i = 0; i < n; i++) {
                m += (i + 1) * c.x[i];
            }
            if (m > n) {
                int zero = n - c.sum;
                long res = fact[n] / fact[zero];
                for (int i = 0; i < n; i++) {
                    res /= fact[c.x[i]];
                }
                ans += res;
            } else {
                t2 = new Thing();
                rec(c, 0, 0);
            }
        }
 
        ans += single.size();
        out.write(ans + "");
    }
 
    int n;
 
    Thing t2;
    HashSet<Integer> single;
    Thing[] q;
    int qlen;
 
    public void rec(Thing t, int cur, int start) {
        if (cur == n) {
            int h = t2.hashCode();
            if (!single.contains(h)) {
                single.add(h);
                t2.calcSum();
                if (t2.sum > n) {
                    System.out.println("WTF");
                }
                q[qlen++] = new Thing().copyFrom(t2);
            }
            return;
        }
 
        if (t.x[cur] > 0) {
            for (int i = start; i < n; i++) {
                if (t2.x[i] == 0) {
                    t.x[cur]--;
                    t2.x[i] = cur + 1;
                    rec(t, cur, i + 1);
                    t2.x[i] = 0;
                    t.x[cur]++;
                }
            }
        } else {
            rec(t, cur + 1, 0);
        }
 
    }
 
    public class Thing {
        int[] x = new int[n];
        int sum;
 
        public void calcSum() {
            sum = 0;
            for (int i = 0; i < n; i++)
                sum += x[i];
        }
 
        public int hashCode() {
            int h = 0;
            for (int i = 0; i < n; i++) {
                h = h * 10 + x[i];
            }
            return h;
        }
 
        public boolean equals(Object o) {
            return this.hashCode() == o.hashCode();
        }
 
        public Thing copyFrom(Thing t) {
            for (int i = 0; i < n; i++)
                x[i] = t.x[i];
            return this;
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