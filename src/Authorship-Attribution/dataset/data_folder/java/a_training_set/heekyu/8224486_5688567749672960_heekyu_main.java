import java.io.InputStreamReader;
 import java.io.IOException;
 import java.util.Locale;
 import java.io.BufferedReader;
 import java.io.OutputStream;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.io.FileInputStream;
 import java.io.File;
 import java.util.StringTokenizer;
 import java.io.FilenameFilter;
 import java.io.InputStream;
 
 
 public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "A-(small|large).*[.]in";
            File directory = new File(".");
            File[] candidates = directory.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.matches(regex);
                }
            });
            File toRun = null;
            for (File candidate : candidates) {
                if (toRun == null || candidate.lastModified() > toRun.lastModified())
                    toRun = candidate;
            }
            inputStream = new FileInputStream(toRun);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream("a.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskA {
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         out.print("Case #" + testNumber + ": ");
         int N = in.nextInt();
         if (N <= 10) {
             out.println(N);
             return;
         }
         int[] dp = new int[N+1];
         dp[1] = 1;
         boolean[] visit = new boolean[N+1];
         visit[1] = true;
         int[] v = new int[N];
         v[0] = 1;
         int p = 1;
         for (int i = 0; i < N; i++) {
             if (v[i] == N) {
                 out.println(dp[N]);
                 return;
             }
             if (!visit[v[i]+1]) {
                 visit[v[i]+1] = true;
                 dp[v[i]+1] = dp[v[i]] + 1;
                 v[p++] = v[i]+1;
             }
             int r = reverse(v[i]);
             if (r <= N && !visit[r]) {
                 visit[r] = true;
                 dp[r] = dp[v[i]] + 1;
                 v[p++] = r;
             }
         }
     }
     private int reverse(int N) {
         int res = 0;
         while (N > 0) {
             res *= 10;
             res += N%10;
             N /= 10;
         }
         return res;
     }
 }
 
 class InputReader {
     public BufferedReader reader;
     public StringTokenizer tokenizer;
 
     public InputReader(InputStream stream) {
         reader = new BufferedReader(new InputStreamReader(stream));
         tokenizer = null;
     }
 
     public String next() {
         while (tokenizer == null || !tokenizer.hasMoreTokens()) {
             try {
                 tokenizer = new StringTokenizer(reader.readLine());
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }
         }
         return tokenizer.nextToken();
     }
 
     public int nextInt() {
         return Integer.parseInt(next());
     }
 
 }
 
