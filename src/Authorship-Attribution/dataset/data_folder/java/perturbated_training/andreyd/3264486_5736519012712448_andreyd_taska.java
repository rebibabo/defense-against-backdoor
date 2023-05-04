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
             int end = s.lastIndexOf(' ');
             int k = Integer.valueOf(s.substring(end + 1, s.length()));
             int left = s.indexOf('-');
             if (left == -1) {
                 print(i, "0");
                 continue;
             }
             int right = s.lastIndexOf('-');
             s = s.substring(left, right + 1);
             if (s.length() < k) {
                 print(i, "IMPOSSIBLE");
                 continue;
             }
             boolean[] a = new boolean[s.length()];
             for (int j = 0; j < s.length(); j++) {
                 a[j] = s.charAt(j) == '+';
             }
             int count = 0;
             for (int j = 0; j < a.length - k + 1; j++) {
                 if (!a[j]) {
                     count++;
                     for (int m = 0; m < k; m++) {
                         a[j+m] = !a[j+m];
                     }
                 }
             }
             boolean possible = true;
             for (int j = a.length - k + 1; j < a.length; j++) {
                 if (!a[j]) {
                     possible = false;
                     break;
                 }
             }
             if (possible) {
                 print(i, Integer.toString(count));
             } else {
                 print(i, "IMPOSSIBLE");
             }
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, String answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
 
 }
