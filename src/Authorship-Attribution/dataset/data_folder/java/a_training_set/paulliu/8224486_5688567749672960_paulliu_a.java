
 
 
 import java.util.*;
 import java.io.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     
     private void init() {
    Arrays.fill(fR_Ret,-1);
    fR_Ret[1]=1;
    for (int i=2; i<fR_Ret.length; i++) {
        if (fR_Ret[i]==-1) {
        fR_Ret[i] = fR_Ret[i-1]+1;
        } else if (fR_Ret[i-1]+1 < fR_Ret[i]) {
        fR_Ret[i] = fR_Ret[i-1]+1;
        }
        
        long r1 = revNum(i);
        long r2 = revNum(r1);
        if (r1 < i && r2 == i) {
        if (fR_Ret[(int)r1]+1 < fR_Ret[i]) {
            fR_Ret[i] = fR_Ret[(int)r1]+1;
        }
        }
    }
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
        long n;
        if (stdin.hasNextLong()) {
        n = stdin.nextLong();
        } else {
        return ret;
        }
        solve(n);
    }
    ret=0;
        return ret;
     }
 
     private long revNum(long x) {
    long ret=0;
    while (x>0) {
        ret *= 10;
        ret += x%10;
        x/=10;
    }
    return ret;
     }
     
     private long[] fR_Ret = new long[1000000+1];
     private long fR(long n) {
    long ret=0;
    if (n==1) {
        return 1;
    }
    if (fR_Ret[(int)n] != -1) {
        return fR_Ret[(int)n];
    }
    
    long min=n;
    long result1 = fR(n-1);
    if (result1+1<min) {
        min = result1+1;
    }
    long r1 = revNum(n);
    long r2 = revNum(r1);
    if (r2 == n && r1 < n) {
        long result2 = fR(r1);
        if (result2+1<min) {
        min = result2+1;
        }
    }
    fR_Ret[(int)n]=min;
    return min;
     }
     
     private void solve(long n) {
    long ans = fR(n);
    output(ans);
     }
 
     private int output_N=0;
     
     private void output(long ans) {
    output_N++;
    System.out.format("Case #%1$d: %2$d%n",output_N, ans);
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
