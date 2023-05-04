import java.util.*;
 import java.io.*;
 
 public class B {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        int n = in.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++)
            a.add(in.nextInt());
        Collections.sort(a);
        int maxAi = a.get(a.size() - 1);
        int ans = Integer.MAX_VALUE;
        for (int eatTime = 1; eatTime <= maxAi; eatTime++) {
            int specTime = 0;
            for (int x : a) {
                int parts = (x + eatTime - 1) / eatTime;
                specTime += parts - 1;
            }
            ans = Math.min(ans, specTime + eatTime);
        }
        out.println(ans);
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
        new B().run();
    }
 }