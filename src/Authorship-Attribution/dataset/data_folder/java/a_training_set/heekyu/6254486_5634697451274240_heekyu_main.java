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
 
     static class TaskB {
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + testNumber + ": ");
             String s = in.next();
             int N = s.length();
             int[] sign = new int[N];
             int ans = 0;
             for (int i = 0; i < N; i++) {
                 sign[i] = s.charAt(i) == '+' ? 1 : -1;
             }
             cont:
             while (true) {
                 if (sign[0] == 1) {
                     
                     for (int i = 1; i < N; i++) {
                         if (sign[i] == -1) {
                             ans++;
                             for (int j = 0; j < i; j++) {
                                 sign[j] = -1;
                             }
                             continue cont;
                         }
                     }
                     out.println(ans);
                     return;
                 } else {
                     
                     for (int i = N - 1; i >= 0; i--) {
                         if (sign[i] == -1) {
                             ans++;
                             int[] next = new int[i + 1];
                             for (int j = 0; j < next.length; j++) {
                                 next[i - j] = -sign[j];
                             }
                             for (int j = 0; j < next.length; j++) sign[j] = next[j];
                             continue cont;
                         }
                     }
                 }
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
 
     }
 }
 
