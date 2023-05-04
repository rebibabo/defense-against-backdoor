
 
 
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
        int R;
        int C;
        if (stdin.hasNextInt()) {
        R = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        C = stdin.nextInt();
        } else {
        return ret;
        }
        solve(R,C);
    }
    ret=0;
        return ret;
     }
 
     private int [][] dirT = { {1,0}, {-1,0}, {0,1}, {0,-1} };
     private boolean checkgrid1(int[][] grid,int r,int c) {
    boolean ret = true;
    cl1: for (int i=0; i<grid.length ; i++) {
        for (int j=0; j<grid[i].length; j++) {
        if (i>r || (i>=r && j>c) ) {
            break cl1;
        }
        int numOfK=0;
        int numOfMinus=0;
        int numOfNot=0;
        for (int dir=0; dir<4; dir++) {
            int nR = i+dirT[dir][0];
            int nC = j+dirT[dir][1];
            if (nR < 0 || nR >= grid.length) {
            continue;
            }
            nC = (nC + grid[nR].length)%grid[nR].length;
            if (grid[nR][nC] == grid[i][j]) {
            numOfK++;
            } else if (grid[nR][nC]==-1) {
            numOfMinus++;
            } else {
            numOfNot++;
            }
        }
        if (numOfK > grid[i][j]) {
            ret=false;
            break cl1;
        }
        if (numOfK + numOfMinus < grid[i][j]) {
            ret=false;
            break cl1;
        }
        }
    }
    return ret;
     }
 
     private boolean checkgrid(int[][] grid) {
    boolean ret = true;
    cgg11: for (int i=0; i<grid.length ; i++) {
        for (int j=0; j<grid[i].length; j++) {
        int numOfK=0;
        int numOfMinus=0;
        int numOfNot=0;
        for (int dir=0; dir<4; dir++) {
            int nR = i+dirT[dir][0];
            int nC = j+dirT[dir][1];
            if (nR < 0 || nR >= grid.length) {
            continue;
            }
            nC = (nC + grid[nR].length)%grid[nR].length;
            if (grid[nR][nC] == grid[i][j]) {
            numOfK++;
            } else if (grid[nR][nC]==-1) {
            numOfMinus++;
            } else {
            numOfNot++;
            }
        }
        if (numOfK != grid[i][j]) {
            ret=false;
            break cgg11;
        }
        }
    }
    return ret;
     }
 
     private ArrayList< ArrayList< ArrayList < Integer > > > ans;
 
     private boolean isAnsEqual(ArrayList< ArrayList < Integer> > a1,
                   ArrayList< ArrayList < Integer> > b1) {
    for (int r=0; r<a1.get(0).size(); r++) {
        boolean equalFlag=true;
        iae1: for (int i=0; i<a1.size(); i++) {
        for (int j=0; j<a1.get(i).size(); j++) {
            int c1 = (j+r)%a1.get(i).size();
            if (a1.get(i).get(j).compareTo(b1.get(i).get(c1)) != 0) {
            equalFlag=false;
            break iae1;
            }
        }
        }
        if (equalFlag) {
        return true;
        }
    }
    return false;
     }
     
     private void solveR(int[][] grid,int r,int c) {
    if (r>=grid.length || c >= grid[0].length) {
        if (checkgrid(grid)) {
        
        ArrayList < ArrayList < Integer> > ans1 = new ArrayList< ArrayList < Integer> > ();
        for (int i=0; i<grid.length; i++) {
            ArrayList<Integer> ans11 = new ArrayList<Integer>();
            for (int j=0; j<grid[i].length; j++) {
            ans11.add(new Integer(grid[i][j]));
            }
            ans1.add(ans11);
        }
        boolean equalFlag=false;
        for (ArrayList<ArrayList<Integer> > r1 : ans ) {
            if (isAnsEqual(r1,ans1)) {
            equalFlag=true;
            break;
            }
        }
        if (!equalFlag) {
            ans.add(ans1);
        }
        }
        return;
    }
    
    for (grid[r][c]=1; grid[r][c]<=3; grid[r][c]++) {
        boolean result1 = checkgrid1(grid,r,c);
        if (!result1) {
        continue;
        }
        if (c+1 >= grid[0].length) {
        solveR(grid,r+1,0);
        } else {
        solveR(grid,r,c+1);
        }
    }
    grid[r][c]=-1;
    
     }
     
     
     private void solve(int R,int C) {
    int[][] grid = new int[R][C];
    for (int r=0; r<grid.length; r++) {
        for (int c=0; c<grid[r].length; c++) {
        grid[r][c]=-1;
        }
    }
    ans = new ArrayList< ArrayList < ArrayList< Integer > > > ();
    solveR(grid,0,0);
    if (logger != null) {
        for (int i=0; i<ans.size(); i++) {
        System.err.format("%1$d: %n",i);
        for (int j=0; j<ans.get(i).size(); j++) {
            for (int k=0; k<ans.get(i).get(j).size(); k++) {
            System.err.format("%1$d ",ans.get(i).get(j).get(k));
            }
            System.err.println();
        }
        }
    }
    int ans1 = ans.size();
    output(ans1);
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
