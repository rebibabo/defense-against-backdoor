import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.System.exit;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class C {
    
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
    
    
    static final byte MUL[][] = {
        {0, 1, 2, 3, 4, 5, 6, 7},
        {1, 4, 3, 6, 5, 0, 7, 2},
        {2, 7, 4, 1, 6, 3, 0, 5},
        {3, 2, 5, 4, 7, 6, 1, 0},
        {4, 5, 6, 7, 0, 1, 2, 3},
        {5, 0, 7, 2, 1, 4, 3, 6},
        {6, 3, 0, 5, 2, 7, 4, 1},
        {7, 6, 1, 0, 3, 2, 5, 4}
    };
    
    static byte mul(byte a, byte b) {
        return MUL[a][b];
    }
    
    static byte fromChar(char c) {
        switch (c) {
        case 'i':
            return 1;
        case 'j':
            return 2;
        case 'k':
            return 3;
        default:
            throw new AssertionError();
        }
    }
    
    static byte mul(byte a, char b) {
        return mul(a, fromChar(b));
    }
    
    static void solve() throws Exception {
        int l = nextInt();
        long x = nextLong();
        String s = next();
        byte smul = 0;
        for (int i = 0; i < l; i++) {
            smul = mul(smul, s.charAt(i));
        }
        byte fmul = smul;
        for (int i = 62 - Long.numberOfLeadingZeros(x); i >= 0; i--) {
            fmul = mul(fmul, fmul);
            if ((x & (1L << i)) != 0) {
                fmul = mul(fmul, smul);
            }
        }
        if (fmul != 4) {
            printCase();
            out.println("NO");
            return;
        }
        byte cmul = 0;
        long cnt = 0;
        int pos = 0;
        for (long i = 0; i < l * x; i++) {
            cmul = mul(cmul, s.charAt((int) (i % l)));
            if (pos == 0 && cmul == 1) {
                pos = 1;
                cmul = 0;
                cnt = 0;
            } else if (pos == 1 && cmul == 2) {
                printCase();
                out.println("YES");
                return;
            } else if (++cnt >= 4 * l) {
                break;
            }
        }
        printCase();
        out.println("NO");
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