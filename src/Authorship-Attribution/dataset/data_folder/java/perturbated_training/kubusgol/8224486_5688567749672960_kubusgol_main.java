import java.io.FileInputStream;
 import java.util.Arrays;
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
 }
 
 class TaskA {
     int steps[] = new int[1000001];
 
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         int N = in.nextInt();
         Arrays.fill(steps, Integer.MAX_VALUE);
         steps[1] = 1;
         steps[0] = 0;
         for (int i = 2; i <= 1000000; i++) {
             steps[i] = Math.min(steps[i], steps[i - 1] + 1);
             int rev = (int) reverse(i);
             steps[rev] = Math.min(steps[rev], steps[i] + 1);
         }
 
         out.println("Case #" + testNumber + ": " + steps[N]);
     }
 
     private long reverse(long cur) {
         String c = cur + "";
 
         return Long.parseLong(reverse(c.toCharArray()));
     }
 
     private String reverse(char[] chars) {
         int N = chars.length;
         for (int i = 0; i < N / 2; i++) {
             char tmp = chars[N - i - 1];
             chars[N - i - 1] = chars[i];
             chars[i] = tmp;
         }
         return new String(chars);
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
 
