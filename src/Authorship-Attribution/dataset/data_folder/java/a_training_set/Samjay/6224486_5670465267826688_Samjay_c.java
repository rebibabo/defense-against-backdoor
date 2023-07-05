package con2015.con2015Q;
 
 
 import java.io.*;
 import java.util.*;
 
 public class C {
    
 
    private static final String fileName = "results/con2015/con2015Q/"+C.class.getSimpleName().toLowerCase();
    private static final String inputFileName = fileName + ".in";
    private static final String outputFileName = fileName + ".out";
    private static InputReader in;
    private static OutputWriter out;
    
    static final String YES = "YES", NO = "NO";
    static int[]chars, ltr, rtl;
    static int full, full2, full3;
    private void solve() {
        int T = in.readInt(),
            L = in.readInt();
        String s = in.readString();
        int num = 1;
        chars = new int[T];
        for(int i=0;i<T;i++){
            chars[i] = (s.charAt(i)-'i')+2;
        }
        ltr = new int[T];
        for(int i=0;i<T;i++){
            num = convert(num,chars[i]);
            ltr[i]=num;
        }
        num = 1;
        rtl = new int[T];
        for(int i=T-1;i>=0;i--){
            num = convert(chars[i],num);
            rtl[i]=num;
        }
        full = rtl[0];
        full2 = convert(full,full);
        full3 = convert(full2,full);
        out.printLine(isPossibleI(T, L)?YES:NO);
    }
    static boolean isPossibleI(int T, int L){
        for(int i=0;i < T;i++){
            if(ltr[i]==2){ 
                if(isPossibleJ(T, L, i, 0))
                    return true;
            }
            if(convert(full,ltr[i])==2){ 
                if(isPossibleJ(T, L, i, 1))
                    return true;
            }
            if(convert(full2, ltr[i])==2){ 
                if(isPossibleJ(T, L, i, 2))
                    return true;
            }
            if(convert(full3, ltr[i])==2){ 
                if(isPossibleJ(T, L, i, 3))
                    return true;
            }
        }
        return false;
    }
    
    static boolean isPossibleJ(int T, int L, int left, int fulls){
        left++;
        if(left < T){
            if(isPossibleJgo(T, L, left, fulls, 1)){
                return true;
            }                   
        }else{
            fulls++;
            left=0;
        }
        int num = rtl[left];
        for(int loops = 1;loops <= 4;loops++){
            if(isPossibleJgo(T, L, 0, fulls+loops, num)){
                return true;
            }
            num = convert(num, rtl[0]);
        }
        return false;
    }
    
    static boolean isPossibleJgo(int T, int L, int from, int fulls, int sub){
        if(fulls > L)return false;
        for(int i=from;i < T;i++){
            sub = convert(sub, chars[i]);
            if(sub==3 && isPossibleK(T, L, i, fulls)){ 
                return true;
            }
        }
        return false;
    }
    
    static boolean isPossibleK(int T, int L, int left, int fulls){
        left++;
        if(left == T){
            fulls++;
            left = 0;
        }
        int num = rtl[left];
        fulls = L - (fulls+1);
        if(fulls < 0)return false;
        if(fulls > 0){
            if(rtl[0]!=1){
                if((fulls&1) == 1){
                    num = convert(num,rtl[0]);
                    fulls--;
                }
                if(fulls > 0 && rtl[0]!=-1){
                    fulls/=2;
                    if((fulls&1)==1){
                        num *= -1;
                    }
                }
            }
        }
        return num == 4;
    }
    
    static int convert(int from, int to){
        int res = 1;
        if(from < 0){
            from *= -1;
            res *= -1;
        }
        if(to < 0){
            to *= -1;
            res *= -1;          
        }
        return res * CONV[from][to];
    }
    
    static void precalc(){
        CONV[1][1]=1;
        for(int i=2;i<=4;i++){
            CONV[i][i]=-1;
            CONV[1][i]=CONV[i][1]=i;
        }
        CONV[2][3]=4;
        CONV[2][4]=-3;
        CONV[3][2]=-4;
        CONV[3][4]=2;
        CONV[4][2]=3;
        CONV[4][3]=-2;      
    }
    
    static void test(int T, int[]ltr, int[]rtl){
        System.err.println("----------------------- ");
        System.err.println(ltr[T-1]+" == "+rtl[0]);
        for(int i=0;i+1 < T;i++){
            System.err.println(ltr[i]+" + "+rtl[i+1]+" = "+convert(ltr[i],rtl[i+1]));
        }
    }
 
    private static final int[][] CONV = new int[5][5];
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        in = new InputReader(new FileInputStream(inputFileName));
        out = new OutputWriter(new FileOutputStream(outputFileName));
        precalc();
        int tests = in.readInt();
        for (int t = 1; t <= tests; t++) {
            out.print("Case #" + t + ": ");
            new C().solve();
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
