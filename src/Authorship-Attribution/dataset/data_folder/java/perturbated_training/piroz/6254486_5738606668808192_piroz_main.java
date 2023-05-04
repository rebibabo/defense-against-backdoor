package c;
 
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 
 public class Main {
 
    public static void main(String[] args) throws Exception {
        solve(16,50,"C-small.out");
        solve(32,500,"C-large.out");
    }
    
    public static void solve(int n,int j,String fileName) throws Exception {
        PrintWriter pw = new PrintWriter(fileName);
        pw.println("Case #1:");
        int count = 0;
        while(true) {
            String s = Long.toBinaryString((long) (Math.random() * (1L << (n-2))));
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<n-2-s.length();i++) {
                sb.append('0');
            }
            String t = "1" + sb.toString() + s + "1";
            ArrayList<BigInteger> divisors = isJamcoin(t);
            if (divisors != null) {
                System.err.println(count);
                count++;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(t);
                for(BigInteger bi: divisors) {
                    sb2.append(' ');
                    sb2.append(bi);
                }
                pw.println(sb2);
                if (count == j) {
                    break;
                }
            }
        }
        pw.flush();
        pw.close();
    }
    
    public static ArrayList<BigInteger> isJamcoin(String s) {
        ArrayList<BigInteger> divisors = new ArrayList<>();
        for(int b=2;b<=10;b++) {
            BigInteger x = new BigInteger(s,b);
            if (x.isProbablePrime(20)) {
                return null;
            }
            BigInteger d = findDivisor(x);
            if (d == null) {
                return null;
            }
            divisors.add(d);
        }
        return divisors;
    }
    
    public static ArrayList<BigInteger> primeList = primes(10000);
    
    public static ArrayList<BigInteger> primes(int max) {
        boolean[] isNotPrime = new boolean[max+1];
        isNotPrime[0] = isNotPrime[1] = true;
        for(int i=2;i*i<=max;i++) {
            if (!isNotPrime[i]) {
                int j = 2;
                while(i*j<=max) {
                    isNotPrime[i*j] = true;
                    j++;
                }
            }
        }
        ArrayList<BigInteger> prime = new ArrayList<>();
        for(int i=2;i<=max;i++) {
            if(!isNotPrime[i]) {
                prime.add(BigInteger.valueOf(i));
            }
        }
        return prime;
    }
    
    public static BigInteger findDivisor(BigInteger n) {
        for(BigInteger p: primeList) {
            if (n.remainder(p).equals(BigInteger.ZERO)) {
                return p;
            }
        }
        return rho(n);
    }
    
    public static BigInteger rho(BigInteger n) {
        return rho(n,1);
    }
    
    public static BigInteger rho(BigInteger n,int c) {
        long stime = System.nanoTime();
        BigInteger c0 = BigInteger.valueOf(c);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger x = two, y = two, d = BigInteger.ONE;
        while(d.equals(BigInteger.ONE)) {
            if ((System.nanoTime() - stime) / 1000000 > 1000) { 
                return null;
            }
            x = f(x,c0,n);
            y = f(y,c0,n);
            y = f(y,c0,n);
            d = x.subtract(y).abs().gcd(n);
        }
        if (n.equals(d)) return null;
        return d;
    }
    
    public static BigInteger f(BigInteger x,BigInteger c,BigInteger n) {
        return x.multiply(x).add(c).mod(n);
    }
 
 }
