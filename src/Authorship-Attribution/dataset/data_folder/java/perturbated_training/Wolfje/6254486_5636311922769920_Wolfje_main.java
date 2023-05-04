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
             final String regex = "D-(small|large).*[.]in";
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
             outputStream = new FileOutputStream("d.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         Scanner in = new Scanner(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskD solver = new TaskD();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskD {
         public void solve(int testNumber, Scanner in, PrintWriter out) {
             int k = in.nextInt();
             int c = in.nextInt();
             int s = in.nextInt();
             out.printf("Case #%d:", testNumber);
             if (c == 1) {
                 if (s < k) out.println(" IMPOSSIBLE");
                 else {
                     for (int i = 1; i <= s; i++) out.print(" " + i);
                     out.println();
                 }
             } else {
                 if (s < (k + 1) / 2) out.println(" IMPOSSIBLE");
                 else {
                     for (int i = 0; i < (k + 1) / 2; i++) out.print(" " + (i * k + k - i));
                     out.println();
                 }
             }
         }
 
     }
 }
 
