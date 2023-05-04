package codejam2015;
 
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 
 public class CProbCSmall2 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numcases = sc.nextInt();
        ML:
        for(int cas  = 1;cas <= numcases;cas++ )
        {
            System.out.print("Case #"+cas+": ");
            int C = sc.nextInt();
            int D = sc.nextInt();
            int v = sc.nextInt();
            V=v;
            int[] coins = new int[D];
            Set<Integer> coin = new HashSet<Integer>();
            for(int i=0;i<D;i++)
            {
                coins[i] = (sc.nextInt());
                coin.add(coins[i]);
            }
            int modi =0;
            if(!coin.contains(1))
                {modi++;
                coin.add(1);
                }
            if((!coin.contains(2))&&(V>1))
            {modi++;
            coin.add(2);
            }
            for(int i=0;i<=V-D;i++)
            {
            cando = false;
            tryall(coin,i);
            if(cando)
            {
                System.out.println(i+modi);
                break;
            }
            }
            
        }
    }
    static int V;
    static boolean cando;
    public static void tryall(Set<Integer> S,int i)
    {
        if(cando)
            return;
        if(i==0)
        {
            sums = new HashSet<Integer>();
            allsums(S,S.size(),0);
            Set<Integer> asdass= sums;
            cando = true;
            for(int j=1;j<=V;j++)
            {
                if(!sums.contains(j))
                    cando = false;
            }
            return;
        }
        
        for(int j=1;j<V;j++)
        {
            if(S.contains(j))
                continue;
            S.add(j);
            tryall(S,i-1);
            S.remove(j);
            
        }
    }
    static Set<Integer> sums;
    public static void allsums(Set<Integer> source,int sizesums,int sum)
    {
        sums.add(sum);
        if(sizesums ==0)
        {
            
            return;
        }
        Set<Integer> cl  = new HashSet<Integer>(source);
        for(Integer s : cl)
        {
            source.remove(s);
            allsums(source,sizesums-1,sum+s);
 
            source.add(s);
        }
    }
 }
