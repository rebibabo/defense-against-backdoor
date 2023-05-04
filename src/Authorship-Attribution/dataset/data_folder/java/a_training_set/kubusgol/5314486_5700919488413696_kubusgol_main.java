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
 import java.util.TreeMap;
 import java.util.StringTokenizer;
 import java.util.Map;
 import java.io.BufferedReader;
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
         Map<State, Integer> cache;
         int[] rems;
         int P;
 
         public void solve(int testNumber, InputReader in, PrintWriter out) {
             int N = in.nextInt();
             P = in.nextInt();
 
             int[] G = new int[N];
             rems = new int[4];
             for (int i = 0; i < N; i++) {
                 G[i] = in.nextInt();
                 rems[G[i] % P]++;
             }
             cache = new TreeMap<>();
 
             int ANS = 0;
 
             if (N == 1) {
                 ANS = 1;
             } else {
                 for (int i = 0; i < P; i++) {
                     if (rems[i] > 0) {
                         rems[i]--;
                         State state = new State(i, rems);
                         ANS = Math.max(ANS, 1 + find(N - 1, state));
                         rems[i]++;
                     }
                 }
             }
             out.println(String.format("Case #%d: %d", testNumber, ANS));
         }
 
         private int find(int n, State cur) {
             if (n == 0) return 0;
             if (cache.containsKey(cur)) {
                 return cache.get(cur);
             }
             int ANS = 0;
 
             for (int i = 0; i < 4; i++) {
                 if (rems[i] > 0) {
                     rems[i]--;
                     State newState = new State((cur.curRem + i) % P, rems);
                     if (cur.curRem == 0) {
                         ANS = Math.max(ANS, 1 + find(n - 1, newState));
                     } else {
                         ANS = Math.max(ANS, find(n - 1, newState));
                     }
                     rems[i]++;
                 }
             }
 
             cache.put(cur, ANS);
 
             return ANS;
         }
 
         private class State implements Comparable<State> {
             private int curRem;
             private int[] rems;
 
             public State(int curRem, int[] r) {
                 this.curRem = curRem;
                 this.rems = new int[4];
                 for (int i = 0; i < 4; i++) {
                     this.rems[i] = r[i];
                 }
             }
 
 
             public int compareTo(State that) {
                 if (this.curRem < that.curRem) return -1;
                 if (this.curRem > that.curRem) return +1;
 
                 for (int i = 0; i < 4; i++) {
                     if (this.rems[i] < that.rems[i]) return -1;
                     if (this.rems[i] > that.rems[i]) return +1;
                 }
                 return 0;
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
 
