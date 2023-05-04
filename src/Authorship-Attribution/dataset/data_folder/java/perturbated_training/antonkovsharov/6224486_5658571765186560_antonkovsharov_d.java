import java.util.*;
 import java.io.*;
 
 public class D {
    FastScanner in;
    PrintWriter out;
 
    int count = 0;
    
    public void solve() throws IOException {
        int x = in.nextInt(), r = in.nextInt(), c = in.nextInt();
        if (r > c) {
            int tmp = r;
            r = c;
            c = tmp;
        }
        String ok = "GABRIEL";
        String bad = "RICHARD";
        if (r * c % x != 0) {
            out.println(bad);
        } else if (x <= 2) {
            out.println(ok);
        } else if (r == 1) {
            
            out.println(bad);
        } else if (x > c) {
            out.println(bad);
        } else if (x == 3){
            out.println(ok);
        } else if (r == 2) {
            out.println(bad);
        } else {
            out.println(ok);
            
            
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
            System.out.println(count);
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