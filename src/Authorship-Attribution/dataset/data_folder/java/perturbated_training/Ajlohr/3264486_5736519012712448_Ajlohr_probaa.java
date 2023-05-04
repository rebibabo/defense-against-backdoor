package Qualification;
 
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class ProbAA {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextLong();
        sc.nextLine();
        LOOP:
        for(int curcase=1;curcase<=t;curcase++)
        {
            String s = sc.next();
            int K = sc.nextInt();
            sc.nextLine();
            boolean[] cakes = new boolean[s.length()];
            for(int i=0;i<cakes.length;i++)
            {
                cakes[i] = ('+' == s.charAt(i));
            }
            int flips = 0;
            for(int i=0;i<cakes.length-K+1;i++)
            {
                if(!cakes[i])
                {
                    
                    for(int j=0;j<K;j++)
                    {
                        cakes[i+j]= !cakes[i+j];
                    }
                    flips++;
 
                }
            }
            for(int j=0;j<K;j++)
            {
                if (!cakes[cakes.length-K+j])
                {
                    System.out.println("Case #"+curcase+": IMPOSSIBLE");
                    continue LOOP;
                }
            }
            System.out.println("Case #"+curcase+": "+flips);
        }
        
    }
 }
