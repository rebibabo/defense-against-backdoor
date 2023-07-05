import java.util.*;
 import java.io.*;
 
 public class A {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        int n = in.nextInt();
        if (n == 0) {
            out.println("INSOMNIA");
            return;
        }
        int x = 0;
        boolean[] was = new boolean[10];
        while (true) {
            x += n;
            char[] c = Integer.toString(x).toCharArray();
            for (char cc : c) {
                was[cc - '0'] = true;
            }
            boolean stop = true;
            for (int i = 0; i < was.length; i++) {
                if (!was[i]) {
                    stop = false;
                }
            }
            if (stop) {
                break;
            }
        }
        out.println(x);
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