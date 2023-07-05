import java.util.*;
 import java.io.*;
 
 public class B {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        char[] c = in.next().toCharArray();
        int ans = 0;
        while (true) {
            boolean isMinus = false;
            for (char cc : c) {
                if (cc == '-') {
                    isMinus = true;
                }
            }
            if (!isMinus) {
                break;
            }
            ans++;
            if (c[0] == '+') {
                int p = 0;
                while (p < c.length && c[p] == '+') {
                    c[p++] = '-';
                }
            } else {
                int p = c.length;
                while (p > 0 && c[p - 1] == '+') {
                    p--;
                }
                int l = 0, r = p - 1;
                while (l < r) {
                    char t = c[l];
                    c[l] = c[r];
                    c[r] = t;
                    l++;
                    r--;
                }
                for (int i = 0; i < p; i++) {
                    c[i] = (c[i] == '+') ? '-' : '+';
                }
            }
        }
        out.println(ans);
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
        new B().run();
    }
 }