import java.io.*;
 import java.util.*;
 
 public class D {
    FastScanner in;
    PrintWriter out;
 
    int solve(int x, int r, int c) {
        if (r > c) {
            return solve(x, c, r);
        }
        int sq = r * c;
        if (sq % x != 0) {
            return 0;
        }
        if (x <= 2) {
            return 1;
        }
        if (x == 3) {
            if (r == 1) {
                return 0;
            }
            return 1;
        }
        if (r == 1) {
            return 0;
        }
        if (c == 2) {
            return 0;
        }
        if (r == 2) {
            return 0;
        }
        return 1;
    }
 
    void solve() {
        int tc = in.nextInt();
        for (int t = 0; t < tc; t++) {
            out.print("Case #" + (t + 1) + ": ");
            int x = in.nextInt();
            int r = in.nextInt();
            int c = in.nextInt();
            out.println(solve(x, r, c) == 1 ? "GABRIEL" : "RICHARD");
            System.err.println((t + 1) + "/" + tc + " done");
        }
    }
 
    void run() {
        try {
            in = new FastScanner(new File("D.in"));
            out = new PrintWriter(new File("D.out"));
 
            solve();
 
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    void runIO() {
        in = new FastScanner(System.in);
        out = new PrintWriter(System.out);
 
        solve();
 
        out.close();
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        public FastScanner(InputStream f) {
            br = new BufferedReader(new InputStreamReader(f));
        }
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                String s = null;
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null)
                    return null;
                st = new StringTokenizer(s);
            }
            return st.nextToken();
        }
 
        boolean hasMoreTokens() {
            while (st == null || !st.hasMoreTokens()) {
                String s = null;
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null)
                    return false;
                st = new StringTokenizer(s);
            }
            return true;
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
 
    public static void main(String[] args) {
        new D().run();
    }
 }