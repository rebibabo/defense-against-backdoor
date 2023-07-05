package con2016.con2016R1A;
 
 
 import java.io.*;
 import java.util.*;
 
 public class B {
    
 
    private static final String fileLoc = "src/con2016/con2016R1A/files/";
    private static final String fileName = fileLoc+B.class.getSimpleName().toLowerCase();
    private static final String inputFileName = fileName + ".in";
    private static final String outputFileName = fileName + ".out";
    private static InputReader in;
    private static OutputWriter out;
    
    static final int MAX_H = 2501;
    private void solve() {
        int N = in.readInt();
        Row[]all = new Row[(2*N)-1];
        for(int r=0;r < (2*N)-1;r++){
            all[r]=new Row(N);
            for(int len=0;len<N;len++){
                all[r].nums[len]=in.readInt();
            }
        }
        Arrays.sort(all);
        Row[] rs = new Row[N];
        Row[] cs = new Row[N];
        int res = dfs(all, rs, cs, 0, 0, 0, -1);
        
        for(int r : rs[res].nums){
            out.print(" "+r);
        }
        out.printLine();
    }
    private static int dfs(Row[] all, Row[]rs, Row[] cs, int cur, int curRow, int curCol, int skipRow) {
        if(cur==all.length){
            if(curCol != cs.length){
                throw new RuntimeException("not all columns done: "+curCol+" of "+cs.length+" at place ("+curRow+", "+curCol+" )");
            }
            if(skipRow == -1){
                System.err.println("determine skiprow");
                if(curRow+1==rs.length){
                    rs[curRow] = new Row(rs.length,true);
                    for(int c=0;c<curCol;c++){
                        rs[curRow].nums[c]=cs[c].nums[curRow];
                    }
                }else{
                    throw new RuntimeException("how possible? "+skipRow+" ("+curRow+", "+curCol+")");                   
                }
                skipRow = curRow;
            }
            for(int c=0;c<cs.length;c++){
                if(skipRow > 0 && rs[skipRow-1].nums[c] >= rs[skipRow].nums[c])return -1;
                if(skipRow + 1 < rs.length && rs[skipRow].nums[c] >= rs[skipRow+1].nums[c])return -1;
            }
            return skipRow;
        }
        if(curRow == 0 || (curRow < rs.length && all[cur].biggerThan(rs[curRow-1]))){
            if(skipRow >= 0 || curRow+1 < rs.length){
                if(eq(cs,all[cur],curRow)){
                    rs[curRow] = all[cur];
                    int res = dfs(all,rs,cs,cur+1,curRow+1,curCol,skipRow);
                    if(res >= 0){
                        return res;
                    }
                    rs[curRow] = null;
                }
            }
            if(skipRow == -1){
                rs[curRow] = new Row(rs.length,true);
                for(int c=0;c<curCol;c++){
                    rs[curRow].nums[c]=cs[c].nums[curRow];
                }
                if(eq(cs,rs[curRow],curRow)){
                    int res = dfs(all,rs,cs,cur,curRow+1,curCol,curRow);
                    if(res >= 0){
                        return res;
                    }
                }
                rs[curRow]=null;
            }
        }
        if(curCol == 0 || (curCol < cs.length && all[cur].biggerThan(cs[curCol-1]))){
            if(eq(rs,all[cur],curCol)){
                cs[curCol] = all[cur];
                int res = dfs(all,rs,cs,cur+1,curRow,curCol+1,skipRow);
                if(res >= 0){
                    return res;
                }
                cs[curCol] = null;
            }
        }
        return -1;
    }
    static boolean eq(Row[]rs, Row col, int c){
        for(int i=0;i<rs.length;i++){
            if(rs[i] == null)continue;
            if(rs[i].isDummy){
                System.err.println("set dummy row = "+i+", col = "+c);
                rs[i].nums[c] = col.nums[i];continue;
            }
            if(rs[i].nums[c] != col.nums[i])return false;
        }
        return true;
    }
    static class Row implements Comparable<Row>{
        final int[] nums;
        final boolean isDummy;
        public Row(int r) {
            this(r,false);
        }
        public Row(int r, boolean isDummy){
            nums = new int[r];
            this.isDummy = isDummy;
        }
        @Override
        public int compareTo(Row o) {
            for(int i=0;i<nums.length;i++){
                if(this.nums[i]==o.nums[i])continue;
                return this.nums[i] < o.nums[i] ? -1 : 1;
            }
            return 0;
        }
        boolean biggerThan(Row oth){
            for(int c=0;c<nums.length;c++){
                if(this.nums[c]<=oth.nums[c]){
                    return false;
                }
            }
            return true;
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        in = new InputReader(new FileInputStream(inputFileName));
        out = new OutputWriter(new FileOutputStream(outputFileName));
        int tests = in.readInt();
        for (int t = 1; t <= tests; t++) {
            out.print("Case #" + t + ":");
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
