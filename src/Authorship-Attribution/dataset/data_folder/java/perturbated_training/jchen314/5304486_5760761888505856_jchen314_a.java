import java.util.Scanner;
 
 public class A {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             int R = sc.nextInt();
             int C = sc.nextInt();
             char[][] grid = new char[R][C];
             int r = -1, c = -1;
             for (int i = 0; i < R; i++) {
                 String str = sc.next();
                 for (int j = 0; j < C; j++) {
                     if (str.charAt(j) != '?') {
                         grid[i][j] = str.charAt(j);
                         if (r < 0) {
                             r = i;
                             c = j;
                         }
                     }
                 }
             }
 
             char lastChar = grid[r][c];
             for (int j = 0; j < C; j++) {
                 if (grid[r][j] != 0) {
                     lastChar = grid[r][j];
                 } else {
                     grid[r][j] = lastChar;
                 }
             }
             for (int i = r - 1; i >= 0; i--) {
                 for (int j = 0; j < C; j++) {
                     grid[i][j] = grid[i + 1][j];
                 }
             }
             for (int i = r + 1; i < R; i++) {
                 char firstChar = 0;
                 for (int j = 0; j < C; j++) {
                     if (grid[i][j] != 0) {
                         firstChar = grid[i][j];
                         break;
                     }
                 }
                 if (firstChar == 0) {
                     for (int j = 0; j < C; j++) {
                         grid[i][j] = grid[i - 1][j];
                     }
                 } else {
                     lastChar = firstChar;
                     for (int j = 0; j < C; j++) {
                         if (grid[i][j] != 0) {
                             lastChar = grid[i][j];
                         } else {
                             grid[i][j] = lastChar;
                         }
                     }
                 }
             }
 
             System.out.println("Case #" + caseNum + ":");
             for (int i = 0; i < R; i++) {
                 for (int j = 0; j < C; j++) {
                     System.out.print(grid[i][j]);
                 }
                 System.out.println();
             }
         }
     }
 }
