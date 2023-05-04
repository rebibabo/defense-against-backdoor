package qual;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 
 public class B {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("qual/B-small-1.in"), new FileOutputStream("qual/B-small-1.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new B().solve(io);
         }
         io.close();
     }
 
     private void solve(Kattio io) {
         int n = io.getInt();
         int[] a = new int[n];
         for (int i = 0; i < n; i++) {
             a[i] = io.getInt();
         }
 
         int best = Integer.MAX_VALUE;
 
         for (int special = 0;special < best; special++) {
             int[] splits = new int[n];
             for (int i = 0; i < special; i++) {
                 int most = 0, select = 0;
                 for (int j = 0; j < n; j++) {
                     int largest = (a[j] + splits[j]) / (splits[j] + 1);
                     if (largest > most) {
                         most = largest;
                         select = j;
                     }
                 }
                 splits[select]++;
             }
 
             int most = 0;
             for (int i = 0; i < n; i++) {
                 most = Math.max(most, (a[i] + splits[i]) / (splits[i] + 1));
             }
             best = Math.min(best, special + most);
         }
 
         io.println(best);
 
     }
 }
