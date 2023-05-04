import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 public class TaskB {
     
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             int ac = sc.nextInt();
             int aj = sc.nextInt();
             int[][] ct = readInts(sc, ac, 2);
             int[][] jt = readInts(sc, aj, 2);
             if (ac == 1 && aj == 1 || ac + aj < 2) {
                 print(i, 2);
                 continue;
             }
             if (aj == 2) {
                 ct = jt;
             }
             if (Math.max(ct[0][1], ct[1][1]) - Math.min(ct[0][0], ct[1][0]) <= 720 ||
                     1440 - Math.max(ct[0][0], ct[1][0]) + Math.min(ct[0][1], ct[1][1]) <= 720) {
                 print(i, 2);
             } else {
                 print(i, 4);
             }
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, int answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
     
     private static int[][] readInts(Scanner sc, int n, int m) {
         int[][] result = new int[n][m];
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < m; j++) {
                 result[i][j] = sc.nextInt();
             }
         }
         return result;
     }
     
 }
