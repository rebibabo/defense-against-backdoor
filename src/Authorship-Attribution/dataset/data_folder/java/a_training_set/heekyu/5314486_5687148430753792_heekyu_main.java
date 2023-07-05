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
             int N = in.nextInt();
             int C = in.nextInt();
             int M = in.nextInt();
             int[][] tickets = new int[C][N];
             for (int i = 0; i < M; i += 1) {
                 int p = in.nextInt() - 1;
                 int c = in.nextInt() - 1;
                 tickets[c][p] += 1;
             }
             int acount = 0;
             int bcount = 0;
             for (int i = 0; i < N; i += 1) {
                 acount += tickets[0][i];
                 bcount += tickets[1][i];
             }
             int ans = Math.max(acount, bcount);
             tickets[0][0] -= Math.min(tickets[0][0], bcount - tickets[1][0]);
             tickets[1][0] -= Math.min(tickets[1][0], acount - tickets[0][0]);
             int firstconflict = Math.min(tickets[0][0], tickets[1][0]);
             ans += firstconflict;
             int promo = 0;
             for (int i = 1; i < N; i += 1) {
                 int sum = tickets[0][i] + tickets[1][i];
                 if (sum > ans) {
                     promo += sum - ans;
                 }
             }
             out.print(ans);
             out.print(" ");
             out.println(promo);
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
 
