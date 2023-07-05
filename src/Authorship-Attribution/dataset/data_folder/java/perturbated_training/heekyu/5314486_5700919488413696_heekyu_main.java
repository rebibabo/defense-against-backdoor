import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.StringTokenizer;
 import java.io.IOException;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
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
 
     static class TaskA {
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + testNumber + ": ");
             int N = in.nextInt();
             int P = in.nextInt();
             int[] cnt = new int[P];
             for (int i = 0; i < N; i += 1) {
                 cnt[in.nextInt() % P] += 1;
             }
             int ans = cnt[0] + 1;
             if (ans >= N) {
                 out.println(N);
                 return;
             }
             if (P == 2) {
                 ans += (cnt[1] - 1) / 2;
             } else if (P == 3) {
                 int group = N - cnt[0];
                 int pair = Math.min(cnt[1], cnt[2]);
                 ans += pair;
                 cnt[1] -= pair;
                 cnt[2] -= pair;
                 group -= pair * 2;
                 if (cnt[1] >= 3) {
                     int more = cnt[1] / 3;
                     ans += more;
                     group -= more * 3;
                 }
                 if (cnt[2] >= 3) {
                     int more = cnt[2] / 3;
                     ans += more;
                     group -= more * 3;
                 }
                 if (group == 0) ans -= 1;
             }
             out.println(ans);
         }
 
     }
 
     static class InputReader {
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
 }
 
