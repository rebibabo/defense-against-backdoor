package Round1A;
 
 import java.util.HashMap;
 import java.util.Scanner;
 import java.util.TreeSet;
 
 public class ProbB {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextInt();
        for(int curcase=1;curcase<=t;curcase++)
        {
                HashMap<Integer,Integer> counts = new HashMap<Integer,Integer>();
                int N = sc.nextInt();
                for(int i=0;i<2*N*N-N;i++)
                {
                    int in = sc.nextInt();
                    if(!counts.containsKey(in))
                        counts.put(in, 0);
                    counts.put(in, counts.get(in)+1);
                }
                TreeSet<Integer> odds = new TreeSet<Integer>();
                for(Integer i : counts.keySet())
                {
                    if(counts.get(i)%2==1)
                        odds.add(i);
                }
                String ret ="";
                for(Integer i : odds)
                {
                    ret= ret+" "+i;
                }
                System.out.println("Case #"+curcase+":"+ret);
    
        }
        
    }
 }
