import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class c {
    public static void main(String[] Args) throws Exception {
        FastScanner sc = new FastScanner(new File("c.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(new File(
                "c.out"))));
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            long l = sc.nextLong();
            String s = sc.next();
            int cur = 0;
            int ans = 0;
            boolean canI = false;
            boolean canJ = false;
            long ll = l % 4 + (l >= 4 ? 4 : 0);
            for (int i = 0; i < ll; i++) {
                for (int k = 0; k < n; k++) {
                    cur = mult(cur, get(s.charAt(k)));
                    if (cur == 1) {
                        canI = true;
                    }
                    if (canI && cur == 3) {
                        canJ = true;
                    }
                }
            }
            ans = cur;
            out.printf("Case #%d: %s%n", ++cc,
                    (ans == 4 && canI && canJ) ? "YES" : "NO");
        }
        out.close();
    }
 
    public static int mult(int i, int j) {
        if (i > 3 && j > 3) {
            return mult(i - 4, j - 4);
        }
        if (i > 3 || j > 3) {
            return 4 ^ mult(i % 4, j % 4);
        }
        if (i == 0) {
            return j;
        }
        if (i == j) {
            return 4;
        }
        if ((i + j) % 4 == 1) {
            return (j == 2) ? 5 : 1;
        }
        if ((i + j) == 3) {
            return (j == 1) ? 7 : 3;
        }
        return (j == 3) ? 6 : 2;
    }
 
    public static int get(char c) {
        if (c == 'i')
            return 1;
        if (c == 'j')
            return 2;
        return 3;
    }
 
    public static PrintWriter out;
 
    public static class FastScanner {
        StringTokenizer st;
        BufferedReader br;
 
        FastScanner(File in) throws Exception {
            br = new BufferedReader(new FileReader(in));
            st = new StringTokenizer(br.readLine());
        }
 
        String next() throws Exception {
            if (st.hasMoreTokens()) {
                return st.nextToken();
            }
            st = new StringTokenizer(br.readLine());
            return next();
        }
 
        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }
 
        long nextLong() throws Exception {
            return Long.parseLong(next());
        }
    }
 }
