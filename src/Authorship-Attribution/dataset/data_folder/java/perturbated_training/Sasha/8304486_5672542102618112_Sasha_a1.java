import java.io.File;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class A1 {
 
     private static final int[] d10 = new int[]{1, 10, 100, 1000, 10000, 100000};
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         Scanner in = new Scanner(new File("problem.in"));
         PrintStream out = new PrintStream(new File("problem.out"));
 
         int T = in.nextInt();
 
         for (int test = 1; test <= T; test++) {
             String g = in.next();
 
             int len = g.length();
 
             int t = 0, m = 1;
 
             for (int i = g.length() - 1; i >= 0; i--) {
                 t += m * (g.charAt(i) - '0');
                 m *= 10;
             }
 
 
             int result = 0;
 
             for (int i = 1; i < d10[len] - 1; i++) {
                 int n = i;
 
                 while (n != d10[len-1]) {
                     if (n == t) {
                         break;
                     }
 
 
                     int[] count = new int[10];
 
                     while (n != 0) {
                         int c = n % 10;
                         if (c > len) {
                             Arrays.fill(count, 0);
                             break;
                         }
                         count[c]++;
                         n /= 10;
                     }
 
                     n = 0;
                     for (int j = 1; j <= len; j++) {
                         n += count[j] * d10[len - j];
                     }
                     if (n == 0) {
                         break;
                     }
 
                 }
                 if (n == t) {
                     result++;
                 }
 
             }
 
             out.printf("Case #%d: %d\n", test, result);
         }
     }
 }
