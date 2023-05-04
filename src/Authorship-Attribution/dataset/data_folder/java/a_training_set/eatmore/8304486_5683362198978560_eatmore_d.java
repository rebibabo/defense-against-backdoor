import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.Math.abs;
 import static java.lang.Math.min;
 import static java.lang.System.exit;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class D {
 
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
 
    static final int MOD = 1000000007;
 
    static void solve() throws Exception {
        int r = nextInt();
        int c = nextInt();
        int n = nextInt();
        long d = nextLong();
        int pi[] = new int[n];
        int pj[] = new int[n];
        long pv[] = new long[n];
        for (int i = 0; i < n; i++) {
            pi[i] = nextInt() - 1;
            pj[i] = nextInt() - 1;
            pv[i] = nextLong();
        }
        long v[][] = new long[r][c];
        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                long cv = Long.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    cv = min(cv, pv[k] + d * (abs(i - pi[k]) + abs(j - pj[k])));
                }
                v[i][j] = cv;
                ans = (int) ((ans + cv) % MOD);
            }
        }
        for (int i = 0; i < n; i++) {
            if (v[pi[i]][pj[i]] != pv[i]) {
                printCase();
                out.println("IMPOSSIBLE");
                return;
            }
        }
        printCase();
        out.println(ans);
    }
 
    static void printCase() {
        out.print("Case #" + test + ": ");
    }
 
    static void printlnCase() {
        out.println("Case #" + test + ":");
    }
 
    static int nextInt() throws IOException {
        return parseInt(next());
    }
 
    static long nextLong() throws IOException {
        return parseLong(next());
    }
 
    static double nextDouble() throws IOException {
        return parseDouble(next());
    }
 
    static String next() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }
 
    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            int tests = nextInt();
            for (test = 1; test <= tests; test++) {
                solve();
            }
            in.close();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
 }