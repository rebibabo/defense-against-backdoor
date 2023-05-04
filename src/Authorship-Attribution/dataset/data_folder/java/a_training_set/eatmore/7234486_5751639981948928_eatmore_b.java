import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.System.exit;
 import static java.util.Arrays.fill;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.StringTokenizer;
 
 public class B {
 
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
 
    static final int MOD = 1000000007;
 
    static int add(int a, int b) {
        int res = a + b;
        if (res >= MOD) {
            res -= MOD;
        }
        return res;
    }
 
    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }
 
    static int inv(int a) {
        return BigInteger.valueOf(a).modInverse(BigInteger.valueOf(MOD)).intValue();
    }
 
    static void solve() throws Exception {
        int n = nextInt();
        int k = nextInt() - 1;
        int sums1[] = new int[n];
        int sums2[] = new int[n];
        int prev[] = new int[n];
        int ans[] = new int[n];
        fill(prev, 0, 2, 1);
        int den = 1;
        for (int i = 2; i < n; i++) {
 
            int div = i - 1;
            for (int j = 0, jj = n - 1 - i; j <= i; j++, jj++) {
                int cur = j == 0 || j == i ? den : add(den, den);
                cur = add(cur, sums2[jj]);
                cur = add(cur, sums1[j]);
                sums1[j] = mul(sums1[j], div);
                sums2[jj] = mul(sums2[jj], div);
                if (j < i) {
                    sums1[j] = add(sums1[j], prev[j]);
                }
                if (j > 0) {
                    sums2[jj] = add(sums2[jj], prev[j - 1]);
                }
                ans[j] = mul(cur, div);
            }
            den = mul(den, div);
 
 
 
 
            int t[] = prev;
            prev = ans;
            ans = t;
        }
        den = mul(den, n - 1);
 
        printCase();
        out.println(mul(prev[k], inv(den)));
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