
 
 
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
        String S;
        if (stdin.hasNext()) {
        S = stdin.next();
        } else {
        return ret;
        }
        solve(S);
        
    }
 
    ret=0;
        return ret;
     }
 
     String list2str(LinkedList<String> list) {
    StringBuffer sb1 = new StringBuffer();
    for (String s : list) {
        sb1.append(s);
    }
    return sb1.toString();
     }
     
     
     private void solve(String S) {
    String ans = "";
    LinkedList<String> list = new LinkedList<String>();
    for (int i=0; i<S.length(); i++) {
        String ss = S.substring(i,i+1);
        String s1 = ans.concat(ss);
        String s2 = ss.concat(ans);
        if (s1.compareTo(s2)>=0) {
        ans = s1;
        } else {
        ans = s2;
        }
    }
    output(ans);
     }
 
     private int output_N=0;
     
     private void output(String X) {
    output_N++;
    if (X != null) {
        System.out.format("Case #%1$d: %2$s%n",output_N,X);
    } else {
        System.out.format("Case #%1$d: %2$s%n",output_N,X);
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
