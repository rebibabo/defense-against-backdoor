import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskD {
 
     
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 1; i <= t; i++) {
             int k = sc.nextInt();
             int c = sc.nextInt();
             int s = sc.nextInt();
             if (k == 1) {
                 long[] ans = {1L};
                 print(i, ans);
                 continue;
             }
             long[] answer = new long[s];
             long mul = 1L;
             for (int j = 0; j < c; j++) {
                 mul *= k;
             }
             mul--;
             mul /= (k - 1);
             for (int j = 0; j < s; j++) {
                 answer[j] = mul * j + 1;
             }
             print(i, answer);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, long[] answer) {
         StringBuilder sb = new StringBuilder();
         sb.append("Case #").append(caseNum).append(':');
         for (long l : answer) {
             sb.append(' ').append(l);
         }
         System.out.println(sb.toString());
     }
 
 }
