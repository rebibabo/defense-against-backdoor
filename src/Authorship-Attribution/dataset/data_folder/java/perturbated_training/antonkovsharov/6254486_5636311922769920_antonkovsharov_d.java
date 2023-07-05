import java.util.*;
 import java.io.*;
 
 public class D {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        int k = in.nextInt(), c = in.nextInt(), s = in.nextInt();
        int need = (k + c - 1) / c;
        if (need > s) {
            out.println("IMPOSSIBLE");
        } else {
            int id = 0;
            for (int i = 0; i < need; i++) {
                long x = 0;
                for (int j = 0; j < c; j++) {
                    if (id < k) {
                        x = x * k + id;
                        id++;
                    }
                }
                out.print((x + 1) + " ");
            }
            out.println();
        }
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
        new D().run();
    }
 }