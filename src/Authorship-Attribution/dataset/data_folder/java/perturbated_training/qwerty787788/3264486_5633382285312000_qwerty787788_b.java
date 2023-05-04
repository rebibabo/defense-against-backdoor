import java.io.*;
 import java.util.*;
 
 public class B {
    FastScanner in;
    PrintWriter out;
 
    void solveOneTest() {
        char[] s = in.next().toCharArray();
        for (int i = 0; i < s.length; i++) {
            char res;
            for (res = '9'; res >= '0'; res--) {
                boolean ok = true;
                for (int j = i; j < s.length; j++) {
                    if (s[j] > res) {
                        break;
                    }
                    if (s[j] < res) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    if (res != '0') {
                        out.print(res);
                    }
                    break;
                }
            }
            if (res != s[i]) {
                for (int j = i + 1; j < s.length; j++) {
                    out.print('9');
                }
                break;
            }
        }
        out.println();
    }
 
    void solve() {
        int tc = in.nextInt();
        for (int t = 0; t < tc; t++) {
            System.err.println("test " + t);
            out.print("Case #" + (t + 1) + ": ");
            solveOneTest();
        }
    }
 
    void run() {
        try {
            in = new FastScanner(new File("B.in"));
            out = new PrintWriter(new File("B.out"));
 
            solve();
 
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    void runIO() {
 
        in = new FastScanner(System.in);
        out = new PrintWriter(System.out);
 
        solve();
 
        out.close();
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        public FastScanner(InputStream f) {
            br = new BufferedReader(new InputStreamReader(f));
        }
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                String s = null;
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null)
                    return null;
                st = new StringTokenizer(s);
            }
            return st.nextToken();
        }
 
        boolean hasMoreTokens() {
            while (st == null || !st.hasMoreTokens()) {
                String s = null;
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null)
                    return false;
                st = new StringTokenizer(s);
            }
            return true;
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
 
    public static void main(String[] args) {
        new B().run();
    }
 }