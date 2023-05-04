import java.io.*;
 import java.util.*;
 
 public class Dijkstra {
 
    FastScanner in;
    PrintWriter out;
 
    final static int[][] mulTable = new int[][] { { 0, 1, 2, 3 },
            { 1, 0 + 4, 3, 2 + 4 }, { 2, 3 + 4, 0 + 4, 1 },
            { 3, 2, 1 + 4, 0 + 4 } };
    final static int states = 3 * 2 * 4;
 
    void solve() {
        int l = in.nextInt();
        long x = in.nextLong();
        String s = in.nextToken();
 
        boolean[][] matrix = id(states);
 
        for (int i = 0; i < s.length(); i++) {
            matrix = multiply(matrix, getMatrix(s.charAt(i)));
        }
 
        boolean[][] result = id(states);
        while (x > 0) {
            if (x % 2 == 1) {
                result = multiply(result, matrix);
            }
            matrix = multiply(matrix, matrix);
            x >>>= 1;
        }
 
        int endState = 2 * 2 * 4 + 3;
        if (result[0][endState]) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
 
    private boolean[][] getMatrix(char c) {
        int id = "1ijk".indexOf(c);
        boolean[][] mat = new boolean[states][states];
 
        for (int blocks = 0; blocks < 3; blocks++) {
            for (int sign = 0; sign < 2; sign++) {
                for (int value = 0; value < 4; value++) {
                    int newSign = (sign << 2);
                    int newValue = newSign ^ mulTable[value][id];
                    
                    int from = blocks * 2 * 4 + sign * 4 + value;
                    mat[from][blocks * 2 * 4 + newValue] = true;
                    if (blocks < 2 && newValue == (blocks + 1)) {
                        mat[from][(blocks + 1) * 2 * 4] = true;
                    }
                }
            }
        }
        return mat;
    }
 
    boolean[][] id(int n) {
        boolean[][] a = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            a[i][i] = true;
        }
        return a;
    }
 
    boolean[][] multiply(boolean[][] a, boolean[][] b) {
        int n = a.length;
        boolean[][] c = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] |= a[i][k] & b[k][j];
                }
            }
        }
        return c;
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
        new Dijkstra().run();
    }
 }
