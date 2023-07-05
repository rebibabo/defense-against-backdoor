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
            final String regex = "D-(small|large).*[.]in";
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
            outputStream = new FileOutputStream("d.out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskD {
     public void solve(int testNumber, InputReader in, PrintWriter out) {
         out.print("Case #" + testNumber + ": ");
         R = in.nextInt();
         C = in.nextInt();
         if (R == 2) {
             out.println("1");
             return;
         }
         if (R == 3) {
             out.println("2");
             return;
         }
         if (R == 4) {
             out.println("1");
             return;
         }
         if (R == 5) {
             out.println("1");
             return;
         }
         if (R == 6) {
             out.println("2");
             return;
         }
         drum = new int[R][C];
         ans = 0;
         permute(0,0);
         out.println(ans);
     }
     int[][] drum;
     int R;
     int C;
     int ans = 0;
     private void permute(int x, int y) {
         if (y == C) {
             x++;
             y = 0;
             if (x == R) {
                 if (check()) ans++;
                 return;
             }
         }
         for (int i = 2; i <= 3; i++) {
             drum[x][y] = i;
             permute(x,y+1);
         }
     }
     private boolean check() {
         for (int i = 0; i < R; i++) {
             for (int j = 0; j < C; j++) {
                 int same = 0;
                 int l = j-1;
                 if (l == -1) l = C-1;
                 if (drum[i][l] == drum[i][j]) same++;
                 int r = j+1;
                 if (r == C) r = 0;
                 if (drum[i][r] == drum[i][j]) same++;
                 if (i > 0 && drum[i-1][j] == drum[i][j]) same++;
                 if (i < R-1 && drum[i+1][j] == drum[i][j]) same++;
                 if (same != drum[i][j]) return false;
             }
         }
         return true;
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
 
