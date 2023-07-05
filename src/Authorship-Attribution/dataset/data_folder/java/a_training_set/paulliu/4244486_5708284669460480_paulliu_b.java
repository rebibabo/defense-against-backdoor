
 
 
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
        int K,L,S;
        if (stdin.hasNextInt()) {
        K = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        L = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        S = stdin.nextInt();
        } else {
        return ret;
        }
        String keyboard;
        if (stdin.hasNext()) {
        keyboard = stdin.next();
        } else {
        return ret;
        }
        String target;
        if (stdin.hasNext()) {
        target = stdin.next();
        } else {
        return ret;
        }
        solve(keyboard,target,S);
 
    }
    ret=0;
        return ret;
     }
 
     
     private void solve(String keyboard,String target, int S) {
    int[] keyTable = new int[26];
    int bringBanana=0;
    int rLen=0;
    for (int i=0; i<target.length()-1; i++) {
        if (target.endsWith(target.substring(0,target.length()-i-1))) {
        rLen = i+1;
        break;
        }
    }
    logInfo("rLen = %1$d",rLen);
 
    for (int i=0; i<keyboard.length(); i++) {
        keyTable[keyboard.charAt(i)-'A']++;
    }
    int nonZero=0;
    for (int i=0; i<keyTable.length; i++) {
        if (keyTable[i] != 0) {
        nonZero++;
        }
    }
    char[] keyboardChar = new char[nonZero];
    int[] keyboardCount = new int[nonZero];
    nonZero=0;
    for (int i=0; i<keyTable.length; i++) {
        if (keyTable[i] != 0) {
        keyboardChar[nonZero] = (char)(i+'A');
        keyboardCount[nonZero] = keyTable[i];
        nonZero++;
        }
    }
    for (int i=0; i<keyboardChar.length; i++) {
        logInfo("keyTable: %1$c %2$d",keyboardChar[i], keyboardCount[i]);
    }
 
    double expectBanana=0.0;
 
    int[] bt = new int[S];
    for (int i=0; i<bt.length; i++) {
        bt[i]=-1;
    }
    for (int i=0; i>=0; ) {
        if (i>=bt.length) {
        
        StringBuffer sb1 = new StringBuffer();
        double probability=1.0;
        for (int j=0; j<bt.length; j++) {
            sb1.append(keyboardChar[bt[j]]);
            probability *= (double)(keyboardCount[bt[j]])/(double)(keyboard.length());
        }
        String resultStr = sb1.toString();
        int b=0;
        for (int j=0; j<resultStr.length(); j++) {
            if (j+target.length() > resultStr.length()) {
            break;
            }
            if (resultStr.substring(j,j+target.length()).compareTo(target)==0) {
            b++;
            }
        }
        if (b>bringBanana) {
            bringBanana=b;
        }
        expectBanana += probability * (double)(b);
        i--;
        continue;
        }
        bt[i]++;
        if (bt[i] >= nonZero) {
        bt[i]=-1;
        i--;
        continue;
        }
        i++;
    }
    logInfo("bringBanana = %1$d, expectBanana = %2$f",bringBanana, expectBanana);
    double ans = ((double)bringBanana) - ((double)expectBanana);
    output(ans);
     }
 
     private int output_N=0;
     
     private void output(double ans) {
    output_N++;
    String ans1 = String.format("%.7f",ans);
    if (ans1.indexOf('.') >= 0) {
        while (ans1.length()>=2 && ans1.charAt(ans1.length()-2) != '.' && ans1.endsWith("0")) {
        ans1 = ans1.substring(0,ans1.length()-1);
        }
    }
    System.out.format("Case #%1$d: %2$s%n",output_N, ans1);
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
