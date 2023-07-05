import java.util.*;
 import java.io.*;
 
 public class C
 {
     public static void main(String ... orange) throws Exception
     {
         Scanner input = new Scanner(System.in);
         int numCases = input.nextInt();
         for (int n = 0; n < numCases; n++)
         {
             int N = input.nextInt();
             List<Hiker> hikers = new ArrayList<Hiker>();
             for (int i = 0; i < N; i++)
             {
                 int D = input.nextInt();
                 int H = input.nextInt();
                 int M = input.nextInt();
                 for (int j = 0; j < H; j++)
                     hikers.add(new Hiker(D, M + j));
             }
 
             int minEncounters = Integer.MAX_VALUE;
             int numPassed = 0;
             while (true)
             {
                 
                 Hiker next = hikers.get(0);
                 for (Hiker hiker : hikers)
                     if (hiker.cross < next.cross)
                         next = hiker;
 
                 if (next.passed)
                     break;
                 next.passed = true;
 
                 
                 int numEncounters = 0;
                 for (Hiker hiker : hikers)
                     if (hiker != next && hiker.cross >= next.cross + 360L * next.M)
                         numEncounters++;
 
                 if (numPassed + numEncounters < minEncounters)
                     minEncounters = numPassed + numEncounters;
                 numPassed++;
 
                 next.cross += 360L * next.M;
             }
 
             System.out.printf("Case #%d: ", n + 1);
             System.out.println(minEncounters);
         }
     }
 
     static class Hiker
     {
         int D;
         int M;
         long cross;
         boolean passed;
 
         Hiker(int D, int M)
         {
             this.D = D;
             this.M = M;
             this.cross = (360L - D) * M;
             this.passed = false;
         }
     }
 }
