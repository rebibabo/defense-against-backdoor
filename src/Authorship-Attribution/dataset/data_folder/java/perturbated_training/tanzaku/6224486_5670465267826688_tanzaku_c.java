package qual;
 
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
 
        public static final String BASE_DIR = "data\\qual\\C" ;
        public static final String IN_FILE = "C-small-attempt1.in";
        public static final String OUT_FILE = IN_FILE + "_out.txt";
        public static final int MAX_THREAD = 4;
    }
 
 
    
    static
    class ParallelSolver implements Runnable {
        private static final int mod = (int)1e9+7;
        
        int L;
        long X;
        char[] cs;
        public void readInput(final IOFast io) throws IOException {
            L = io.nextInt();
            X = io.nextLong();
            cs = io.next();
        }
        
        @Override
        public void run() {
            int[] prefix = new int[L+1];
            int[] suffix = new int[L+1];
            prefix[0] = 1;
            suffix[L] = 1;
            for(int i = 0; i < L; i++) {
                prefix[i+1] = mul(prefix[i], toNum(cs[i]));
                suffix[L-1-i] = mul(toNum(cs[L-1-i]), suffix[L-i]);
            }
            
            
            
            for(int x = 0; x < L; x++) {
                int loop = 1;
                for(int i = 0; i < 4; i++, loop = mul(loop, prefix[L])) {
                    if(mul(loop, prefix[x]) != 2) continue;
 
                    
                    int val2 = 1;
                    for(int y = 0; y < L; y++) {
                        int loop2 = 1;
                        for(int j = 0; j < 4; j++, loop2 = mul(loop2, mul(suffix[x], prefix[x]))) {
                            if(mul(loop2, val2) != 3) continue;
 
 
                            int loop3 = 1;
                            for(int k = 0; k < 4; k++) {
                                int loopLen = i+j+(x+y)/L+1;
                                final int len3 = L - (x+y)%L;
 
 
 
 
                                
                                if(loopLen + k <= X && (loopLen + k) % 4 == X % 4) {
 
 
 
                                    
                                    if(mul(suffix[L-len3], loop3) == 4) {
                                        out.println("YES");
                                        return;
                                    }
                                }
                                
                                loop3 = mul(loop3, prefix[L]);
                            }
                        }
                        val2 = mul(val2, toNum(cs[(y+x)%L]));
                    }
                }
            }
            
            out.println("NO");
        }
        
        int toNum(char c) {
            if(c == 'i') return 2;
            if(c == 'j') return 3;
            if(c == 'k') return 4;
            throw new RuntimeException();
        }
        
        static final int[][] mulTable = new int[][]{
            new int[]{ 1, 2, 3, 4, },
            new int[]{ 2,-1, 4,-3, },
            new int[]{ 3,-4,-1, 2, },
            new int[]{ 4, 3,-2,-1, },
        };
        int mul(int x, int y) {
            int sign = 1;
            if(x < 0) { sign = -sign; x = -x; }
            if(y < 0) { sign = -sign; y = -y; }
            return sign * mulTable[x-1][y-1];
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
