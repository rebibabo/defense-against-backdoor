
 
 
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
        long K;
        long C;
        long S;
        if (stdin.hasNextLong()) {
        K = stdin.nextLong();
        } else {
        return ret;
        }
        if (stdin.hasNextLong()) {
        C = stdin.nextLong();
        } else {
        return ret;
        }
        if (stdin.hasNextLong()) {
        S = stdin.nextLong();
        } else {
        return ret;
        }
        solve(K,C,S);
        
    }
 
    ret=0;
        return ret;
     }
 
     private void solveSmall(long K,long C,long S) {
    ArrayList<Long> answers = new ArrayList<Long>();
    for (long G=0; G<K; G++) {
        long m=G;
        for (long c1=0; c1+1<C; c1++) {
        m=m*K;
        }
        answers.add(new Long(m));
    }
    output(answers);
     }
     
     private void solve(long K,long C,long S) {
    if (K==S) {
        
        solveSmall(K,C,S);
        return;
    }
    output(null);
     }
 
     private int output_N=0;
     
     private void output(ArrayList<Long> ans) {
    output_N++;
    System.out.format("Case #%1$d:",output_N);
    if (ans == null) {
        System.out.print(" IMPOSSIBLE");
    } else {
        for (int i=0; i<ans.size(); i++) {
        System.out.format(" %1$d",ans.get(i).longValue()+1);
        }
    }
    System.out.println();
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
