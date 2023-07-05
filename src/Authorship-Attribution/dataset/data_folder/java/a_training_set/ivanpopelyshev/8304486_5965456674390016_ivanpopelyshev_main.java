import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
    final String problem = "C";
    
 
 
 
     final String filename= problem+"-small-attempt1";
    
 
    public void solve() throws Exception {
        int n = iread();
 
        int ans0 = 0;
 
        Edge[][] c_in = new Edge[2][n];
        Edge[][] c_out = new Edge[2][n];
        int[][] c = new int[2][n];
 
        for (int i = 0; i < 2 * n; i++) {
            int x = i / 2;
            int y = iread() - 1;
            int start = iread();
            int d = iread();
            ans0 += d;
            int finish = start + d;
            finish %= 24;
            Edge e1 = new Edge(x, y, start, finish);
            c_out[i % 2][x] = e1;
            if (c_in[0][y] != null) {
                c_in[1][y] = e1;
            } else {
                c_in[0][y] = e1;
            }
        }
 
        int superans = 1000000000;
        for (int tt = 0; tt < 2; tt++) {
            for (int x = 0; x < n; x++) {
                c[0][x] = (c_out[0][x].start - c_in[0][x].finish + 24) % 24
                        + (c_out[1][x].start - c_in[1][x].finish + 24) % 24;
                c[1][x] = (c_out[1][x].start - c_in[0][x].finish + 24) % 24
                        + (c_out[0][x].start - c_in[1][x].finish + 24) % 24;
 
                if (x == 0) {
                    if (tt == 0) {
                        c[0][x] = c_out[0][x].start
                                + (c_out[1][x].start - c_in[1][x].finish + 24)
                                % 24;
                        c[1][x] = c_out[0][x].start
                                + (c_out[1][x].start - c_in[0][x].finish + 24)
                                % 24;
                    } else {
                        c[0][x] = c_out[1][x].start
                                + (c_out[0][x].start - c_in[0][x].finish + 24)
                                % 24;
                        c[1][x] = c_out[1][x].start
                                + (c_out[0][x].start - c_in[1][x].finish + 24)
                                % 24;
                    }
                }
            }
 
            a = new int[n * 2];
            for (int i = 0; i < a.length; i++)
                a[i] = i;
            rank = new int[n * 2];
            ArrayList<Event> events = new ArrayList<Event>();
 
            int ans1 = 0;
            for (int x = 0; x < n; x++) {
                int y1 = c_out[0][x].to;
                int y2 = c_out[1][x].to;
                int ind1 = c_in[0][y1] == c_out[0][x] ? 0 : 1;
                int ind2 = c_in[0][y2] == c_out[1][x] ? 0 : 1;
                if (c[0][x] < c[1][x]) {
                    ans1 += c[0][x];
                    join(2 * x + 0, 2 * y1 + ind1);
                    join(2 * x + 1, 2 * y2 + ind2);
                } else {
                    ans1 += c[1][x];
                    join(2 * x + 1, 2 * y1 + ind1);
                    join(2 * x + 0, 2 * y2 + ind2);
                }
                events.add(new Event(2 * x, 2 * x + 1, Math.abs(c[1][x]
                        - c[0][x])));
            }
 
            int ans2 = 0;
            Collections.sort(events);
            for (int i = 0; i < events.size(); i++) {
                Event e = events.get(i);
                if (join(e.a1, e.a2)) {
                    ans2 += e.cost;
                }
            }
 
            int ans = ans0 + ans1 + ans2;
 
            superans = Math.min(superans, ans);
        }
        out.write(superans + "");
    }
 
    int[] a;
    int[] rank;
 
    int par(int x) {
        int y = x, z = y;
        while (y != a[y])
            y = a[y];
        while (x != y) {
            z = a[x];
            a[x] = y;
            x = z;
        }
        return x;
    }
 
    boolean join(int x, int y) {
        int u = par(x), v = par(y);
        if (u == v)
            return false;
        if (rank[u] < rank[v]) {
            a[u] = v;
        } else if (rank[u] > rank[v]) {
            a[v] = u;
        } else {
            a[u] = v;
            rank[v]++;
        }
        return true;
    }
 
    class Event implements Comparable<Event> {
        int a1, a2;
        int cost;
 
        public Event(int a1, int a2, int cost) {
            this.a1 = a1;
            this.a2 = a2;
            this.cost = cost;
        }
 
        @Override
        public int compareTo(Event o) {
            return cost - o.cost;
        }
    }
 
    class Edge {
        int from, to;
        int start, finish;
 
        public Edge(int from, int to, int start, int finish) {
            this.from = from;
            this.to = to;
            this.start = start;
            this.finish = finish;
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