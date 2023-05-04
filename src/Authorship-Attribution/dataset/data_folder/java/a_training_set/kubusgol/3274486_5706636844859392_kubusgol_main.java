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
         private static final int INF = (int) 1e7;
         private static final int CAMERON = 0;
         private static final int JAMIE = 1;
         int DAY = 1440;
         int HALF = 720;
         boolean[] cameronBusy;
         boolean[] jamieBusy;
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             int AC = in.nextInt();
             int AJ = in.nextInt();
 
             int[] C = new int[AC];
             int[] D = new int[AC];
 
             int[] J = new int[AJ];
             int[] K = new int[AJ];
 
 
             cameronBusy = new boolean[DAY];
             jamieBusy = new boolean[DAY];
 
             for (int i = 0; i < AC; i++) {
                 C[i] = in.nextInt();
                 D[i] = in.nextInt();
 
                 for (int j = C[i]; j < D[i]; j++) {
                     cameronBusy[j] = true;
                 }
             }
             for (int i = 0; i < AJ; i++) {
                 J[i] = in.nextInt();
                 K[i] = in.nextInt();
 
                 for (int j = J[i]; j < K[i]; j++) {
                     jamieBusy[j] = true;
                 }
             }
 
             int ANS = Math.min(licz(false), licz(true));
 
             out.println(String.format("Case #%d: %d", testNumber, ANS));
         }
 
         private int licz(boolean cameronStarts) {
             int[][][] exch = new int[DAY + 1][HALF + 1][2];
 
             
             
             
 
             for (int[][] x : exch) for (int[] y : x) Arrays.fill(y, INF);
 
             if (cameronStarts) {
                 exch[0][0][CAMERON] = 0;
                 exch[0][0][JAMIE] = INF;
             } else {
                 exch[0][0][CAMERON] = INF;
                 exch[0][0][JAMIE] = 0;
             }
 
             for (int total = 1; total <= DAY; total++) {
                 for (int cameron = 0; cameron <= HALF; cameron++) {
                     for (int last = 0; last <= 1; last++) {
                         if (last == CAMERON) {
                             if (!cameronBusy[total - 1]) {
                                 
                                 exch[total][cameron][last] = Math.min(exch[total][cameron][last], cameron == 0 ? INF : exch[total - 1][cameron - 1][CAMERON]);
                                 exch[total][cameron][last] = Math.min(exch[total][cameron][last], cameron == 0 ? INF : (1 + exch[total - 1][cameron - 1][JAMIE]));
                             }
                         } else {
                             if (!jamieBusy[total - 1]) {
                                 
                                 exch[total][cameron][last] = Math.min(exch[total][cameron][last], exch[total - 1][cameron][JAMIE]);
                                 exch[total][cameron][last] = Math.min(exch[total][cameron][last], 1 + exch[total - 1][cameron][CAMERON]);
                             }
                         }
 
                     }
                 }
             }
             int A1 = cameronStarts ? exch[DAY][HALF][CAMERON] : exch[DAY][HALF][CAMERON] + 1;
             int A2 = cameronStarts ? exch[DAY][HALF][JAMIE] + 1 : exch[DAY][HALF][JAMIE];
 
             int ANS = Math.min(A1, A2);
             return ANS;
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
 
