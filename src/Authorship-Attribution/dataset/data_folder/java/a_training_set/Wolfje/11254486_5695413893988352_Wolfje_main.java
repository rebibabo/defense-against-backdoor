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
             String sC = in.next();
             String sJ = in.next();
             int n = sC.length();
             ArrayList<String> candC = getCandidates(sC);
             ArrayList<String> candJ = getCandidates(sJ);
             Integer bestDelta = 100000;
             String bestC = "";
             String bestJ = "";
             for (String cc : candC) {
                 int vcc = Integer.valueOf(cc);
                 for (String jj : candJ) {
                     int vjj = Integer.valueOf(jj);
                     int delta = Math.abs(vcc - vjj);
                     if (delta < bestDelta) {
                         bestC = cc;
                         bestJ = jj;
                         bestDelta = delta;
                     }
                 }
             }
             out.printf("Case #%d: %s %s\n", testNumber, bestC, bestJ);
         }
 
         public ArrayList<String> getCandidates(String s) {
             int n = s.length();
             ArrayList<String> result = new ArrayList<String>();
             int maxZ = 1;
             for (int i = 0; i < n; i++) maxZ *= 10;
             for (int z = 0; z < maxZ; z++) {
                 String u = Integer.toString(z);
                 while (u.length() != n) u = "0" + u;
                 boolean ok = true;
                 for (int i = 0; i < n; i++) {
                     ok &= s.charAt(i) == '?' || s.charAt(i) == u.charAt(i);
                 }
                 if (ok) result.add(u);
             }
             return result;
         }
 
     }
 }
 
