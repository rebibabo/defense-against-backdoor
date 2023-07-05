package con2015.con2015R2;
 
 import java.io.*;
 import java.util.*;
 
 public class D {
    
 
    private static final String fileName = "results/con2015/con2015R2/"+D.class.getSimpleName().toLowerCase();
    private static final String inputFileName = fileName + ".in";
    private static final String outputFileName = fileName + ".out";
    private static InputReader in;
    private static OutputWriter out;
    static final int MOD = 1000000007;
    private void solve() {
        int R = in.readInt(),
            C = in.readInt();
        int[]sol = new int[]{0,0,1,2,1,1,2};
        
        
        
        
        
        
        
        
        
        
        
        if(R <= 6){
            if(C == 3){
                if(R==2){
                    out.printLine(sol[R]+3);
                }else if(R==4){
                    out.printLine(sol[R]+6);                    
                }else if(R==5){
                    out.printLine(sol[R]+6);                    
                }else if(R==6){
                    out.printLine(sol[R]+12); 
                }else{
                    out.printLine(sol[R]); 
                }               
            }
            else if(C == 4){
                if(R==3){
                    out.printLine(sol[R]+4); 
                }else if(R==5){
                    out.printLine(sol[R]+8);
                }else if(R==6){
                    out.printLine(sol[R]+8);                    
                }else{
                    out.printLine(sol[R]); 
                }
            }else if(C == 6){
                if(R==2){
                    out.printLine(sol[R]+9); 
                }else if(R==4){
                    out.printLine(sol[R]+18); 
                }else if(R==5){
                    out.printLine(sol[R]+18); 
                }else if(R==6){
                    out.printLine(sol[R]+90); 
                }else{
                    out.printLine(sol[R]); 
                }
            }else{
                out.printLine(sol[R]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        in = new InputReader(new FileInputStream(inputFileName));
        out = new OutputWriter(new FileOutputStream(outputFileName));
        int tests = in.readInt();
        for (int t = 1; t <= tests; t++) {
            out.print("Case #" + t + ": ");
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
