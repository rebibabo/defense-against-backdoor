package Qualification;
 
 import java.util.Scanner;
 
 public class ProbDsmall {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextInt();
        for(int curcase=1;curcase<=t;curcase++)
        {
            int K= sc.nextInt();
            int C= sc.nextInt();
            int S= sc.nextInt();        
            if(K==S)
            {System.out.print("Case #"+curcase+":");
            for(int i = 1;i<K+1;i++)
            {
                System.out.print(" "+i);
            }
            System.out.println();
            }else
            {
            
            }
        }
    }
 }
