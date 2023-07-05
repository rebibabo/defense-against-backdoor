import java.io.*;
 import java.util.*;
 
 public class StandingOvation {
 
    FastScanner in;
    PrintWriter out;
 
    void solve() {
        int n = in.nextInt();
        String s = in.nextToken();
        int ans = 0, sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int count = s.charAt(i) - '0';
            if (count == 0) {
                continue;
            }
            while (sum < i) {
                ans++;
                sum++;
            }
            sum += count;
        }
        out.println(ans);
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
        new StandingOvation().run();
    }
 }
