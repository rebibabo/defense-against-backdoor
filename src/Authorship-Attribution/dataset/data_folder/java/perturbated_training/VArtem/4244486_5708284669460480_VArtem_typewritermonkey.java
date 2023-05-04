import java.io.*;
 import java.util.*;
 
 public class TypewriterMonkey {
 
    FastScanner in;
    PrintWriter out;
 
    void solve() {
        in.nextInt();
        in.nextInt();
        int s = in.nextInt();
 
        String keys = in.nextToken();
        String goal = in.nextToken();
        int[] pref = new int[goal.length()];
        
        for (int i = 1; i < goal.length(); i++) {
            int pr = pref[i - 1];
            while (pr > 0 && goal.charAt(pr) != goal.charAt(i)) {
                pr = pref[pr - 1];
            }
            if (goal.charAt(pr) == goal.charAt(i)) {
                pr++;
            }
            pref[i] = pr;
        }
        boolean canType = true;
        for (char c : goal.toCharArray()) {
            boolean can = false;
            for (char d : keys.toCharArray()) {
                if (c == d) {
                    can = true;
                }
            }
            if (!can) {
                canType = false;
            }
        }
        if (!canType) {
            out.println(0.0);
            return;
        }
        int max = 1 + (s - goal.length()) / (goal.length() - pref[goal.length() - 1]);
        
        double[][][] dp = new double[s + 1][goal.length()][s + 1];
        dp[0][0][0] = 1;
        for (int i = 0; i < s; i++) {
            for (int pr = 0; pr < goal.length(); pr++) {
                for (int occ = 0; occ <= s; occ++) {
                    double val = dp[i][pr][occ];
                    if (val == 0) {
                        continue;
                    }
                    val /= keys.length();
                    for (char c : keys.toCharArray()) {
                        int newLen = pr;
                        while (newLen > 0 && goal.charAt(newLen) != c) {
                            newLen = pref[newLen - 1];
                        }
                        if (goal.charAt(newLen) == c) {
                            newLen++;
                        }
                        
                        if (newLen == goal.length()) {
                            dp[i + 1][pref[newLen -  1]][occ + 1] += val;
                        } else {
                            dp[i + 1][newLen][occ] += val;
                        }
                    }
                }
            }
        }
        
        double exp = 0;
        for (int i = 0; i < goal.length(); i++) {
            for (int j = 0; j <= s; j++) {
                exp += j * dp[s][i][j];
            }
        }
        out.println(max - exp);
        
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
        new TypewriterMonkey().run();
    }
 }
