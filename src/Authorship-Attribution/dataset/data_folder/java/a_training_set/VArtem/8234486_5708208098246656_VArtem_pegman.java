import java.io.*;
 import java.util.*;
 
 public class Pegman {
 
    FastScanner in;
    PrintWriter out;
 
    void solve() {
        int r = in.nextInt(), c = in.nextInt();
        int[] dx = new int[] { -1, 0, 1, 0 };
        int[] dy = new int[] { 0, 1, 0, -1 };
 
        String s = "^>v<";
 
        char[][] a = new char[r][c];
        for (int i = 0; i < r; i++) {
            a[i] = in.nextToken().toCharArray();
        }
 
        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (a[i][j] == '.') {
                    continue;
                }
                boolean[] good = new boolean[4];
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int ci = i + dx[d], cj = j + dy[d];
                    
                    while (0 <= ci && ci < r && 0 <= cj && cj < c && a[ci][cj] == '.') {
                        ci += dx[d];
                        cj += dy[d];
                    }
                    if (0 <= ci && ci < r && 0 <= cj && cj < c) {
                        good[d] = true;
                        cnt++;
                    }
                }
                
                int pos = s.indexOf(a[i][j]);
                if (good[pos]) {
                    continue;
                }
                if (cnt == 0) {
                    out.println("IMPOSSIBLE");
                    return;
                }
                ans++;
            }
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
        new Pegman().run();
    }
 }
