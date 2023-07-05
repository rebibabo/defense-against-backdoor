
 
 
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
 
        ArrayList < ArrayList < Integer > > lists = new ArrayList< ArrayList < Integer > > ();
        for (int i=0; i<2*N-1; i++) {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        for (int j=0; j<N; j++) {
            int X;
            if (stdin.hasNextInt()) {
            X = stdin.nextInt();
            } else {
            return ret;
            }
            list1.add(new Integer(X));
        }
        lists.add(list1);
        }
        
        solve(lists);
        
    }
 
    ret=0;
        return ret;
     }
 
     class Comparator1 implements Comparator<ArrayList<Integer> > {
    public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
        for (int i=0; i<a.size() && i<b.size(); i++) {
        if (a.get(i).compareTo(b.get(i)) < 0) {
            return -1;
        } else if (a.get(i).compareTo(b.get(i)) > 0) {
            return 1;
        }
        }
        if (a.size() > b.size()) {
        return 1;
        } else if (a.size() < b.size()) {
        return -1;
        } 
        return 0;
    }
    public boolean equals(Object obj) {
        return (this==obj);
    }
    
     }
     
     private int sorting(ArrayList < ArrayList < Integer > > lists) {
    for (int i=0; i<lists.size(); i++) {
        for (int j=i+1; j<lists.size(); j++) {
        if (lists.get(i).get(i/2).compareTo(lists.get(j).get(i/2)) > 0) {
            ArrayList<Integer> tmp = lists.get(i);
            lists.set(i, lists.get(j));
            lists.set(j, tmp);
        }
        }
    }
    return 0;
     }
     
     
     private void solve(ArrayList< ArrayList < Integer> > lists) {
    sorting(lists);
    int problemRow=-1;
    for (int i=0; i<lists.size(); i+=2) {
        int j=i+1;
        problemRow = i/2;
        if (j>=lists.size()) {
        break;
        }
        if (lists.get(i).get(problemRow).compareTo(lists.get(j).get(problemRow)) != 0) {
        break;
        }
    }
    logInfo("ProblemRow = %1$d",problemRow);
    int[] newRow = new int[(lists.size()+1)/2];
    newRow[problemRow] = lists.get(problemRow*2).get(problemRow).intValue();
    for (int i=0; i<problemRow; i++) {
        int x1 = lists.get(problemRow*2).get(i).intValue();
        if (x1 == lists.get(i*2).get(problemRow).intValue()) {
        newRow[i] = lists.get(i*2+1).get(problemRow).intValue();
        } else {
        newRow[i] = lists.get(i*2).get(problemRow).intValue();
        }
    }
    for (int i=problemRow+1; i<(lists.size()+1)/2; i++) {
        int x1 = lists.get(problemRow*2).get(i).intValue();
        if (x1 == lists.get(i*2-1).get(problemRow).intValue()) {
        newRow[i] = lists.get(i*2).get(problemRow).intValue();
        } else {
        newRow[i] = lists.get(i*2-1).get(problemRow).intValue();
        }
    }
    output(newRow);
     }
 
     private int output_N=0;
     
     private void output(int[] row) {
    output_N++;
    if (row != null) {
        System.out.format("Case #%1$d:",output_N);
        for (int i=0; i<row.length; i++) {
        System.out.print(" ");
        System.out.print(row[i]);
        }
        System.out.println();
    } else {
        System.out.format("Case #%1$d: NULL%n",output_N);
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
