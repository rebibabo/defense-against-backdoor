import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 @SuppressWarnings("FieldCanBeLocal")
 public class A {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private static PrintStream out;
     private static boolean PRINT_TO_CONSOLE = true;
 
 
     static int[] map = new int[256];
 
     static {
         map['.'] = -1;
         map['^'] = 0;
         map['>'] = 1;
         map['v'] = 2;
         map['<'] = 3;
     }
 
     static int[] dr = new int[] {-1, 0, 1, 0};
     static int[] dc = new int[] {0, 1, 0, -1};
 
     private int[][] grid;
     private boolean[][] ok;
     private int rows;
     private int cols;
     private boolean[][] seen;
     private int ans;
     private boolean cant;
 
     void solve() {
         rows = sc.nextInt();
         cols = sc.nextInt();
         sc.nextLine();
 
         grid = new int[rows][cols];
         ok = new boolean[rows][cols];
         seen = new boolean[rows][cols];
         for (int r = 0; r < rows; ++r) {
             String line = sc.nextLine();
             for (int c = 0; c < cols; ++c) {
                 char ch = line.charAt(c);
                 grid[r][c] = map[ch];
             }
         }
 
 
         cant = false;
         ans = 0;
         for (int r = 0; r < rows && !cant; ++r) {
             for (int c = 0; c < cols && !cant; ++c) {
                 int n = grid[r][c];
                 if (n >= 0 && !ok[r][c] && !seen[r][c]) {
                     cant = !go(r, c, -1);
                 }
             }
         }
 
         if (cant) {
             out.printf("IMPOSSIBLE\n");
         } else {
             out.printf("%d\n", ans);
         }
     }
 
     boolean go(int r1, int c1, int fromDir) {
         seen[r1][c1] = true;
 
         for (int attempt = 0; attempt < 4; ++attempt) {
             int dir1 = grid[r1][c1];
             int r = r1;
             int c = c1;
             r += dr[dir1];
             c += dc[dir1];
             while (inGrid(r, c)) {
                 int dir = grid[r][c];
                 if (dir >= 0) {
                     if (ok[r][c]) {
                         return true;
                     }
 
                     if ((dir1 + 2) % 4 == dir) {
                         ok[r][c] = true;
                         ok[r1][c1] = true;
                         return true;
                     } else {
                         if (!seen[r][c]) {
                             return go(r, c, dir1);
                         } else {
                             ok[r][c] = true;
                             ok[r1][c1] = true;
                             return true;
                         }
                     }
                 }
 
                 r += dr[dir1];
                 c += dc[dir1];
             }
 
             if (attempt == 0) {
                 ++ans;
             }
             grid[r1][c1] = (grid[r1][c1] + 1) % 4;
         }
 
         return false;
     }
 
     boolean inGrid(int r, int c) {
         return 0 <= r && r < rows && 0 <= c && c < cols;
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
         String file = "A-small-attempt0";
 
         String outFileName = file + ".out";
         out = PRINT_TO_CONSOLE ? new CJPrintStream(outFileName) : new PrintStream(outFileName);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             out.printf("Case #%s: ", caseNumber);
             A template = new A();
             template.caseNumber = caseNumber;
             template.solve();
             out.flush();
         }
 
         sc.close();
     }
 
     static class CJPrintStream extends PrintStream {
         public CJPrintStream(String fileName) throws FileNotFoundException {
             super(fileName);
         }
 
         @SuppressWarnings("NullableProblems")
         @Override
         public PrintStream printf(String format, Object... args) {
             System.out.printf(format, args);
             return super.printf(format, args);
         }
 
         @Override
         public void println() {
             System.out.println();
             super.println();
         }
 
         @Override
         public void flush() {
             System.out.flush();
             super.flush();
         }
     }
 }
