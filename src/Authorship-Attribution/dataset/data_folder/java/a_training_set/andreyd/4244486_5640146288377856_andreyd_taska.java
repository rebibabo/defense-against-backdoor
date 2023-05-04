import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskA {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 1; i <= t; i++) {
             int r = sc.nextInt();
             int c = sc.nextInt();
             int w = sc.nextInt();
             int res = c / w;
             if (c % w == 0) {
                 res = res * r + w -1;
             }
             else {
                 res = res * r + w;
             }
             print(i, res);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, long answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
     
     private static int[] readInts(Scanner sc, int n) {
         int[] result = new int[n];
         for (int i = 0; i < n; i++) {
             result[i] = sc.nextInt();
         }
         return result;
     }
 
 }
