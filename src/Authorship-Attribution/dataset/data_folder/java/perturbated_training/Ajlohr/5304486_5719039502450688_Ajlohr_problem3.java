package Round1A;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.TreeSet;
 
 public class Problem3 {
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner sc = new Scanner(new FileInputStream("C-small-attempt1.in"));
        PrintStream out = new PrintStream(new FileOutputStream("out3.txt"));
        long t = sc.nextLong();
        sc.nextLine();
        LOOP:
        for(int curcase=1;curcase<=t;curcase++)
        {
            List<Integer> startState = new ArrayList<Integer>(6);
            int dragH = sc.nextInt();
            startingHealth = dragH;
            int dragA = sc.nextInt();
            int knH = sc.nextInt();
            int knA = sc.nextInt();
            int B = sc.nextInt();
            int D = sc.nextInt();
            startState.add(dragH);
            startState.add(dragA);
            startState.add(knH);
            startState.add(knA);
            startState.add(B);
            startState.add(D);
            startState.add(0);
            List<Set<List<Integer>>> frontier = new ArrayList<>();
            frontier.add(new HashSet<>());
            frontier.get(0).add(startState);
            Set<List<Integer>>  seen = new HashSet<>();
            seen.add(startState);
            for(int i=0;!frontier.get(i).isEmpty();i++)
            {
                System.out.println("Case: "+curcase+" frontier: "+frontier.get(i).size()+ " i:"+i + "  seen:"+seen.size());
                frontier.add(new HashSet<>());
                for(List<Integer> state : frontier.get(i))
                {
                    if(state.get(2)<=0)
                    {
                        out.println("Case #"+curcase+": "+i);
                        continue LOOP;
                    }
                    if(state.get(0)<=0)
                    {
                        continue;
                    }
 
                    for(Move m : Move.values())
                    {
                        if((state.get(0)> state.get(3))&&(m==Move.CURE))
                        {
                            continue;
                        }
                        
                        if((state.get(6)==1)&&((m==Move.BUFF)||(m==Move.DEBUFF)))
                        {
                            continue;
                        }
                        List<Integer> newstate = step(state,m);
                        if(m==Move.ATTACK)
                        {
                            newstate.set(6, 1);
                        }
                        boolean hasbetter = strictlyBetter(state,newstate);
                        for(List<Integer> s:seen)
                        {
                            hasbetter = hasbetter ||strictlyBetter(s,newstate);
                        }
                        if(seen.contains(newstate))
                            continue;
                        seen.add(newstate); 
 
                        if(!hasbetter)
                        frontier.get(i+1).add(newstate);
                    }
                }
            }
            out.println("Case #"+curcase+": IMPOSSIBLE");
        }
        
    }
    public static int startingHealth;
    public static List<Integer> step(List<Integer> config,Move move)
    {
        List<Integer> ret = new ArrayList<>(config);
        switch(move)
        {
        case ATTACK:
            ret.set(2, ret.get(2)-ret.get(1));
            ret.set(0, ret.get(0)-ret.get(3));
            return ret;
        case BUFF:
            ret.set(1, ret.get(1) + ret.get(4));
            ret.set(0, ret.get(0)-ret.get(3));
            return ret;
        case DEBUFF:
            ret.set(3, ret.get(3)-ret.get(5));
            ret.set(0, ret.get(0)-ret.get(3));
            return ret;
        case CURE:
            ret.set(0, startingHealth);
            ret.set(0, ret.get(0)-ret.get(3));
            return ret;
        }
        throw(new IllegalStateException());
    }
    
    public static boolean strictlyBetter(List<Integer> s1,List<Integer> s2)
    {
        if((s1.get(0)>=s2.get(0))&&(s1.get(2)<=s2.get(2)&&(s1.get(1)>=s2.get(1))&&(s1.get(3)<=s2.get(3))))
        return true;
        return false;
    }
    public static enum Move
    {
        ATTACK,
        BUFF,
        DEBUFF,
        CURE;
    }
 }
