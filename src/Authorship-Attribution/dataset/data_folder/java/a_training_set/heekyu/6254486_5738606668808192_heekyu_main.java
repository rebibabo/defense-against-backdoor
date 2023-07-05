import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.Arrays;
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
             final String regex = "C-(small|large).*[.]in";
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
             outputStream = new FileOutputStream("c.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskC solver = new TaskC();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.println("Case #" + testNumber + ":");
             int N = in.nextInt();
             int J = in.nextInt();
             long[][] vals = new long[11][N];
             for (int base = 2; base <= 10; base++) {
                 vals[base][0] = 1L;
                 for (int i = 1; i < N; i++) {
                     vals[base][i] = vals[base][i - 1] * base;
                 }
             }
             int made = 0;
             long[] values = new long[11];
             cont:
             for (int mid = 0; mid < (1 << (N - 2)); mid++) {
                 Arrays.fill(values, 0);
                 long v = -1;
                 for (int base = 2; base <= 10; base++) {
                     v = vals[base][N - 1] + 1;
                     for (int i = 1; i < N - 1; i++) {
                         if (((1 << (i - 1)) & mid) > 0) {
                             v += vals[base][i];
                         }
                     }
                     for (long i = 2; i * i <= v; i++) {
                         if (v % i == 0) {
                             values[base] = i;
                             break;
                         }
                     }
                     if (values[base] == 0) continue cont;
                 }
                 made++;
                 out.print(v);
                 for (int i = 2; i <= 10; i++) {
                     out.print(" " + values[i]);
                 }
                 out.println();
                 if (made == J) break;
             }
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
 
