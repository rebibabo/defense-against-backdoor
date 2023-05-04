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
 import java.util.Scanner;
 
 
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
         Scanner in = new Scanner(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskC solver = new TaskC();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         int[] tmp = new int[9];
         int[] somePrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
 
         public void solve(int testNumber, Scanner in, PrintWriter out) {
             int n = in.nextInt();
             int need = in.nextInt();
             int found = 0;
             out.printf("Case %d:\n", testNumber);
             for (long z = 0; z < 1 << (n - 2) && need > found; z++) {
                 long mask = 1L << (n - 1) | (z << 1) | 1;
                 int[] divisors = check(mask, n);
                 if (divisors != null) {
                     found++;
                     out.print(Long.toBinaryString(mask));
                     for (int d : divisors) out.print(" " + d);
                     out.println();
                 }
             }
         }
 
         int[] check(long mask, int n) {
             for (int b = 2; b <= 10; b++) {
                 boolean found = false;
                 for (int p : somePrimes) {
                     int modBK = 1;
                     int modSum = 0;
                     for (int i = 0; i < n; i++) {
                         if (((1L << i) & mask) != 0) modSum += modBK;
                         modBK = (modBK * b) % p;
                     }
                     if (modSum % p == 0) {
                         found = true;
                         tmp[b - 2] = p;
                         break;
                     }
                 }
                 if (!found) return null;
             }
             return Arrays.copyOf(tmp, 9);
         }
 
     }
 }
 
