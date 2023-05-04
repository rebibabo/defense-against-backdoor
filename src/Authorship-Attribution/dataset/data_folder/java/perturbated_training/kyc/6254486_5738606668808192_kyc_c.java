import java.util.*;
 import java.io.*;
 import java.math.*;
 
 public class C
 {
     static int N = 16;
     static int J = 50;
 
     public static void main(String ... orange) throws Exception
     {
         System.out.println("Case #1:");
         int numFound = 0;
         while (numFound < J)
         {
             StringBuilder builder = new StringBuilder("");
             builder.append('1');
             for (int i = 1; i < N - 1; i++)
                 builder.append(Math.random() < 0.5 ? '0' : '1');
             builder.append('1');
             if (isJamcoin(builder.toString()))
             {
                 System.out.print(builder);
                 for (BigInteger factor : factors)
                     System.out.print(" " + factor);
                 System.out.println();
                 numFound++;
             }
         }
     }
 
     static BigInteger[] bigs = new BigInteger[100];
     static {
         for (int i = 0; i < 100; i++)
             bigs[i] = new BigInteger((i + 2) + "");
     }
     static List<BigInteger> factors = new ArrayList<BigInteger>();
     static boolean isJamcoin(String code)
     {
         factors.clear();
         for (int base = 2; base <= 10; base++)
         {
             BigInteger big = new BigInteger(code, base);
             for (BigInteger b : bigs)
                 if (big.mod(b).equals(BigInteger.ZERO)) {
                     factors.add(b);
                     break;
                 }
             if (factors.size() != base - 1)
                 return false;
         }
         return true;
     }
 }
