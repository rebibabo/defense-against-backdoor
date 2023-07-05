import java.util.*;
 
 public class a {
 
 
     public static void main(String[] args) {
 
         int[] dp = new int[1000001];
         Arrays.fill(dp, 100000000);
         dp[1] = 1;
 
         for (int i=2; i<dp.length; i++) {
             dp[i] = dp[i-1]+1;
             int myrev = rev(i);
             if (myrev < i && rev(myrev) == i)
                 dp[i] = Math.min(dp[i], 1+dp[myrev]);
         }
 
         Scanner stdin = new Scanner(System.in);
         int numCases = stdin.nextInt();
 
         
         for (int loop=1; loop<=numCases; loop++)
             System.out.println("Case #"+loop+": "+dp[stdin.nextInt()]);
     }
 
     public static int rev(int n) {
 
         int res = 0;
         while (n > 0) {
             res = 10*res + n%10;
             n /= 10;
         }
         return res;
     }
 }
