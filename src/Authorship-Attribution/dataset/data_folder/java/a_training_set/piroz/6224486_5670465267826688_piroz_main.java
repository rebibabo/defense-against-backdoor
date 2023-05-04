import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.util.InputMismatchException;
 import java.util.NoSuchElementException;
 
 
 public class Main {
    static IO io;
    public static void main(String[] args) throws Exception {
        
        io = new IO("C-small-attempt1.in","C-small-attempt1.out");
        int t = io.nextInt();
        for(int i=0;i<t;i++) {
            io.nextCase();
            solve();
        }
        io.flush();
    }
    public static void solve() {
        int l = io.nextInt();
        long x = io.nextLong();
        char[] s = io.nextCharArray(l);
 
        int pi = 1;
        long left = 1 << 60;
        for(long i=0;i<l*x;i++) {
            if (i >= 8 * l) {
                break;
            }
            pi = multiply(pi, val(s[(int) (i%l)]));
            if (pi == val('i')) {
                left = i;
                break;
            }
        }
        pi = 1;
        long right = -1;
        for(long i=l*x-1;i>=0;i--) {
            if (i < l*x - 8*l) {
                break;
            }
            pi = multiply(val(s[(int) (i%l)]), pi);
            if (pi == val('k')) {
                right = i;
                break;
            }
        }
        boolean ans = false;
        if (left + 1 < right) {
            int pi2 = 1;
            for(int i=0;i<l;i++) {
                pi2 = multiply(pi2, val(s[i]));
            }
            pi2 = pow(pi2, x);
            if (multiply(multiply(inverse(val('i')), pi2), inverse(val('k'))) == val('j')) {
                ans = true;
            }
        }
        io.println(ans ? "YES" : "NO");
    }
 
    
    private static int[][] table = {{0,0,0,0,0},{0,1,2,3,4},{0,2,-1,4,-3},{0,3,-4,-1,2},{0,4,3,-2,-1}};
    static int val(char c) {
        if (c == '1') {
            return 1;
        }else{
            return c - 'i' + 2;
        }
    }
    static int multiply(int x,int y) {
        int sign = x * y > 0 ? 1 : -1;
        return sign * table[Math.abs(x)][Math.abs(y)];
    }
    static int inverse(int x) {
        if (Math.abs(x) == 1) {
            return x;
        }else{
            return -x;
        }
    }
    static int pow(int x,long n) {
        int a = 1;
        int b = x;
        while(n > 0) {
            if ((n & 1) == 1) {
                a = multiply(a, b);
            }
            b = multiply(b, b);
            n /= 2;
        }
        return a;
    }
    static String tostr(int q) {
        StringBuilder sb = new StringBuilder();
        if (q < 0) {
            sb.append('-');
        }
        q = Math.abs(q);
        if (q == 1) {
            sb.append(1);
        }else{
            sb.append((char) ('i' + q - 1));
        }
        return sb.toString();
    }
 
 }
 class IO extends PrintWriter {
    private final InputStream in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private int casenum = 0;
 
    public IO() { this(System.in,System.out);}
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
        print("Case #" + casenum + ": ");
    }
 }
 
