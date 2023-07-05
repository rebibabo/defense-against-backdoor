import java.io.*;
 import java.util.*;
 
 public class c {
 
    public static final String FILE_NAME = "c.in";
    public static final String OUTPUT_FILE_NAME = "c.out";
    public static PrintWriter out;
 
    public static void main(String[] Args) throws Exception {
        FastScanner sc = new FastScanner(new File(FILE_NAME));
        out = new PrintWriter(new BufferedWriter(new FileWriter(new File(
                OUTPUT_FILE_NAME))));
        int cc = 0, t = sc.nextInt();
 
        while (t-- > 0) {
            int n = sc.nextInt();
            long[][] pts = new long[2][n];
            for (int k = 0; k < n; k++) {
                pts[0][k] = sc.nextLong();
                pts[1][k] = sc.nextLong();
            }
 
            int[] ans = new int[n];
            for (int k = 0; k < n; k++) {
                ans[k] = n - 1;
            }
 
            Pair[] ps = new Pair[n];
            for (int k = 0; k < n; k++) {
                ps[k] = new Pair();
            }
            for (int k = 0; k < n; k++) {
                for (int j = k + 1; j < n; j++) {
                    long dx = pts[0][k] - pts[0][j];
                    long dy = pts[1][k] - pts[1][j];
                    for (int i = 0; i < n; i++) {
                        ps[i].val = (dy * pts[0][i]) - (dx * pts[1][i]);
                        ps[i].index = i;
                    }
 
                    Arrays.sort(ps);
                    int num = 0;
                    ans[ps[0].index] = 0;
                    for (int i = 1; i < n; i++) {
                        if (ps[i].val != ps[i - 1].val) {
                            num = i;
                        }
                        ans[ps[i].index] = (ans[ps[i].index] > num) ? num
                                : ans[ps[i].index];
                    }
                    num = 0;
                    ans[ps[n - 1].index] = 0;
                    for (int i = n - 2; i >= 0; i--) {
                        if (ps[i].val != ps[i + 1].val) {
                            num = n - i - 1;
                        }
                        ans[ps[i].index] = (ans[ps[i].index] > num) ? num
                                : ans[ps[i].index];
                    }
                }
            }
            out.printf("Case #%d:%n", ++cc);
            for (int k = 0; k < n; k++) {
                out.printf("%d%n", ans[k]);
            }
        }
 
        out.close();
    }
 
    public static class Pair implements Comparable<Pair> {
        int index;
        long val;
 
        Pair() {
        }
 
        public int compareTo(Pair o) {
            return Long.compare(val, o.val);
        }
    }
 
    public static class FastScanner {
        private StringTokenizer st;
        private BufferedReader br;
 
        FastScanner(File in) throws Exception {
            br = new BufferedReader(new FileReader(in));
            st = new StringTokenizer(br.readLine());
        }
 
        String next() throws Exception {
            if (st.hasMoreTokens()) {
                return st.nextToken();
            }
 
            st = new StringTokenizer(br.readLine());
            return next();
        }
 
        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }
 
        long nextLong() throws Exception {
            return Long.parseLong(next());
        }
    }
 }
