import java.io.BufferedInputStream;
 import java.util.Arrays;
 import java.util.Scanner;
 
 
 public class TaskC {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             int n = sc.nextInt();
             int j = sc.nextInt();
             if (j > (1 << (n-4)/2)) {
                 System.err.println("j is greater than " + (1 << (n-4)/2));
             }
             System.out.println("Case #" + i + ":");
             for (int k = 0; k < j; k++) {
                 String s = generate(n, k);
                 print(s);
             }
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     
     private static String generate(int n, int seed) {
         String s = Integer.toBinaryString(seed);
         StringBuilder sb = new StringBuilder(n);
         sb.append("11");
         if (2 * s.length() < n - 4) {
             char[] zeros = new char[n - 4 - 2 * s.length()];
             Arrays.fill(zeros, '0');
             sb.append(zeros);
         }
         for (int i = 0; i < s.length(); i++) {
             sb.append(s.charAt(i)).append(s.charAt(i));
         }
         sb.append("11");
         return sb.toString();
     }
     
     
     private static void print(String s) {
         System.out.println(s + " 3 4 5 6 7 8 9 10 11");
     }
 
 }
