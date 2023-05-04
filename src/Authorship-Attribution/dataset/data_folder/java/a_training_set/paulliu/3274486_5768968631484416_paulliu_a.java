
 
 
 import java.util.*;
 import java.io.*;
 import java.math.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     
     private void init() {
    
     }
 
     class Pancake {
    public double r;
    public double h;
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
        int K;
        if (stdin.hasNextInt()) {
        K = stdin.nextInt();
        } else {
        return ret;
        }
        ArrayList<Pancake> pancakes = new ArrayList<Pancake>();
        for (int i=0; i<N; i++) {
        Pancake p = new Pancake();
        if (stdin.hasNextDouble()) {
            p.r = stdin.nextDouble();
        } else {
            return ret;
        }
        if (stdin.hasNextDouble()) {
            p.h = stdin.nextDouble();
        } else {
            return ret;
        }
        pancakes.add(p);
        }
        solve(N, K, pancakes);
    }
 
    ret=0;
        return ret;
     }
 
     class MyComparator1 implements Comparator<Pancake> {
    public int compare(Pancake p1, Pancake p2) {
        if (p1.r < p2.r) {
        return -1;
        } else if (p1.r > p2.r) {
        return 1;
        }
        if (p1.h > p2.h) {
        return -1;
        } else if (p1.h < p2.h) {
        return 1;
        }
        return 0;
    }
    
     }
 
     double[][] f_Ans = null;
     private double f(int N, int start, ArrayList<Pancake> pancakes) {
    if (pancakes.size()-start < N) {
        return 0.0;
    }
    if (start>=pancakes.size()) {
        return 0.0;
    }
    if (N<=0) {
        return 0.0;
    }
    if (!Double.isNaN(f_Ans[N][start])) {
        return f_Ans[N][start];
    }
    if (N==1) {
        double aMax=0.0;
        for (int i=start; i<pancakes.size(); i++) {
        double a;
        a = Math.PI * pancakes.get(i).r * pancakes.get(i).r;
        a = a + Math.PI*2.0*pancakes.get(i).r * pancakes.get(i).h;
        logInfo ("%1$f", a);
        if (aMax < a) {
            aMax = a;
        }
        }
        f_Ans[N][start] = aMax;
        return aMax;
    }
    double aRest = f(N, start+1, pancakes);
    double aWith = Math.PI*2.0*pancakes.get(start).r * pancakes.get(start).h + f(N-1, start+1, pancakes);
    if (aWith > aRest) {
        f_Ans[N][start] = aWith;
        return aWith;
    }
    f_Ans[N][start] = aRest;
    return aRest;
     }
     
     
     private void solve(int N, int K, ArrayList<Pancake> pancakes) {
    Collections.sort(pancakes, new MyComparator1());
 
    double ans;
    f_Ans = new double[K+1][N];
    for (int i=0; i<K+1; i++) {
        for (int j=0; j<N; j++) {
        f_Ans[i][j] = Double.NaN;
        }
    }
    ans = f(K, 0, pancakes);
    
    output(ans);
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
