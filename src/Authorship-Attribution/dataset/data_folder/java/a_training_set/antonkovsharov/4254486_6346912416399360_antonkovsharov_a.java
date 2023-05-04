import java.util.*;
 import java.io.*;
 
 public class A {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        int n = in.nextInt(), k = in.nextInt();
        long[] sum = new long[n - k + 1];
        long[] a = new long[n];
        long[] l = new long[k];
        long[] r = new long[k];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = in.nextInt();
            if (i > 0) {
                long x = sum[i] - sum[i - 1];
                int id = i - 1;
                a[id + k] = a[id] + x;
                l[id % k] = Math.min(l[id % k], a[id + k]);
                r[id % k] = Math.max(r[id % k], a[id + k]);
            }
        }
        long s = sum[0];
        long ans = Long.MAX_VALUE;
        for (int max = 0; max < k; max++) {
            long now = 0;
            long minL = l[max], maxL = r[max] + 10;
            for (int i = 0; i < k; i++) {
                now += r[max] - r[i];
                minL = Math.min(minL, r[max] - r[i] + l[i]);
            }
            now += s;
            while (now <= -k)
                now += k;
            while (now > 0)
                now -= k;
            now = Math.abs(now);
            minL -= now;
            minL = Integer.MIN_VALUE;
            
            maxL++;
            while (maxL - minL > 1) {
                long curL = (minL + maxL) / 2;
                long cur = now;
                boolean ok = true;
                for (int i = 0; i < k; i++) {
                    if (r[max] - curL < r[i] - l[i]) {
                        ok = false;
                        break;
                    }
                    if (i == max)
                        continue;
                    long here = l[i] + (r[max] - r[i]) - curL;
                    if (here < 0)
                        throw new AssertionError();
                    cur -= Math.min(cur, here);
                }
                if (cur == 0 && ok)
                    minL = curL;
                else
                    maxL = curL;
            }
            
            long maxVal = r[max];
            long minVal = minL;
            ans = Math.min(ans, maxVal - minVal);
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
                System.out.println(i);
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