import java.util.*;
 import java.io.*;
 
 public class A {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        char[] c = in.next().toCharArray();
        int k = in.nextInt();
        int ans = 0;
        for (int i = 0; i < c.length - k + 1; i++) {
            if (c[i] == '-') {
                ans++;
                for (int j = 0; j < k; j++) {
                    if (c[i + j] == '-')
                        c[i + j] = '+';
                    else
                        c[i + j] = '-';
                }
            }
        }
        boolean ok = true;
        for (char cc : c)
            if (cc != '+') {
                ok = false;
                break;
            }
        if (ok)
            out.println(ans);
        else
            out.println("IMPOSSIBLE");
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            out = new PrintWriter(new File("a.out"));
 
            int tn = in.nextInt();
            for (int i = 0; i < tn; i++) {
                System.err.println(i);
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