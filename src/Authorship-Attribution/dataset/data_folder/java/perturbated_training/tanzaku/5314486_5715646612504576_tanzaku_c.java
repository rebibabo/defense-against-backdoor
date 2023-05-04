package r2;
 
 
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
 
        public static final String BASE_DIR = "data\\r2" ;
        public static final String IN_FILE = "C-small-attempt5.in";
        public static final String OUT_FILE = IN_FILE + "_out.txt";
        public static final int MAX_THREAD = 8;
    }
 
    
    static
    class ParallelSolver implements Runnable {
        private static final int mod = (int)1e9+7;
 
        int h, w;
        char[][] cs;
        TreeMap<Integer, Integer> pos = new TreeMap<>();
        public void readInput(final IOFast io) throws IOException {
            h = io.nextInt();
            w = io.nextInt();
            cs = new char[h][];
            pos.clear();
            for (int y = 0; y < h; y++) {
                cs[y] = io.next();
                for (int x = 0; x < w; x++) {
                    if (cs[y][x] == '-' || cs[y][x] == '|') {
                        pos.put(y*w+x, pos.size());
                    }
                }
            }
        }
        
        @Override
        public void run() {
 
 
 
 
 
 
 
 
 
 
            yoko = new boolean[w*h];
 
            visc = new int[w*h];
            if (!dfs(w-1, h-1)) {
                out.println("IMPOSSIBLE");
            } else {
                out.println("POSSIBLE");
                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                        if (cs[y][x] == '-' || cs[y][x] == '|') {
                            out.print(yoko[y*w+x] ? '-' : '|');
                        } else {
                            out.print(cs[y][x]);
                        }
                    }
                    out.println();
                }
            }
        }
 
        int[] visc;
        boolean dfs(int x, int y) {
            if (y < 0) return true;
            if (x < 0) return dfs(w-1, y-1);
            if (cs[y][x] == '#' || visc[y*w+x] > 0) return dfs(x-1, y);
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
            return fill(x, y);
        }
        
        boolean fill(int x, int y) {
            int c = 0;
            int l, r;
            for (r = x+1; r < w && cs[y][r] != '#'; r++) {
                if (cs[y][r] == '-' || cs[y][r] == '|') { c++; }
            }
            for (l = x; l >= 0 && cs[y][l] != '#'; l--) {
                if (cs[y][l] == '-' || cs[y][l] == '|') { c++; }
            }
            if (c == 1) {
                for (int i = l+1; i < r; i++) {
                    visc[y*w+i]++;
                    if (cs[y][i] == '-' || cs[y][i] == '|') {
                        yoko[y*w+i] = true;
                    }
                }
                if (dfs(x-1, y)) return true;
                for (int i = l+1; i < r; i++) {
                    visc[y*w+i]--;
                }
            }
            
            c = 0;
            for (r = y+1; r < h && cs[r][x] != '#'; r++) {
                if (cs[r][x] == '-' || cs[r][x] == '|') { c++; }
            }
            for (l = y; l >= 0 && cs[l][x] != '#'; l--) {
                if (cs[l][x] == '-' || cs[l][x] == '|') { c++; }
            }
            if (c == 1) {
                for (int i = l+1; i < r; i++) {
                    visc[i*w+x]++;
                    if (cs[i][x] == '-' || cs[i][x] == '|') {
                        yoko[i*w+x] = false;
                    }
                }
                if (dfs(x-1, y)) return true;
                for (int i = l+1; i < r; i++) {
                    visc[i*w+x]--;
                }
            }
            
            return false;
        }
 
        boolean[] yoko;
        boolean[] vis;
        
        boolean ok() {
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (cs[y][x] == '.') {
                        if (vis[(y*w+x)*2+0] || vis[(y*w+x)*2+1]) {
                        } else {
                            return false;
                        }
                    }
                }
            }
            out.println("POSSIBLE");
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (cs[y][x] == '-' || cs[y][x] == '|') {
                        out.print(vis[(y*w+x)*2+0] ? '-' : '|');
                    } else {
                        out.print(cs[y][x]);
                    }
                }
                out.println();
            }
            return true;
        }
 
        void dfs(int x, int y, int d) {
            if (x < 0 || x >= w || y < 0 || y >= h || cs[y][x] == '#') return;
            if (vis[(y*w+x)*2+d]) return;
            vis[(y*w+x)*2+d] = true;
            if (d == 0) {
                dfs(x+1, y, d);
                dfs(x-1, y, d);
            } else {
                dfs(x, y+1, d);
                dfs(x, y-1, d);
            }
        }
        
 
 
 
 
 
 
 
 
 
 
 
 
 
 
        
        
        
        void printList(int[] res) {
            for(int i = 0; i < res.length; i++) {
                out.print(res[i] + (i==res.length-1?"\n":" "));
            }
        }
        void printList(List<Integer> res) {
            for(int i = 0; i < res.size(); i++) {
                out.print(res.get(i) + (i==res.size()-1?"\n":" "));
            }
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
 
    static void dump(Object... o) { System.err.println(Arrays.deepToString(o)); } 
    
    static class Dinic {
        final int INF = 1 << 29;
 
 
        AdjListGraph g;
        int[] level, iter, q;
        List<int[]> es = new ArrayList<>();
 
        void bfs(int s) {
            for (int i = 0; i < level.length; i++) level[i] = -1;
 
            int qs = 0, qt = 0;
            level[s] = 0;
            q[qt++] =s;
            while (qs != qt) {
                int v = q[qs++];
                for(int e = g.head[v]; e != -1; e = g.next[e]) {
                    final int t = g.to[e];
                    if (g.cap[e] > 0 && level[t] < 0) {
                        level[t] = level[v] + 1;
                        q[qt++] = t;
                    }
                }
            }
        }
 
        int dfs(int v, int t, int f) {
            if (v == t) return f;
            for (int i = iter[v]; i != -1; i = iter[v] = g.next[i]) {
                if (g.cap[i] > 0 && level[v] < level[g.to[i]]) {
                    int d = dfs(g.to[i], t, Math.min(f, g.cap[i]));
                    if (d > 0) {
                        g.cap[i] -= d;
                        g.cap[g.rev[i]] += d;
                        return d;
                    }
                }
            }
            return 0;
        }
 
        public void AddEdge(int from, int to, int cap) {
            es.add(new int[]{from, to, cap, });
 
 
        }
        
        private void build() {
            if(g == null) {
                g = new AdjListGraph(level.length, 2 * es.size() * 2);
                for(int[] e : es) {
                    g.addEdge(e[0], e[1], e[2], g.m + 1);
                    g.addEdge(e[1], e[0], 0, g.m - 1);
                }
            }
        }
 
        public int MaxFlow(int s, int t) {
            build();
            int flow = 0;
            for (; ; ) {
                bfs(s);
                if (level[t] < 0) return flow;
                for (int i = 0; i < iter.length; i++) iter[i] = g.head[i];
                for (int f = 0; (f = dfs(s, t, INF)) > 0; )
                    flow += f;
            }
        }
 
        public int BipartiteMatching(int s, int t) {
            return MaxFlow(s, t);
        }
        
        void clear() {
            g.clear();
        }
        
        void clear(int n) {
            g.clear(n);
        }
 
        public Dinic(int size) {
            level = new int[size];
            iter = new int[size];
            q = new int[size];
        }
 
        static class AdjListGraph {
            int m;
            int[] head, next, to, rev;
            int[] cap;
 
            public AdjListGraph(int V, int E) {
                head = new int[V];
                next = new int[E];
                to = new int[E];
                cap = new int[E];
                rev = new int[E];
                clear();
            }
 
            public void clear(int v) {
                m = 0;
                for(int i = 0; i < v; i++) {
                    head[i] = -1;
                }
            }
 
            public void clear() {
                m = 0;
                Arrays.fill(head, -1);
            }
 
            public void addEdge(int s, int t, int c, int r) {
                next[m] = head[s];
                head[s] = m;
                to[m] = t;
                cap[m] = c;
                rev[m++] = r;
            }
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
