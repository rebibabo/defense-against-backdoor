import java.util.*;
 import java.io.*;
 
 public class A {
    FastScanner in;
    PrintWriter out;
 
    int[] di = { -1, 0, 1, 0 };
    int[] dj = { 0, -1, 0, 1 };
 
    int toDir(char c) {
        if (c == '.')
            return -1;
        if (c == '^')
            return 0;
        if (c == '<')
            return 1;
        if (c == 'v')
            return 2;
        if (c == '>')
            return 3;
        throw new AssertionError();
    }
 
    public void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt();
        char[][] c = new char[n][m];
        for (int i = 0; i < n; i++) {
            c[i] = in.next().toCharArray();
        }
        boolean[][][] is = new boolean[n][m][di.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int t = 0; t < di.length; t++) {
                    int ci = i, cj = j;
                    ci += di[t];
                    cj += dj[t];
                    while (ci >= 0 && cj >= 0 && ci < n && cj < m) {
                        if (c[ci][cj] != '.') {
                            is[i][j][t] = true;
                            break;
                        }
                        ci += di[t];
                        cj += dj[t];
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] != '.') {
                    boolean ok = false;
                    int curT = toDir(c[i][j]);
                    if (is[i][j][curT]) {
                        ok = true;
                    } else {
                        for (int t = 0; t < 4; t++) {
                            if (is[i][j][t]) {
                                ans++;
                                ok = true;
                                break;
                            }
                        }
                    }
                    if (!ok) {
                        out.println("IMPOSSIBLE");
                        return;
                    }
                }
            }
        }
        out.println(ans);
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            out = new PrintWriter(new File("a.out"));
 
            int tests = in.nextInt();
            for (int i = 0; i < tests; i++) {
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
        StringTokenizer st;
 
        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
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
        new A().run();
    }
 }