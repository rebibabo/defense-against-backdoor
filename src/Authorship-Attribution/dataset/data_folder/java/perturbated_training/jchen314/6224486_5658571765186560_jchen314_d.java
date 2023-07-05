import java.util.Scanner;
 
 public class D {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int T = sc.nextInt();
         for (int caseNum = 1; caseNum <= T; caseNum++) {
             int X = sc.nextInt();
             int R = sc.nextInt();
             int C = sc.nextInt();
             if (alwaysPossible(X, R, C)) {
                 System.out.println("Case #" + caseNum + ": GABRIEL");
             } else {
                 System.out.println("Case #" + caseNum + ": RICHARD");
             }
         }
     }
 
     private static boolean alwaysPossible(int X, int R, int C) {
         if ((R * C) % X != 0) {
             return false;
         }
         if (X <= 2) {
             return true;
         }
         if (X == 3) {
             return Math.min(R, C) >= 2;
         }
         if (X == 4) {
             return Math.min(R, C) >= 3;
         }
         if (X == 5) {
             return Math.min(R, C) >= 4 || Math.max(R, C) >= 10;
         }
         if (X == 6) {
             return Math.min(R, C) >= 4;
         }
         return false;
     }
 }
