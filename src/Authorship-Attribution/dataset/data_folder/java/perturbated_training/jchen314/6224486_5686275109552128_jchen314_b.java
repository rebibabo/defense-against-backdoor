import java.util.Scanner;
 
 public class B {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int T = sc.nextInt();
         for (int caseNum = 1; caseNum <= T; caseNum++) {
             int D = sc.nextInt();
             int[] P = new int[D];
             int maxP = 0;
             for (int i = 0; i < D; i++) {
                 P[i] = sc.nextInt();
                 maxP = Math.max(maxP, P[i]);
             }
 
             int minTime = Integer.MAX_VALUE;
             for (int max = 1; max <= maxP; max++) {
                 int specialMinutes = 0;
                 for (int pancakes : P) {
                     if (pancakes > max) {
                         specialMinutes += (pancakes - 1) / max;
                     }
                 }
                 minTime = Math.min(minTime, specialMinutes + max);
             }
             System.out.println("Case #" + caseNum + ": " + minTime);
         }
     }
 }
