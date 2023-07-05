package gcj2015.round3;
 
 import java.io.PrintWriter;
 import java.util.*;
 
 public class B {
 
     public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         PrintWriter out = new PrintWriter(System.out);
 
         int t = in.nextInt();
         for (int cs = 1 ; cs <= t ; cs++) {
             int n = in.nextInt();
             int k = in.nextInt();
             int[] a = new int[n-k+1];
             for (int i = 0 ; i < a.length ; i++) {
                 a[i] = in.nextInt();
             }
             int ans = solve(n, k, a);
             out.println(String.format("Case #%d: %d", cs, ans));
         }
         out.flush();
     }
 
     private static int solve(int n, int k, int[] a) {
         graph = new List[n];
         for (int i = 0 ; i < n ; i++) {
             graph[i] = new ArrayList<>();
         }
         for (int i = 0 ; i < a.length-1 ; i++) {
             int to = i + k;
             int diff = a[i+1] - a[i];
             graph[i].add(new int[]{to, diff});
         }
         range = new int[k][2];
         for (int grp = 0 ; grp < k ; grp++) {
             dfs(grp, grp, 0);
         }
 
         int max = 0;
         Set<Integer> dset = new HashSet<>();
         for (int i = 0 ; i < k ; i++) {
             max = Math.max(max, range[i][1] - range[i][0]);
             dset.add(range[i][1] - range[i][0]);
         }
         if (k >= 2 && dset.size() == 1) {
             int od = 0;
             int ev = 0;
             for (int i = 0 ; i < k ; i++) {
                 int par = Math.abs(range[i][0]) % 2;
                 if (par == 1) {
                     od++;
                 } else {
                     ev++;
                 }
             }
             if (od == k || ev == k) {
                 if (Math.abs(a[0]) % 2 == 1) {
                     max++;
                 }
             } else {
                 if (Math.abs(a[0]) % 2 == 0) {
                     max++;
                 }
             }
         }
 
 
         return max;
     }
 
     static int[][] range;
     static List<int[]>[] graph;
 
     private static void dfs(int gid, int now, int val) {
         range[gid][0] = Math.min(range[gid][0], val);
         range[gid][1] = Math.max(range[gid][1], val);
 
         for (int[] edge : graph[now]) {
             int to = edge[0];
             int vk = edge[1];
             dfs(gid, to, val + vk);
         }
     }
 
     static void debug(Object... o) {
         System.err.println(Arrays.deepToString(o));
     }
 }
 
 
 
