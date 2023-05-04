import java.util.*;
 import java.io.*;
 
 public class B {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        char[] c = in.next().toCharArray();
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i + 1 < c.length; i++) {
                if (c[i] > c[i + 1]) {
                    c[i]--;
                    changed = true;
                    for (int j = i + 1; j < c.length; j++)
                        c[j] = '9';
                    break;
                }
            }
        }
        out.println(Long.parseLong(new String(c)));
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