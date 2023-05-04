
 import java.io.*;
 import java.util.*;
 
 public class C {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
 
    public void run() {
        int T = in.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            int res = 1000000;
            
            int N = in.nextInt();
            String[] s = new String[N];
            for (int i = 0; i < N; i++) 
                s[i] = in.nextLine();
            
            HashSet<String> baseEng = new HashSet<String>();
            HashSet<String> baseFre = new HashSet<String>();
            HashSet<String> baseBoth = new HashSet<String>();
            
            String[][] words = new String[N][];
            for (int i = 0; i < N; i++) {
                words[i] = s[i].split(" ");
            }
            
            for (int i = 0; i < words[0].length; i++) {
                baseEng.add(words[0][i]);
            }
            
            for (int i = 0; i < words[1].length; i++) {
                baseFre.add(words[1][i]);
                if (baseEng.contains(words[1][i])) {
                    baseBoth.add(words[1][i]);
                }
            }
            HashSet<String> eng = new HashSet<String>();
            HashSet<String> fre = new HashSet<String>();
            HashSet<String> both = new HashSet<String>();
            
            for (int i = 0; i < (1 << N); i++) {
                if ((i & 1) == 1 && (i & 2) == 2) {
                    eng.clear();
                    fre.clear();
                    both.clear();
                    both.addAll(baseBoth);
                    
                    for (int idx = 2; idx < N; idx++) {
                        for (int j = 0; j < words[idx].length; j++) {
                            if (((i >> idx) & 1) != 0) {
                                eng.add(words[idx][j]);
                                if (fre.contains(words[idx][j]) || baseFre.contains(words[idx][j])) {
                                    both.add(words[idx][j]);
                                }
                            } else {
                                fre.add(words[idx][j]);
                                if (eng.contains(words[idx][j]) || baseEng.contains(words[idx][j])) {
                                    both.add(words[idx][j]);
                                }
                            }
                        } 
                    }
                    
                    res = Math.min(res, both.size());
                }
            }
            
            out.println("Case #" + caseN + ": " + res);
            out.flush();
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
 
