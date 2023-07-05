package con2016.con2016Q;
 
 
 import java.io.*;
 import java.util.*;
 
 public class D {
    
 
    private static final String fileLoc = "src/con2016/con2016Q/files/";
    private static final String fileName = fileLoc+D.class.getSimpleName().toLowerCase();
    private static final String inputFileName = fileName + ".in";
    private static final String outputFileName = fileName + ".out";
    private static InputReader in;
    private static OutputWriter out;
    
    static final String IMPOSSIBRU = " IMPOSSIBLE";
    private void solve() {
        int K = in.readInt(), 
            C = in.readInt(), 
            S = in.readInt(); 
        if(S*C < K){
            out.printLine(IMPOSSIBRU);
            return;
        }
        int cur = 0;
        while(cur < K){
            long pos = 1;
            long fac = 1;
            for(int c=1;c<=C;c++){
                pos += fac * Math.min(cur,K-1);
                fac*=K;
                cur++;
            }
            out.print(" "+pos);
        }
        out.printLine();
    }
    
    static void smarterCase(int K, int C, int S){
        if(S*2 < K || (S < K && C == 1)){
            out.printLine(IMPOSSIBRU);
            return;
        }
        long len = (long)Math.pow(K,C-1);
        System.err.println("len: "+len);
        int pos = 2,
            range = 0;
        out.print((pos+range));
        for(int s=2;s*2 <= K;s++){
            pos+=2;
            range+=2*len;
            out.print(" "+(pos+range));
        }
        if((K&1) == 1){
            out.print(" "+(len*K));
        }
        out.printLine();        
    }
    
    static void simpleCase(int K){
        
        out.print(1);
        for(int i=2;i<=K;i++){
            out.print(" "+i);
        }
        out.printLine();
    }
 
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        in = new InputReader(new FileInputStream(inputFileName));
        out = new OutputWriter(new FileOutputStream(outputFileName));
        int tests = in.readInt();
        for (int t = 1; t <= tests; t++) {
            out.print("Case #" + t + ":");
            new D().solve();
            System.out.println("Case #" + t + ": solved");
        }
        out.close();
        long stop = System.currentTimeMillis();
        System.out.println(stop-start+" ms");
    }
    
 
    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
 
        public InputReader(InputStream stream) {
            this.stream = stream;
        }
 
        public int read() {
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
 
        public String readLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }
 
        public String readString() {
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
 
        public long readLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        public boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }
 
    static class OutputWriter {
        private final PrintWriter writer;
 
        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    outputStream)));
        }
 
        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }
 
        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }
 
        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }
 
        public void close() {
            writer.close();
        }
    }
 }
