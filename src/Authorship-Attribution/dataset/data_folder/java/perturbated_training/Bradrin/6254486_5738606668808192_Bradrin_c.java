import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Scanner;
 import java.util.Set;
 
 public class C {
    
    public void solve(Scanner scan, PrintWriter out) {
        System.out.println();
        out.println();
        int n = scan.nextInt();
        int j = scan.nextInt();
        n -= 2;
        
        Set<Long> primes = generatePrimes(n+2, 10000);
        
        
        int numFound = 0;
        for (int i = 0; i < Math.pow(2, n) && numFound < j; i++) {
            int[] coin = toCoin(i, n);
            boolean isJamcoin = true;
            for (int base = 2; base <= 10; base++) {
                long value = asBase(coin, base);
                if (isPrime(value, primes)) {
                    isJamcoin = false;
                    break;
                }
            }
             if (isJamcoin) {
                 numFound++;
                 outputJamcoin(coin, out);
                 for (int base = 2; base <= 10; base++) {
                     long value = asBase(coin, base);
                     boolean found = false;
                     for (long prime : primes) {
                         if (value % prime == 0) {
                             System.out.print(":" + base + " " + value + " " + prime);
                             out.print(" " + prime);
                             found = true;
                             break;
                         }
                     }
                     if (!found) {
                         throw new RuntimeException();
                     }
                 }
                 out.println();
                 System.out.println();
             }
        }
    }
 
     private boolean isPrime(long value, Set<Long> primes) {
         for (long prime : primes) {
             if (value % prime == 0) {
                 return false;
             }
         }
         return true;
     }
 
     private void outputJamcoin(int[] coin, PrintWriter out) {
         for (int i = coin.length - 1; i >= 0; i--) {
             System.out.print(coin[i]);
             out.print(coin[i]);
         }
     }
 
    private Set<Long> generatePrimes(int length, int limit) {
        List<Long> primes = new ArrayList<>();
        primes.add(2L);
        for (long i = 3; i < Math.pow(10, length/2) && i < limit; i++) {
            boolean isPrime = true;
            for (long prime : primes) {
                if (i % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
        Set<Long> result = new HashSet<>();
        for (long prime : primes) {
            result.add(prime);
        }
        return result;
    }
    
    private int[] toCoin(int value, int length) {
        int[] result = new int[length+2];
         for (int i = 0; i < length; i++) {
             result[i+1] = (value >> i) & 1;
         }
        result[0] = 1;
        result[length+1] = 1;
        return result;
    }
    
    private long asBase(int[] coin, int base) {
        long result = 0;
        long mult = 1;
        for (int i = 0; i < coin.length; i++) {
            result += coin[i] * mult;
            mult *= base;
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        String filename = "C-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new C().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }