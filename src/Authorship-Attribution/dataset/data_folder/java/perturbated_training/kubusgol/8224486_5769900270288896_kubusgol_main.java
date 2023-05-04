import java.io.FileInputStream;
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
     int ans = Integer.MAX_VALUE;
     boolean grid[][];
     int R, C, N;
 
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         R = in.nextInt();
         C = in.nextInt();
         N = in.nextInt();
         ans = Integer.MAX_VALUE;
         grid = new boolean[R + 2][C + 2];
         find(1, 1, N);
         out.println("Case #" + testNumber + ": " + ans);
     }
 
     private int find(int x, int y, int n) {
         if (n == 0) {
             ans = Math.min(ans, compute());
             return 0;
         }
         if (y == C + 1) {
             return find(x + 1, 1, n);
         }
         if (x == R + 1) {
             return 0;
         }
 
         grid[x][y] = false;
         find(x, y + 1, n);
         grid[x][y] = true;
         find(x, y + 1, n - 1);
         return 0;
     }
 
     private int compute() {
         int ans = 0;
         for (int i = 1; i <= R; i++) {
             for (int j = 1; j <= C; j++) {
                 if (grid[i][j]) {
                     if (grid[i - 1][j]) ans++;
                     if (grid[i][j - 1]) ans++;
                 }
             }
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
 
