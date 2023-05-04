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
 import java.util.TreeSet;
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
         char[] AA = new char[]{'R', 'Y', 'B'};
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             int N = in.nextInt();
             
             int RED = in.nextInt();
             in.nextInt();
             int YELLOW = in.nextInt();
             in.nextInt();
             int BLUE = in.nextInt();
             in.nextInt();
 
             TreeSet<Color> colors = new TreeSet<>();
             if (RED > 0) colors.add(new Color(0, RED));
             if (YELLOW > 0) colors.add(new Color(1, YELLOW));
             if (BLUE > 0) colors.add(new Color(2, BLUE));
             char[] ANS = new char[N];
             int cur = 0;
             while (colors.size() > 0) {
                 Color c = colors.pollLast();
                 ANS[cur++] = AA[c.idx];
 
                 if (c.count > 1) {
                     c.count--;
                     colors.add(c);
                 }
             }
 
             for (int i = 0; i < N; i++) {
                 if (ANS[i] == ANS[(N + i - 1) % N]) {
                     out.println("Case #" + testNumber + ": IMPOSSIBLE");
                     return;
                 }
             }
             out.println("Case #" + testNumber + ": " + new String(ANS));
         }
 
         private class Color implements Comparable<Color> {
             private int idx;
             private int count;
 
             public Color(int idx, int count) {
 
                 this.idx = idx;
                 this.count = count;
             }
 
 
             public int compareTo(Color that) {
                 if (this.count < that.count) return -1;
                 if (this.count > that.count) return +1;
                 return this.idx - that.idx;
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
 
