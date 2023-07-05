import java.util.*;
 import java.io.*;
 
 public class C {
 
    void solve() {
        int n = in.nextInt(), q = in.nextInt();
        int[] dist = new int[n], speed = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = in.nextInt();
            speed[i] = in.nextInt();
        }
        
        long[][] a = new long[n][n];
        long INF = Long.MAX_VALUE / 3;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
                if (a[i][j] == -1) {
                    a[i][j] = INF;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                }
            }
        }
        
        double[][] d = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == INF || a[i][j] > dist[i]) {
                    d[i][j] = INF;
                } else {
                    d[i][j] = a[i][j] * 1.0 / speed[i];
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
        for (int i = 0; i < q; i++) {
            int u = in.nextInt() - 1, v = in.nextInt() - 1;
            out.print(d[u][v] + " ");
        }
        out.println();
    }
 
    FastScanner in;
    PrintWriter out;
 
    void run() {
        in = new FastScanner("input.txt");
        try {
            out = new PrintWriter("output.txt");
        } catch (FileNotFoundException e) {
        }
        int tests = in.nextInt();
        for (int i = 0; i < tests; i++) {
            long startTime = System.currentTimeMillis();
            out.printf("Case #%d: ", i + 1);
            solve();
            System.err.printf("Test #%d solved in %d ms\n", i + 1, System.currentTimeMillis() - startTime);
        }
        out.close();
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
 
        public FastScanner(String s) {
            try {
                br = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                
                e.printStackTrace();
            }
        }
 
        public String nextToken() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                }
            }
            return st.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(nextToken());
        }
 
        public long nextLong() {
            return Long.parseLong(nextToken());
        }
 
        public double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
 
    public static void main(String[] args) {
        new C().run();
    }
 }
