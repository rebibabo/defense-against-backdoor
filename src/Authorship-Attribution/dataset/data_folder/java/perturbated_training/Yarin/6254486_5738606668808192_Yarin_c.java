package qual;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 
 public class C {
     private Primes primes;
 
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("qual/C-small-0.in"), new FileOutputStream("qual/C-small-0.out"));
 
 
         int cases = io.getInt();
         C c = new C();
         c.init();
         for (int i = 1; i <= cases; i++) {
             io.println("Case #" + i + ":");
             c.solve(io);
         }
         io.close();
     }
 
     private void init() {
         long start = System.currentTimeMillis();
         primes = new Primes(100000000);
         long stop = System.currentTimeMillis();
         System.err.println("Done initiating primes in " + (stop-start) + " ms");
     }
 
     private String toBin(long n) {
         StringBuilder sb = new StringBuilder();
         while (n > 0) {
             sb.append((char)('0' + (n % 2)));
             n /= 2;
         }
         sb.reverse();
         return sb.toString();
     }
 
     private long inBase(long n, int base) {
         long t = 0, p = 1;
         while (n > 0) {
             t += (n%2)*p;
             p *= base;
             n /= 2;
         }
         return t;
     }
 
     private void solve(Kattio io) {
         int N = io.getInt(), J = io.getInt();
         long n = (1L << (N-1)) + 1, end = 1L << N;
         while (n < end && J > 0) {
             boolean ok = true;
             for (int base = 2; base <= 10 && ok; base++) {
                 long t = inBase(n, base);
                 if (primes.isPrime(t)) ok = false;
             }
             if (ok) {
                 io.print(toBin(n));
                 for (int base = 2; base <= 10; base++) {
                     io.print(" " + primes.getOnePrimeFactor(inBase(n, base)));
                 }
                 io.println();
                 J--;
             }
             n += 2;
         }
 
     }
 }
