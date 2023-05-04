import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.*;
 
 public class FreshChocolate {
 
 
     static int N, P;
     static int[] a = new int[4];
 
     static Map<List<Integer>, Integer> map = new HashMap<>();
 
     static int F(List<Integer> a) {
         if (map.containsKey(a)) return map.get(a);
         int ans = 0;
 
         for (int i = 0; i < 4; i++) {
             if (a.get(i) > 0) {
                 List<Integer> b = new ArrayList<>(a);
                 b.set(i, b.get(i) - 1);
 
                 int sum = 0;
                 for (int j = 0; j < 4; j++)
                     sum += j * b.get(j);
 
                 ans = Math.max(ans, (sum % P == 0 ? 1 : 0) + F(b));
             }
         }
 
         map.put(a, ans);
         return ans;
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("A-small-attempt0.in"));
         PrintStream cout = new PrintStream("A-small-attempt0.out");
 
 
 
 
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
 
             N = cin.nextInt();
             P = cin.nextInt();
             a = new int[4];
             for (int i = 0; i < N; i++) {
                 a[cin.nextInt() % P]++;
             }
             map = new HashMap<>();
 
             map.put(Arrays.asList(0, 0, 0, 0), 0);
 
             String ans = "" + F(Arrays.asList(a[0], a[1], a[2], a[3]));
             cout.printf("Case #%d: %s%n", _case, ans);
         }
 
         cin.close();
         cout.close();
     }
 }
