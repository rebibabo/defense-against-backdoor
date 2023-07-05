import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.System.exit;
 import static java.util.Arrays.fill;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class E {
    
    static class Card {
        final int value, suite;
 
        Card(int value, int suite) {
            this.value = value;
            this.suite = suite;
        }
        
        public String toString() {
            return suite + ":" + value;
        }
    }
    
    static Card[][] premade;
    
    static Card curState[][];
    static int curSize[];
 
    static void solve() throws Exception {
        int n = scanInt();
        curState = new Card[n][];
        curSize = new int[n];
        fill(curSize, scanInt());
        for (int i = 0; i < n; i++) {
            int j = scanInt();
            curState[i] = premade[j].clone();
        }
 
        boolean res = go();
        printCase();
        out.println(res ? "POSSIBLE " : "IMPOSSIBLE");
    }
    
    static boolean go() {
        int n = curState.length;
        boolean big = false;
        for (int i = 0; i < n; i++) {
            int si = curSize[i];
            if (si > 1) {
                big = true;
            }
            if (si == 0) {
                for (int j = 0; j < n; j++) {
                    int sj = curSize[j];
                    if (sj >= 2) {
                        Card i0 = curState[i][0];
                        curState[i][0] = curState[j][sj - 1];
                        curSize[i] = 1;
                        curSize[j] = sj - 1;
                        if (go()) {
                            return true;
                        }
                        curState[i][0] = i0;
                        curSize[i] = 0;
                        curSize[j] = sj;
                    }
                }
                continue;
            }
            Card iTop = curState[i][si - 1];
            for (int j = i + 1; j < n; j++) {
                int sj = curSize[j];
                if (sj == 0) {
                    continue;
                }
                Card jTop = curState[j][sj - 1];
                if (iTop.suite == jTop.suite) {
                    if (iTop.value < jTop.value) {
                        curSize[i] = si - 1;
                        if (go()) {
                            return true;
                        }
                        curSize[i] = si;
                    } else {
                        curSize[j] = sj - 1;
                        if (go()) {
                            return true;
                        }
                        curSize[j] = sj;
                    }
                }
            }
        }
        return !big;
    }
 
    static int scanInt() throws IOException {
        return parseInt(scanString());
    }
 
    static long scanLong() throws IOException {
        return parseLong(scanString());
    }
 
    static String scanString() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }
 
    static void printCase() {
        out.print("Case #" + test + ": ");
    }
 
    static void printlnCase() {
        out.println("Case #" + test + ":");
    }
 
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
 
    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            int p = scanInt();
            premade = new Card[p][];
            for (int i = 0; i < p; i++) {
                int c = scanInt();
                premade[i] = new Card[c];
                for (int j = 0; j < c; j++) {
                    premade[i][c - j - 1] = new Card(scanInt(), scanInt());
                }
            }
            int tests = scanInt();
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