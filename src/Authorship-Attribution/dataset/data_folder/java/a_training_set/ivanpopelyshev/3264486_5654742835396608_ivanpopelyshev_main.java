import java.io.*;
 
 import java.awt.geom.Point2D;
 import java.text.*;
 import java.math.*;
 import java.util.*;
 
 public class Main implements Runnable {
 
    final String problem = "C";
 
 
     final String filename= problem+"-small-1-attempt0";
    
    
 
    public void solve() throws Exception {
        int N = iread(), K = iread();
 
        PriorityQueue<Pair> q = new PriorityQueue<Pair>();
        q.add(new Pair(N, 1));
        while (true) {
            Pair p = q.poll();
            while (q.size() > 0 && q.peek().len == p.len) {
                p.count += q.poll().count;
            }
            long a = (p.len - 1) / 2;
            long b = p.len - 1 - a;
            if (K <= p.count) {
                out.write(b + " " + a);
                return;
            }
            K -= p.count;
            q.add(new Pair(a, p.count));
            q.add(new Pair(b, p.count));
        }
    }
 
    class Pair implements Comparable<Pair> {
        long len, count;
 
        public Pair(long len, long count) {
            this.len = len;
            this.count = count;
        }
 
        @Override
        public int compareTo(Pair o) {
            return Long.compare(o.len, len);
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