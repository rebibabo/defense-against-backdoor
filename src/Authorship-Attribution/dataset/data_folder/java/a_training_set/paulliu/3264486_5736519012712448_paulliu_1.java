
 
 
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
    int N=0;
 
    
    if (stdin.hasNextInt()) {
        N = stdin.nextInt();
    } else {
        return ret;
    }
 
    for (int n1=0; n1<N; n1++) {
        String pancake=null;
        if (stdin.hasNext()) {
        pancake = stdin.next();
        } else {
        return 0;
        }
        int K;
        if (stdin.hasNextInt()) {
        K = stdin.nextInt();
        } else {
        return 0;
        }
        solve(pancake, K);
 
    }
 
    ret=0;
        return ret;
     }
 
     boolean testPossible(int[] pancakeO, int K) {
    int[] pancake = Arrays.copyOf(pancakeO, pancakeO.length);
    for (int i=0; i+K-1<pancake.length; i++) {
        if (pancake[i] != 1) {
        for (int j=0; j<K; j++) {
            pancake[i+j] = 1-pancake[i+j];
        }
        }
    }
    for (int i=0; i<pancake.length; i++) {
        if (pancake[i] == 0) {
        return false;
        }
    }
    return true;
     }
 
     private int getStepsRec(int[] pancake, int left, int right, int K) {
    int ret=0;
 
    if (left >= right) {
        return 0;
    }
 
    if (pancake[left] == 1) {
        return getStepsRec(pancake, left+1, right, K);
    }
    if (pancake[right] == 1) {
        return getStepsRec(pancake, left, right-1, K);
    }
 
    if (right-left+1 < K) {
        for (int i=left; i<=right; i++) {
        if (pancake[i]==0) {
            return -1;
        }
        }
        return 0;
    }
 
    int min1=-1;
    
    for (int i=left; i<left+K; i++) {
        pancake[i] = 1-pancake[i];
    }
    int leftResult = getStepsRec(pancake, left+1, right, K);
    for (int i=left; i<left+K; i++) {
        pancake[i] = 1-pancake[i];
    }
    if (leftResult != -1) {
        min1 = leftResult + 1;
    }
 
    
    for (int i=right; i>right-K; i--) {
        pancake[i] = 1-pancake[i];
    }
    int rightResult = getStepsRec(pancake, left, right-1, K);
    for (int i=right; i>right-K; i--) {
        pancake[i] = 1-pancake[i];
    }
    if (rightResult != -1) {
        if (min1 == -1) {
        min1 = rightResult + 1;
        } else {
        if (rightResult+1 < min1) {
            min1 = rightResult + 1;
        }
        }
    }
    return min1;
     }
     
     
     private void solve(String pancakeS, int K) {
    int[] pancake = new int[pancakeS.length()];
    for (int i=0; i<pancakeS.length(); i++) {
        if (pancakeS.charAt(i)=='+') {
        pancake[i]=1;
        } else {
        pancake[i]=0;
        }
    }
    if (!testPossible(pancake, K)) {
        output(-1);
        return;
    }
 
    int steps = getStepsRec(pancake, 0, pancake.length-1, K);
    output(steps);
     }
 
     int output_N=0;
     
     private void output(int steps) {
    output_N++;
    System.out.format("Case #%1$d: ", output_N);
    if (steps<0) {
        System.out.print("IMPOSSIBLE");
    } else {
        System.out.print(steps);
    }       
    System.out.println();
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
