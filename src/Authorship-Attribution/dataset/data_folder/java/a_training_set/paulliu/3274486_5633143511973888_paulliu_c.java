
 
 
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
        int N;
        int K;
        if (stdin.hasNextInt()) {
        N = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        K = stdin.nextInt();
        } else {
        return ret;
        }
        double U;
        if (stdin.hasNextDouble()) {
        U = stdin.nextDouble();
        } else {
        return ret;
        }
        ArrayList<Double> Pi = new ArrayList<Double>();
        for (int i=0; i<N; i++) {
        double p;
        if (stdin.hasNextDouble()) {
            p = stdin.nextDouble();
        } else {
            return ret;
        }
        Pi.add(new Double(p));
        }
        
        solve(N, K, U, Pi);
        
    }
 
    ret=0;
        return ret;
     }
 
     
     private void solve(int N,int K,double U,ArrayList<Double> PO) {
    Collections.sort(PO);
    double[] P = new double[PO.size()];
    for (int i=0; i<PO.size(); i++) {
        P[i] = PO.get(i).doubleValue();
    }
    if (N==K) {
        for (int i=0; i+1<N; i++) {
        if (U<=0.0) {
            break;
        }
        if (P[i] < P[i+1]) {
            if ((P[i+1]-P[i])*((double)(i+1)) <= U) {
            U -= ((P[i+1]-P[i])*((double)(i+1)));
            for (int j=0; j<=i; j++) {
                P[j] = P[i+1];
            }
            } else {
            for (int j=0; j<=i; j++) {
                P[j] = P[j] + U/((double)(i+1));
            }
            U=0.0;
            }
        }
        }
        if (U > 0.0) {
        double add = U/((double)(N));
        for (int i=0; i<N; i++) {
            if (P[i]+add >= 1.0) {
            P[i] = 1.0;
            } else {
            P[i] = P[i] + add;
            }
        }
        }
        double ans = 1.0;
        for (int i=0; i<N; i++) {
        ans = ans * P[i];
        }
        output(ans);
        return;
    }
    output(0.0);
     }
 
     private int output_N=0;
     
     private void output(double ans) {
    output_N++;
    System.out.format("Case #%1$d: %2$.9f%n",output_N,ans);
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
