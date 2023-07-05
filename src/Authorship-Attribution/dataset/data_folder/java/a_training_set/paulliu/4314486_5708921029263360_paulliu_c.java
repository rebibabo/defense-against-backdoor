
 
 
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
        int J,P,S,K;
        
        if (stdin.hasNextInt()) {
        J = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        P = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        S = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        K = stdin.nextInt();
        } else {
        return ret;
        }
        solve(J,P,S,K);
        
    }
 
    ret=0;
        return ret;
     }
 
     private boolean check1(int J,int P, int S, int K, ArrayList < ArrayList < Integer> > ans) {
    for (int j=0; j<J; j++) {
        for (int p=0; p<P; p++) {
        int k=0;
        for (ArrayList<Integer> o1 : ans) {
            if (o1.get(0).intValue()==j && o1.get(1).intValue()==p) {
            k++;
            }
        }
        if (k>K) {
            return false;
        }
        }
        for (int s=0; s<S; s++) {
        int k=0;
        for (ArrayList<Integer> o1 : ans) {
            if (o1.get(0).intValue()==j && o1.get(2).intValue()==s) {
            k++;
            }
        }
        if (k>K) {
            return false;
        }
        }
    }
    for (int p=0; p<P; p++) {
        for (int s=0; s<S; s++) {
        int k=0;
        for (ArrayList<Integer> o1 : ans) {
            if (o1.get(1).intValue()==p && o1.get(2).intValue()==s) {
            k++;
            }
        }
        if (k>K) {
            return false;
        }
        }
    }
    return true;
     }
 
     int solveRMaxSize=-1;
     ArrayList < ArrayList < Integer > > solveRMaxAns = null;
     private void solveR(int J,int P,int S,int K,ArrayList < ArrayList < Integer > > ans) throws Exception {
    if (ans.size() > solveRMaxSize) {
        solveRMaxSize = ans.size();
        solveRMaxAns = new ArrayList < ArrayList < Integer > > ();
        for (ArrayList<Integer> r1 : ans) {
        solveRMaxAns.add(r1);
        }
    }
    if (solveRMaxSize == J*P*S) {
        throw new Exception();
    }
 
    boolean startFlag=false;
    for (int j=0 ;j<J; j++) {
        for (int p=0; p<P; p++) {
        for (int s=0; s<S; s++) {
            if (!startFlag) {
            j=ans.get(ans.size()-1).get(0).intValue();
            p=ans.get(ans.size()-1).get(1).intValue();
            s=ans.get(ans.size()-1).get(2).intValue();
            startFlag=true;
            }
            
            ArrayList<Integer> o1 = new ArrayList<Integer>();
            o1.add(new Integer(j));
            o1.add(new Integer(p));
            o1.add(new Integer(s));
            if (ans.contains(o1)) {
            continue;
            }
            ans.add(o1);
            if (check1(J,P,S,K,ans)) {
            solveR(J,P,S,K,ans);
            }
            ans.remove(ans.size()-1);
        }
        }
    }
     }
 
     
     
     private void solve(int J,int P,int S,int K) {
 
    ArrayList<ArrayList<Integer> > ans1= new ArrayList<ArrayList<Integer> > ();
    ArrayList<Integer> r1 = new ArrayList<Integer>();
    r1.add(new Integer(0));
    r1.add(new Integer(0));
    r1.add(new Integer(0));
    ans1.add(r1);
 
    solveRMaxSize = Integer.MIN_VALUE;
    try {
        solveR(J,P,S,K,ans1);
    } catch (Exception e) {
    }
 
    logInfo("solveRMaxSize: %1$d",solveRMaxSize);
    
    output(solveRMaxSize, solveRMaxAns);
     }
 
     private int output_N=0;
     
     private void output(int m, ArrayList<ArrayList<Integer> > ans) {
    output_N++;
    System.out.format("Case #%1$d: %2$d%n",output_N,m);
    for (int i=0; i<ans.size(); i++) {
        for (int j=0; j<ans.get(i).size(); j++) {
        if (j>0) {
            System.out.print(" ");
        }
        System.out.print(ans.get(i).get(j).intValue()+1);
        }
        System.out.println();
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
