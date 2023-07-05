import java.util.*;
 import java.io.*;
 
 public class D
 {
     public static void main(String ... orange) throws Exception
     {
         Scanner input = new Scanner(System.in);
         int numCases = input.nextInt();
         for (int n = 0; n < numCases; n++)
         {
             int len = input.nextInt();
             int com = input.nextInt();
             int look = input.nextInt();
 
             System.out.printf("Case #%d:", n + 1);
             if (com * look < len)
                 System.out.println(" IMPOSSIBLE");
             else
             {
                 Set<Long> used = new HashSet<Long>();
                 for (int i = 0; i * com < len; i++)
                 {
                     long index = 0;
                     for (int j = 0; j < com && i * com + j < len; j++)
                         index += (i * com + j) * pow(len, j);
                     if (!used.contains(index))
                     {
                         used.add(index);
                         System.out.print(" " + (index + 1));
                     }
                 }
                 System.out.println();
             }
         }
     }
 
     static long pow(long base, long exp)
     {
         return (long) Math.round(Math.pow(base, exp));
     }
 }
 
