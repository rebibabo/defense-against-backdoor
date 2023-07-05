import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.System.exit;
 import static java.math.BigInteger.ONE;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.StringTokenizer;
 
 public class C {
    
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
    
    static final BigInteger TWO = BigInteger.valueOf(2);
    static final BigInteger THREE = BigInteger.valueOf(3);
    static final BigInteger FOUR = BigInteger.valueOf(4);
    
    static final BigInteger LIMIT = BigInteger.valueOf(7);
    
    static void solve() throws Exception {
        int n = nextInt();
        int cnt = nextInt();
        if (n < 2 || n > 65) {
            throw new AssertionError();
        }
        if (cnt == 0) {
            return;
        }
        BigInteger start[] = new BigInteger[9];
        BigInteger pow[][] = new BigInteger[9][n - 2];
        for (int i = 0; i < 9; i++) {
            BigInteger base = BigInteger.valueOf(i + 2);
            BigInteger cpow = base;
            for (int j = 0; j < n - 2; j++) {
                pow[i][j] = cpow;
                cpow = cpow.multiply(base);
            }
            start[i] = ONE.add(cpow);
        }
 
 
 
 
        BigInteger divs[] = new BigInteger[9];
        printlnCase();
        cur: for (long cur = 0, limit = 1L << (n - 2); cur != limit; cur++) {
            i: for (int i = 0; i < 9; i++) {
                BigInteger num = start[i];
                for (int j = 0; j < n - 2; j++) {
                    if ((cur & (1L << j)) != 0) {
                        num = num.add(pow[i][j]);
                    }
                }
 
 
                for (BigInteger div = TWO, div2 = FOUR, div2a = THREE;
                    div2.compareTo(num) <= 0 && div.compareTo(LIMIT) <= 0;
                    div = div.add(ONE), div2a = div2a.add(TWO), div2 = div2.add(div2a)) {
                    if (num.mod(div).signum() == 0) {
                        divs[i] = div;
                        continue i;
                    }
                }
                continue cur;
            }
            out.print('1');
            for (int i = n - 3; i >= 0; i--) {
                out.print((cur & (1L << i)) != 0 ? '1' : '0');
            }
            out.print('1');
            for (int i = 0; i < 9; i++) {
                out.print(' ');
                out.print(divs[i].toString());
            }
            out.println();
            if (--cnt == 0) {
                return;
            }
        }
        throw new AssertionError();
    }
    
    static void printCase() {
        out.print("Case #" + test + ": ");
    }
    
    static void printlnCase() {
        out.println("Case #" + test + ":");
    }
    
    static int nextInt() throws IOException {
        return parseInt(next());
    }
 
    static long nextLong() throws IOException {
        return parseLong(next());
    }
 
    static double nextDouble() throws IOException {
        return parseDouble(next());
    }
 
    static String next() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }
 
    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            int tests = nextInt();
            for (test = 1; test <= tests; test++) {
                solve();
            }
            in.close();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
 }