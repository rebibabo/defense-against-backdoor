import java.io.FileInputStream;
 import java.math.BigDecimal;
 import java.io.InputStream;
 import java.io.InputStreamReader;
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
     long sum = 0L;
     int max = 0;
     int K, L, S;
     char keyboard[];
     String target;
     char word[];
     int steps = 0;
 
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         K = in.nextInt();
         L = in.nextInt();
         S = in.nextInt();
         keyboard = in.next().toCharArray();
         target = in.next();
         word = new char[S];
         steps = 0;
         max = 0;
         sum = 0L;
         find(0);
 
         double pay = (double) sum / steps;
         double ans = (double) max - pay;
         BigDecimal bans = new BigDecimal(ans).setScale(7, BigDecimal.ROUND_HALF_UP);
         out.println("Case #" + testNumber + ": " + bans);
     }
 
     private void find(int cur) {
         if (cur == S) {
             int howmany = substr();
             max = Math.max(max, howmany);
             sum += howmany;
             steps++;
             return;
         }
 
         for (int i = 0; i < keyboard.length; i++) {
             word[cur] = keyboard[i];
             find(cur + 1);
         }
     }
 
     private int substr() {
         int ans = 0;
         String tmp = new String(word);
         for (int i = 0; i <= tmp.length() - target.length(); i++) {
             String cand = tmp.substring(i, i + target.length());
             if (target.equals(cand)) ans++;
         }
         return ans;
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
 
