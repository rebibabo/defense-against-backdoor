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
             int P = input.nextInt();
             int[] nums = new int[P];
             for (int i = 0; i < P; i++)
                 nums[i] = input.nextInt();
             int[] freqs = new int[P];
             for (int i = 0; i < P; i++)
                 freqs[i] = input.nextInt();
 
             int totalFreq = 0;
             for (int freq : freqs)
                 totalFreq += freq;
 
             List<Integer> result = new ArrayList<Integer>();
             for (int i = 0; (1 << i) < freqs[0]; i++)
                 result.add(0);
             for (int i = 1; (1 << result.size()) < totalFreq; i++)
             {
                 int freq = freqs[i];
                 for (int subset = 0; subset < (1 << result.size()); subset++)
                 {
                     int sum = 0;
                     for (int j = 0; j < result.size(); j++)
                         if ((subset & (1 << j)) > 0)
                             sum += result.get(j);
                     if (sum == nums[i])
                         freq--;
                 }
                 for (int j = 0; j * freqs[0] < freq; j++)
                     result.add(nums[i]);
             }
 
             System.out.printf("Case #%d:", n + 1);
             for (int res : result)
                 System.out.print(" " + res);
             System.out.println();
         }
     }
 }
