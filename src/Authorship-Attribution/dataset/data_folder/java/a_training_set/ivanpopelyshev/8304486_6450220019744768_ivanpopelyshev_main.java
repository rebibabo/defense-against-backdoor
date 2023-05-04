import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
    final String problem = "B";
 
 
     final String filename= problem+"-small-attempt0";
    
    
 
    public void solve() throws Exception {
        int n = iread(), m = iread();
 
        first = new Edge[n];
        Edge[] all = new Edge[m];
        for (int i = 0; i < m; i++) {
            all[i] = addEdge(iread() - 1, iread() - 1);
        }
 
        timer = 0;
        used = new boolean[n];
        tin = new int[n];
        fup = new int[n];
        found = false;
 
        balance = new int[n];
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfs(i, null);
            }
        }
 
        if (found) {
            out.write("IMPOSSIBLE");
            return;
        }
 
        for (int i = 0; i < n; i++) {
            if (balance[i] != 0) {
                System.out.println("WTF");
            }
        }
 
        for (int i = 0; i < m; i++) {
            out.write(all[i].flow + "");
            if (all[i].flow ==0) {
                System.out.println("WTF");
            }
            if (all[i].flow>n*n) {
                System.out.println("WTF");
            }
            if (i + 1 < m)
                out.write(" ");
        }
    }
 
    Edge addEdge(int x, int y) {
        Edge e1 = new Edge(x, y);
        Edge e2 = new Edge(y, x);
        e1.back = e2;
        e2.back = e1;
        return e1;
    }
 
    Edge[] first;
 
    class Edge {
        int from, to, flow;
        Edge back, next;
 
        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
            next = first[from];
            first[from] = this;
        }
    }
 
    boolean[] used;
    int[] tin, fup;
    int timer;
    boolean found = false;
    int[] balance;
 
    void dfs(int v, Edge back) {
        used[v] = true;
        tin[v] = fup[v] = timer++;
        for (Edge e = first[v]; e != null; e = e.next) {
            int to = e.to;
            if (e == back)
                continue;
            if (used[to]) {
                if (e.flow == 0) {
                    e.flow = 1;
                    e.back.flow = -1;
                    balance[v]++;
                    balance[e.to]--;
                }
 
                fup[v] = Math.min(fup[v], tin[to]);
            } else {
                dfs(to, e.back);
 
                e.flow = balance[to];
                e.back.flow = -balance[to];
                balance[v] += balance[to];
                balance[to] = 0;
 
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    found = true;
                }
            }
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