import java.io.*;
 import java.util.*;
 
 public class KiddiePool {
 
    FastScanner in;
    PrintWriter out;
 
    long nextDouble() {
        String token = in.nextToken();
        return Long.parseLong(token.replace(".", "")); 
    }
 
    void solve() {
        int n = in.nextInt();
        long v = nextDouble();
        long x = nextDouble();
 
        long[] r = new long[n], c = new long[n];
        long mn = Long.MAX_VALUE, mx = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            r[i] = nextDouble();
            c[i] = nextDouble();
            mx = Math.max(mx, c[i]);
            mn = Math.min(mn, c[i]);
        }
        Pair[] a = new Pair[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Pair(r[i], c[i]);
        }
        Arrays.sort(a);
        if (mn > x || x > mx) {
            out.println("IMPOSSIBLE");
            return;
        }
        if (mx == mn) {
            double sum = 0;
            for (int i = 0; i < n; i++) {
                sum += a[i].x;
            }
            double ans = 1.0 * v / sum;
            out.println(ans);
            return;
        }
        double ans = Double.MAX_VALUE;
 
        long curX = 0, curY = 0;
        for (int i = 0; i < n; i++) {
            long newX = curX + a[i].x;
            long newY = curY + a[i].x * a[i].y;
 
            if (i > 0 && between(curX, curY, x, newX, newY)) {
                Line line1 = new Line(curX, curY, newX, newY);
                Line line2 = new Line(x, -1, 0);
                double[] result = line1.intersect(line2);
                ans = Math.min(ans, 1.0 / result[0]);
            }
            curX = newX;
            curY = newY;
        }
        for (int i = 0; i < n; i++) {
            long newX = curX - a[i].x;
            long newY = curY - a[i].x * a[i].y;
 
            if (i < n - 1 && between(curX, curY, x, newX, newY)) {
                Line line1 = new Line(curX, curY, newX, newY);
                Line line2 = new Line(x, -1, 0);
                double[] result = line1.intersect(line2);
                ans = Math.min(ans, 1.0 / result[0]);
            }
            curX = newX;
            curY = newY;
        }
 
        out.println(ans * v);
    }
 
    class Line {
        double a, b, c;
 
        public Line(double x1, double y1, double x2, double y2) {
            a = y2 - y1;
            b = x1 - x2;
            c = -(a * x1 + b * y1);
        }
 
        public Line(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
 
        double[] intersect(Line other) {
            double d = a * other.b - b * other.a;
            double dx = b * other.c - c * other.b;
            double dy = c * other.a - a * other.c;
            return new double[] { dx / d, dy / d };
        }
    }
 
    boolean between(long x1, long y1, long y, long x2, long y2) {
        return vectMul(x1, y1, 1, y) && vectMul(1, y, x2, y2);
    }
 
    boolean vectMul(double x1, double y1, double x2, double y2) {
        return x1 * y2 >= x2 * y1;
    }
 
    class Pair implements Comparable<Pair> {
        long x, y;
 
        public Pair(long x, long y) {
            super();
            this.x = x;
            this.y = y;
        }
 
        @Override
        public int compareTo(Pair o) {
            int t = Long.compare(y, o.y);
            if (t == 0) {
                return -Long.compare(x, o.x);
            }
            return t;
        }
 
    }
 
    void run() {
        try {
            in = new FastScanner("input.txt");
            out = new PrintWriter("output.txt");
            int T = in.nextInt();
            for (int i = 1; i <= T; i++) {
                long time = System.currentTimeMillis();
                out.printf("Case #%d: ", i);
                solve();
                System.err.println("Test #" + i + " done in "
                        + (System.currentTimeMillis() - time) + " ms");
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner(String s) {
            try {
                br = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                
                e.printStackTrace();
            }
        }
 
        String nextToken() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() {
            return Integer.parseInt(nextToken());
        }
 
        long nextLong() {
            return Long.parseLong(nextToken());
        }
 
        double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
 
    public static void main(String[] args) {
        new KiddiePool().run();
    }
 }
