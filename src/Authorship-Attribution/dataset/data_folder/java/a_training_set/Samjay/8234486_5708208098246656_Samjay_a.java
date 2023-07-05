package con2015.con2015R2;
 
 import java.io.*;
 import java.util.*;
 
 public class A {
    
 
    private static final String fileName = "results/con2015/con2015R2/"+A.class.getSimpleName().toLowerCase();
    private static final String inputFileName = fileName + ".in";
    private static final String outputFileName = fileName + ".out";
    private static InputReader in;
    private static OutputWriter out;
    
    static final int BLANK = 0,
            UP = 1,
            RIGHT = 2,
            DOWN = 3,
            LEFT = 4;
    private void solve() {
        
        int R = in.readInt(), C = in.readInt();
        int[][] grid = new int[R][C];
        for (int i = 0; i < R; i++) {
            String s = in.readLine();
            for (int j = 0; j < C; j++) {
                switch(s.charAt(j)){
                    case '^':
                        grid[i][j] = UP;
                        break;
                    case '>':
                        grid[i][j]=RIGHT;
                        break;
                    case 'v':
                        grid[i][j]=DOWN;
                        break;
                    case '<':
                        grid[i][j]=LEFT;
                        break;
                    case '.':
                        grid[i][j]=BLANK;
                        break;
                    default:
                        throw new RuntimeException("bad char: "+s.charAt(j));
                }
            }
        }
        int rep = 0;
        boolean impossible=false;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(grid[i][j]==BLANK)continue;
                if(!possibleUp(grid, i, j)
                    && !possibleDown(grid, i, j)
                    && !possibleRight(grid, i, j)
                    && !possibleLeft(grid, i, j)){
                        impossible=true;
                        break;
                    }
                switch(grid[i][j]){
                    case UP:
                        if(!possibleUp(grid, i, j)){
                            rep++;
                        }
                        break;
                    case RIGHT:
                        if(!possibleRight(grid, i, j)){
                            rep++;
                        }
                        break;
                    case DOWN:
                        if(!possibleDown(grid, i, j)){
                            rep++;
                        }
                        break;
                    case LEFT:
                        if(!possibleLeft(grid, i, j)){
                            rep++;
                        }
                        break;
                }
            }
        }
        if(impossible){
            out.printLine("IMPOSSIBLE");
        }else{
            out.printLine(rep);         
        }
    }
    
    static boolean possibleUp(int[][]grid, int i, int j){
        i--;
        if(i < 0)return false;
        while(i >= 0){
            if(grid[i][j]==BLANK){
                i--;
                continue;
            }
            return true;
        }
        return false;
    }
    static boolean possibleRight(int[][]grid, int i, int j){
        j++;
        if(j >= grid[i].length)return false;
        while(j < grid[i].length){
            if(grid[i][j]==BLANK){
                j++;
                continue;
            }
            return true;
        }
        return false;
    }
    static boolean possibleDown(int[][]grid, int i, int j){
        i++;
        if(i >= grid.length)return false;
        while(i < grid.length){
            if(grid[i][j]==BLANK){
                i++;
                continue;
            }
            return true;
        }
        return false;
    }
    static boolean possibleLeft(int[][]grid, int i, int j){
        j--;
        if(j < 0)return false;
        while(j >= 0){
            if(grid[i][j]==BLANK){
                j--;
                continue;
            }
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        in = new InputReader(new FileInputStream(inputFileName));
        out = new OutputWriter(new FileOutputStream(outputFileName));
        int tests = in.readInt();
        for (int t = 1; t <= tests; t++) {
            out.print("Case #" + t + ": ");
            new A().solve();
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
