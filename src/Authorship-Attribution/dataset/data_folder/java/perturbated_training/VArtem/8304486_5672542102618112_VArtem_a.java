import java.util.*;
 import java.io.*;
 
 public class A {
 
    Set<String> count;
    
    void go(String s) {
        if (count.contains(s)) {
            return;
        }
        count.add(s);
        int sum = 0;
        int[] cnt = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cnt[i] = s.charAt(i) - '0';
            sum += cnt[i];
        }
        generate(cnt, sum, "");
    }
    
    void generate(int[] cnt, int sum, String cur) {
        int pos = cur.length();
        if (cnt.length - pos < sum) {
            return;
        }
        if (pos == cnt.length) {
            go(cur);
            return;
        }
        generate(cnt, sum, cur + "0");
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] > 0) {
                cnt[i]--;
                generate(cnt, sum - 1, cur + (char) ('0' + i + 1)); 
                cnt[i]++;
            }
        }
    }
    
    void solve() {
        count = new HashSet<>();
        go(in.nextToken());
        out.println(count.size());
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
