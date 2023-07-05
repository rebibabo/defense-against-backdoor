
 import java.io.*;
 import java.util.*;
 
 public class C {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
 
    int[][] table = {
            {1, 2, 3, 4},
            {2, -1, 4, -3},
            {3, -4, -1, 2},
            {4, 3, -2, -1}
    };
    
    int calc(int a, int b) {
        int pm = a > 0 ? 1 : -1;
        a = Math.abs(a);
        return pm * table[a-1][b-1];
    }
    
    int sub(int a, int b) {
 
        int pm = b > 0 ? 1 : -1;
        b = Math.abs(b);
        for (int i = 0; i < 4; i++) {
            if (table[b-1][i] == pm * a) {
                return i + 1;
            }
        }
        
        return -1;
    }
    
    public void run() {
        int T = in.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            int L = in.nextInt(), X = in.nextInt();
            String _s = in.next();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < X; i++) {
                sb.append(_s);
            }
            String s = sb.toString();
            
            int[] a = new int[L*X];
            for (int i = 0; i < L * X; i++) {
                char c = s.charAt(i);
                if (c == 'i') a[i] = 2;
                else if (c == 'j') a[i] = 3;
                else a[i] = 4;
            }
            
            int[] sum = new int[L*X+1];
            sum[0] = 1;
            for (int i = 0; i < L * X; i++) {
                sum[i+1] = calc(sum[i], a[i]);
            }
            
            boolean flag = false;
            loop : 
            for (int left = 1; left <= L * X - 2; left++) {
                for (int right = left + 1; right <= L * X - 1; right++) {
 
                    int i = sub(sum[left], sum[0]);
                    int j = sub(sum[right], sum[left]);
                    int k = sub(sum[L*X], sum[right]);
                    if (i == 2 && j == 3 && k == 4) {
                        flag = true;
                        break loop;
                    }
                }
            }
            
            System.out.println("Case #" + caseN + ": " + (flag ? "YES" : "NO"));
        }
        out.close();
    }
 
    public static void main(String[] args) {
        new C().run();
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
 
