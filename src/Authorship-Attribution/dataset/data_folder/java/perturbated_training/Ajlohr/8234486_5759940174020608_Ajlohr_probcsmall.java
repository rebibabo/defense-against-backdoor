import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Scanner;
 import java.util.ArrayList;
 
 public class ProbCSmall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numcases = sc.nextInt();
        for(int curcase = 1; curcase<=numcases; curcase++)
        {
            int N = sc.nextInt();
            sc.nextLine();
            HashMap<String,HashSet<Integer>> linehas = new HashMap<String,HashSet<Integer>>();
            List<HashSet<String>> eng = new ArrayList<HashSet<String>>();
            List<HashSet<String>> fr = new ArrayList<HashSet<String>>();
            String engs = sc.nextLine();
            String[] engss = engs.split(" ");
            String frs = sc.nextLine();
            String[] frss = frs.split(" ");
            eng.add(new HashSet<String>());
            for(String s : engss)
            {
                eng.get(0).add(s);
                linehas.put(s, new HashSet<Integer>());
            }
            fr.add(new HashSet<String>());
            for(String s : frss)
            {
                fr.get(0).add(s);
                linehas.put(s, new HashSet<Integer>());
            }
            if(N>2)
            {
            List<HashSet<String>> extra = new ArrayList<HashSet<String>>();
            for(int i=0;i<N-2;i++)
            {
                String line = sc.nextLine();
                extra.add(new HashSet<String>());
                for(String s : line.split(" "))
                {
                    extra.get(i).add(s);
                    if(linehas.keySet().contains(s))
                    linehas.get(s).add(i);
                    else
                    {
                        linehas.put(s, new HashSet<Integer>());
                        linehas.get(s).add(i);
                    }
                }
            }
            globalmin = Integer.MAX_VALUE;
            System.out.println("Case #"+curcase+": "+tryall(eng,fr,extra));
 
            }else{
                eng.get(0).retainAll(fr.get(0));
                System.out.println("Case #"+curcase+": "+eng.get(0).size());
            }
 
        }
    }
    public static int globalmin;
        public static int tryall(List<HashSet<String>> en, List<HashSet<String>> fr, List<HashSet<String>> yet)
        {
            if(yet.size()==0)
            {
                return count(en,fr);
            }else
            {
                if(count(en,fr)>globalmin)
                    return Integer.MAX_VALUE;
                HashSet<String> h = yet.remove(yet.size()-1);
                en.add(h);
                int one = tryall(en,fr,yet);
                en.remove(en.size()-1);
                fr.add(h);
                int two = tryall(en,fr,yet);
                fr.remove(fr.size()-1);
                yet.add(h);
                if (globalmin >Math.min(one, two))
                globalmin = Math.min(one, two);
                return Math.min(one, two);
            }
        }
    public static int count(List<HashSet<String>> en, List<HashSet<String>> fr)
    {
        HashSet<String> english = collect(en);
        HashSet<String> french = collect(fr);       
        english.retainAll(french);
        return english.size();
    }
    public static HashSet<String> collect(List<HashSet<String>> en)
    {
        HashSet<String> ret = new HashSet<String>();
        for( HashSet<String> h : en)
        {
            for( String s : h)
                ret.add(s);
        }
        return ret;
    }
 }
