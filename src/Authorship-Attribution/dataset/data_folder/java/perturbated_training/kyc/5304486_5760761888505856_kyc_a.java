package CodeJam;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.PrintStream;
 import java.nio.file.Files;
 import java.util.Scanner;
 import org.junit.Test;
 
 public class A extends CodeJamCommons {
 
     int h, w;
     char[][] grid;
 
     @SuppressWarnings("resource")
     @Test
     public void test() throws Exception {
         Scanner in = new Scanner(new FileInputStream(new File(ROOT, "A-small-attempt3.in")));
         File file = new File(ROOT, "data.out");
         PrintStream out = new PrintStream(new FileOutputStream(file));
 
         int numCases = in.nextInt();
         for (int n = 0; n < numCases; n++) {
             h = in.nextInt();
             w = in.nextInt();
             grid = new char[h][];
             for (int i = 0; i < h; i++)
                 grid[i] = in.next().toCharArray();
             char[][] orig = new char[h][w];
             for (int i = 0; i < h; i++)
                 for (int j = 0; j < w; j++)
                     orig[i][j] = grid[i][j];
 
             if (n==75)
                 pr(grid);
             char[][] newGrid = new char[h][w];
             for (int i = 0; i < h; i++)
                 for (int j = 0; j < w; j++)
                     newGrid[i][j] = '?';
             for (int i = 0; i < h; i++)
                 for (int j = 0; j < w; j++)
                     if (orig[i][j] != '?') {
                         char c = grid[i][j];
                         int minI = i, maxI = i, minJ = j, maxJ = j;
                         while (good(minI - 1, maxI, minJ, maxJ, c))
                             minI--;
                         while (good(minI, maxI + 1, minJ, maxJ, c))
                             maxI++;
                         while (good(minI, maxI, minJ - 1, maxJ, c))
                             minJ--;
                         while (good(minI, maxI, minJ, maxJ + 1, c))
                             maxJ++;
                         for (int k = minI; k <= maxI; k++)
                             for (int l = minJ; l <= maxJ; l++)
                                 newGrid[k][l] = grid[k][l] = c;
                     }
 
             out.printf("Case #%d:", n + 1);
             out.println();
             for (int i = 0; i < h; i++)
                 out.println(newGrid[i]);
         }
 
         out.close();
         System.out.printf("Output to %s (first 10 lines):\n", file);
         Files.lines(file.toPath()).limit(10).forEach(System.out::println);
     }
 
     boolean good(int minI, int maxI, int minJ, int maxJ, char c) {
         if (minI < 0 || maxI >= h || minJ < 0 || maxJ >= w)
             return false;
         for (int i = minI; i <= maxI; i++)
             for (int j = minJ; j <= maxJ; j++)
                 if (grid[i][j] != c && grid[i][j] != '?')
                     return false;
         return true;
     }
 }
