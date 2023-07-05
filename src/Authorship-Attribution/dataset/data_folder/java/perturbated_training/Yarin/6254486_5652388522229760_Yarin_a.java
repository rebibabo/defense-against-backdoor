package qual;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.util.Random;
 
 public class A {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("qual/A-small-attempt0.in"), new FileOutputStream("qual/A-small-0.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new A().solve(io);
         }
         io.close();
     }
 
     private int getDigitMask(long x) {
         int mask = 0;
         while (x > 0) {
             mask |= (1 << (x % 10));
             x /= 10;
         }
         return mask;
     }
 
     private void solve(Kattio io) {
         long n = io.getLong();
         long last = solve(n);
         if (last < 0) {
             io.println("INSOMNIA");
         } else {
             io.println(last);
         }
     }
 
     private long solve(long n) {
         if (n == 0) return -1;
         long cur = 0;
         int seen = 0;
         while (seen != 1023) {
             cur += n;
             seen |= getDigitMask(cur);
         }
         return cur;
     }
 }
