import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.TreeMap;
 import java.util.StringTokenizer;
 import java.util.Map;
 import java.io.BufferedReader;
 import java.util.Collections;
 import java.io.InputStream;
 
 
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
         InputReader in = new InputReader(inputStream);
         PrintWriter out = new PrintWriter(outputStream);
         TaskA solver = new TaskA();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskA {
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             int D = in.nextInt();
             int N = in.nextInt();
 
             int[] K = new int[N + 1];
             int[] S = new int[N + 1];
 
             for (int i = 0; i < N; i++) {
                 K[i] = in.nextInt();
                 S[i] = in.nextInt();
             }
 
             K[N] = D;
             S[N] = 0;
 
             double T = 0D;
 
             ArrayList<Horse> horses = new ArrayList<>();
             for (int i = 0; i <= N; i++) {
                 horses.add(new Horse(i, K[i], S[i]));
             }
 
             Collections.sort(horses);
 
             while (horses.size() > 1) {
                 double minT = Double.POSITIVE_INFINITY;
 
                 for (int i = 1; i < horses.size(); i++) {
                     double timeToCatch = timeToCatch(i - 1, i, horses);
                     minT = Math.min(minT, timeToCatch);
                 }
 
                 T += minT;
 
                 for (int i = 0; i < horses.size(); i++) {
                     horses.get(i).advance(minT);
                 }
 
                 horses = eliminateDuplicates(horses);
             }
 
             double ANS = (double) D / T;
 
             out.println("Case #" + testNumber + ": " + ANS);
         }
 
         private ArrayList<Horse> eliminateDuplicates(ArrayList<Horse> horses) {
             ArrayList<Horse> ans = new ArrayList<>();
 
             TreeMap<Double, Horse> map = new TreeMap<>();
 
             for (Horse h : horses) {
                 map.compute(h.pos, (k, v) -> {
                     if (v == null) {
                         return h;
                     }
 
                     if (v.speed < h.speed) return v;
                     return h;
                 });
             }
 
             for (Horse h : map.values()) {
                 ans.add(h);
             }
 
             return ans;
         }
 
         private double timeToCatch(int i, int j, ArrayList<Horse> horses) {
             Horse first = horses.get(i);
             Horse second = horses.get(j);
 
             if (first.speed < second.speed) return Double.POSITIVE_INFINITY;
 
             double K = (first.speed * second.pos - second.speed * first.pos) / (first.speed - second.speed);
             double T = (K - first.pos) / first.speed;
             return T;
         }
 
         private class Horse implements Comparable<Horse> {
             private int idx;
             private double pos;
             private double speed;
 
             public Horse(int idx, double pos, double speed) {
                 this.idx = idx;
                 this.pos = pos;
                 this.speed = speed;
             }
 
 
             public int compareTo(Horse that) {
                 if (this.pos < that.pos) return -1;
                 if (this.pos > that.pos) return +1;
                 return this.idx - that.idx;
             }
 
             public void advance(double time) {
                 this.pos += this.speed * time;
             }
 
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
 
