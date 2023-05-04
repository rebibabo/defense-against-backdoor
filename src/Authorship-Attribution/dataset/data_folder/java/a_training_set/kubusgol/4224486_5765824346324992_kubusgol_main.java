import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.BufferedReader;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.IOException;
 import java.io.FileOutputStream;
 import java.io.File;
 import java.util.StringTokenizer;
 
 
 public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        InputStream inputStream;
        try {
            final String regex = "B-(small|large).*[.]in";
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
            outputStream = new FileOutputStream("b.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskB {
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         int B = in.nextInt();
         int N = in.nextInt();
         int M[] = new int[B];
         for (int i = 0; i < B; i++) {
             M[i] = in.nextInt();
         }
 
         long lo = 0, hi = 10L * Integer.MAX_VALUE;
 
         while (lo + 1 < hi) {
             long mid = (lo + hi) >> 1;
             if (value(mid, M) >= N) {
                 hi = mid;
             } else {
                 lo = mid;
             }
         }
 
         long val = value(hi, M);
         int ans = 0;
         for (int i = M.length - 1; i >= 0; i--) {
             if (hi % M[i] == 0) {
                 if (val == N){
                     out.println("Case #" + testNumber + ": " + (i + 1));
                     return;
                 }
                 val--;
             }
         }
         out.println("Case #" + testNumber + ": " + (M.length));
         return;
     }
 
     private long value(long mid, int[] m) {
         long ans = 0;
         for (int i = 0; i < m.length; i++) {
             ans += (mid+1) / m[i];
             if ((mid+1) % m[i] > 0) ans++;
         }
         return ans;
     }
 }
 
 class InputReader {
     public BufferedReader reader;
     public StringTokenizer tokenizer;
 
     public InputReader(InputStream stream) {
         reader = new BufferedReader(new InputStreamReader(stream), 32768);
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
 
