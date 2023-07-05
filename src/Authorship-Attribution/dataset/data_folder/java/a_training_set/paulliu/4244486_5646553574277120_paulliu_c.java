
 
 
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
        int C;
        int D;
        int V;
        if (stdin.hasNextInt()) {
        C = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        D = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        V = stdin.nextInt();
        } else {
        return ret;
        }
        int[] denominations = new int[D];
        for (int i=0; i<denominations.length;i++) {
        if (stdin.hasNextInt()) {
            denominations[i] = stdin.nextInt();
        } else {
            return ret;
        }
        }
        solve(C,V,denominations);
    }
    ret=0;
        return ret;
     }
 
     
     private void solveC1(int V,int[] denominationsO) {
    ArrayList<Integer> denominations = new ArrayList<Integer>();
    for (int i=0; i<denominationsO.length; i++) {
        denominations.add(new Integer(denominationsO[i]));
    }
    int D=denominations.size();
    int ans=0;
 
    while (true) {
        int[] Vt = new int[V];
        Arrays.fill(Vt,0);
        for (int k=1; k<=denominations.size(); k++) {
        int[] set = new int[k];
        for (int i=0; i<k; i++){
            set[i]=i;
        }
        while (true) {
            int sum=0;
            for (int i=0; i<set.length; i++) {
            sum += denominations.get(set[i]).intValue();
            }
            if (sum-1 < Vt.length) {
            if (Vt[sum-1] == 0  || Vt[sum-1] > k) {
                Vt[sum-1]=k;
            }
            }
            boolean result1 = Permutation.next_ksubset(denominations.size(), set);
            if (!result1) {
            break;
            }
        }
        }
        boolean allGenerated=true;
        for (int i=0; i<Vt.length; i++) {
        if (Vt[i] == 0) {
            allGenerated=false;
            denominations.add(new Integer(i+1));
            logInfo("Adding denominations %1$d",i+1);
            ans++;
            break;
        }
        }
        if (allGenerated) {
        break;
        }
    }
    output(ans);
     }
     
     private void solve(int C,int V,int[] denominations) {
    if (C==1) {
        solveC1(V,denominations);
        return ;
    }
    
    output(-1);
     }
 
     private int output_N=0;
     
     private void output(long ans) {
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
 
 class Permutation {
     private static <T> void reverse(java.util.List<T> list, int i, int j) {
    while (i<j) {
        java.util.Collections.swap(list,i,j);
        i++;
        j--;
    }
     }
     public static <T extends Comparable<? super T> > boolean next_permutation(java.util.List<T> list) {
    if (list.size()<=1) {
        return false;
    }
    int i=list.size()-1;
    while (true) {
        int j = i;
        i--;
 
        if (list.get(i).compareTo(list.get(j))<0) {
        int k = list.size()-1;
        while ( !(list.get(i).compareTo(list.get(k))<0) ) {
            k--;
        }
        java.util.Collections.swap(list,i,k);
        reverse(list,j,list.size()-1);
        return true;
        }
 
        if (i==0) {
        java.util.Collections.reverse(list);
        return false;
        }
    }
     }
 
     public static boolean next_ksubset(int n,int[] set) {
    int i;
    for (i=set.length-1; i>=0; i--) {
        if (set[i]+1 < n-(set.length-1-i)) {
        break;
        }
    }
    if (i<0) {
        return false;
    }
    set[i]++;
    i++;
    for (; i<set.length; i++) {
        set[i] = set[i-1]+1;
    }
    return true;
     }
 
     public static void unittest_permutation() {
    java.util.List<Integer> list = new java.util.ArrayList<Integer>();
    for (int i=0; i<4; i++) {
        list.add(new Integer(i));
    }
    while (true) {
        boolean spaceFlag=false;
        for (Integer l : list) {
        if (spaceFlag) {
            System.out.print(" ");
        } else {
            spaceFlag=true;
        }
        System.out.print(l);
        }
        System.out.println();
        boolean result1 = next_permutation(list);
        if (!result1) {
        break;
        }
    }
     }
 
     public static void unittest_ksubset() {
    int n=5;
    int[] set = new int[3];
    for (int i=0; i<set.length; i++) {
        set[i]=i;
    }
    while (true) {
        boolean spaceFlag=false;
        for (int i=0; i<set.length; i++) {
        if (spaceFlag) {
            System.out.print(" ");
        } else {
            spaceFlag=true;
        }
        System.out.print(set[i]);
        }
        System.out.println();
        boolean result1 = next_ksubset(n,set);
        if (!result1) {
        break;
        }
    }
    
     }
 
 
     public static void unittest() {
    unittest_permutation();
    unittest_ksubset();
     }
 
 }
