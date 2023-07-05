
 
 
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
        int B;
        long M;
        if (stdin.hasNextInt()) {
        B = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextLong()) {
        M = stdin.nextLong();
        } else {
        return ret;
        }
        solve(B,M);
        
    }
 
    ret=0;
        return ret;
     }
 
     long countWaysRet=0;
     void countWays_r(int[][] graph, int[] mark, int S) {
    if (countWaysRet == Long.MAX_VALUE) {
        return;
    }
    if (S == graph.length-1) {
        countWaysRet++;
        return;
    }
    mark[S]=1;
    for (int i=0; i<graph[S].length; i++) {
        if (i==S) {
        continue;
        }
        if (graph[S][i] != 0) {
        if (mark[i] == 1) {
            countWaysRet =  Long.MAX_VALUE;
            return;
        }
        countWays_r(graph, mark, i);
        }
    }
    mark[S]=0;
     }
 
     long countWays(int[][] graph) {
    int[] mark = new int[graph.length];
    Arrays.fill(mark,0);
    countWaysRet = 0;
    countWays_r(graph, mark, 0);
    return countWaysRet;
     }
 
     class FoundAnswer extends Exception {
    public FoundAnswer() {
    }
     }
     long solveSmall_r_M=-1;
 
     private void solveSmall_r(int[][] graph, int S1, int S2) throws FoundAnswer {
    int N = graph.length;
    long w1;
 
    graph[S1][S2]=1;
    
    if (S2 == N-1 && S1 == N-2) {
        w1 = countWays(graph);
        if (w1 == solveSmall_r_M) {
        throw new FoundAnswer();
        }
    } else {
        int nS1 = S1;
        int nS2 = S2+1;
        if (nS2 >= N) {
        nS1++;
        nS2 = nS1+1;
        }
        solveSmall_r(graph, nS1, nS2);
    }
 
    graph[S1][S2]=0;
    
    if (S2 == N-1 && S1 == N-2) {
        w1 = countWays(graph);
        if (w1 == solveSmall_r_M) {
        throw new FoundAnswer();
        }
    } else {
        int nS1 = S1;
        int nS2 = S2+1;
        if (nS2 >= N) {
        nS1++;
        nS2 = nS1+1;
        }
        solveSmall_r(graph, nS1, nS2);
    }
     }
     
     
     private void solve(int B, long M) {
 
    int[][] graph = new int[B][B];
    boolean ans1 = false;
 
    try {
        solveSmall_r_M = M;
        solveSmall_r(graph, 0, 1);
    } catch (FoundAnswer a) {
        ans1 = true;
    }
    
    if (ans1) {
        output(graph);
    } else {
        output(null);
    }
    
     }
 
     private int output_N=0;
     
     private void output(int[][] graph) {
    output_N++;
    if (graph != null) {
        System.out.format("Case #%1$d: POSSIBLE%n",output_N);
        for (int i=0; i<graph.length; i++) {
        for (int j=0; j<graph[i].length; j++) {
            System.out.format("%1$d",graph[i][j]);
        }
        System.out.println();
        }
    } else {
        System.out.format("Case #%1$d: IMPOSSIBLE%n",output_N);
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
