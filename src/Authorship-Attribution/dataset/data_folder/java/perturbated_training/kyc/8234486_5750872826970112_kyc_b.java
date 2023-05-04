import java.util.*;
 import java.io.*;
 
 public class B
 {
     static double E = 1e-12;
 
     public static void main(String ... orange) throws Exception
     {
         Scanner input = new Scanner(System.in);
         int numCases = input.nextInt();
         for (int n = 0; n < numCases; n++)
         {
             int N = input.nextInt();
             double targetV = input.nextDouble();
             double targetT = input.nextDouble();
 
             double[] rates = new double[N];
             double[] temps = new double[N];
             for (int i = 0; i < N; i++)
             {
                 rates[i] = input.nextDouble();
                 temps[i] = input.nextDouble();
             }
 
             double solution = 1e99;
 
             for (int target = 0; target < N; target++)
                 if (Math.abs(targetT - temps[target]) < E)
                 {
                     double cand = targetV / rates[target];
                     if (cand < solution)
                         solution = cand;
                 }
 
             if (N == 2)
             {
                 if (Math.abs(temps[0] - temps[1]) > E)
                 {
                     double vol1 = (targetT - temps[1]) * targetV /
                         (temps[0] - temps[1]);
                     double vol2 = targetV - vol1;
                     if (vol1 >= 0 && vol2 >= 0)
                     {
                         double cand = Math.max(vol1 / rates[0], vol2 / rates[1]);
                         if (cand < solution)
                             solution = cand;
                     }
                 }
             }
 
             
 
             if (solution >= 1e99 - E)
                 System.out.printf("Case #%d: IMPOSSIBLE\n", n + 1);
             else
                 System.out.printf("Case #%d: %.8f\n", n + 1, solution);
         }
     }
 }
