
 import java.io.*;
 import java.math.BigInteger;
 import java.util.*;
 
 public class C {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
 
    BigInteger convert(long x, int base) {
        String s = Long.toBinaryString(x);
        BigInteger bbase = new BigInteger(String.valueOf(base));
        BigInteger res = BigInteger.ZERO;
        for (int i = 0, p = s.length() - 1; i < s.length(); i++, p--) {
            if (s.charAt(i) != '0') {
                res = res.add(bbase.pow(p));
            }
        }
        return res;
    }
    
    int[] calc(long x) {
        final int MAX = 1000;
        int[] res = new int[11];
        for (int base = 2; base <= 10; base++) {
            BigInteger b = convert(x, base);
            for (int i = 2; i <= MAX; i++) {
                BigInteger div = new BigInteger(Integer.toString(i));
                if (b.mod(div).intValue() == 0) {
                    res[base] = i;
                    break;
                } else if (i == MAX) {
                    return null;
                }
            }
        }
        return res;
    }
    
    public void run() {
        int T = in.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            int N = in.nextInt();
            int J = in.nextInt();
            
            ArrayList<String> res = new ArrayList<String>();
            
            for (long i = (1L << (N-1)) + 1; i < (1L << N); i += 2) {
                int[] divs = calc(i);
                if (divs == null) continue;
                
                String next = Long.toBinaryString(i) + " ";
                for (int j = 2; j <= 10; j++) {
                    next += divs[j];
                    if (j != 10) next += " ";
                }
                res.add(next);
                if (res.size() == J) break;
            }
            
            if (res.size() != J) {
                throw new RuntimeException();
            }
            out.println("Case #" + caseN + ": ");
            for (String s : res)
                out.println(s);
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
 
