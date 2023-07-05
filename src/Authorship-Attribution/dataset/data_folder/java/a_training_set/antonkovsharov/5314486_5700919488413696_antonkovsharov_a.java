import java.util.*;
 import java.io.*;
 
 public class A {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        int n = in.nextInt();
        int p = in.nextInt();
        int[] cnt = new int[p];
        for (int i = 0; i < n; i++) {
            cnt[in.nextInt() % p]++;
        }
        int ans = cnt[0];
        if (p == 2) {
            ans += (cnt[1] + 1) / 2;
        } else if (p == 3) {
            int x = Math.min(cnt[1], cnt[2]);
            ans += x;
            cnt[1] -= x;
            cnt[2] -= x;
            ans += (cnt[1] + cnt[2] + 2) / 3;
        } else if (p == 4) {
            
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
        new A().run();
    }
 }