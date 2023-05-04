package qual;
 
 import java.util.ArrayList;
 import java.util.List;
 
 public class Primes {
     public final List<Integer> primeList;
 
     private int maxPrime;
     private byte[] _sieve;
 
     public Primes(int largestSieve) {
         maxPrime = largestSieve + (largestSieve & 1);
         primeList = new ArrayList<Integer>();
         primeList.add(2);
         _sieve = new byte[maxPrime / 16 + 2];
         _sieve[0] = 1;
         for (int i = 1; i < maxPrime / 2; i++)
             if ((_sieve[i >> 3] & (1 << (i & 7))) == 0) {
                 primeList.add(i * 2 + 1);
                 for (int j = i + i + i + 1; j < maxPrime / 2; j += i + i + 1)
                     _sieve[j >> 3] |= (byte)(1 << (j & 7));
             }
     }
 
     public boolean isPrime(long value) {
         if (value == 2)
             return true;
         if (value < 2 || (value % 2) == 0)
             return false;
         if (value <= maxPrime) {
             int ivalue = (int) value;
             return (_sieve[ivalue >> 4] & (1 << ((ivalue >> 1) & 7))) == 0;
         }
         if (value > ((long)maxPrime) * maxPrime)
             throw new IllegalArgumentException("Value too large.");
         for (int p : primeList) {
             if (((long)p) * p > value)
                 return true;
             if (value % p == 0)
                 return false;
         }
         return true;
     }
 
     public long getOnePrimeFactor(long value) {
         for (int i = 0; i < primeList.size(); i++) {
             long p = primeList.get(i);
             while (value%p == 0) return p;
         }
         throw new IllegalArgumentException(value + " has too large prime factors.");
     }
 
     public List<Long> getAllPrimeFactors(long value) {
         if (value < 2)
             return new ArrayList<>();
         List<Long> factors = new ArrayList<>();
         for (int i = 0; i < primeList.size() && value > 1; i++) {
             long p = primeList.get(i);
             while (value%p == 0) {
                 factors.add(p);
                 value /= p;
             }
         }
         if (value <= ((long)maxPrime) * maxPrime) {
             if (value > 1)
                 factors.add(value);
             return factors;
         }
         throw new IllegalArgumentException(value + " has too large prime factors.");
     }
 
     public List<Long> getUniquePrimeFactors(long value) {
         List<Long> allPrimeFactors = getAllPrimeFactors(value);
         List<Long> unique = new ArrayList<>();
         for(long p : allPrimeFactors)
             if (unique.size() == 0 || p != unique.get(unique.size()-1))
                 unique.add(p);
         return unique;
     }
 }