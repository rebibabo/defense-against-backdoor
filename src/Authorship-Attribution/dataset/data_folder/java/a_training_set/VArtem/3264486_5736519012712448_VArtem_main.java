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
         FastScanner in = new FastScanner(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskA solver = new TaskA();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskA {
         public void solve(int testNumber, FastScanner in, PrintWriter out) {
             out.printf("Case #%d: ", testNumber);
             solve(in, out);
         }
 
         public void solve(FastScanner in, PrintWriter out) {
             char[] c = in.next().toCharArray();
             int k = in.nextInt();
             int ans = 0;
             for (int i = 0; i < c.length; i++) {
                 if (c[i] == '-') {
                     if (i + k - 1 >= c.length) {
                         out.println("IMPOSSIBLE");
                         return;
                     }
                     ans++;
                     for (int j = 0; j < k; j++) {
                         c[i + j] ^= '-' ^ '+';
                     }
                 }
             }
             out.println(ans);
         }
 
     }
 
     static class FastScanner {
         BufferedReader br;
         StringTokenizer st;
 
         public FastScanner(InputStream in) {
             br = new BufferedReader(new InputStreamReader(in));
         }
 
         public String next() {
             while (st == null || !st.hasMoreElements()) {
                 String line = null;
                 try {
                     line = br.readLine();
                 } catch (IOException e) {
                 }
                 st = new StringTokenizer(line);
             }
             return st.nextToken();
         }
 
         public int nextInt() {
             return Integer.parseInt(next());
         }
 
     }
 }
 
