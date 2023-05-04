package codejam.y2016.r3.z2;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.PrintWriter;
 import java.util.*;
 
 public class Solution {
 
     public static void main(String[] args) throws FileNotFoundException {
         FileInputStream fileInputStream = new FileInputStream("/Users/ratmir/Downloads/B-small-attempt1 (1).in");
         
         Scanner sc = new Scanner(fileInputStream);
         FileOutputStream outputStream = new FileOutputStream("/Users/ratmir/Documents/code/hackerrank/algs/src/codejam/y2016/r3/z2/ztest3.txt");
         PrintWriter printWriter = new PrintWriter(outputStream);
         int tn = sc.nextInt();
         for (int ti = 1; ti <= tn; ti++) {
             int n = sc.nextInt();
             int m = sc.nextInt();
             if (n==2 && m==1) {
                 String res = "Case #" + ti + ": POSSIBLE\n01\n00";
                 printWriter.write(res + "\n");
                 System.out.println(res);
                 continue;
             }
             if (n==2 && m>1) {
                 String res = "Case #" + ti + ": IMPOSSIBLE";
                 printWriter.write(res + "\n");
                 System.out.println(res);
                 continue;
             }
             int nv = 1 + ((n - 1) * (n - 2));
             int cnt = 1 << nv;
             System.err.println(cnt);
             int cntt = 0;
             boolean ok = false;
             int [] rx = new int[nv];
             int [] cx = new int[nv];
             int cidx = 0;
             for (int i=0;i<n-1;i++) {
                 for (int j=1;j<=n-1;j++) {
                     if (i!=j) {
                         rx[cidx] = i;
                         cx[cidx] = j;
                         cidx++;
                     }
                 }
             }
             inner:
             for (int i = 0; i < cnt; i++) {
                 Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
                 Map<Integer, Integer> income = new HashMap<Integer, Integer>();
                 int k = i;
                 int kx = 0;
                 while (k > 0) {
                     if (k % 2 == 1) {
                         int v = rx[kx];
                         int v2 = cx[kx];
                         Set<Integer> integers = map.get(v);
                         if (integers == null) {
                             integers = new HashSet<Integer>();
                             map.put(v, integers);
                         }
                         integers.add(v2);
                         Integer idx1 = income.get(v2);
                         if (idx1 == null) {
                             income.put(v2, 1);
                         } else {
                             income.put(v2, idx1 + 1);
                         }
                     }
                     k = k / 2;
                     kx++;
                 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
                 Map<Integer, Integer> ways = new HashMap<Integer, Integer>();
                 ways.put(0, 1);
                 Set<Integer> cur = new HashSet<Integer>();
                 cur.add(0);
                 int x = 0;
                 while (cur.size() > 0) {
                     x++;
                     if (x > 100) {
                         continue inner;
                     }
                     Set<Integer> cs = new HashSet<Integer>();
                     for (Integer integer : cur) {
                         Set<Integer> ss = map.get(integer);
                         if (ss == null) {
                             continue;
                         }
                         for (Integer v1 : map.get(integer)) {
                             
                             Integer w1 = ways.get(v1);
                             if (w1 == null) {
                                 ways.put(v1, ways.get(integer));
                             } else {
                                 ways.put(v1, w1 + ways.get(integer));
                             }
                             Integer inc = income.get(v1);
                             if (inc == 1) {
                                 cs.add(v1);
                             } else {
                                 income.put(v1, inc - 1);
                             }
                         }
                     }
                     cur = cs;
                 }
                 if (ways.get(n - 1) != null && ways.get(n - 1) == m) {
 
 
                     String res = "Case #" + ti + ": POSSIBLE";
                     printWriter.write(res + "\n");
                     System.out.println(res);
                     StringBuilder sb = new StringBuilder();
                     for (int idx = 0; idx < n; idx++) {
                         for (int jdx = 0; jdx < n; jdx++) {
                             if (idx == jdx) {
                                 sb = sb.append(0);
                             } else {
                                 sb = sb.append((map.get(idx) == null || !map.get(idx).contains(jdx)) ? 0 : 1);
                             }
                         }
                         sb = sb.append("\n");
                     }
                     System.out.print(sb);
                     printWriter.write(sb.toString());
                     ok = true;
                     break inner;
                 }
             }
             if (!ok) {
                 String res = "Case #" + ti + ": IMPOSSIBLE";
                 printWriter.write(res + "\n");
                 System.out.println(res);
             }
         }
         printWriter.close();
     }
 
 }
