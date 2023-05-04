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
 import java.util.HashMap;
 
 
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
         HashMap<String, Integer> indexMap;
 
         public void solve(int testNumber, Scanner in, PrintWriter out) {
             int n = in.nextInt();
             int[] first = new int[n];
             int[] second = new int[n];
             indexMap = new HashMap<String, Integer>();
             for (int i = 0; i < n; i++) {
                 first[i] = getIndex(in.next());
                 second[i] = getIndex(in.next());
             }
             int[] dp = new int[1 << n];
             for (int mask = 0; mask < (1 << n); mask++) {
                 for (int i = 0; i < n; i++) {
                     if (((1 << i) & mask) != 0) continue;
                     boolean foundFirst = false;
                     boolean foundSecond = false;
                     for (int j = 0; j < n; j++) {
                         if (((1 << j) & mask) == 0) continue;
                         foundFirst |= first[i] == first[j];
                         foundSecond |= second[i] == second[j];
                     }
                     int extra = foundFirst && foundSecond ? 1 : 0;
                     int nmask = mask | (1 << i);
                     dp[nmask] = Math.max(dp[nmask], dp[mask] + extra);
                 }
             }
             out.printf("Case #%d: %d\n", testNumber, dp[(1 << n) - 1]);
         }
 
         public int getIndex(String s) {
             if (!indexMap.containsKey(s)) {
                 indexMap.put(s, indexMap.size());
             }
             return indexMap.get(s);
         }
 
     }
 }
 
