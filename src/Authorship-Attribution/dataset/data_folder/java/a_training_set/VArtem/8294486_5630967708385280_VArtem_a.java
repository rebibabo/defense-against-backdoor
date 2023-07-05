import java.util.*;
 import java.io.*;
 
 public class A {
 
    void solve() {
        int d = in.nextInt(), n = in.nextInt();
        int[] pos = new int[n], speed = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = in.nextInt();
            speed[i] = in.nextInt();
        }
        
        double max = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            double left = 0, right = 2000000000;
            left = Math.max(speed[i], 1.0 * speed[i] * d / (d - pos[i]));
            max = Math.min(max, left);
        }
        out.println(max);
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
        new A().run();
    }
 }
