import java.util.*;
 import java.io.*;
 
 public class A
 {
     static int N;
 
     static String ans;
 
     public static void main(String ... orange) throws Exception
     {
         Scanner input = new Scanner(System.in);
         int numCases = input.nextInt();
         for (int n = 0; n < numCases; n++)
         {
             N = input.nextInt();
 
             if (N == 0)
                 ans = "INSOMNIA";
             else
                 ans = f(N) + "";
 
             System.out.printf("Case #%d: ", n + 1);
             System.out.println(ans);
         }
     }
 
     static long f(long n)
     {
         Set<Long> digits = new HashSet<Long>();
         long nMult = 0;
         while (digits.size() < 10)
         {
             nMult += n;
             long nCopy = nMult;
             while (nCopy > 0)
             {
                 digits.add(nCopy % 10);
                 nCopy /= 10;
             }
         }
         return nMult;
     }
 }
