import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskA {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 1; i <= t; i++) {
             long n = sc.nextLong();
             if (n == 0L) {
                 print(i, -1);
                 continue;
             }
             long kn = n;
             boolean[] seen = new boolean[10];
             while (true) {
                 char[] digits = Long.toString(kn).toCharArray();
                 for (char c : digits) {
                     seen[c - '0'] = true;
                 }
                 if (seenAll(seen)) {
                     print(i, kn);
                     break;
                 } else {
                     kn += n;
                 }
             }
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static boolean seenAll(boolean[] arr) {
         for (boolean b : arr) {
             if (!b) {
                 return false;
             }
         }
         return true;
     }
     
     private static void print(int caseNum, long answer) {
         System.out.println("Case #" + caseNum + ": " + (answer == -1 ? "INSOMNIA" : answer));
     }
 
 }
