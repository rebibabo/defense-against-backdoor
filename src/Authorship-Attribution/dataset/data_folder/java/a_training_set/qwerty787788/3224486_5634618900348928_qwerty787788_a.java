import java.io.*;
 import java.util.*;
 
 public class A {
    FastScanner in;
    PrintWriter out;
 
    int sol(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++) {
            for (int fr = 0; fr + len <= n; fr++) {
                for (int l1 = 2; l1 <= len; l1++) {
                    dp[fr][len] = Math.max(dp[fr][len], dp[fr][l1]
                            + dp[fr + l1][len - l1]);
                }
                dp[fr][len] = Math.max(
                        dp[fr][len],
                        dp[fr + 1][len - 2]
                                + (s.charAt(fr) == s.charAt(fr + len - 1) ? 10
                                        : 5));
            }
        }
        return dp[0][n];
    }
 
    void solve() {
        int tc = in.nextInt();
        for (int t = 0; t < tc; t++) {
            out.print("Case #" + (t + 1) + ": ");
            String s = in.next();
            int n = s.length();
            char[] a = new char[n];
            int cur = 0;
            int sz = 0;
            for (char c : s.toCharArray()) {
                if (sz != 0 && c == a[sz - 1]) {
                    cur += 10;
                    sz--;
                } else {
                    a[sz++] = c;
                }
            }
            cur += (sz / 2) * 5;
 
 
 
 
            out.println(cur);
            System.err.println((t + 1) + "/" + tc + " done");
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