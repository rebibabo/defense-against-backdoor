package qual;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 
 public class B {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("qual/B-small-attempt0.in"), new FileOutputStream("qual/B-small-0.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new B().solve(io);
         }
         io.close();
     }
 
     private void solve(Kattio io) {
         String s = io.getWord();
         io.println(transform('+', s));
     }
 
     private int transform(char target, String cur) {
         if (cur.length() == 0) return 0;
         char last = cur.charAt(cur.length() - 1);
         String rest = cur.substring(0, cur.length() - 1);
         if (last == target) {
             return transform(target, rest);
         }
         return transform(target == '-' ? '+' : '-', rest) + 1;
     }
 }
