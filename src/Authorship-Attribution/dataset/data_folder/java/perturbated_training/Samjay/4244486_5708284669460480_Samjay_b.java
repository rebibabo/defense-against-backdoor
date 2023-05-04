package con2015.con2015R1c;
 
 import java.io.*;
 import java.text.DecimalFormat;
 import java.text.DecimalFormatSymbols;
 import java.util.*;
 
 public class B {
    
    
    private static final String fileName = "results/con2015/con2015R1c/"+B.class.getSimpleName().toLowerCase();
    private static final String inputFileName = fileName + ".in";
    private static final String outputFileName = fileName + ".out";
    private static InputReader in;
    private static OutputWriter out;
    
    static final int MAX_CHARS = 26;
    static final DecimalFormat df = new DecimalFormat("#.########",
             DecimalFormatSymbols.getInstance(Locale.US));
    private void solve() {
        int K = in.readInt(),
            L = in.readInt(),
            S = in.readInt();
        String keyboard = in.readString();
        
        double[]pKey = new double[MAX_CHARS];
        for(int i=0;i<K;i++){
            pKey[conv(keyboard.charAt(i))]++;
        }
        for(int i=0;i<MAX_CHARS;i++){
            pKey[i] /= K;
        }
        String lword = in.readString();
        double res = 0D;
        for(int i=0;i<lword.length();i++){
            if(pKey[conv(lword.charAt(i))]==0)
            {
                out.printLine(0);
                return;
            }
        }
        double[]pTotal=new double[L+1]; 
        pTotal[0]=1D; 
        int[][]dfa = KMP(lword,MAX_CHARS);
        int start=1;
        while(start <= L && !lword.substring(start).equals(lword.substring(0, L-start))){
 
            start++;
        }
 
        int failure = L-start;
        int expected = (S+start-L)/start;
 
        for(int t=0;t<S;t++){
            double[] pNext = new double[L+1];
            for(int key=0;key<MAX_CHARS;key++){ 
                if(pKey[key]==0)continue; 
                for(int l=0;l<L;l++){ 
                    if(pTotal[l]==0)continue;
                    pNext[dfa[key][l]] += pTotal[l]*pKey[key]; 
                }
            }
            pTotal = pNext;
            res += pTotal[L];   
            
            pTotal[failure]+=pTotal[L]; 
            pTotal[L] = 0; 
        }
        out.printLine(df.format(expected-res));         
    }
    
    private static int conv(char c){
        return c-'A';
    }
    
     public static int[][] KMP(String pattern, int R) {
         
         int M = pattern.length();
         int[][] dfa = new int[R][M]; 
         dfa[conv(pattern.charAt(0))][0] = 1; 
         for (int X = 0, j = 1; j < M; j++) {
             for (int c = 0; c < R; c++) 
                 dfa[c][j] = dfa[c][X];     
             dfa[conv(pattern.charAt(j))][j] = j+1;      
             X = dfa[conv(pattern.charAt(j))][X];        
         }
         return dfa;
     } 
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Locale.setDefault(Locale.US);
        in = new InputReader(new FileInputStream(inputFileName));
        out = new OutputWriter(new FileOutputStream(outputFileName));
        int tests = in.readInt();
        for (int t = 1; t <= tests; t++) {
            out.print("Case #" + t + ": ");
            new B().solve();
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
