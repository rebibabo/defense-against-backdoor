import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.System.exit;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class A {
    
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
    
    static void solve() throws Exception {
        int n = nextInt();
        if (n == 0) {
            printCase();
            out.println("INSOMNIA");
            return;
        }
        boolean d[] = new boolean[10];
        val: for (long val = n;; val += n) {
            for (long cur = val; cur > 0; cur /= 10) {
                d[(int) (cur % 10)] = true;
            }
            for (int i = 0; i < 10; i++) {
                if (!d[i]) {
                    continue val;
                }
            }
            printCase();
            out.println(val);
            return;
        }
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