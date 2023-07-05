import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskC {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 0; i < t; i++) {
             int j = sc.nextInt();
             int p = sc.nextInt();
             int s = sc.nextInt();
             int k = sc.nextInt();
             if (k > s) {
                 print(i+1, j * p * s);
                 k = s;
             } else {
                 print(i+1, j * p * k);
             }
             for (int c1 = 1; c1 <= j; c1++) {
                 for (int c2 = 1; c2 <= p; c2++) {
                     for (int c3 = c1 + c2 - 1; c3 <= c1 + c2 + k - 2; c3++) {
                         printLine(c1, c2, c3 <= s ? c3 : mod(c3, s));
                     }
                 }
             }
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static int mod(int c3, int s) {
         return c3 % s == 0 ? s : c3 % s;
     }
     
     private static void print(int caseNum, int answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
     
     private static void printLine(int a, int b, int c) {
         System.out.println(a + " " + b + " " + c);
     }
 
 }
