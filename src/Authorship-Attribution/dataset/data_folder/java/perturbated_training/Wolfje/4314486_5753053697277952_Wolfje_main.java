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
 import java.util.ArrayList;
 
 
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
         Scanner in = new Scanner(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskA solver = new TaskA();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskA {
         public void solve(int testNumber, Scanner in, PrintWriter out) {
             int n = in.nextInt();
             int[] count = new int[n];
             int partiesLeft = n;
             for (int i = 0; i < n; i++) count[i] = in.nextInt();
             int senators = 0;
             for (int s : count) senators += s;
             ArrayList<Integer> largest = new ArrayList<>();
             int best;
             out.printf("Case #%d: ", testNumber);
             while (senators > 0) {
                 best = 0;
                 largest.clear();
                 for (int i = 0; i < n; i++) {
                     if (count[i] > best) {
                         largest.clear();
                         best = count[i];
                         largest.add(i);
                     } else if (count[i] == best) {
                         largest.add(i);
                     }
                 }
                 String s = "" + toChar(largest.get(0));
                 count[largest.get(0)]--;
                 senators--;
                 if (largest.size() == 2) {
                     s += toChar(largest.get(1));
                     count[largest.get(1)]--;
                     senators--;
                 }
                 out.print(s + " ");
             }
             out.println();
         }
 
         public char toChar(int a) {
             return (char) ('A' + a);
         }
 
     }
 }
 
