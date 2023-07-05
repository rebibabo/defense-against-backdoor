import java.io.*;
 import java.util.*;
 
 public class A {
    FastScanner in;
    PrintWriter out;
 
    void solveOneTest() {
        char[] a = in.next().toCharArray();
        int len = in.nextInt();
        int res = 0;
        for (int pos = 0; pos + len <= a.length; pos++) {
            if (a[pos] == '-') {
                for (int i = 0; i < len; i++) {
                    a[i + pos] ^= '-' ^ '+';
                }
                res++;
            }
        }
        boolean ok = true;
        for (char c : a) {
            if (c == '-') {
                ok = false;
                break;
            }
        }
        out.println(ok ? res : "IMPOSSIBLE");
    }
 
    void solve() {
        int tc = in.nextInt();
        for (int t = 0; t < tc; t++) {
            System.err.println("test " + t);
            out.print("Case #" + (t + 1) + ": ");
            solveOneTest();
        }
    }
 
    void run() {
        try {
            in = new FastScanner(new File("A.in"));
            out = new PrintWriter(new File("A.out"));
 
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
        new A().run();
    }
 }