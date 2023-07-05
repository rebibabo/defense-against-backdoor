
 import java.io.*;
 import java.util.*;
 
 public class B {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
 
    void copyH(int[][] C, int[] a, int x) {
        for (int i = 0; i < C.length; i++) {
            C[i][x] = a[i];
        }
    }
    
    void copyV(int[][] C, int[] a, int y) {
        for (int i = 0; i < C.length; i++) {
            C[y][i] = a[i];
        }
    }
    
    boolean okV(int[][] C, int[] a, int curH) {
        for (int i = 0; i < a.length; i++) {
            if (C[curH][i] != 0 && C[curH][i] != a[i]) 
                return false;
        }
        return true;
    }
    
    boolean okH(int[][] C, int[] a, int curW) {
        for (int i = 0; i < a.length; i++) {
            if (C[i][curW] != 0 && C[i][curW] != a[i])              
                return false;
        }
        return true;
    }
    
    int MAX = 2520;
    public void run() {
        int T = in.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            String res = "";
            int n = in.nextInt();
            int[][] A = in.nextIntMap(2 * n - 1, n);
            Arrays.sort(A, (a, b) -> (a[0] - b[0]));
            int[][] C = new int[n][n];
            copyH(C, A[0], 0);
            copyV(C, A[1], 0);
            boolean[] usedH = new boolean[n];
            boolean[] usedW = new boolean[n];
            
            int curH = 1, curW = 1;
            for (int i = 2; i < 2 * n - 1; i++) {
                if (curH < n && okV(C, A[i], curH)) {
                    usedW[curH] = true;
                    copyV(C, A[i], curH++);
                } else if (curW < n && okH(C, A[i], curW)) {
                    usedH[curW] = true;
                    copyH(C, A[i], curW++);
                } else {
                    
                }
            }
            for (int i = 1 ;i < n; i++) {
                if (!usedW[i]) {
                    for (int j = 0; j < n; j++) 
                        res += C[i][j] + " ";
                }
            }
            for (int j = 1; j < n; j++) {
                if (!usedH[j]) {
                    for (int i = 0; i < n; i++) {
                        res += C[i][j] + " ";
                    }
                }
            }
            
            out.println("Case #" + caseN + ": " + res);
        }
        out.close();
    }
 
    public static void main(String[] args) {
        new B().run();
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
 
