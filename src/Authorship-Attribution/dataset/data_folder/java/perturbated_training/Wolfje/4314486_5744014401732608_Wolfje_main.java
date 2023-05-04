import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
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
         Scanner in = new Scanner(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskB solver = new TaskB();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskB {
         public void solve(int testNumber, Scanner in, PrintWriter out) {
             out.printf("Case #%d: ", testNumber);
             int b = in.nextInt();
             long m = in.nextLong();
             int k = 0;
             long z = 1L;
             while (z < m) {
                 k++;
                 z *= 2;
             }
             if (k + 2 > b) {
                 out.println("IMPOSSIBLE");
 
             } else {
                 long[][] mat = new long[b][b];
                 
                 if (z == m) {
                     for (int i = 0; i < k; i++) mat[0][1 + i] = 1;
                     mat[0][b - 1] = 1;
                 } else {
                     for (int i = 0; i < k; i++) {
                         if (((1L << i) & m) != 0) mat[0][1 + i] = 1;
                     }
                 }
                 for (int i = 0; i < k; i++) {
                     mat[1 + i][b - 1] = 1;
                     for (int j = 0; j < i; j++) mat[1 + i][1 + j] = 1;
                 }
                 out.println("POSSIBLE");
                 for (long[] a : mat) {
                     for (long aa : a) out.print(aa);
                     out.println();
                 }
             
             }
 
 
         }
 
     }
 }
 
