import java.util.LinkedList;
 import java.io.FileInputStream;
 import java.util.ArrayList;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.List;
 import java.io.BufferedReader;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.IOException;
 import java.io.FileOutputStream;
 import java.io.File;
 import java.util.StringTokenizer;
 
 
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
 }
 
 class TaskC {
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         int C = in.nextInt();
         int D = in.nextInt();
         int V = in.nextInt();
         int existing[] = new int[D];
         for (int i = 0; i < D; i++) existing[i] = in.nextInt();
         boolean used[] = new boolean[V + 1];
         for (int i = 0; i < existing.length; i++) {
             for (int j = i + 1; j < existing.length; j++) {
                 if (existing[i] + existing[j] <= V) {
                     used[existing[i] + existing[j]] = true;
                 }
             }
         }
 
         ArrayList<Integer> ex = new ArrayList<>();
         for (Integer e : existing) {
             ex.add(e);
         }
         if (cover(ex, V)) {
             out.println("Case #" + testNumber + ": 0");
             return;
         }
 
         boolean us[] = new boolean[V];
         int added = 0;
         for (int i = 1; i <= V; i++) {
             if (ex.contains(i)) continue;
             ex.add(i);
             added++;
             if (cover(ex, V)) {
                 out.println("Case #" + testNumber + ": " + added);
                 return;
             }
         }
     }
 
     private boolean cover(ArrayList<Integer> ex, int v) {
         Integer[] integers = ex.toArray(new Integer[0]);
         boolean cov[] = new boolean[v + 1];
         for (Integer i : integers) {
             cov[i] = true;
         }
         for (int i = 0; i < integers.length; i++) {
             for (int j = i + 1; j < integers.length; j++) {
                 int i1 = ex.get(i) + ex.get(j);
                 if (i1 <= v) {
                     cov[i1] = true;
                 }
             }
         }
         for (int i = 1; i <= v; i++) {
             if (!cov[i]) return false;
         }
         return true;
     }
 
 }
 
 class InputReader {
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
 
