
 
 
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
        double D;
        int H;
        double M;
        if (stdin.hasNextInt()) {
        N = stdin.nextInt();
        } else {
        return ret;
        }
        ArrayList<Double> hikerStart = new ArrayList<Double>();
        ArrayList<Double> hikerSpeed = new ArrayList<Double>();
        for (int i=0; i<N; i++) {
        if (stdin.hasNextDouble()) {
            D = stdin.nextDouble();
        } else {
            return ret;
        }
        if (stdin.hasNextInt()) {
            H = stdin.nextInt();
        } else {
            return ret;
        }
        if (stdin.hasNextDouble()) {
            M = stdin.nextDouble();
        } else {
            return ret;
        }
        for (int j=0; j<H; j++) {
            hikerStart.add(new Double(D));
            hikerSpeed.add(new Double(360.0/(M+(double)j)));
        }
        }
        solve(hikerStart,hikerSpeed);
    }
    ret=0;
        return ret;
     }
 
     private void solve2(double h1Start,double h1Speed,double h2Start,double h2Speed) {
    double currentDegree;
 
    logInfo("h1Start = %1$f",h1Start);
    logInfo("h1Speed = %1$f",h1Speed);
    logInfo("h2Start = %1$f",h2Start);
    logInfo("h2Speed = %1$f",h2Speed);
 
    double t1 = (360.0-h1Start)/h1Speed;
    double t2 = (360.0-h2Start)/h2Speed;
    logInfo("t1 = %1$f",t1);
    logInfo("t2 = %1$f",t2);
    if (t1<t2) {
        h1Start += h1Speed*t1;
        h2Start += h2Speed*t1;
        h1Start = 0.0;
        currentDegree = h2Start;
        logInfo("h1Start = %1$f",h1Start);
        logInfo("h2Start = %1$f",h2Start);
        double leftTime = (360.0-currentDegree)/h2Speed;
        logInfo("leftTime = %1$f",leftTime);
        if (h1Start + h1Speed*leftTime >= 360.0) {
        output(1);
        } else {
        output(0);
        }
    } else {
        h1Start += h1Speed*t2;
        h2Start += h2Speed*t2;
        h2Start = 0.0;
        currentDegree = h1Start;
        logInfo("h1Start = %1$f",h1Start);
        logInfo("h2Start = %1$f",h2Start);
        double leftTime = (360.0-currentDegree)/h1Speed;
        logInfo("leftTime = %1$f",leftTime);
        if (h2Start + h2Speed*leftTime >= 360.0) {
        output(1);
        } else {
        output(0);
        }
    }
    
     }
    
    
     
     private void solve(ArrayList<Double> hikerStart, ArrayList<Double> hikerSpeed) {
    boolean equalSpeed=true;
    FixDoubleComparator comparator = FixDoubleComparator.getInstance();
    for (int i=1; i<hikerSpeed.size(); i++) {
        if (comparator.compare(hikerSpeed.get(i), hikerSpeed.get(i-1)) != 0) {
        equalSpeed=false;
        break;
        }
    }
    if (equalSpeed) {
        output(0);
        return;
    }
    if (hikerStart.size() == 2) {
        solve2(hikerStart.get(0).doubleValue(),
           hikerSpeed.get(0).doubleValue(),
           hikerStart.get(1).doubleValue(),
           hikerSpeed.get(1).doubleValue());
    }
     }
 
     private int output_N=0;
     
     private void output(int n) {
    output_N++;
    System.out.format("Case #%1$d: %2$d%n",output_N,n);
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
 
 class FixDoubleComparator implements java.util.Comparator<Double> {
     private double e = 1e-9;
     
     public FixDoubleComparator() {
     }
     public FixDoubleComparator(double e) {
    this.e = e;
     }
     
     public int compare(Double a, Double b) {
    return compare(a.doubleValue(), b.doubleValue());
     }
 
     public int compare(double a, double b) {
    if (Math.abs(a-b) < e) {
        return 0;
    }
    if (a<b) {
        return -1;
    }
    return 1;
     }
 
     public boolean equals(Object obj) {
    if ( ! (obj instanceof FixDoubleComparator) ) {
        return false;
    }
    FixDoubleComparator b = (FixDoubleComparator) obj;
    if (b.e==this.e) {
        return true;
    }
    return false;
     }
 
     private static FixDoubleComparator instance = null;
     public static FixDoubleComparator getInstance() {
    if (instance == null) {
        instance = new FixDoubleComparator();
    }
    return instance;
     }
 }
