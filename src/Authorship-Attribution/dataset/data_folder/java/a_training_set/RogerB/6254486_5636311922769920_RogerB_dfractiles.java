package Qualifier;
 
 import java.util.Scanner;
 
 public class DFractiles { 
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int length = sc.nextInt();
             int complexity = sc.nextInt();
             int availableChecks = sc.nextInt();
             int requiredChecks = (int)Math.ceil(length / (double)complexity);
             if (availableChecks >= requiredChecks) {
                 System.out.printf("Case #%d:", t);
                 for (int startIndex = 0; startIndex < length; startIndex += complexity) {
                     int endIndex = Math.min(startIndex + complexity - 1, length - 1);
                     System.out.printf(" %d", testIndex(startIndex, endIndex, length));
                 }
                 System.out.println();
             } else {
                 System.out.printf("Case #%d: IMPOSSIBLE%n", t);
             }
         }
         sc.close();
     }
     
     static long testIndex(int startCheckVal, int endCheckVal, int length) {
         long index = 0;
         for (int x = startCheckVal; x <= endCheckVal; x++) {
             index *= length;
             index += x;
         }
         return index + 1;
     }
 }
