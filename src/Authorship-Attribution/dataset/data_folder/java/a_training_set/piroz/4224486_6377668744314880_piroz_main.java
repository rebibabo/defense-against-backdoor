import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.InputMismatchException;
 import java.util.NoSuchElementException;
 
 
 public class Main {
    static IO io;
    public static void main(String[] args) throws Exception {
        
        io = new IO("C-small-attempt1");
        int t = io.nextInt();
        for(int i=0;i<t;i++) {
            io.nextCase();
            solve();
        }
        io.flush();
    }
    public static void solve() {
        int n = io.nextInt();
        Vector2[] v = new Vector2[n];
        for(int i=0;i<n;i++) {
            v[i] = new Vector2(io.nextInt(),io.nextInt());
        }
        int[] ans = new int[n];
        Arrays.fill(ans, 1<<29);
        if (n == 1) {
            ans[0] = 0;
        }
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                Vector2 d = v[i].subtract(v[j]);
                int plus = 0;
                int minus = 0;
                for(int k=0;k<n;k++) {
                    long sign = d.cross(v[i].subtract(v[k]));
                    if (sign < 0) {
                        minus++;
                    }else if(sign > 0) {
                        plus++;
                    }
                }
                int min = Math.min(plus,minus);
                ans[i] = Math.min(ans[i], min);
                ans[j] = Math.min(ans[j], min);
            }
        }
        for(int i=0;i<n;i++) {
            io.println(ans[i]);
        }
    }
 
 }
 class Vector2 {
    long x = 0;
    long y = 0;
    public Vector2(long x,long y) {
        this.x = x;
        this.y = y;
    }
    public long dot(Vector2 v) {
        return this.x*v.x+this.y*v.y;
    }
    public long cross(Vector2 v) {
        return this.x*v.y-this.y*v.x;
    }
    public Vector2 add(Vector2 v) {
        return new Vector2(this.x+v.x,this.y+v.y);
    }
    public Vector2 subtract(Vector2 v) {
        return new Vector2(this.x-v.x,this.y-v.y);
    }
    public Vector2 multiply(long k) {
        return new Vector2(k*this.x,k*this.y);
    }
    public long normSquare() {
        return x * x + y * y;
    }
    public long distSquare(Vector2 v) {
        return this.subtract(v).normSquare();
    }
    public String toString() {
        return this.x + " " + this.y;
    }
 }
 
 class IO extends PrintWriter {
    private final InputStream in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private int casenum = 0;
 
    public IO() { this(System.in,System.out);}
    public IO(String fileName) throws FileNotFoundException {
        this(fileName + ".in",fileName + ".out");
    }
    public IO(String filenameIn, String filenameOut) throws FileNotFoundException {
        this(new FileInputStream(filenameIn),new FileOutputStream(filenameOut));
    }
    public IO(InputStream in,OutputStream out) {
        super(out);
        this.in = in;
    }
 
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    private static boolean isNewLine(int c) { return c == '\n' || c == '\r';}
    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
    public boolean hasNextLine() { while(hasNextByte() && isNewLine(buffer[ptr])) ptr++; return hasNextByte();}
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public char[] nextCharArray(int len) {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        char[] s = new char[len];
        int i = 0;
        int b = readByte();
        while(isPrintableChar(b)) {
            if (i == len) {
                throw new InputMismatchException();
            }
            s[i++] = (char) b;
            b = readByte();
        }
        return s;
    }
    public String nextLine() {
        if (!hasNextLine()) {
            throw new NoSuchElementException();
        }
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(!isNewLine(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) {
            throw new NumberFormatException();
        }
        return (int) nl;
    }
    public char nextChar() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (char) readByte();
    }
    public double nextDouble() { return Double.parseDouble(next());}
    public int[] nextIntArray(int n) { int[] a = new int[n]; for(int i=0;i<n;i++) a[i] = nextInt(); return a;}
    public long[] nextLongArray(int n) { long[] a = new long[n]; for(int i=0;i<n;i++) a[i] = nextLong(); return a;}
    public double[] nextDoubleArray(int n) { double[] a = new double[n]; for(int i=0;i<n;i++) a[i] = nextDouble(); return a;}
    public void nextIntArrays(int[]... a) { for(int i=0;i<a[0].length;i++) for(int j=0;j<a.length;j++) a[j][i] = nextInt();}
    public int[][] nextIntMatrix(int n,int m) { int[][] a = new int[n][]; for(int i=0;i<n;i++) a[i] = nextIntArray(m); return a;}
    public char[][] nextCharMap(int n,int m) { char[][] a = new char[n][]; for(int i=0;i<n;i++) a[i] = nextCharArray(m); return a;}
    public void close() { super.close(); try {in.close();} catch (IOException e) {}}
    public void nextCase() {
        casenum++;
        print("Case #" + casenum + ":\n");
    }
 }
 
