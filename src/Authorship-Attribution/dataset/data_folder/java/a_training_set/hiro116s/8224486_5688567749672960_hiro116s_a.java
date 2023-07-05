
 import java.io.*;
 import java.util.*;
 
 public class A {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
 
    int MAX = 1000105;
    int[] dp = new int[MAX];
    
    int reverse(int x) {
        String s = Integer.toString(x);
        int len = s.length();
        int res = 0;
        for (int i = 0, rate = 1; i < len; i++, rate *= 10) {
            res += (s.charAt(i) - '0') * rate;
        }
        return res;
    }
    
    public void run() {
        int T = in.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            int n = in.nextInt();
            
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[1] = 1;
            Queue<Integer> q = new LinkedList<Integer>();
            q.add(1);
            while (!q.isEmpty()) {
                int next = q.poll();
                if (dp[next + 1] > dp[next] + 1 && next + 1 <= n) {
                    dp[next + 1] = dp[next] + 1;
                    q.add(next + 1);
                }
                int r = reverse(next);
                if (dp[r] > dp[next] + 1 && r <= n) {
                    dp[r] = dp[next] + 1;
                    q.add(r);
                }
            }
            out.println("Case #" + caseN + ": " + dp[n]);
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
 
