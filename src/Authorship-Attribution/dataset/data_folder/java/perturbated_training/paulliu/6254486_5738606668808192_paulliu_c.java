
 
 
 import java.util.*;
 import java.io.*;
 import java.math.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     private int[] primes;
 
     
     private void init() {
    PrimeGenerator pg = new PrimeGenerator();
    pg.linearSieve(1048576);
    primes = Arrays.copyOf(pg.primes, pg.primesLen);
     }
 
     
     private int input() {
    int ret=0;
    String com1;
    int T;
 
    if (stdin.hasNextInt()) {
        T = stdin.nextInt();
    } else {
        return ret;
    }
 
    for (int t1=0; t1<T; t1++) {
        int N;
        int J;
        if (stdin.hasNextInt()) {
        N = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        J = stdin.nextInt();
        } else {
        return ret;
        }
        solve(N,J);
        
    }
 
    ret=0;
        return ret;
     }
 
     
     private void solve(int N, int J) {
    Set<String> set1 = new HashSet<String>();
    Random r = new Random();
    output();
    loop1: for (int j1=0; j1<J; j1++) {
        StringBuffer sb1 = new StringBuffer();
        sb1.append("1");
        for (int i=0; i<N-2; i++) {
        if (r.nextInt()%2==0) {
            sb1.append("0");
        } else {
            sb1.append("1");
        }
        }
        sb1.append("1");
        String Xstr = sb1.toString();
        if (set1.contains(Xstr)) {
        j1--;
        continue;
        }
        BigInteger[] X = new BigInteger[9];
        for (int i=2; i<=10; i++) {
        X[i-2] = new BigInteger(Xstr, i);
        if (X[i-2].isProbablePrime(100)) {
            j1--;
            continue loop1;
        }
        }
        BigInteger[] fact = new BigInteger[9];
        for (int i=2; i<=10; i++) {
        fact[i-2]=null;
        for (int k=0; k<primes.length; k++) {
            BigInteger prime = new BigInteger(Integer.toString(primes[k]));
            if (prime.multiply(prime).compareTo(X[i-2])>0) {
            break;
            }
            if (X[i-2].divideAndRemainder(prime)[1].compareTo(BigInteger.ZERO)==0) {
            fact[i-2] = prime;
            break;
            }
        }
        if (fact[i-2]==null) {
            j1--;
            continue loop1;
        }
        }
        set1.add(Xstr);
        System.out.format("%1$s",Xstr);
        for (int i=0; i<fact.length; i++) {
        System.out.format(" %1$d", fact[i]);
        }
        System.out.println();
        if (logger != null) {
        for (int i=0; i<fact.length; i++) {
            System.err.format(" %1$d/%2$d",X[i], fact[i]);
        }
        System.err.println();
        }
    }
    
     }
 
     private int output_N=0;
     
     private void output() {
    output_N++;
    System.out.format("Case #%1$d:%n",output_N);
     }
 
 
     
     public void logInfo(String a, Object... args) {
    if (logger != null) {
        logger.info(String.format(a,args));
    }
     }
 
     public void begin() {
    this.logger = java.util.logging.Logger.getLogger(Main.loggerName);
    if (this.logger.getLevel() != java.util.logging.Level.INFO) {
        this.logger = null;
    }
    init();
    while (input()==1) {
    }
     }
 
     public void unittest() {
    this.logger = java.util.logging.Logger.getLogger(Main.loggerName);
     }
 
     public static void main (String args[]) {
    Main myMain = new Main();
    if (args.length >= 1 && args[0].equals("unittest")) {
        myMain.unittest();
        return;
    }
    java.util.logging.Logger.getLogger(Main.loggerName).setLevel(java.util.logging.Level.SEVERE);
    for (int i=0; args!=null && i<args.length; i++) {
        if (args[i].equals("debug")) {
        java.util.logging.Logger.getLogger(Main.loggerName).setLevel(java.util.logging.Level.INFO);
        }
    }
    myMain.begin();
     }
 }
 
 class PrimeGenerator {
 
     public int[] primes=null;
     public int primesLen=0;
     public boolean[] sieve=null;
 
     public PrimeGenerator () {
     }
 
 
     
     private int primeCountingEstimate (int n) {
    if (n<17) {
        if (n>=13) {
        return 6;
        }
        if (n>=11) {
        return 5;
        }
        if (n>=7) {
        return 4;
        }
        if (n>=5) {
        return 3;
        }
        if (n>=3) {
        return 2;
        }
        if (n>=2) {
        return 1;
        }
        return 0;
    }
 
    double k;
    k = 1.25506 * ((double)(n)) / (java.lang.Math.log((double)(n)));
    return ((int)(java.lang.Math.ceil(k)));
     }
     
     
     public void sixPlusMinusOne(int x) {
    int n = primeCountingEstimate(x);
    primes = new int[n];
    primesLen=0;
    if (x>=2) {
        primes[primesLen]=2;
        primesLen++;
    }
    if (x>=3) {
        primes[primesLen]=3;
        primesLen++;
    }
    sixPlusMinusOne_loop1: for (int i=1; primesLen < primes.length; i++) {
        for (int c=-1; c<=1; c+=2) {
        int p1 = i*6+c;
        if (p1 > x) {
            break sixPlusMinusOne_loop1;
        }
        boolean isPrimeFlag=true;
        for (int j=2; j<primesLen; j++) {
            int prime = primes[j];
            if ( (((long)prime)*((long)prime)) > ((long)p1)) {
            break;
            }
            if (p1 % prime == 0) {
            isPrimeFlag=false;
            break;
            }
        }
        if (isPrimeFlag) {
            primes[primesLen] = p1;
            primesLen++;
        }
        }
        if (primes[primesLen-1] > x) {
        break;
        }
    }
     }
 
     
     public void linearSieve(int x) {
    int n = primeCountingEstimate(x);
    primes = new int[n];
    primesLen=0;
    sieve = new boolean[x+1];
    java.util.Arrays.fill(sieve,false);
    sieve[0]=true;
    sieve[1]=true;
    for (int i=2; i<sieve.length; i++) {
        if (!sieve[i]) {
        primes[primesLen]=i;
        primesLen++;
        }
        for (int j=0; j<primesLen && ((long)i)*((long)(primes[j]))<((long)(sieve.length)); j++) {
        sieve[i*primes[j]]=true;
        if (i%primes[j]==0) {
            break;
        }
        }
    }
     }
 
 }
