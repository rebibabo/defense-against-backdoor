
 import java.io.*;
 import java.util.*;
 
 public class A {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
 
    int[] vx = {0, 1, 0, -1};
    int[] vy = {-1, 0, 1, 0};
    char[] arrow;
    
    int ans;
    int R, C;
    char[][] map;
    boolean[][] vis;
    
    boolean ok = false;
    void dfs(int y, int x, int prev) {
        if (vis[y][x]) return;
        vis[y][x] = true;
 
        int d = -1;
        for (int i = 0; i < vx.length; i++) {
            if (map[y][x] == arrow[i]) {
                d = i; 
                break;
            }
        }
 
        int nx = x, ny = y;
        while (true) {
            nx += vx[d];
            ny += vy[d];
            if (nx >= 0 && nx < C && ny >= 0 && ny < R) {
                if (map[ny][nx] == arrow[(d + 2) % 4]) {
                    vis[ny][nx] = true;
                    return;
                } else if (map[ny][nx] != '.') {
                    dfs(ny, nx, d);
                    return;
                }
            } else {
                ok = false;
                if (prev != -1) {
                    map[y][x] = arrow[(prev + 2) % 4];
                    ans++;
                    return;
                } else {
                    vis[y][x] = false;
                    break;
                }
            }
        }
    }
    
    public void run() {
        arrow = "^>v<".toCharArray();
        int T = in.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            int res = 0;
            R = in.nextInt();
            C = in.nextInt();
            
            vis = new boolean[R][C];
            map = new char[R][];
            for (int i = 0; i < R; i++) {
                map[i] = in.next().toCharArray();
            }
            
            ans = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == '.') continue;
                    dfs(i, j, -1);                  
                }
            }
            
            for (int i = 0; i < R; i++) Arrays.fill(vis[i], false);
            
            ok = true;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == '.') continue;
                    dfs(i, j, -1);
                }
            }
            
            out.println("Case #" + caseN + ": " + (!ok ? "IMPOSSIBLE" : ans));
            out.flush();
        }
        out.close();
    }
 
    public static void main(String[] args) {
        new A().run();
    }
 
    public void mapDebug(int[][] a) {
        System.out.println("--------map display---------");
 
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.printf("%3d ", a[i][j]);
            }
            System.out.println();
        }
 
        System.out.println("----------------------------");
        System.out.println();
    }
 
    public void debug(Object... obj) {
        System.out.println(Arrays.deepToString(obj));
    }
 
    class FastScanner {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
 
        public FastScanner(InputStream stream) {
            this.stream = stream;
            
 
        }
 
        int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }
 
        boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        boolean isEndline(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
        int[] nextIntArray(int n) {
            int[] array = new int[n];
            for (int i = 0; i < n; i++)
                array[i] = nextInt();
 
            return array;
        }
 
        int[][] nextIntMap(int n, int m) {
            int[][] map = new int[n][m];
            for (int i = 0; i < n; i++) {
                map[i] = in.nextIntArray(m);
            }
            return map;
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        long[] nextLongArray(int n) {
            long[] array = new long[n];
            for (int i = 0; i < n; i++)
                array[i] = nextLong();
 
            return array;
        }
 
        long[][] nextLongMap(int n, int m) {
            long[][] map = new long[n][m];
            for (int i = 0; i < n; i++) {
                map[i] = in.nextLongArray(m);
            }
            return map;
        }
 
        double nextDouble() {
            return Double.parseDouble(next());
        }
 
        double[] nextDoubleArray(int n) {
            double[] array = new double[n];
            for (int i = 0; i < n; i++)
                array[i] = nextDouble();
 
            return array;
        }
 
        double[][] nextDoubleMap(int n, int m) {
            double[][] map = new double[n][m];
            for (int i = 0; i < n; i++) {
                map[i] = in.nextDoubleArray(m);
            }
            return map;
        }
 
        String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }
 
        String[] nextStringArray(int n) {
            String[] array = new String[n];
            for (int i = 0; i < n; i++)
                array[i] = next();
 
            return array;
        }
 
        String nextLine() {
            int c = read();
            while (isEndline(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndline(c));
            return res.toString();
        }
    }
 }
 
