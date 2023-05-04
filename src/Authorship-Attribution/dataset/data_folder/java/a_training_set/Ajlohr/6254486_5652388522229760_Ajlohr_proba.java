package Qualification;
 
 import java.util.Scanner;
 
 public class ProbA {
 public static void main(String[] args)
 {
    Scanner sc = new Scanner(System.in);
    long t = sc.nextInt();
    for(int curcase=1;curcase<=t;curcase++)
    {
        long N = sc.nextInt();
        boolean[] seen = new boolean[10];
        if(N==0)
        {
            System.out.println("Case #"+curcase+": INSOMNIA");
            continue;
        }
        for(int i=1;true;i++)
        {
            
            digits(i*N,seen);
            if(check(seen))
            {
                System.out.println("Case #"+curcase+": "+(i*N));
                break;
            }
        }
    }
    
 }
 public static boolean check(boolean[] a)
 {
    for(int i=0;i<10;i++)
        if(!a[i])
            return false;
    return true;
 }
 public static void digits(long n,boolean[] arr)
 {
    if(n==0)
        return;
    arr[(int)n%10]=true;
    digits(n/10,arr);
 }
 }
