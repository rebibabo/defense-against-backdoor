
 
 
 import java.util.*;
 import java.io.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     
     private void init() {
    
     }
 
     class MyComparator implements Comparator<Integer> {
    public int compare(Integer aI,Integer bI) {
        int a=aI.intValue();
        int b=bI.intValue();
        if (a<b) {
        return 1;
        } else if(a>b) {
        return -1;
        }
        return 0;
    }
    public boolean equals(Object obj) {
        return true;
    }
     }
     
     
     private int input() {
    int ret=0;
    String com1;
    int T=0;
 
    if (stdin.hasNextInt()) {
        T = stdin.nextInt();
    } else {
        return ret;
    }
 
    for (int t1=0; t1<T; t1++) {
        int D;
        if (stdin.hasNextInt()) {
        D = stdin.nextInt();
        } else {
        return ret;
        }
        PriorityQueue <Integer> p = new PriorityQueue<Integer>(D,new MyComparator());
        for (int d1=0; d1<D; d1++) {
        int pi;
        if (stdin.hasNextInt()) {
            pi = stdin.nextInt();
        } else {
            return ret;
        }
        p.add(new Integer(pi));
        }
        solve_BruteForce(D,p);
    }
    ret=0;
        return ret;
     }
 
     private int solve_BruteForce(ArrayList<Integer> a, int time, int uMinTime) {
    Collections.sort(a, new MyComparator());
    if (a.get(0).intValue()<=3) {
        return a.get(0).intValue()+time;
    }
    if (time >= uMinTime) {
        return a.get(0).intValue()+time;
    }
    int minTime = a.get(0).intValue()+time;
    for (int i=a.get(0).intValue()/2; i>=1; i--) {
        ArrayList<Integer> b = new ArrayList<Integer>(a);
        int m1 = a.get(0).intValue()-i;
        int m2 = i;
        b.set(0,new Integer(m1));
        b.add(new Integer(m2));
        int nowTime = solve_BruteForce(b, time+1, Math.min(minTime, uMinTime));
        if (nowTime < minTime) {
        minTime = nowTime;
        }
    }
    return minTime;
     }
     private void solve_BruteForce(int D,PriorityQueue<Integer> p) {
    ArrayList<Integer> a = new ArrayList<Integer>();
    while (!p.isEmpty()) {
        a.add(p.poll());
    }
    Collections.sort(a, new MyComparator());
    int minTime = solve_BruteForce(a,0,a.get(0).intValue());
    output(minTime);
     }
     
     
     private void solve_Wrong1(int D,PriorityQueue<Integer> p) {
    int minTime = Integer.MAX_VALUE;
    for (int i=0; !p.isEmpty(); i++) {
        int pi = p.poll().intValue();
        if (pi+i < minTime) {
        minTime = pi+i;
        }
        if (pi<=3) {
        break;
        }
        if (pi%2==1) {
        p.add(new Integer(pi/2));
        p.add(new Integer(pi/2+1));
        } else {
        p.add(new Integer(pi/2));
        p.add(new Integer(pi/2));
        }
    }
    output(minTime);
     }
 
     private int output_N=0;
     
     private void output(int minTime) {
    output_N++;
    System.out.format("Case #%1$d: %2$d%n",output_N,minTime);
    
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
