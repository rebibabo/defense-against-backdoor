
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.InputMismatchException;
 
 public class C {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);
 
    class Point implements Comparable<Point> {
        int x;
        int y;
 
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
 
        public int compareTo(Point p) {
            return y - p.y;
        }
 
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
    
    double getDist(double dx, double dy) {
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    int INF = 100000000;
    double EPS = 1e-15;
    Point[] getConvexHull(Point[] pa) {
        if (pa.length <= 3) return pa;
        Arrays.sort(pa);
        ArrayList<Point> res = new ArrayList<Point>();
        
 
         Point first = pa[0];
         res.add(first);
         long vx = 1;
         long vy = 0;
         Point oldpoint = first;
         main : 
         for(;;){
             Point minpoint = null;
             long minvecx = 0;
             long minvecy = 0;
             double maxcos = -10;
             for(Point p : pa){
                 if(p.x == oldpoint.x && p.y == oldpoint.y) continue;
                 long ox = p.x - oldpoint.x;
                 long oy = p.y - oldpoint.y;
                 double cos = (ox * vx + oy * vy) / 
                         (Math.sqrt(ox * ox + oy * oy) * Math.sqrt(vx * vx + vy * vy));
                 if(cos > maxcos || 
                    (Math.abs(cos - maxcos) < EPS && getDist(ox, oy) < getDist(minvecx, minvecy))) {
                     maxcos = cos;
                     minvecx = ox;
                     minvecy = oy;
                     minpoint = p;
                 }
             }
             
 
             for (Point pp : res) {
                if (minpoint.x == pp.x && minpoint.y == pp.y) break main;
             }
             vx = minvecx;
             vy = minvecy;
             oldpoint = minpoint;
             res.add(minpoint);
         }
         
         return res.toArray(new Point[0]);
    } 
    
    public void run() {
        int T = in.nextInt();
        for (int caseN = 1; caseN <= T; caseN++) {
            int n = in.nextInt();
            int[] x = new int[n];
            int[] y = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = in.nextInt();
                y[i] = in.nextInt();
            }
            
            out.println("Case #" + caseN + ":");
 
 
            
            for (int next = 0; next < n; next++) {
                int min = Integer.MAX_VALUE;
                for (int bit = 1; bit < (1 << n); bit++) {
                    if ((bit & (1 << next)) == 0) continue;
                    Point[] np = new Point[n];
                    int size = 0;
                    for (int i = 0; i < n; i++) {
                        if ((bit & (1 << i)) == 0) continue;
                        np[size++] = new Point(x[i], y[i]);
                    }
                    
                    np = Arrays.copyOf(np, size);
                    Point[] ch = getConvexHull(np);
                    for (Point p : ch) {
                        if (p.x == x[next] && p.y == y[next]) {
                            if (min > n - Integer.bitCount(bit)) {
                                min = Math.min(min, n - Integer.bitCount(bit));
                            }
                            break;
                        }
                    }
                }
                out.println(min);
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
 
