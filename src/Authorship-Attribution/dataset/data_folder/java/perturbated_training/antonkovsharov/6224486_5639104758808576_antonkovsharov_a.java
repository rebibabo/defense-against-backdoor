import java.util.*;
 import java.io.*;
 
 public class A {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        int n = in.nextInt();
        char[] c = in.next().toCharArray();
        int[] a = new int[n + 1];
        for (int i = 0; i <= n; i++)
            a[i] = c[i] - '0';
        if (n == 0) {
            out.println(0);
            return;
        }
        for (int add = 0; add + a[0] <= 20 * n; add++) {
            int count = add + a[0];
            boolean bad = false;
            for (int i = 1; i <= n; i++) {
                if (count < i) {
                    bad = true;
                    break;
                }
                count += a[i];
            }
            if (!bad) {
                out.println(add);
                return;
            }
        }
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            out = new PrintWriter(new File("a.out"));
 
            int tests = in.nextInt();
            for (int i = 0; i < tests; i++) {
                out.print("Case #" + (i + 1) + ": ");
                solve();
            }
 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
 
    public static void main(String[] arg) {
        new A().run();
    }
 }