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
         String str = "ROYGBV";
         static String IMP = "IMPOSSIBLE";
 
         private String solve2(int n, char a, char b) {
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < 2 * n; i += 1) {
                 if (i % 2 == 0) {
                     sb.append(a);
                 } else {
                     sb.append(b);
                 }
             }
             return sb.toString();
         }
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + testNumber + ": ");
             int N = in.nextInt();
             int R = in.nextInt();
             int O = in.nextInt();
             int Y = in.nextInt();
             int G = in.nextInt();
             int B = in.nextInt();
             int V = in.nextInt();
             if (R == 0) {
                 out.println(Y == B ? solve2(Y, 'Y', 'B') : IMP);
                 return;
             }
             if (Y == 0) {
                 out.println(R == B ? solve2(R, 'R', 'B') : IMP);
                 return;
             }
             if (B == 0) {
                 out.println(R == Y ? solve2(R, 'R', 'Y') : IMP);
                 return;
             }
             if (R > Y + B || Y > R + B || B > R + Y) {
                 out.println(IMP);
                 return;
             }
             int[] cnt = new int[]{R, Y, B};
             int[] inds = new int[]{0, 2, 4};
             for (int i = 0; i < cnt.length; i += 1) {
                 int best = i;
                 for (int j = i; j < cnt.length; j += 1) {
                     if (cnt[j] > cnt[best]) {
                         best = j;
                     }
                 }
                 if (best > i) {
                     int cc = cnt[i];
                     int ii = inds[i];
                     cnt[i] = cnt[best];
                     inds[i] = inds[best];
                     cnt[best] = cc;
                     inds[best] = ii;
                 }
             }
             int[] answer = new int[N];
             int ind = 0;
             for (int i = 0; i < N; i += 2) {
                 cnt[ind] -= 1;
                 answer[i] = inds[ind];
                 if (cnt[ind] == 0) ind += 1;
             }
             for (int i = 1; i < N; i += 2) {
                 cnt[ind] -= 1;
                 answer[i] = inds[ind];
                 if (cnt[ind] == 0) ind += 1;
             }
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < N; i += 1) {
                 sb.append(str.charAt(answer[i]));
             }
             out.println(sb.toString());
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
 
