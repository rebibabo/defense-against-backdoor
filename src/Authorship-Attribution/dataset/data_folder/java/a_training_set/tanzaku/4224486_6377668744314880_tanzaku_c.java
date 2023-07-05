package R1A;
 
 import java.io.*;
 import java.math.*;
 import java.util.*;
 import java.util.concurrent.atomic.AtomicInteger;
 
 import static java.util.Arrays.*;
 
 public class C {
    
    static
    class Config {
        public static final boolean DISP_PROGRESS = true;
        public static final boolean DISP_ELASPED_TIME = true;
        public static final boolean STDIO = false;
 
        public static final String BASE_DIR = "data\\R1A\\C";
        public static final String IN_FILE = "C-small-attempt1.in";
        public static final String OUT_FILE = IN_FILE + "_out.txt";
        public static final int MAX_THREAD = 3;
    }
 
 
    
    static
    class ParallelSolver implements Runnable {
        private static final int mod = (int)1e9+7;
        
        int N;
        int[] x, y;
        Point[] pt;
        public void readInput(final IOFast io) throws IOException {
            N = io.nextInt();
            x = new int[N];
            y = new int[N];
            pt = new Point[N];
            for(int i = 0; i < N; i++) {
                x[i] = io.nextInt();
                y[i] = io.nextInt();
                pt[i] = new Point(x[i], y[i]);
            }
        }
        
        @Override
        public void run() {
            out.println();
            
            for(int i = 0; i < N; i++) {
                out.println(naive(pt, i));
            }
            if(true) return;
            
            List<Point> cs = GenConvexHull(Arrays.asList(pt.clone()));
            TreeSet<Point> set = new TreeSet<>(cs);
            
 
 
            
            for(int i = 0; i < N; i++) {
 
                if(set.contains(pt[i])) {
                    out.println(0);
                    continue;
                }
 
 
 
 
                double[] xs = new double[N - 1];
                for(int j = 0, k = 0; j < N; j++) if(i != j) {
                    xs[k++] = Math.atan2(y[j]-y[i], x[j]-x[i]);
                }
                Arrays.sort(xs);
 
                int ans = N;
                for(int j = 0, k = 0; j < N - 1; j++) {
                    while(true) {
                        double d = xs[k] - xs[j];
                        if(d < 0) d += Math.PI * 2;
                        if(d >= Math.PI) break;
 
                        k = (k + 1) % (N - 1);
                    }
                    if(k > j) {
                        ans = Math.min(ans, k - j - 1);
                    }
                    else {
                        ans = Math.min(ans, N - 1 - j + k);
                    }
                }
                out.println(ans);
            }
        }
        
        int naive(Point[] pt, int idx) {
            List<Point> xs = new ArrayList<Point>();
            int ans = pt.length;
            for(int i = 0; i < 1<<pt.length; i++) {
                if((i>>idx&1) == 0) continue;
                xs.clear();
                for(int j = 0; j < pt.length; j++) {
                    if((i>>j&1) == 1) {
                        xs.add(pt[j]);
                    }
                }
                List<Point> cs = GenConvexHull(xs);
                TreeSet<Point> set = new TreeSet<>(cs);
                if(set.contains(pt[idx])) {
                    ans = Math.min(ans, pt.length - xs.size());
                }
            }
            return ans;
        }
        
        
 
        final Random random = new Random(System.currentTimeMillis());
        final StringWriter sw = new StringWriter();
        final PrintWriter out = new PrintWriter(sw);
        
        @Override
        public String toString() {
            out.flush();
            return sw.toString();
        }
        
        public ParallelSolver init(final IOFast io) throws IOException {
            readInput(io);
            return this;
        }
    }
 
    private static boolean LeftRotate(List<Point> ps, int len) {
        int i = len - 2;
        double x1 = ps.get(i - 1).x, x2 = ps.get(i).x, x3 = ps.get(i + 1).x,
                y1 = ps.get(i - 1).y, y2 = ps.get(i).y, y3 = ps.get(i + 1).y;
        Double cp = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        return cp > 0;  
 
    }
 
    static List<Point> GenConvexHull(List<Point> ps) {
        if (ps.size() < 3) return ps;
 
        Collections.sort(ps);
        int ul = 0;
        final List<Point> upper = new ArrayList<Point>(ps.size());
        for(int i = 0; i < ps.size(); i++) {
            upper.add(null);
        }
 
        upper.set(ul++, ps.get(0));
        upper.set(ul++, ps.get(1));
        for (int i = 2; i < ps.size(); i++) {
            upper.set(ul++, ps.get(i));
            while (ul >= 3 && LeftRotate(upper, ul)) {
                upper.set(ul - 2, upper.get(ul - 1));
                ul--;
            }
        }
 
        int ll = 0;
        final List<Point> lower = new ArrayList<Point>(ps.size());
        for(int i = 0; i < ps.size(); i++) {
            lower.add(null);
        }
 
        lower.set(ll++, ps.get(ps.size() - 1));
        lower.set(ll++, ps.get(ps.size() - 2));
        for (int i = ps.size() - 3; i >= 0; i--) {
            lower.set(ll++, ps.get(i));
            while (ll >= 3 && LeftRotate(lower, ll)) {
                lower.set(ll - 2, lower.get(ll - 1));
                ll--;
            }
        }
 
        final List<Point> res = new ArrayList<Point>(ul + ll - 2);
        for(int i = 0; i < ul; i++) res.add(upper.get(i));
        for(int i = 0; i < ll; i++) res.add(lower.get(i));
        return res;
    }
    
    static
    public class Point implements Comparable<Point> {
        public double x, y;
        public Point() { }
        public Point(double xx, double yy) { x = xx; y = yy; }
        public Point(Point p) { this(p.x, p.y); }
        public Point rotate(final double theta) {
            final double a = x, b = y;
            x = a * Math.cos(theta) - b * Math.sin(theta);
            y = a * Math.sin(theta) + b * Math.cos(theta);
            return this;
        }
        public Point addM(Point p) { return addM(p.x, p.y); }
        public Point addM(double x, double y) {
            this.x += x;
            this.y += y;
            return this;
        }
        public Point add(Point p) { return add(p.x, p.y); }
        public Point add(double x, double y) {
            return new Point(this).addM(x, y);
        }
        public Point subM(Point p) { return subM(p.x, p.y); }
        public Point subM(double x, double y) {
            this.x -= x;
            this.y -= y;
            return this;
        }
        public Point sub(Point p) { return sub(p.x, p.y); }
        public Point sub(double x, double y) {
            return new Point(this).subM(x, y);
        }
        public Point mulM(double d) {
            x *= d;
            y *= d;
            return this;
        }
        public Point mul(double d) {
            return new Point(this).mulM(d);
        }
        public Point divM(double v) {
            this.x /= v;
            this.y /= v;
            return this;
        }
        public Point div(double v) {
            return new Point(this).divM(v);
        }
 
        public static double dist(Point a, Point b) { return a.sub(b).abs(); }
        public static double cross(Point a, Point b) { return a.x*b.y - a.y*b.x; }
        public static double dot(Point a, Point b) { return a.x*b.x + a.y*b.y; }
        public double dot(Point p) { return dot(this, p); }
 
        public double norm() { return x*x+y*y; }
        public double abs() { return Math.sqrt(norm()); }
 
        
        
        static int ccw(Point a, Point b, Point c) {
            b=b.sub(a); c=c.sub(a);
            if (cross(b, c) > 0)   return +1;       
            if (cross(b, c) < 0)   return -1;       
            if (dot(b, c) < 0)     return +2;       
            if (b.norm() < c.norm()) return -2;     
            return 0;                               
        }
 
        
        static boolean intersectSS(Point[] p, Point[] q) {
            return ccw(p[0], p[1], q[0]) * ccw(p[0], p[1], q[1]) <= 0 &&
                    ccw(q[0], q[1], p[0]) * ccw(q[0], q[1], p[1]) <= 0;
        }
 
        static boolean intersectSS(Point p1, Point p2, Point q1, Point q2) {
            return ccw(p1, p2, q1) * ccw(p1, p2, q2) <= 0 &&
                    ccw(q1, q2, p1) * ccw(q1, q2, p2) <= 0;
        }
        
        public String toString() {
            return "(" + x + "," + y + ")";
        }
        
        @Override
        public int compareTo(Point o) {
            final int cmp = Double.compare(x, o.x);
            return cmp != 0 ? cmp : Double.compare(y, o.y);
        }
        
        public Point copy() {
            return new Point(this);
        }
    }
 
 
    public static class Pair<T1 extends Comparable<T1>, T2 extends Comparable<T2>>
    implements Comparable<Pair<T1, T2>>
    {
        public T1 x;
        public T2 y;
 
        public Pair(T1 t1, T2 t2) {
            x = t1;
            y = t2;
        }
 
        @Override
        public int compareTo(final Pair<T1, T2> arg0) {
            final Pair<T1, T2> p = arg0;
            final int cmp = x.compareTo(p.x);
            return cmp != 0 ? cmp : y.compareTo(p.y);
        }
        
        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
 
 
    
    final IOFast io = new IOFast();
    
    
    public long elaspedTimeMilli;
    
    
    
    static class Par implements Runnable {
        private static AtomicInteger cur = new AtomicInteger(1);
        
        IOFast io;
        int T;
        int caseIndex;
        String[] answer;
        ParallelSolver ps;
        
        public Par(int T, String[] answer, IOFast io) {
            this.T = T;
            this.answer = answer;
            this.io = io;
        }
        
        public boolean init() throws IOException {
            synchronized (answer) {
                caseIndex = cur.getAndIncrement();
                if(caseIndex > T) return false;
                System.gc();
                ps = new ParallelSolver();
                ps.init(io);
                return true;
            }
        }
        
        public void debugProgress() {
            synchronized (answer) {
                if(Config.DISP_PROGRESS) {
                    System.err.println("Case #" + caseIndex + ": DONE");
                }
            }
        }
        
        @Override
        public void run() {
            try {
                while(init()) {
                    ps.run();
                    answer[caseIndex - 1] = "Case #" + caseIndex + ": " + ps.toString();
                    debugProgress();
                }
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        }
    }
    
    public void run() throws IOException, InterruptedException {
        if(!Config.STDIO) {
            io.setFileDir(Config.BASE_DIR);
            io.setFileIO(Config.IN_FILE, Config.OUT_FILE);
        }
        
        int T = io.nextInt();
 
        Runnable[] inst = new Runnable[Config.MAX_THREAD];
        Thread[] thread = new Thread[Config.MAX_THREAD];
        String[] answer = new String[T];
        
        final long start = System.currentTimeMillis();
        for(int i = 0; i < Config.MAX_THREAD; i++) {
            inst[i] = new Par(T, answer, io);
 
            thread[i] = new Thread(null, inst[i], ""+(i+1), 1<<25);
            thread[i].start();
        }
        for(int i = 0; i < Config.MAX_THREAD; i++) {
            thread[i].join();
        }
        for(String s : answer) {
            io.out.print(s);
        }
        final long end = System.currentTimeMillis();
        elaspedTimeMilli = end - start;
        System.err.println(elaspedTimeMilli + " [ms]");
    }
 
 
    
    static int gcd(int n, int r) { return r == 0 ? n : gcd(r, n%r); }
    static long gcd(long n, long r) { return r == 0 ? n : gcd(r, n%r); }
    
    static <T> void swap(T[] x, int i, int j) {
        T t = x[i];
        x[i] = x[j];
        x[j] = t;
    }
    
    static void swap(int[] x, int i, int j) {
        int t = x[i];
        x[i] = x[j];
        x[j] = t;
    }
    
 
    static void radixSort(int[] xs) {
        int[] cnt = new int[(1<<16)+1];
        int[] ys = new int[xs.length];
        
        for(int j = 0; j <= 16; j += 16) {
            Arrays.fill(cnt, 0);
            for(int x : xs) { cnt[(x>>j&0xFFFF)+1]++; }
            for(int i = 1; i < cnt.length; i++) { cnt[i] += cnt[i-1]; }
            for(int x : xs) { ys[cnt[x>>j&0xFFFF]++] = x; }
            { final int[] t = xs; xs = ys; ys = t; }
        }
    }
    
    static void radixSort(long[] xs) {
        int[] cnt = new int[(1<<16)+1];
        long[] ys = new long[xs.length];
        
        for(int j = 0; j <= 48; j += 16) {
            Arrays.fill(cnt, 0);
            for(long x : xs) { cnt[(int)(x>>j&0xFFFF)+1]++; }
            for(int i = 1; i < cnt.length; i++) { cnt[i] += cnt[i-1]; }
            for(long x : xs) { ys[cnt[(int)(x>>j&0xFFFF)]++] = x; }
            { final long[] t = xs; xs = ys; ys = t; }
        }
    }
    
 
    static void arrayIntSort(int[][] x, int... keys) {
        Arrays.sort(x, new ArrayIntsComparator(keys));
    }
    
    static class ArrayIntsComparator implements Comparator<int[]> {
        final int[] KEY;
        
        public ArrayIntsComparator(int... key) {
            KEY = key;
        }
        
        @Override
        public int compare(int[] o1, int[] o2) {
            for(int k : KEY) if(o1[k] != o2[k]) return o1[k] - o2[k];
            return 0;
        }
    }
    
    static class ArrayIntComparator implements Comparator<int[]> {
        final int KEY;
        
        public ArrayIntComparator(int key) {
            KEY = key;
        }
        
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[KEY] - o2[KEY];
        }
    }
    
    
    void main() throws IOException, InterruptedException {
        
        try {
            run();
        }
        catch (EndOfFileRuntimeException e) { }
        io.out.flush();
    }
 
    public static void main(String[] args) throws IOException, InterruptedException {
        new C().main();
    }
    
    static class EndOfFileRuntimeException extends RuntimeException {
        private static final long serialVersionUID = -8565341110209207657L; }
 
    static
    public class IOFast {
        private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        private PrintWriter out = new PrintWriter(System.out);
        private String _dir;
        
        void setFileDir(String dir) {
            this._dir = dir;
        }
 
        void setFileIn(String ins) throws IOException {
            String pf = _dir == null ? "" : _dir + "\\";
            in = new BufferedReader(new FileReader(pf + ins));
        }
 
        void setFileOut(String outs) throws IOException {
            String pf = _dir == null ? "" : _dir + "\\";
            out = new PrintWriter(new FileWriter(pf + outs));
        }
 
        void setFileIO(String ins, String outs) throws IOException {
            out.flush();
            out.close();
            in.close();
 
 
            setFileIn(ins);
            setFileOut(outs);
            System.err.println("reading from " + ins);
        }
 
        
        private static int pos, readLen;
        private static final char[] buffer = new char[1024 * 8];
        private static char[] str = new char[500*8*2];
        private static boolean[] isDigit = new boolean[256];
        private static boolean[] isSpace = new boolean[256];
        private static boolean[] isLineSep = new boolean[256];
 
        static {
            for(int i = 0; i < 10; i++) { isDigit['0' + i] = true; }
            isDigit['-'] = true;
            isSpace[' '] = isSpace['\r'] = isSpace['\n'] = isSpace['\t'] = true;
            isLineSep['\r'] = isLineSep['\n'] = true;
        }
 
        public int read() throws IOException {
            if(pos >= readLen) {
                pos = 0;
                readLen = in.read(buffer);
                if(readLen <= 0) { throw new EndOfFileRuntimeException(); }
            }
            return buffer[pos++];
        }
 
        public int nextInt() throws IOException {
            int len = 0;
            str[len++] = nextChar();
            len = reads(len, isSpace);
            
            int i = 0;
            int ret = 0;
            if(str[0] == '-') { i = 1; }
            for(; i < len; i++) ret = ret * 10 + str[i] - '0';
            if(str[0] == '-') { ret = -ret; }
            return ret;
 
        }
 
        public long nextLong() throws IOException {
            int len = 0;
            str[len++] = nextChar();
            len = reads(len, isSpace);
            
            int i = 0;
            long ret = 0;
            if(str[0] == '-') { i = 1; }
            for(; i < len; i++) ret = ret * 10 + str[i] - '0';
            if(str[0] == '-') { ret = -ret; }
            return ret;
 
        }
 
        public char nextChar() throws IOException {
            while(true) {
                final int c = read();
                if(!isSpace[c]) { return (char)c; }
            }
        }
        
        int reads(int len, boolean[] accept) throws IOException {
            try {
                while(true) {
                    final int c = read();
                    if(accept[c]) { break; }
                    
                    if(str.length == len) {
                        char[] rep = new char[str.length * 3 / 2];
                        System.arraycopy(str, 0, rep, 0, str.length);
                        str = rep;
                    }
                    
                    str[len++] = (char)c;
                }
            }
            catch(EndOfFileRuntimeException e) { ; }
            
            return len;
        }
        
        int reads(char[] cs, int len, boolean[] accept) throws IOException {
            try {
                while(true) {
                    final int c = read();
                    if(accept[c]) { break; }
                    cs[len++] = (char)c;
                }
            }
            catch(EndOfFileRuntimeException e) { ; }
            
            return len;
        }
 
        public char[] nextLine() throws IOException {
            int len = 0;
 
            str[len++] = (char)read();
            len = reads(len, isLineSep);
            
            try {
                if(str[len-1] == '\r') { len--; read(); }
            }
            catch(EndOfFileRuntimeException e) { ; }
            
            return Arrays.copyOf(str, len);
        }
 
        public String nextString() throws IOException {
            return new String(next());
        }
 
        public char[] next() throws IOException {
            int len = 0;
            str[len++] = nextChar();
            len = reads(len, isSpace);
            return Arrays.copyOf(str, len);
        }
 
        public int next(char[] cs) throws IOException {
            int len = 0;
            cs[len++] = nextChar();
            len = reads(cs, len, isSpace);
            return len;
        }
 
        public double nextDouble() throws IOException {
            return Double.parseDouble(nextString());
        }
 
        public long[] nextLongArray(final int n) throws IOException {
            final long[] res = new long[n];
            for(int i = 0; i < n; i++) {
                res[i] = nextLong();
            }
            return res;
        }
 
        public int[] nextIntArray(final int n) throws IOException {
            final int[] res = new int[n];
            for(int i = 0; i < n; i++) {
                res[i] = nextInt();
            }
            return res;
        }
 
        public int[][] nextIntArray2D(final int n, final int k) throws IOException {
            final int[][] res = new int[n][];
            for(int i = 0; i < n; i++) {
                res[i] = nextIntArray(k);
            }
            return res;
        }
 
        public int[][] nextIntArray2DWithIndex(final int n, final int k) throws IOException {
            final int[][] res = new int[n][k+1];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < k; j++) {
                    res[i][j] = nextInt();
                }
                res[i][k] = i;
            }
            return res;
        }
 
        public double[] nextDoubleArray(final int n) throws IOException {
            final double[] res = new double[n];
            for(int i = 0; i < n; i++) {
                res[i] = nextDouble();
            }
            return res;
        }
 
    }
 
 }
