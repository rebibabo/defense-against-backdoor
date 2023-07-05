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
         FastScanner in = new FastScanner(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskC solver = new TaskC();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         public void solve(int testNumber, FastScanner in, PrintWriter out) {
             out.printf("Case #%d: ", testNumber);
             solve(in, out);
         }
 
         public void solve(FastScanner in, PrintWriter out) {
             long n = in.nextLong(), k = in.nextLong();
             out.println(get(n, k));
         }
 
         String get(long n, long k) {
             n--;
             k--;
             if (k == 0) {
                 return ((n + 1) / 2) + " " + (n / 2);
             }
             long n1 = n / 2, n2 = (n + 1) / 2;
             long k1 = k / 2, k2 = (k + 1) / 2;
             if (k % 2 == 1) {
                 return get(n2, k2);
             } else {
                 return get(n1, k1);
             }
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
 
         public long nextLong() {
             return Long.parseLong(next());
         }
 
     }
 }
 
