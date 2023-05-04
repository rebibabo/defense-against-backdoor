import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.Math.min;
 import static java.lang.System.exit;
 import static java.util.Arrays.fill;
 import static java.util.Arrays.sort;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.Comparator;
 import java.util.StringTokenizer;
 
 public class C {
 
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
 
    static int interval(int a, int b) {
        return (b - a + 24) % 24;
    }
 
    static int dsuGet(int dsu[], int i) {
        return dsu[i] == i ? i : (dsu[i] = dsuGet(dsu, dsu[i]));
    }
 
    static boolean dsuMerge(int dsu[], int i, int j) {
        i = dsuGet(dsu, i);
        j = dsuGet(dsu, j);
        if (i == j) {
            return false;
        }
        dsu[j] = i;
        return true;
    }
 
    static void solve() throws Exception {
        int n = nextInt();
        int incoming[] = new int[2 * n];
        fill(incoming, -1);
        int startTime[] = new int[2 * n];
        int endTime[] = new int[2 * n];
        int totalDuration = 0;
        for (int i = 0; i < 2 * n; i++) {
            int e = nextInt() - 1;
            startTime[i] = nextInt();
            int l = nextInt();
            totalDuration += l;
            endTime[i] = (startTime[i] + l) % 24;
            if (incoming[2 * e] < 0) {
                incoming[2 * e] = i;
            } else if (incoming[2 * e + 1] < 0) {
                incoming[2 * e + 1] = i;
            } else {
                throw new AssertionError();
            }
        }
        final int extraTime[] = new int[n];
        int dsu[] = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            dsu[i] = i;
        }
        for (int i = 1; i < n; i++) {
            int t1 = interval(endTime[incoming[2 * i]], startTime[2 * i]) + interval(endTime[incoming[2 * i + 1]], startTime[2 * i + 1]);
            int t2 = interval(endTime[incoming[2 * i]], startTime[2 * i + 1]) + interval(endTime[incoming[2 * i + 1]], startTime[2 * i]);
            if (t1 > t2) {
                int t = incoming[2 * i];
                incoming[2 * i] = incoming[2 * i + 1];
                incoming[2 * i + 1] = t;
                t = t1;
                t1 = t2;
                t2 = t;
            }
            totalDuration += t1;
            dsuMerge(dsu, 2 * i, incoming[2 * i]);
            dsuMerge(dsu, 2 * i + 1, incoming[2 * i + 1]);
            extraTime[i] = t2 - t1;
        }
        Integer byExtra[] = new Integer[n - 1];
        for (int i = 1; i < n; i++) {
            byExtra[i - 1] = i;
        }
        sort(byExtra, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return extraTime[o1] - extraTime[o2];
            }
        });
        for (int i: byExtra) {
            int v0 = dsuGet(dsu, 0), v1 = dsuGet(dsu, 1),
                v2i = dsuGet(dsu, 2 * i), v2i1 = dsuGet(dsu, 2 * i + 1);
            if ((v2i == v0 || v2i == v1) && (v2i1 == v0 || v2i1 == v1)) {
                continue;
            }
            if (dsuMerge(dsu, 2 * i, 2 * i + 1)) {
                totalDuration += extraTime[i];
            }
        }
        {
            int v0 = dsuGet(dsu, 0), v1 = dsuGet(dsu, 1),
                vi0 = dsuGet(dsu, incoming[0]), vi1 = dsuGet(dsu, incoming[1]);
            if (v0 == v1) {
                throw new AssertionError();
            }
            if (v0 == vi1 && v1 == vi0) {
                int t = incoming[0];
                incoming[0] = incoming[1];
                incoming[1] = t;
            }
        }
        int ans = totalDuration + min(
            startTime[0] + interval(endTime[incoming[0]], startTime[1]),
            startTime[1] + interval(endTime[incoming[1]], startTime[0]));
        for (int i: byExtra) {
            if (dsuMerge(dsu, 2 * i, 2 * i + 1)) {
                totalDuration += extraTime[i];
                ans = min(ans, totalDuration + min(
                    startTime[0] + interval(endTime[incoming[1]], startTime[1]),
                    startTime[1] + interval(endTime[incoming[0]], startTime[0])));
                break;
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