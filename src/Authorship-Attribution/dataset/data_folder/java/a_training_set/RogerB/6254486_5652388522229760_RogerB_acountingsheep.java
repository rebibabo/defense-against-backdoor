package Qualifier;
 
 import java.util.Scanner;
 
 public class ACountingSheep {
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int input = sc.nextInt();
             if (input == 0) {
                 System.out.printf("Case #%d: INSOMNIA%n", t);
                 continue;
             }
             int currentNumber = input;
             boolean[] seenDigit = new boolean[10];
             int seenCount = 0;
             while (true) {
                 String currentString = "" + currentNumber;
                 for (int x = 0; x < currentString.length(); x++) {
                     int digit = currentString.charAt(x) - '0';
                     if (!seenDigit[digit]) {
                         seenDigit[digit] = true;
                         seenCount++;
                     }
                 }
                 if (seenCount == 10) break;
                 currentNumber += input;
             }
             System.out.printf("Case #%d: %d%n", t, currentNumber);
         }
         sc.close();
     }
 }
