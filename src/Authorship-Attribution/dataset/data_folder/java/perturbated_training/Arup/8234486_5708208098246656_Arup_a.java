import java.util.*;
 
 public class a {
 
     final public static int[] DX = {1,-1,0,0};
     final public static int[] DY = {0,0,1,-1};
     final public static char[] DIR = {'v','^','>','<'};
 
     public static void main(String[] args) {
 
         Scanner stdin = new Scanner(System.in);
         int numCases = stdin.nextInt();
 
         for (int loop=1; loop<=numCases; loop++) {
 
             int r = stdin.nextInt();
             int c = stdin.nextInt();
             char[][] grid = new char[r][];
             for (int i=0; i<r; i++)
                 grid[i] = stdin.next().toCharArray();
 
             
             
 
             int cnt = 0;
             boolean imp = false;
             for (int i = 0; i<r; i++) {
                 for (int j=0; j<c; j++) {
 
                     if (out(grid, i, j)) cnt++;
                     if (impossible(grid, i, j)) {
                         imp = true;
                         break;
                     }
                 }
                 if (imp) break;
             }
 
             if (imp)    System.out.println("Case #"+loop+": IMPOSSIBLE");
             else        System.out.println("Case #"+loop+": "+cnt);
 
         }
     }
 
     public static boolean out(char[][] grid, int x, int y) {
 
         if (grid[x][y] == '.') return false;
 
         
         for (int i=0; i<4; i++) {
             if (grid[x][y] == DIR[i]) {
 
                 int myx = x + DX[i];
                 int myy = y + DY[i];
                 while (inbounds(myx, myy, grid) && grid[myx][myy] == '.') {
                     myx += DX[i];
                     myy += DY[i];
                 }
 
                 if (inbounds(myx, myy, grid)) return false;
                 else                    return true;
             }
         }
 
         return false;
     }
 
     public static boolean impossible(char[][] grid, int x, int y) {
 
         if (grid[x][y] == '.') return false;
 
         
         for (int i=0; i<DX.length; i++) {
 
             int myx = x + DX[i];
             int myy = y + DY[i];
             while (inbounds(myx, myy, grid) && grid[myx][myy] == '.') {
                 myx += DX[i];
                 myy += DY[i];
             }
 
             if (inbounds(myx, myy, grid)) return false;
         }
         return true;
     }
 
     public static boolean inbounds(int x, int y, char[][] grid) {
         return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
     }
 }
 
