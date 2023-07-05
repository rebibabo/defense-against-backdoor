
 import java.io.*;
 import java.util.*;
 
 public class B {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
 
    long two(int x) {
        long res = 1;
        while (x > 0) {
            res *= 2;
            x--;
        }
        return res;
    }
    
    public void run() {
        int T = in.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            int B = in.nextInt();
            long M = in.nextLong();
            if (two(B-2) < M) {
                out.println("Case #" + caseN + ": IMPOSSIBLE");
                continue;
            }           
            
            int[][] res = new int[B][B];
            for (int i = 0; i < B - 1; i++) {
                for (int j = i + 1; j < B - 1; j++) {
                    res[i][j] = 1;
                }
            }
            
            long rest = M;
            for (int from = B - 2; from >= 0; from--) {
                long val = (from == 0) ? 1 : two(from-1);
                if (rest >= val) {
                    rest -= val;
                    res[from][B-1] = 1;
                }
            }
            out.println("Case #" + caseN + ": POSSIBLE");
            for (int i = 0; i < B; i++) {
                for (int j = 0; j < B; j++) {
                    out.print(res[i][j]);
                }
                out.println();
            }
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
 
