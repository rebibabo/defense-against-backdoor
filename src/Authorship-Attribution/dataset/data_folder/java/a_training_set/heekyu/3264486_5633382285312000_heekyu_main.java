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
 import java.util.ArrayList;
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
             long N = in.nextLong();
             long ans = N;
             ArrayList<Integer> digits = new ArrayList<>();
             while (N > 0) {
                 digits.add((int) (N % 10));
                 N /= 10L;
             }
             int L = digits.size();
             for (int i = L - 1; i > 0; i -= 1) {
                 if (digits.get(i) <= digits.get(i - 1)) {
                     continue;
                 }
                 ans = 0;
                 int s = i;
                 while (s < L - 1 && digits.get(s) == digits.get(s + 1)) {
                     s += 1;
                 }
                 for (int j = L - 1; j >= s; j -= 1) {
                     ans += digits.get(j);
                     ans *= 10;
                 }
                 ans = ans * (long) Math.pow(10, s - 1) - 1;
                 out.println(ans);
                 return;
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
 
         public long nextLong() {
             return Long.parseLong(next());
         }
 
     }
 }
 
