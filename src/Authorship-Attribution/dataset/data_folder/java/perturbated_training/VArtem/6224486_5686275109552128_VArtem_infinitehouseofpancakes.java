import java.io.*;
 import java.util.*;
 
 public class InfiniteHouseOfPancakes {
 
    FastScanner in;
    PrintWriter out;
 
    final int MAX = 1000;
 
    void solve() {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
 
        int result = MAX;
        for (int max = MAX; max >= 1; max--) {
            int add = 0;
            for (int val : a) {
                add += (val + max - 1) / max - 1;
            }
            result = Math.min(result, max + add);
        }
        out.println(result);
    }
 
    void run() {
        try {
            in = new FastScanner("input.txt");
            out = new PrintWriter("output.txt");
            int T = in.nextInt();
            for (int test = 1; test <= T; test++) {
                out.print(String.format("Case #%d: ", test));
                solve();
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
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
 
        String nextToken() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() {
            return Integer.parseInt(nextToken());
        }
 
        long nextLong() {
            return Long.parseLong(nextToken());
        }
 
        double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
 
    public static void main(String[] args) {
        new InfiniteHouseOfPancakes().run();
    }
 }
