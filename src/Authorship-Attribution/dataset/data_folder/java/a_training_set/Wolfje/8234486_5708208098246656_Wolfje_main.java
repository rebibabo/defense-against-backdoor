import java.util.Scanner;
 import java.io.IOException;
 import java.util.Arrays;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.io.File;
 import java.io.FileInputStream;
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
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }
 }
 
 class TaskA {
     public void solve(int testNumber, Scanner in, PrintWriter out) {
         int rows = in.nextInt();
         int cols = in.nextInt();
         String dirs = "v^><.";
         int[] dr = new int[] { 1, -1, 0, 0 };
         int[] dc = new int[] { 0, 0, 1, -1 };
         int[][] map = new int[rows][cols];
         for ( int r = 0; r < rows; r++) {
             String s = in.next();
             for ( int c = 0; c < cols; c++ ) map[r][c] = dirs.indexOf(s.charAt(c));
         }
         int result = 0;
         boolean alwaysFail = false;
         boolean[] check = new boolean[4];
         for ( int r = 0; r < rows; r++)
             for ( int c = 0; c < cols; c++) {
                 if ( map[r][c] == 4 ) continue;
                 Arrays.fill(check, false);
                 for ( int d = 0; d < 4; d++ ) {
                     boolean a = false;
                     int nr = r + dr[d];
                     int nc = c + dc[d];
                     while ( nr >= 0 && nr < rows && nc >=0 && nc < cols) {
                         a |= map[nr][nc] < 4;
                         nr += dr[d];
                         nc += dc[d];
                     }
                     check[d] = a;
                 }
                 if ( !check[map[r][c]] ) {
                     boolean any = false;
                     for ( boolean a: check ) any |= a;
                     if ( any ) result++;
                     else alwaysFail = true;
                 }
             }
         if ( alwaysFail ) {
             out.printf("Case #%d: IMPOSSIBLE\n", testNumber);
         } else {
             out.printf("Case #%d: %d\n", testNumber, result);
         }
 
 
     }
 }
 
