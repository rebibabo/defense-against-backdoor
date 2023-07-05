package round1c;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 
 public class C {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("round1c/C-small-attempt0.in"), new FileOutputStream("round1c/C-small-0.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new C().solve(io);
         }
         io.close();
     }
 
     private int firstImpossible(List<Integer> denom, int max) {
         boolean ok[] = new boolean[max+1];
         for (int i = 0; i < (1 << denom.size()); i++) {
             int sum = 0;
             for (int j = 0; j < denom.size(); j++) {
                 if (((1<<j) & i) > 0) {
                     sum += denom.get(j);
                 }
             }
             if (sum <= max) ok[sum] = true;
         }
         for (int i = 1; i <= max; i++) {
             if (!ok[i]) return i;
         }
         return 0;
     }
 
     private boolean add(int left, List<Integer> denom, int V) {
         int upper = firstImpossible(denom, V);
         if (upper == 0) {
             return true;
         }
         if (left == 0) {
             return false;
         }
         for (int i = 1; i <= upper; i++) {
             if (denom.contains(i)) continue;
 
             denom.add(i);
             if (add(left-1,denom,V)) return true;
             denom.remove(denom.size() - 1);
         }
         return false;
     }
 
     private void solve(Kattio io) {
         int C = io.getInt(), D = io.getInt(), V = io.getInt();
         ArrayList<Integer> denom = new ArrayList<Integer>();
         for (int i = 0; i < D; i++) {
             denom.add(io.getInt());
         }
         Collections.sort(denom);
 
         if (C != 1) {
             io.println("Unsupported");
             return;
         }
 
         for (int maxAdd = 0;; maxAdd++) {
             if (add(maxAdd, denom, V)) {
                 io.println(maxAdd);
                 
                 return;
             }
         }
 
     }
 }
