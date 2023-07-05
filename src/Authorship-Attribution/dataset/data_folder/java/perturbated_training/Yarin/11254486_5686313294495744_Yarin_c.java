package round1b;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 
 public class C {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("round1b/C-small-attempt0.in"), new FileOutputStream("round1b/C-small-0.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new C().solve(io);
         }
         io.close();
     }
 
     private void solve(Kattio io) {
         int n = io.getInt();
         HashMap<String, Integer> aWords = new HashMap<>();
         HashMap<String, Integer> bWords = new HashMap<>();
         ArrayList<BipartiteMatching.Edge> edges = new ArrayList<>();
         for (int i = 0; i < n; i++) {
             String a = io.getWord();
             String b = io.getWord();
             Integer ae = aWords.get(a), be = bWords.get(b);
             if (ae == null) {
                 aWords.put(a, aWords.size());
                 ae = aWords.get(a);
             }
             if (be == null) {
                 bWords.put(b, bWords.size());
                 be = bWords.get(b);
             }
             edges.add(new BipartiteMatching.Edge(ae, be));
         }
         List<BipartiteMatching.Edge> matching = BipartiteMatching.findMaximumMatching(edges);
         io.println(n - (aWords.size() + bWords.size() - matching.size()));
     }
 }
