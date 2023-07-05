import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 
 public class b {
    public static void main(String[] Args) throws Exception {
        FastScanner sc = new FastScanner(new File("bb.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter(new File(
                "b.out"))));
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            int ans = 1001;
            int n = sc.nextInt();
            int[] nums = new int[n];
            int[] counts = new int[n];
            for (int k = 0; k < n; k++) {
                counts[k] = 1;
                nums[k] = sc.nextInt();
            }
            for (int i = 0; i < 1001; i++) {
                int tans = 1001;
                int index = 0;
                for (int k = 0; k < n; k++) {
                    if (nums[k] * counts[index] >= nums[index] * counts[k]) {
                        index = k;
                        tans = i + (nums[k] + counts[k] - 1) / counts[k];
                    }
                }
                counts[index]++;
                if (ans > tans) {
                    ans = tans;
                }
            }
            out.printf("Case #%d: %d%n", ++cc, ans);
        }
        out.close();
    }
 
    public static PrintWriter out;
 
    public static class FastScanner {
        StringTokenizer st;
        BufferedReader br;
 
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
    }
 }
