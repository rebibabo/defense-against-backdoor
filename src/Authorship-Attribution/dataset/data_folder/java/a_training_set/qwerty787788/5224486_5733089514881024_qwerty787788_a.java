import java.io.*;
 import java.util.*;
 
 public class A {
    FastScanner in;
    PrintWriter out;
 
    void solve() {
        int tc = in.nextInt();
        for (int t = 0; t < tc; t++) {
            out.print("Case #" + (t + 1) + ": ");
 
            String s = in.next();
            int n = s.length();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = s.charAt(i) - '0';
            }
            final int MAX = 20 * 9 + 1;
            int[][] dp = new int[MAX][n];
            int[][] last = new int[10][n];
            for (int i = 1; i < 10; i++) {
                for (int j = 0; j < n; j++) {
                    last[i][j] = (j == 0 ? -1 : last[i][j - 1]);
                    if (a[j] == i) {
                        last[i][j] = j;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                dp[0][i] = i;
            }
            for (int cost = 1; cost < MAX; cost++) {
                for (int start = 0; start < n; start++) {
                    int res = start;
                    for (int use = 1; use < 10; use++) {
                        if (cost - use < 0) {
                            continue;
                        }
                        int look = dp[cost - use][start];
                        if (look == n) {
                            res = n;
                        } else {
                            int usePos = last[use][look];
                            if (usePos >= start) {
                                if (usePos == n - 1) {
                                    res = n;
                                } else {
                                    res = Math.max(res,
                                            dp[cost - use][usePos + 1]);
                                }
                            }
                        }
                    }
                    dp[cost][start] = res;
                }
            }
            int result = Integer.MAX_VALUE;
            for (int cost = 0; cost < MAX; cost++) {
                if (dp[cost][0] == n) {
                    result = cost;
                    break;
                }
            }
            if (result == Integer.MAX_VALUE) {
                throw new AssertionError();
            }
            out.println(result);
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