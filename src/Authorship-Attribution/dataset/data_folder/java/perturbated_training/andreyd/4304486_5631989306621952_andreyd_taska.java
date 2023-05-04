import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskA {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             String s = sc.nextLine();
             StringBuilder after = new StringBuilder();
             StringBuilder before = new StringBuilder();
             char last = s.charAt(0);
             after.append(last);
             for (int j = 1; j < s.length(); j++) {
                 char curr = s.charAt(j);
                 if (curr >= last) {
                     before.append(curr);
                     last = curr;
                 } else {
                     after.append(curr);
                 }
             }
             print(i, before.reverse().toString() + after.toString());
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, String answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
     
 }
