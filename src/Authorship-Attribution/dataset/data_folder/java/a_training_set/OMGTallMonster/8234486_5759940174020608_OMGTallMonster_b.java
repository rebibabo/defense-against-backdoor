import java.util.*;
 
 class B {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d: %s\n", i, solve(scan));
       }
    }
 
    public static HashSet<String> wordSet(String line) {
       HashSet<String> a = new HashSet<>();
       for (Scanner l = new Scanner(line); l.hasNext(); ) {
          a.add(l.next());
       }
       return a;
    }
 
    public static Object solve(Scanner scan) {
       int N = scan.nextInt();
       scan.nextLine();
 
       HashSet<String> a = wordSet(scan.nextLine()), b = wordSet(scan.nextLine());
       HashSet<String> inter = new HashSet<>(a);
       inter.retainAll(b);
       a.removeAll(inter);
       b.removeAll(inter);
 
       List<HashSet<String>> lst = new ArrayList<>();
       for (int i = 0; i < N-2; i++) {
          lst.add(wordSet(scan.nextLine()));
       }
       
       return recur(a,b,inter,lst,0,Integer.MAX_VALUE);
    }
 
    public static int recur(HashSet<String> a, HashSet<String> b, HashSet<String> inter, List<HashSet<String>> sets, int ndx, int best) {
       if (ndx == sets.size()) {
          return inter.size();
       }
       HashSet<String> s = sets.get(ndx);
 
       HashSet<String> s1 = new HashSet<>(s), s2 = new HashSet<>(s);
       s1.retainAll(a);
       s2.retainAll(b);
 
       
       HashSet<String> interTmp = new HashSet<>(inter);
       interTmp.addAll(s2);
       if (interTmp.size() < best) {
          HashSet<String> aTmp = new HashSet<>(a), bTmp = new HashSet<>(b);
          aTmp.addAll(s);
          aTmp.removeAll(interTmp);
          bTmp.removeAll(interTmp);
          best = Math.min(recur(aTmp,bTmp,interTmp, sets, ndx+1, best), best);
       }
 
       
       interTmp = new HashSet<>(inter);
       interTmp.addAll(s1);
       if (interTmp.size() < best) {
          HashSet<String> aTmp = new HashSet<>(a), bTmp = new HashSet<>(b);
          bTmp.addAll(s);
          aTmp.removeAll(interTmp);
          bTmp.removeAll(interTmp);
          best = Math.min(recur(aTmp,bTmp,interTmp, sets, ndx+1, best), best);
       }
 
       return best;
    }
 }
