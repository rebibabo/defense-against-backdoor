import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.StringTokenizer;
 import java.util.HashMap;
 import java.io.IOException;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.io.InputStream;
 
 
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
         InputReader in = new InputReader(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskC solver = new TaskC();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + testNumber + ": ");
             int J = in.nextInt();
             int P = in.nextInt();
             int S = in.nextInt();
             int K = in.nextInt();
             if (S == 1) {
                 out.println(1);
                 out.println("1 1 1");
                 return;
             }
             ArrayList<String> ans = new ArrayList<>();
             HashMap<String, Integer> pairs = new HashMap<>();
             for (int j = 1; j <= J; j++) {
                 HashSet<String> localPairs = new HashSet<>();
                 boolean more = true;
                 int[] cused = new int[P];
                 int[] sused = new int[S];
                 while (more) {
                     more = false;
                     int minUsed = K;
                     String what = "";
                     int myp = -1;
                     int mys = -1;
                     for (int p = 1; p <= P; p++) {
                         if (cused[p - 1] == K) continue;
                         for (int s = 1; s <= S; s++) {
                             if (sused[s - 1] == K) continue;
                             String pair = " " + p + " " + s;
                             int pairCount = pairs.containsKey(pair) ? pairs.get(pair) : 0;
                             if (!localPairs.contains(pair) && pairCount < minUsed) {
                                 minUsed = pairCount;
                                 myp = p;
                                 mys = s;
                                 what = pair;
                             }
                         }
                     }
                     if (minUsed < K) {
                         more = true;
                         cused[myp - 1]++;
                         sused[mys - 1]++;
                         String pair = what;
                         pairs.put(pair, pairs.containsKey(pair) ? pairs.get(pair) + 1 : 1);
                         localPairs.add(pair);
                         ans.add(j + pair);
                     }
                 }
             }
 
             out.println(ans.size());
             for (String line : ans) {
                 out.println(line);
             }
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
 
