
 
 
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
        if (stdin.hasNextInt()) {
        N = stdin.nextInt();
        } else {
        return ret;
        }
        int[] Pi = new int[N];
        for (int i=0 ; i<N; i++) {
        if (stdin.hasNextInt()) {
            Pi[i] = stdin.nextInt();
        } else {
            return ret;
        }
        }
        solve(Pi);
        
    }
 
    ret=0;
        return ret;
     }
 
     
     private void solve(int[] Pi) {
 
    ArrayList<String> ans = new ArrayList<String>();
    int N = Pi.length;
 
    while (true) {
        int maxI1 = -1;
        int maxI1Data = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
        if (Pi[i] > maxI1Data) {
            maxI1Data = Pi[i];
            maxI1 = i;
        }
        }
 
        int maxI2 = -1;
        int maxI2Data = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
        if (i == maxI1) {
            continue;
        }
        if (Pi[i] > maxI2Data) {
            maxI2Data = Pi[i];
            maxI2 = i;
        }
        }
 
        if (maxI1Data <= 1) {
        break;
        }
 
        if (maxI1Data > maxI2Data) {
        Pi[maxI1]--;
        char[] a1 = new char[1];
        a1[0] = (char)('A'+maxI1);
        ans.add(new String(a1));
        } else {
        Pi[maxI1]--;
        Pi[maxI2]--;
        char[] a1 = new char[2];
        a1[0] = (char)('A'+maxI1);
        a1[1] = (char)('A'+maxI2);
        ans.add(new String(a1));
        }
        
    }
 
    int sum1=0;
    for (int i=0; i<N; i++) {
        sum1 += Pi[i];
    }
    if (sum1 % 2 == 0) {
        while (true) {
        int maxI1 = -1;
        int maxI1Data = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
            if (Pi[i] > maxI1Data) {
            maxI1Data = Pi[i];
            maxI1 = i;
            }
        }
 
        int maxI2 = -1;
        int maxI2Data = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
            if (i == maxI1) {
            continue;
            }
            if (Pi[i] > maxI2Data) {
            maxI2Data = Pi[i];
            maxI2 = i;
            }
        }
 
        if (maxI1Data <= 0) {
            break;
        }
 
        if (maxI1Data > maxI2Data) {
            Pi[maxI1]--;
            char[] a1 = new char[1];
            a1[0] = (char)('A'+maxI1);
            ans.add(new String(a1));
        } else {
            Pi[maxI1]--;
            Pi[maxI2]--;
            char[] a1 = new char[2];
            a1[0] = (char)('A'+maxI1);
            a1[1] = (char)('A'+maxI2);
            ans.add(new String(a1));
        }
        }
    } else {
        for (int i=0 ;i<N; i++) {
        if (Pi[i] != 0) {
            Pi[i]--;
            char[] a1 = new char[1];
            a1[0] = (char)('A'+i);
            ans.add(new String(a1));
            break;
        }
        }
        while (true) {
        int maxI1 = -1;
        int maxI1Data = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
            if (Pi[i] > maxI1Data) {
            maxI1Data = Pi[i];
            maxI1 = i;
            }
        }
 
        int maxI2 = -1;
        int maxI2Data = Integer.MIN_VALUE;
        for (int i=0; i<N; i++) {
            if (i == maxI1) {
            continue;
            }
            if (Pi[i] > maxI2Data) {
            maxI2Data = Pi[i];
            maxI2 = i;
            }
        }
 
        if (maxI1Data <= 0) {
            break;
        }
 
        if (maxI1Data > maxI2Data) {
            Pi[maxI1]--;
            char[] a1 = new char[1];
            a1[0] = (char)('A'+maxI1);
            ans.add(new String(a1));
        } else {
            Pi[maxI1]--;
            Pi[maxI2]--;
            char[] a1 = new char[2];
            a1[0] = (char)('A'+maxI1);
            a1[1] = (char)('A'+maxI2);
            ans.add(new String(a1));
        }
        }
    }
     
    
    
    output(ans);
     }
 
     private int output_N=0;
     
     private void output(ArrayList<String> ans) {
    output_N++;
 
    StringBuffer s1 = new StringBuffer();
 
    for (int i=0 ; i<ans.size(); i++) {
        if (i>0) {
        s1.append(" ");
        }
        s1.append(ans.get(i));
    }
    
    if (ans != null) {
        System.out.format("Case #%1$d: %2$s%n",output_N,s1.toString());
    } else {
        System.out.format("Case #%1$d: %2$s%n",output_N,s1.toString());
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
