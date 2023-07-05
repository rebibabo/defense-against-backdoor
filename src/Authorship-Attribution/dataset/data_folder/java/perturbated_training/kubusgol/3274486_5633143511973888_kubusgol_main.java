import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.TreeSet;
 import java.util.StringTokenizer;
 import java.io.BufferedReader;
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
             int N = in.nextInt();
             int K = in.nextInt();
 
             int mul = 100000;
             int U = (int) (Double.valueOf(in.next()) * mul);
 
             int[] P = new int[N];
             for (int i = 0; i < N; i++) {
                 P[i] = (int) (Double.valueOf(in.next()) * mul);
             }
 
             TreeSet<Prob> set = new TreeSet<>();
             for (int i = 0; i < N; i++) {
                 set.add(new Prob(i, P[i]));
             }
 
             while (U > 0) {
                 Prob prob = set.pollFirst();
                 if (prob.value < mul) {
                     prob.value++;
                 }
                 U--;
                 set.add(prob);
             }
 
             double ANS = 1D;
             for (Prob p : set) {
                 ANS *= p.value / 100000D;
             }
 
             out.println(String.format("Case #%d: %f", testNumber, ANS));
         }
 
         private class Prob implements Comparable<Prob> {
             int id;
             int value;
 
             public Prob(int id, int value) {
                 this.id = id;
                 this.value = value;
             }
 
 
             public int compareTo(Prob that) {
                 if (this.value < that.value) return -1;
                 if (this.value > that.value) return +1;
                 return this.id - that.id;
             }
 
         }
 
     }
 
     static class InputReader {
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
 }
 
