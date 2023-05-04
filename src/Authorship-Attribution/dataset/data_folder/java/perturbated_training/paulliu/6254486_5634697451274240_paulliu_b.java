
 
 
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
        String panCake;
        if (stdin.hasNext()) {
        panCake = stdin.next();
        } else {
        return ret;
        }
        solve(panCake);
        
    }
 
    ret=0;
        return ret;
     }
 
     private int getbit(int n, int bit) {
    return (n>>bit)&0x01;
     }
     
     private int flip(int n, int s, int i) {
    int ret=0;
    for (int k1=0; k1<i; k1++) {
        ret |= getbit(n, k1)<<k1;
    }
    for (int k1=s-1; k1>=i; k1--) {
        ret |= (1-getbit(n, k1)) << (i - (k1 - (s-1)));
    }
    return ret;
     }
     private void solveSmall(String panCake) {
    int n = panCake.length();
    int[] table = new int[1<<n];
    Arrays.fill(table, -1);
    table[0]=0;
    boolean loopFlag=true;
    for (int i=0; i<table.length && loopFlag ; i++) {
        loopFlag=false;
        for (int j=0; j<table.length; j++) {
        if (table[j] == -1) {
            loopFlag=true;
        }
        if (table[j] == i) {
            for (int k=0; k<n; k++) {
            int nj=flip(j,n,k);
            if (table[nj]==-1) {
                table[nj]=table[j]+1;
            }
            }
        }
        }
    }
    int aa1=0;
    for (int i=0; i<n; i++) {
        if (panCake.charAt(i)=='+') {
        aa1 |= 0 << (n-i-1);
        } else if (panCake.charAt(i)=='-') {
        aa1 |= 1 << (n-i-1);
        }
    }
    output(table[aa1]);
     }
 
     
     private void solve(String panCake) {
    if (panCake.length() <= 10) {
        solveSmall(panCake);
        return;
    }
     }
 
     private int output_N=0;
     
     private void output(int X) {
    output_N++;
    System.out.format("Case #%1$d: %2$d%n",output_N,X);
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
