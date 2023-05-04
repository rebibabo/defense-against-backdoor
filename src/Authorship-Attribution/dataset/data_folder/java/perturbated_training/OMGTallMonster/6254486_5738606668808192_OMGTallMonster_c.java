import java.util.*;
 import java.math.*;
 
 class C {
    public static BigInteger[] smallPrimes;
    public static int MAX_PRIME = 10000000;
 
    static {
       ArrayList<Integer> primes = new ArrayList<Integer>();
       boolean[] isNotPrime = new boolean[MAX_PRIME];
       isNotPrime[0] = isNotPrime[1] = true;
       for (int i = 2; i < MAX_PRIME; i++) {
          if (!isNotPrime[i]) {
             for (int j = i; j < MAX_PRIME; j+=i) {
                isNotPrime[j] = true;
             }
             primes.add(i);
          }
       }
       smallPrimes = new BigInteger[primes.size()];
       for (int i = 0; i < smallPrimes.length; i++) {
          smallPrimes[i] = new BigInteger(primes.get(i)+"");
       }
    }
    
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d:\n", i);
          solve(scan);
       }
    }
 
    public static BigInteger getDivisor(BigInteger i) {
       for (BigInteger p : smallPrimes) {
          if (i.mod(p).equals(BigInteger.ZERO)) {
             return p;
          }
       }
       return null;
    }
 
    public static BigInteger[] jamcoinProof(String repr) {
       BigInteger[] out = new BigInteger[9];
       for (int base = 2; base <= 10; base++) {
          BigInteger tmp = new BigInteger(repr, base);
          if (tmp.isProbablePrime(100)) {
             return null;
          }
          BigInteger divisor = getDivisor(tmp);
          if (divisor == null) {
             return null;
          }
          out[base-2] = divisor;
       }
       return out;
    }
 
    public static void solve(Scanner scan) {
       int N = scan.nextInt(), J = scan.nextInt();
       long i = (1L << (N-1)) | 1;
       for (int count = 0; count < J; i+=2) {
          String repr = Long.toBinaryString(i);
          BigInteger[] arr = jamcoinProof(repr);
          if (arr != null) {
             System.out.print(repr);
             for (BigInteger x : arr) System.out.print(" "+x);
             System.out.println();
             count++;
          }
       }
    }
 }
