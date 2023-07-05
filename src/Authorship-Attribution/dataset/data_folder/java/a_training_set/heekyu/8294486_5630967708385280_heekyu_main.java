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
 import java.util.Comparator;
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
         private boolean can(double d, double D, TaskA.Horse[] horses) {
             for (int i = 0; i < horses.length; i += 1) {
                 if (d > horses[i].s) {
                     double t = horses[i].k / (d - horses[i].s);
                     if (t * d < D) {
                         return false;
                     }
                 }
             }
             return true;
         }
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + testNumber + ": ");
             double D = in.nextInt();
             int N = in.nextInt();
             TaskA.Horse[] horses = new TaskA.Horse[N];
             for (int i = 0; i < N; i += 1) {
                 horses[i] = new TaskA.Horse(in.nextInt(), in.nextInt());
             }
             Arrays.sort(horses, new Comparator<TaskA.Horse>() {
 
                 public int compare(TaskA.Horse o1, TaskA.Horse o2) {
                     if (o1.k < o2.k) return -1;
                     else if (o1.k > o2.k) return 1;
                     return 0;
                 }
             });
             double lo = 0.0;
             double hi = 1e17;
             for (int i = 0; i < 100; i += 1) {
                 double mid = (lo + hi) / 2;
                 if (can(mid, D, horses)) {
                     lo = mid;
                 } else {
                     hi = mid;
                 }
             }
             out.println(hi);
         }
 
         static class Horse {
             int k;
             int s;
 
             public Horse(int k, int s) {
                 this.k = k;
                 this.s = s;
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
 
