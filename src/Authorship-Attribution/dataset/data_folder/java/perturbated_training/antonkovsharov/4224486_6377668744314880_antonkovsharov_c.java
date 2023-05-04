import java.util.*;
 import java.io.*;
 
 public class C {
    FastScanner in;
    PrintWriter out;
 
    class Point {
        long x, y;
 
        public Point(long x, long y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
    
    long vecMul(Point p0, Point p1, Point p2) {
        long x1 = p1.x - p0.x;
        long y1 = p1.y - p0.y;
        long x2 = p2.x - p0.x;
        long y2 = p2.y - p0.y;
        
        return x1 * y2 - x2 * y1;
    }
    
    public void solve() throws IOException {
        int n = in.nextInt();
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++)
            pts[i] = new Point(in.nextLong(), in.nextLong());
        int[][] countToLeft = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    for (int k = 0; k < n; k++) {
                        if (vecMul(pts[i], pts[j], pts[k]) > 0)
                            countToLeft[i][j]++;
                    }
                }
            }
        }
        out.println();
        for (int i = 0; i < n; i++) {
            int ans = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    for (int k = 0; k < n; k++) {
                        if (k != j && k != i) {
                            if (vecMul(pts[j], pts[i], pts[k]) > 0)
                                continue;
                            
                            int cur = 0;
                            for (int jj = 0; jj < n; jj++)
                                if (vecMul(pts[j], pts[i], pts[jj]) > 0 || vecMul(pts[i], pts[k], pts[jj]) > 0)
                                    cur++;
                            ans = Math.min(ans, cur);
                        }
                    }
                }
            }
            if (ans == Integer.MAX_VALUE)
                ans = 0;
            out.println(ans);
        }
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            out = new PrintWriter(new File("a.out"));
 
            int tests = in.nextInt();
            for (int i = 0; i < tests; i++) {
                out.print("Case #" + (i + 1) + ": ");
                solve();
            }
 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
 
    public static void main(String[] arg) {
        new C().run();
    }
 }