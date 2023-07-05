import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskB {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             String s = sc.nextLine();
             int end = s.lastIndexOf('-');
             if (end == -1) {
                 print(i, 0);
                 continue;
             }
             int count = 0;
             char curr = s.charAt(0);
             for (int j = 1; j <= end; j++) {
                 if (curr != s.charAt(j)) {
                     count++;
                     curr = s.charAt(j);
                 }
             }
             print(i, count + 1);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, int answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
 
 }
