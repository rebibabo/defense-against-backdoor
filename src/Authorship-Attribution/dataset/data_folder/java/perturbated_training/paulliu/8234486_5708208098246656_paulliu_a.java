
 
 
 import java.util.*;
 import java.io.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     
     private void init() {
    dir = new int[4][2];
    dir[0][0] = 1;
    dir[0][1] = 0;
    dir[1][0] = -1;
    dir[1][1] = 0;
    dir[2][0] = 0;
    dir[2][1] = 1;
    dir[3][0] = 0;
    dir[3][1] = -1;
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
        ArrayList<String> grid = new ArrayList<String>();
        for (int r1=0; r1<R; r1++) {
        String c1 = null;
        if (stdin.hasNext()) {
            c1 = stdin.next();
        } else {
            break;
        }
        grid.add(c1);
        }
        
        solve(R,C,grid);
    }
    ret=0;
        return ret;
     }
 
     private int[][] dir = null;
     private int char2dir(char a) {
    if (a=='v') {
        return 0;
    } else if (a=='^') {
        return 1;
    } else if (a=='>') {
        return 2;
    } else if (a=='<') {
        return 3;
    }
    return -1;
     }
     private String dir2char = "v^><";
 
     public class Ans1 {
    public boolean result=false;
    public ArrayList<ArrayList<Integer> > path=null;
     }
     
     private Ans1 checkGridPoint(char[][] grid, int r,int c) {
    int R = grid.length;
    int C = grid[0].length;
    Ans1 ret = new Ans1();
    char w = grid[r][c];
    if (w == '.') {
        ret.result=true;
        return ret;
    }
    boolean[][] flag = new boolean[grid.length][grid[0].length];
    for (int r1=0; r1<flag.length; r1++) {
        for (int c1=0; c1<flag[r1].length; c1++) {
        flag[r1][c1]=false;
        }
    }
    boolean dropFlag=false;
    int nowDir = char2dir(grid[r][c]);
    flag[r][c]=true;
    int nowR = r;
    int nowC = c;
    ret.path = new ArrayList< ArrayList < Integer> > ();
    while (true) {
        flag[nowR][nowC] = true;
        if (grid[nowR][nowC] != '.') {
        nowDir = char2dir(grid[nowR][nowC]);
        }
        ArrayList<Integer> path1 = new ArrayList<Integer>();
        path1.add(new Integer(nowR));
        path1.add(new Integer(nowC));
        ret.path.add(path1);
        
        nowR = nowR + dir[nowDir][0];
        nowC = nowC + dir[nowDir][1];
        
        if (nowR < 0 || nowR >= R || nowC < 0 || nowC >= C) {
        dropFlag=true;
        break;
        }
        if (flag[nowR][nowC]) {
        dropFlag=false;
        break;
        }
    }
    if (dropFlag) {
        ret.result=false;
        return ret;
    }
    ret.result = true;
    return ret;
     }
     
     private boolean checkGrid(int R,int C,char[][] grid) {
    for (int r=0; r<R; r++) {
        for (int c=0; c<C; c++) {
        char w = grid[r][c];
        if (w == '.') {
            continue;
        }
        boolean[][] flag = new boolean[R][C];
        for (int r1=0; r1<R; r1++) {
            for (int c1=0; c1<C; c1++) {
            flag[r1][c1]=false;
            }
        }
        boolean dropFlag=false;
        int nowDir = char2dir(grid[r][c]);
        flag[r][c]=true;
        int nowR = r;
        int nowC = c;
        while (true) {
            flag[nowR][nowC] = true;
            if (grid[nowR][nowC] != '.') {
            nowDir = char2dir(grid[nowR][nowC]);
            }
            nowR = nowR + dir[nowDir][0];
            nowC = nowC + dir[nowDir][1];
            if (nowR < 0 || nowR >= R || nowC < 0 || nowC >= C) {
            dropFlag=true;
            break;
            }
            if (flag[nowR][nowC]) {
            dropFlag=false;
            break;
            }
        }
        if (dropFlag) {
            return false;
        }
        }
    }
    return true;
     }
 
     int sAns = 0;
     private void solveR(char[][] grid, int r,int c,int nowChange) {
    if (r >= grid.length || c >= grid[0].length) {
        if (logger != null) {
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
            System.err.print(grid[i][j]);
            }
            System.err.println();
        }
        }
        boolean result1 = checkGrid(grid.length,grid[0].length,grid);
        logInfo("result1 = %b",result1);
        if (result1) {
        if (nowChange < sAns) {
            sAns = nowChange;
        }
        }
        return;
    }
    if (grid[r][c]=='.') {
        if (c+1 >= grid[0].length) {
        solveR(grid,r+1,0,nowChange);
        } else {
        solveR(grid,r,c+1,nowChange);
        }
        return;
    }
    if (nowChange > sAns) {
        return;
    }
    
    Ans1 result1 = checkGridPoint(grid,r,c);
    boolean noChangeFlag=false;
    if (result1.result) {
        noChangeFlag=true;
    } else {
        for (int i=0; i<result1.path.size(); i++) {
        int cR = result1.path.get(i).get(0);
        int cC = result1.path.get(i).get(1);
        if (cR > r || (cR==r && cC > c)) {
            noChangeFlag=true;
            break;
        }
        }
    }
    result1 = null;
    if (noChangeFlag) {
        if (c+1 >= grid[0].length) {
        solveR(grid,r+1,0,nowChange);
        } else {
        solveR(grid,r,c+1,nowChange);
        }
    }
    
    int origDir = char2dir(grid[r][c]);
    for (int i=1; i<4; i++) {
        int newDir = (origDir + i)%4;
        grid[r][c] = dir2char.charAt(newDir);
        if (nowChange+1 > sAns) {
        continue;
        }
 
        Ans1 result2 = checkGridPoint(grid,r,c);
        boolean changedFlag=false;
        if (result2.result) {
        changedFlag=true;
        } else {
        for (int i1=0; i1<result2.path.size(); i1++) {
            int cR = result2.path.get(i1).get(0);
            int cC = result2.path.get(i1).get(1);
            if (cR > r || (cR==r && cC > c)) {
            changedFlag=true;
            break;
            }
        }
        }
 
        if (changedFlag) {
        
        if (c+1 >= grid[0].length) {
            solveR(grid,r+1,0,nowChange+1);
        } else {
            solveR(grid,r,c+1,nowChange+1);
        }
        }
    }
    grid[r][c] = dir2char.charAt(origDir);
     }
     
     
     private void solve(int R,int C,ArrayList<String> gridS) {
    char[][] grid = new char[R][C];
    for (int r=0; r<R; r++) {
        for (int c=0; c<C; c++) {
        grid[r][c] = gridS.get(r).charAt(c);
        }
    }
    sAns = Integer.MAX_VALUE;
    solveR(grid,0,0,0);
    output(sAns);
     }
 
     private int output_N=0;
     
     private void output(int ans) {
    output_N++;
    if (ans == Integer.MAX_VALUE) {
        System.out.format("Case #%1$d: IMPOSSIBLE%n",output_N);
    } else {
        System.out.format("Case #%1$d: %2$d%n",output_N, ans);
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
