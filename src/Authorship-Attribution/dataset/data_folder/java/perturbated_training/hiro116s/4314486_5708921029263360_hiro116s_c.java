
 import java.io.*;
 import java.util.*;
 
 public class C {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
 
    int J, P, S, K;
    int[] JP, JS, PS;
    
    int getIdx(int a, int b, int A) {
        return b * A + a;
    }
    
    String findNext(Set<String> used) {
        String res = null;
        int currentMax = Integer.MAX_VALUE;
        int currentSum = 0;
        for (int j = 0; j < J; j++) {
            for (int p = 0; p < P; p++) {
                for (int s = 0; s < S; s++) {
                    String next = "" + j + p + s;
                    if (used.contains(next)) continue;
                    
                    int maxComb = 0;
                    int sumComb = 0;
                    int js = JS[getIdx(j, s, J)] + 1;
                    int jp = JP[getIdx(j, p, J)] + 1;
                    int ps = PS[getIdx(p, s, P)] + 1;
                    
                    maxComb = Math.max(maxComb, js);
                    maxComb = Math.max(maxComb, jp);
                    maxComb = Math.max(maxComb, ps);
                    sumComb = js + jp + ps;
                    
                    if (maxComb > K) continue;
                    if (maxComb < currentMax || (maxComb == currentMax && sumComb < currentSum)) {
                        res = next;
                        currentMax = maxComb;
                        currentSum = sumComb;
                    }
                }
            }
        }
        return res;
    }
    
    void update(String next) {
        int j = next.charAt(0) - '0';
        int p = next.charAt(1) - '0';
        int s = next.charAt(2) - '0';
        JS[getIdx(j, s, J)]++;
        JP[getIdx(j, p, J)]++;
        PS[getIdx(p, s, P)]++;
        assert JS[getIdx(j, s, J)] <= K;
        assert JP[getIdx(j, p, J)] <= K;
        assert PS[getIdx(p, s, P)] <= K;
    }
    
    String convert(String next) {
        int j = next.charAt(0) - '0';
        int p = next.charAt(1) - '0';
        int s = next.charAt(2) - '0';
        return (j + 1) + " " + (p + 1) + " " + (s + 1);
    }
    
    int calcS(int k, int j, int p) {
        return (j * (P - 1) + p + k + S * 100) % S;
    }
    
    public void run() {
        int T = in.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            J = in.nextInt(); P = in.nextInt(); S = in.nextInt();
            K = in.nextInt();
            
            JS = new int[J*S];
            JP = new int[J*P];
            PS = new int[S*P];
            
            Set<String> hash = new HashSet<String>();
            List<String> ans = new ArrayList<>();
            for (int k = 0; k < K; k++) {
                for (int j = 0; j < J; j++) {
                    for (int p = 0; p < P; p++) {
                        int s = calcS(k, j, p);
                        String next = "" + j + p + s; 
                        if (!hash.contains(next)) {
                            ans.add(next);
                            hash.add(next);
                            update(next);
                        }
                    }
                }
            }
            
            out.println("Case #" + caseN + ": " + ans.size());
            for (String s : ans) {
                out.println(convert(s));
            }
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
 
