import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.BufferedReader;
 import java.io.FilenameFilter;
 import java.util.AbstractQueue;
 import java.util.Locale;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.util.Comparator;
 import java.util.PriorityQueue;
 import java.io.IOException;
 import java.io.FileOutputStream;
 import java.io.File;
 import java.util.StringTokenizer;
 
 
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
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskB {
     private static int ans = Integer.MAX_VALUE;
 
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         int D = in.nextInt();
 
         ans = Integer.MAX_VALUE;
         PriorityQueue<Integer> set = new PriorityQueue<>(new Comparator<Integer>() {
             public int compare(Integer o1, Integer o2) {
                 return o2 - o1;
             }
         });
         for (int i = 0; i < D; i++) {
             set.add(in.nextInt());
         }
         ans = set.peek();
         find(set, 0);
 
         out.println("Case #" + testNumber + ": " + ans);
     }
 
     private void find(PriorityQueue<Integer> pq, int steps) {
         if (steps > ans) {
             return;
         }
         Integer max = pq.remove();
         if (max <= 3) {
             ans = Math.min(ans, steps + max);
             return;
         }
 
         if (ans > steps + max) {
             ans = steps + max;
         }
 
         for (int a = 1; a <= max / 2; a++) {
             PriorityQueue pq1 = new PriorityQueue(pq);
             pq1.add(a);
             pq1.add(max - a);
             find(pq1, steps + 1);
         }
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
 
