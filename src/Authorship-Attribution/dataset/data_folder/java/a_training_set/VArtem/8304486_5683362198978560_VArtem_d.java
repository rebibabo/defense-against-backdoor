import java.util.*;
 import java.io.*;
 
 public class D {
    void solve() {
        int r = in.nextInt(), c = in.nextInt(), n = in.nextInt(), d = in.nextInt();
        int[] rs = new int[n], cs = new int[n], bs = new int[n];
        for (int i = 0; i < n; i++) {
            rs[i] = in.nextInt() - 1;
            cs[i] = in.nextInt() - 1;
            bs[i] = in.nextInt();
        }
        boolean ok = true;
        long sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                long min = Long.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    min = Math.min(min, bs[k] + 1L * d * (Math.abs(i - rs[k]) + Math.abs(j - cs[k])));
                }
                for (int k = 0; k < n; k++) {
                    if (min < bs[k] - 1L * d * (Math.abs(i - rs[k]) + Math.abs(j - cs[k]))) {
                        ok = false;
                    }
                }
 
                sum += min;
            }
        }
        if (!ok) {
            out.println("IMPOSSIBLE");
        } else {
            out.println(sum % 1000000007);
        }
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
        new D().run();
    }
 }
