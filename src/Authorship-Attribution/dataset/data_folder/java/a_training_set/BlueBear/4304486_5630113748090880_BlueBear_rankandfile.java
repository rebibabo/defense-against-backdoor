import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.*;
 
 public class RankAndFile {
 
     static int n;
     static int[][] a;
     static int[][] hehe;
 
     static int cmp(int[] u, int[] v) {
         for (int i = 0; i < u.length; i++)
             if (u[i] < v[i]) return -1;
             else if (u[i] > v[i]) return 1;
         return 0;
     }
 
     static TreeMap<int[], Integer> getAns(int[][] hehe) {
         TreeMap<int[], Integer> set = new TreeMap<>((u, v) -> cmp(u, v));
         for (int i = 0; i < n; i++) {
             if (!set.containsKey(hehe[i])) set.put(hehe[i], 0);
             set.put(hehe[i], set.get(hehe[i]) + 1);
 
             int[] col = new int[n];
             for (int j = 0; j < n; j++)
                 col[j] = hehe[j][i];
 
             if (!set.containsKey(col)) set.put(col, 0);
             set.put(col, set.get(col) + 1);
         }
 
         for (int[] key : a) {
             if (!set.containsKey(key)) continue;
 
             set.put(key, set.get(key) - 1);
             if (set.get(key) == 0) set.remove(key);
         }
         return set;
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("B-small-attempt1.in"));
         PrintStream cout = new PrintStream("B-small-attempt1.out");
 
 
 
 
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
             StringBuilder ans = new StringBuilder();
 
             n = cin.nextInt();
             a = new int[2 * n - 1][n];
             for (int i = 0; i < a.length; i++)
                 for (int j = 0; j < a[i].length; j++)
                     a[i][j] = cin.nextInt();
             Arrays.sort(a, (int[] u, int[] v) -> cmp(u, v));
             hehe = new int[n][n];
 
             for (int mask = 0; mask < (1 << a.length); mask++) {
                 if (Integer.bitCount(mask) != n) continue;
                 int ptr = 0;
                 for (int j = 0; j < a.length; j++)
                     if ((mask & (1 << j)) != 0) {
                         for (int k = 0; k < n; k++) hehe[ptr][k] = a[j][k];
                         ptr++;
                     }
 
                 TreeMap<int[], Integer> set = getAns(hehe);
                 if (set.size() == 1 && set.entrySet().iterator().next().getValue() == 1) {
                     for (int val : set.keySet().iterator().next()) {
                         ans.append(" ");
                         ans.append(val);
                     }
                     break;
                 }
             }
 
             cout.printf("Case #%d:%s%n", _case, ans.toString());
         }
 
         cin.close();
         cout.close();
     }
 }
