package codejam2015;
 
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 
 public class CProbB {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int numcases = sc.nextInt();
        ML:
        for(int cas  = 1;cas <= numcases;cas++ )
        {
            System.out.print("Case #"+cas+": ");
            int K = sc.nextInt();
            int L = sc.nextInt();
            int S = sc.nextInt();
            sc.nextLine();
            String keys = sc.nextLine();
            String target = sc.nextLine();
            Map<Character,Integer> letters = new HashMap<Character,Integer>();
            for(int i=0;i<K;i++)
            {
                Character c = keys.charAt(i);
                if (letters.keySet().contains(c))
                {
                    letters.put(c, letters.get(c)+1);
                }else{
                    letters.put(c,1);
                }
            }
            for(int i=0;i<L;i++)
            {
                if(!letters.containsKey(target.charAt(i)))
                {
                    System.out.println("0.0000000");
                    continue ML;
                }
            }
        
            int r = rootlength(target);
            int maxnum = (S - L)/r +1 ;
            double probsingle = 1;
            for(int i=0;i<L;i++)
            {
                probsingle *= letters.get(target.charAt(i));
                probsingle/=K;
            }
            double totexp = probsingle *(S-L+1);
            
            System.out.printf("%.7f",maxnum-totexp);
            System.out.println();
            
            
        }
    }
    
    public static int rootlength(String S)
    {
        if(S.length()==1)
            return 1;
        LOOP:
        for(int i=1;i<S.length();i++)
        {
            for(int j=i;j<S.length();j++)
            {
                if(S.charAt(j)!=S.charAt(j%i))
                    continue LOOP;
            }
            return i;
        }
        return S.length();
    }
    
 }
