import java.util.Scanner;
 
 public class D {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         outer: for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             int R = sc.nextInt();
             int C = sc.nextInt();
             int N = sc.nextInt();
             int D = sc.nextInt();
 
             long[][] grid = new long[R + 1][C + 1];
 
             long[][] max = new long[R + 1][C + 1];
             long[][] min = new long[R + 1][C + 1];
             for (int i = 1; i <= R; i++) {
                 for (int j = 1; j <= C; j++) {
                     max[i][j] = Long.MAX_VALUE;
                     min[i][j] = 1;
                 }
             }
 
             for (int cell = 0; cell < N; cell++) {
                 int r = sc.nextInt();
                 int c = sc.nextInt();
                 long b = sc.nextLong();
                 grid[r][c] = b;
 
                 for (int i = 1; i <= R; i++) {
                     for (int j = 1; j <= C; j++) {
                         long distance = Math.abs(i - r) + Math.abs(j - c);
                         max[i][j] = Math.min(max[i][j], b + distance * D);
                         min[i][j] = Math.max(min[i][j], b - distance * D);
                     }
                 }
             }
 
             System.out.print("Case #" + caseNum + ": ");
             long total = 0;
             for (int i = 1; i <= R; i++) {
                 for (int j = 1; j <= C; j++) {
                     if (max[i][j] < min[i][j]) {
                         System.out.println("IMPOSSIBLE");
                         continue outer;
                     }
                     total = (total + max[i][j]) % 1_000_000_007L;
                 }
             }
 
             System.out.println(total);
         }
     }
 }
