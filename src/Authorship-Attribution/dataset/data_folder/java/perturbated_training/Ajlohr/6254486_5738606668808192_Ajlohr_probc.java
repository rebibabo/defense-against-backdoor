package Qualification;
 
 import java.util.ArrayList;
 import java.util.Scanner;
 
 public class ProbC {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextInt();
        for(int curcase=1;curcase<=t;curcase++)
        {
            int N = sc.nextInt();
            int J = sc.nextInt();
            ArrayList<String> ret = new ArrayList<String>();
            boolean[] num = new boolean[N];
            num[N-1] =true; num[0]=true;
            numleft= J;
            consider(num,ret,1);
            System.out.println("Case #1:");
            if(numleft>0)
            System.out.println("impossible");
            else
            for(String s : ret)
            {
                System.out.println(s);
            }
        }
    }
    public static int numleft;
    public static int ping=0;
    public static void consider(boolean[] n,ArrayList<String> r,int dig)
    {
        if(numleft<=0)
            return;
        if(dig<n.length-1)
        {
            consider(n,r,dig+1);
            n[dig] =true;
            if(dig==4)
                System.out.println(ping++);
            consider(n,r,dig+1);
            n[dig] = false;
            return;
        }
        String out = base(n,10)+"";
        for(int i=2;i<=10;i++)
        {
            long b = base(n,i);long f = factor(b);
            if(f==b)
                return;
            out+=" "+f;
        }
        numleft--;
        r.add(out);
    }   
    public static long base(boolean[] num,int base)
    {
        long addin=1;
        long ret=0;
        for(int i=0;i<num.length;i++)
        {
            if(num[i])
                ret+=addin;
            addin*=base;
        }
        return ret;
    }
    public static long factor (long num) {
        if (num % 2 == 0) return 2;
        for (long tst = 3 ; tst * tst <= num ; tst += 2)
            if (num % tst == 0)
                return tst;
        return num;
    }
 }
