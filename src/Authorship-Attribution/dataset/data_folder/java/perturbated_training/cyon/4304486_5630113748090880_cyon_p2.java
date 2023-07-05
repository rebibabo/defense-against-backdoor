package ra;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.*;
 import java.io.PrintWriter;
 
 public class p2 {
 
     public static void main(String[]args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(System.out);
         int T = Integer.parseInt(br.readLine());
         for(int i=1;i<=T;i++) {
             pw.println("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static List<List<Integer>> specialSort(List<List<Integer>> z, int N) {
 
         List<List<Integer>> res = new ArrayList<>();
 
         for (int i=0;i<N;i++) {
             int mp = Integer.MAX_VALUE;
             for (List<Integer> li : z) {
                 mp = Math.min(mp, li.get(i));
             }
             for (Iterator<List<Integer>> li = z.iterator();li.hasNext();) {
                 List<Integer> v = li.next();
                 if (v.get(i).equals(mp)) {
                     res.add(v);
                     li.remove();
                 }
             }
         }
         return res;
     }
 
     private static String solve(BufferedReader br) throws IOException {
         int N = Integer.parseInt(br.readLine());
         List<List<Integer>> lls = new ArrayList<>();
 
         for (int i=0;i<2*N-1;i++) {
             StringTokenizer st = new StringTokenizer(br.readLine());
             List<Integer> l = new ArrayList<>();
             for (int j=0;j<N;j++) {
                 int ni = Integer.parseInt(st.nextToken());
                 l.add(ni);
             }
             lls.add(l);
         }
 
         List<List<Integer>> ll = specialSort(lls, N);
 
         
 
         
 
         int[]res = new int[N];
         int idx = N-1;
         for (int i=0;i<N && 2*i+1 < ll.size();i++) {
             if (!ll.get(2*i).get(i).equals(ll.get(2*i+1).get(i))) {
                 idx = i;
                 break;
             }
         }
 
         List<Integer> cand = ll.get(2*idx);
 
         for (int i=0,pos=0;i<2*N-1;i+=2,pos++) {
             
             int candi = cand.get(pos);
             if (i == 2*idx) {
                 i--;
                 res[pos] = candi;
                 continue;
             }
             
             int z1 = ll.get(i).get(idx);
             int z2 = ll.get(i+1).get(idx);
             if (candi == z1) {
                 res[pos] = z2;
             }
             else {
                 res[pos] = z1;
             }
         }
         
 
         StringBuilder bob = new StringBuilder();
         for (int i=0;i<res.length;i++) {
             bob.append(res[i]);
             if (i != res.length-1) bob.append(" ");
         }
         return bob.toString();
     }
 
     private static final class CL implements Comparator<List<Integer>> {
         @Override
         public int compare(List<Integer> o1, List<Integer> o2) {
             for (int i=0;i<o1.size();i++) {
                 int rs = Integer.compare(o1.get(i), o2.get(i));
                 if (rs != 0) return rs;
             }
             return 0;
         }
     }
 
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
