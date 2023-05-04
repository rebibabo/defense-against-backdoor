import java.io.*;
 import java.util.*;
 
 public class b {
 
    public static final String FILE_NAME = "b.in";
    public static final String OUTPUT_FILE_NAME = "b.out";
    public static PrintWriter out;
 
    public static void main(String[] Args) throws Exception {
        FastScanner sc = new FastScanner(new File(FILE_NAME));
        out = new PrintWriter(new BufferedWriter(new FileWriter(new File(
                OUTPUT_FILE_NAME))));
        int cc = 0, t = sc.nextInt();
 
        while (t-- > 0) {
            b = sc.nextInt();
            long n = sc.nextLong()-1;
            vals = new long[b];
            for (int k = 0; k < b; k++) {
                vals[k] = sc.nextLong();
            }
            long lo = 0;
            long hi = n * 100000;
            while (lo + 3 < hi) {
                long mid = (lo + hi) / 2;
                long cur = fun(mid);
 
                if (cur > n)
                    hi = mid;
                else
                    lo = mid;
            }
            while (fun(lo) <= n) {
                lo++;
            }
 
            long dif = n;
            if (lo > 0) {
                dif -= fun(lo - 1);
            }
            int ans = -1;
 
            for (int k = 0; ans == -1 && k < b; k++) {
                if (lo % vals[k] == 0) {
                    if (dif == 0) {
                        ans = k+1;
                    }
                    dif--;
                }
            }
 
            out.printf("Case #%d: %d%n", ++cc, ans);
        }
 
        out.close();
    }
 
    public static int b;
    public static long[] vals;
 
    public static long fun(long x){
        long ret = b;
        for (int k = 0; k < b; k++){
            ret += x / vals[k];
        }
        return ret;
    }
 
    public static class FastScanner {
        private StringTokenizer st;
        private BufferedReader br;
 
        FastScanner(File in) throws Exception {
            br = new BufferedReader(new FileReader(in));
            st = new StringTokenizer(br.readLine());
        }
 
        String next() throws Exception {
            if (st.hasMoreTokens()) {
                return st.nextToken();
            }
 
            st = new StringTokenizer(br.readLine());
            return next();
        }
 
        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }
 
        long nextLong() throws Exception {
            return Long.parseLong(next());
        }
    }
 }
