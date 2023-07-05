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
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + testNumber + ":");
             int N = in.nextInt();
             TaskA.Party[] p = new TaskA.Party[N];
             for (int i = 0; i < N; i++) {
                 p[i] = new TaskA.Party(i, in.nextInt());
             }
             Arrays.sort(p, new Comparator<TaskA.Party>() {
 
                 public int compare(TaskA.Party o1, TaskA.Party o2) {
                     return o2.remain - o1.remain;
                 }
             });
             while (p[0].remain > 0) {
                 if (N == 2) {
                     out.print(" " + (char) ('A' + p[0].idx) + (char) ('A' + p[1].idx));
                     p[0].remain--;
                     p[1].remain--;
                     continue;
                 }
                 TaskA.Party cur = p[0];
                 out.print(" " + (char) ('A' + p[0].idx));
                 cur.remain--;
                 int my = 0;
                 while (my < N - 1 && p[my + 1].remain > cur.remain) {
                     p[my] = p[my + 1];
                     my++;
                 }
                 p[my] = cur;
                 if (cur.remain == 0) N--;
             }
             out.println();
         }
 
         static class Party {
             int idx;
             int remain;
 
             public Party(int idx, int remain) {
                 this.idx = idx;
                 this.remain = remain;
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
 
