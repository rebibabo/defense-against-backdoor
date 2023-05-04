import java.io.*;
 import java.util.*;
 
 public class LessMoney {
 
    FastScanner in;
    PrintWriter out;
 
    void solve() {
        int c = in.nextInt(), d = in.nextInt(), v = in.nextInt();
        int[] a = new int[d];
        for (int i = 0; i < d; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        long cur = 0;
 
        int ans = 0;
        for (int i = 0; i < d; i++) {
            while (cur + 1 <= v && cur + 1 < a[i]) {
                ans++;
                cur += c * (cur + 1);
            }
            
            cur += c * a[i];
        }
        while (cur + 1 <= v) {
            ans++;
            cur += c * (cur + 1);
        }
        out.println(ans);
    }
 
    void run() {
        try {
            in = new FastScanner("input.txt");
            out = new PrintWriter("output.txt");
            int T = in.nextInt();
            for (int i = 1; i <= T; i++) {
                long time = System.currentTimeMillis();
                out.printf("Case #%d: ", i);
                solve();
                System.err.println("Test #" + i + " done in "
                        + (System.currentTimeMillis() - time) + " ms");
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
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
        new LessMoney().run();
    }
 }
