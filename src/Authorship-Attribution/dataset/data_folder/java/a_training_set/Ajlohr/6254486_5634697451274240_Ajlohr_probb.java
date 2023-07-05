package Qualification;
 
 import java.util.Scanner;
 
 public class ProbB {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextInt();
        for(int curcase=1;curcase<=t;curcase++)
        {
            String S = sc.next();
            int changes = 0;
            for(int i=0;i<S.length()-1;i++)
            {
                if(S.charAt(i)!=S.charAt(i+1))
                    changes++;
            }
 
            if(S.charAt(S.length()-1)=='+')
            {
                changes--;
            }
            System.out.println("Case #"+curcase+": "+comp(changes));
        }
        
    }
    
    public static int comp(int n)
    {
        if(n==-1)
            return 0;
        if(n==0)
            return 1;
        return comp(n-1)+1;
    }
 }
