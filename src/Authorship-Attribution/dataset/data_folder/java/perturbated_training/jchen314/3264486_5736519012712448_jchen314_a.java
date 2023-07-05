import java.util.Scanner;
 
 public class A {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         outer:
         for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             String S = sc.next();
             int K = sc.nextInt();
 
             int numFlips = 0;
             boolean[] flipped = new boolean[S.length()];
             System.out.print("Case #" + caseNum + ": ");
             for (int i = 0; i < S.length(); i++) {
                 if (flipped[i] ^ (S.charAt(i) == '-')) {
                     
                     if (i + K > S.length()) {
                         System.out.println("IMPOSSIBLE");
                         continue outer;
                     }
                     for (int j = 0; j < K; j++) {
                         flipped[i + j] = !flipped[i + j];
                     }
                     numFlips++;
                 }
             }
             System.out.println(numFlips);
         }
     }
 }
