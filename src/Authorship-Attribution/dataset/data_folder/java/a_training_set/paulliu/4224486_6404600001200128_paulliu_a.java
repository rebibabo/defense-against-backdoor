
 
 
 import java.util.*;
 import java.io.*;
 
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
        if (stdin.hasNextInt()) {
        N = stdin.nextInt();
        } else {
        return ret;
        }
        int[] m = new int[N];
        for (int n1=0; n1<N; n1++) {
        if (stdin.hasNextInt()) {
            m[n1] = stdin.nextInt();
        } else {
            return ret;
        }
        }
        solve(m);
        
    }
    ret=0;
        return ret;
     }
 
     
     private void solve(int[] m) {
    int sum1=0;
    for (int i=1; i<m.length; i++) {
        if (m[i]<m[i-1]) {
        sum1 += m[i-1]-m[i];
        }
    }
    int maxM=0;
    for (int i=0; i<m.length; i++) {
        if (maxM < m[i]) {
        maxM=m[i];
        }
    }
 
    int sum2Min = Integer.MAX_VALUE;
    for (int c=0; c<=maxM ; c++) {
        boolean validFlag=true;
        for (int i=0; i+1<m.length; i++) {
        if (m[i]-c > m[i+1]) {
            validFlag=false;
            break;
        }
        }
        if (!validFlag) {
        continue;
        }
        int sum2=0;
        for (int i=0; i+1<m.length; i++) {
        if (m[i]>=c) {
            sum2 += c;
        } else {
            sum2 += m[i];
        }
        }
        if (sum2 < sum2Min) {
        sum2Min = sum2;
        }
    }
    output(sum1,sum2Min);
     }
 
     private int output_N=0;
     
     private void output(int a,int b) {
    output_N++;
    System.out.format("Case #%1$d: %2$d %3$d%n",output_N,a,b);
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
