
 
 
 import java.util.*;
 import java.io.*;
 import java.math.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     
     private void init() {
    
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
        BigInteger N;
        if (stdin.hasNextBigInteger()) {
        N = stdin.nextBigInteger();
        } else {
        return ret;
        }
        solve(N);
        
    }
 
    ret=0;
        return ret;
     }
 
     
     private void solve(BigInteger N) {
    BigInteger i;
    boolean[] digits = new boolean[10];
    Arrays.fill(digits,false);
 
    if (N.compareTo(BigInteger.ZERO)==0) {
        output(null);
        return;
    }
    
    for (i=BigInteger.ONE; true; i=i.add(BigInteger.ONE)) {
        BigInteger X = N.multiply(i);
        logInfo("X=%1$d",X);
        String s = X.toString();
        for (int i1=0; i1<s.length(); i1++) {
        char a1 = s.charAt(i1);
        int k = a1-'0';
        logInfo("a1=%1$c, k=%2$d",a1,k);
        digits[k]=true;
        }
        boolean hasFalse=false;
        for (int i1=0; i1<digits.length; i1++) {
        if (!digits[i1]) {
            hasFalse=true;
            break;
        }
        }
        if (!hasFalse) {
        break;
        }
    }
    output(N.multiply(i));
     }
 
     private int output_N=0;
     
     private void output(BigInteger X) {
    output_N++;
    if (X != null) {
        System.out.format("Case #%1$d: %2$d%n",output_N,X);
    } else {
        System.out.format("Case #%1$d: INSOMNIA%n",output_N);
    }       
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
