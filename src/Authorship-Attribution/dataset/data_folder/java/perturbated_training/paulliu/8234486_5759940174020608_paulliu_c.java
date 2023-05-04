
 
 
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
    int T=0;
 
    if (stdin.hasNextLine()) {
        com1 = stdin.nextLine();
        T = Integer.parseInt(com1);
    } else {
        return ret;
    }
    for (int t1=0; t1<T; t1++) {
        int N=0;
        if (stdin.hasNextLine()) {
        com1 = stdin.nextLine();
        N = Integer.parseInt(com1);
        } else {
        return ret;
        }
        ArrayList<String> strs = new ArrayList<String>();
        for (int i=0; i<N; i++) {
        if (stdin.hasNextLine()) {
            com1 = stdin.nextLine();
            strs.add(com1);
        } else {
            break;
        }
        }
        solve(strs);
    }
    ret=0;
        return ret;
     }
 
     int sAns = 0;
     
     private void solveR(ArrayList<String> strs, int c, HashSet<String> englishWords, HashSet<String> frenchWords) {
    if (c >= strs.size()) {
        int nowAns=0;
        for (String w : englishWords) {
        if (frenchWords.contains(w)) {
            nowAns++;
        }
        }
        if (nowAns < sAns) {
            sAns = nowAns;
        }
        return;
    }
    if (c==0) {
        
        String s = strs.get(c);
        String[] s1 = s.split("\\s+");
        for (int i=0; i<s1.length; i++) {
        if (!englishWords.contains(s1[i])) {
            englishWords.add(s1[i]);
        }
        }
        solveR(strs, c+1, englishWords, frenchWords);
    } else if (c==1) {
        
        String s = strs.get(c);
        String[] s1 = s.split("\\s+");
        for (int i=0; i<s1.length; i++) {
        if (!frenchWords.contains(s1[i])) {
            frenchWords.add(s1[i]);
        }
        }
        solveR(strs, c+1, englishWords, frenchWords);
    } else {
        String s = strs.get(c);
        String[] s1 = s.split("\\s+");
 
        for (int t1=0; t1<=1; t1++) {
        if (t1==0) {
            HashSet<String> newEnglishWords = new HashSet<String>(englishWords);
            for (int i=0; i<s1.length; i++) {
            if (!newEnglishWords.contains(s1[i])) {
                newEnglishWords.add(s1[i]);
            }
            }
            int nowAns=0;
            for (String w : newEnglishWords) {
            if (frenchWords.contains(w)) {
                nowAns++;
            }
            }
            if (nowAns < sAns) {
            solveR(strs,c+1,newEnglishWords,frenchWords);
            }
        } else {
            HashSet<String> newFrenchWords = new HashSet<String>(frenchWords);
            for (int i=0; i<s1.length; i++) {
            if (!newFrenchWords.contains(s1[i])) {
                newFrenchWords.add(s1[i]);
            }
            }
            int nowAns=0;
            for (String w : englishWords) {
            if (newFrenchWords.contains(w)) {
                nowAns++;
            }
            }
            if (nowAns < sAns) {
            solveR(strs,c+1,englishWords,newFrenchWords);
            }
        }
        }       
    }
     }
 
     
     private void solve(ArrayList<String> strs) {
    sAns = Integer.MAX_VALUE;
    solveR(strs,0,new HashSet<String>(), new HashSet<String>());
    output(sAns);
     }
 
     private int output_N=0;
     
     private void output(int ans) {
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
