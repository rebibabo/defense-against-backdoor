import java.io.InputStreamReader;
 import java.io.IOException;
 import java.util.Locale;
 import java.io.BufferedReader;
 import java.io.OutputStream;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.io.FileInputStream;
 import java.io.File;
 import java.util.StringTokenizer;
 import java.io.FilenameFilter;
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
 }
 
 class TaskA {
     static String dir = ".^>v<";
 
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         out.print("Case #" + testNumber + ": ");
         int R = in.nextInt();
         int C = in.nextInt();
         int[][] map = new int[R][C];
         for (int i = 0; i < R; i++) {
             String s = in.next();
             s = s.replace("&gt;", ">");
             s = s.replace("&lt;", "<");
             for (int j = 0; j < C; j++) {
                 if (s.charAt(j) == '.') continue;
                 map[i][j] = dir.indexOf(s.charAt(j));
             }
         }
         for (int i = 0; i < R; i++) {
             for (int j = 0; j < C; j++) {
                 if (map[i][j] == 0) continue;
                 int l = j-1;
                 while (l >= 0 && map[i][l] == 0) l--;
                 int r = j+1;
                 while (r < C && map[i][r] == 0) r++;
                 int u = i-1;
                 while (u >= 0 && map[u][j] == 0) u--;
                 int b = i+1;
                 while (b < R && map[b][j] == 0) b++;
                 if (l == -1 && r == C && u == -1 && b == R) {
                     out.println("IMPOSSIBLE");
                     return;
                 }
             }
         }
         int ans = 0;
         for (int i = 0; i < R; i++) {
             int l = 0;
             while (l < C && map[i][l] == 0) l++;
             if (l < C && map[i][l] == 4) ans++;
             int r = C-1;
             while (r >= 0 && map[i][r] == 0) r--;
             if (r >= 0 && map[i][r] == 2) ans++;
         }
         for (int i = 0; i < C; i++) {
             int t = 0;
             while (t < R && map[t][i] == 0) t++;
             if (t < R && map[t][i] == 1) ans++;
             int b = R-1;
             while (b >= 0 && map[b][i] == 0) b--;
             if (b >= 0 && map[b][i] == 3) ans++;
         }
 
         out.println(ans);
     }
 }
 
 class InputReader {
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
 
