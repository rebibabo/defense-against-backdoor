package Qualifier;
 
 import java.util.Scanner;
 
 public class DOminousOmino {
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int x = sc.nextInt();
             int r = sc.nextInt();
             int c = sc.nextInt();
             if ((r*c) % x > 0 || x > Math.min(r, c) * 2 || x > Math.max(r, c) || x >= 7 || (x == Math.min(r, c) * 2 && x >= 4)) {
                 System.out.printf("Case #%d: RICHARD%n", t);
             } else {
                 System.out.printf("Case #%d: GABRIEL%n", t);
             }
         }
         sc.close();
     }
 }
