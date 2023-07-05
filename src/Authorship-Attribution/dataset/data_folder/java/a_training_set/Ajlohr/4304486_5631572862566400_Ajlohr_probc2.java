package Round1A;
 
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.TreeSet;
 
 public class ProbC2 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextInt();
        for(int curcase=1;curcase<=t;curcase++)
        {
            
                int N = sc.nextInt();
                HashMap<Integer,Integer> bffs = new HashMap<Integer,Integer>();
                for(int i=1;i<=N;i++)
                {
                    int in = sc.nextInt();
                    bffs.put(i, in);
                }
                Map<Integer,Integer> compon = new HashMap<Integer,Integer>();
                for(int i=1;i<=N;i++)
                {
                    compon.put(i,i);
                }
                Map<Integer,Integer> colors= new HashMap<Integer,Integer>();
                boolean haschanged = true;
                while(haschanged)
                {
                    
                    haschanged = false;
                    for(int i=1;i<=N;i++)
                    {
                        colors.put(compon.get(i),colors.keySet().size());
                        if(compon.get(i)!=compon.get(bffs.get(i)))
                        {
                            compon.put(i, compon.get(bffs.get(i)));
                            haschanged = true;
                            break;
                        }
                    }
                }
                colors = new HashMap<Integer,Integer>();
                for(int i=1;i<=N;i++)
                {
                    if(!colors.containsKey(compon.get(i)))
                    colors.put(compon.get(i),colors.keySet().size());
                }
                Set<Integer> actual= new HashSet<Integer>();
                for(int i=1;i<=N;i++)
                {
                    compon.put(i,colors.get(compon.get(i)));
                    actual.add(compon.get(i));
                }
                int comps = actual.size();
                int[] best = new int[comps];
                boolean[] pair = new boolean[comps];
                int[] obest  =  new int[comps];
 
                for(int j=0;j<comps;j++)
                {
                Set<Integer> bffae = new TreeSet<Integer>();
                Map<Integer,Integer> bffaebranch = new HashMap<Integer,Integer>();
                for(int i=1;i<=N;i++)
                {
                    if(compon.get(i)!=j)
                        continue;
                    if(i==bffs.get(bffs.get(i)))
                    {
                        bffae.add(i);
                        bffae.add(bffs.get(i));
                        bffaebranch.put(i, 0);
                        bffaebranch.put(bffs.get(i), 0);
                    }
                }
                for(int i=1;i<=N;i++)
                {
 
                    if(compon.get(i)!=j)
                        continue;
                    if(bffae.contains(i))
                    {
                        pair[j] = true;
                        if(2>best[j])
                            best[j] = 2;
                        if(2>obest[j])
                            obest[j] = 2;
                        continue;
                    }
                    Map<Integer,Integer> seen = new HashMap<Integer,Integer>();
                    seen.put(i,0);
                    int d=0;
                    int cur =i;
                    while(true)
                    {
                        d++;
                        int next = bffs.get(cur);
                        if(seen.keySet().contains(next))
                        {
                            if(d - seen.get(next)>best[j])
                                best[j] = d - seen.get(next);
 
                            break;
                        }
                        if(bffae.contains(next))
                        {
                            int dist = bffaebranch.get(bffs.get(next));
                            if(d  > bffaebranch.get(next))
                            {
                                bffaebranch.put(next,d);
                            }
                            if(2+ d + dist>best[j])
                                best[j] = 2+ d + dist;
                            if(2+ d + dist>obest[j])
                                obest[j] = 2+ d + dist;
                            break;
                        }
                        
                        seen.put(next, d);
                        cur = next;
                    }
                }
                }
                
                int bestb=0;
                int b = 0;
                for(int i = 0;i<comps;i++)
                {
                    if(best[i]>b)
                    {
                        b=best[i];
                    }
                }
                int bb = 0;
                for(int i = 0;i<comps;i++)
                {
                        bb+=obest[i];
                }
                bestb = Math.max(b, bb);
                System.out.println("Case #"+curcase+": "+bestb);
        }
        
    }
 }
