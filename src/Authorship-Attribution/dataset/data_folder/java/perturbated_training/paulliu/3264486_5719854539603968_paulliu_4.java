
 
 
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
    int T;
    String com1;
 
    if (stdin.hasNextInt()) {
        T = stdin.nextInt();
    } else {
        return ret;
    }
    for (int t1=0; t1<T; t1++) {
        int N;
        int M;
 
        if (stdin.hasNextInt()) {
        N = stdin.nextInt();
        } else {
        return ret;
        }
        if (stdin.hasNextInt()) {
        M = stdin.nextInt();
        } else {
        return ret;
        }
 
        int [][] board = new int[N][N];
        
        for (int m1=0; m1<M; m1++) {
        int Ri;
        int Ci;
 
        if (stdin.hasNext()) {
            com1 = stdin.next();
        } else {
            return ret;
        }
        if (stdin.hasNextInt()) {
            Ri = stdin.nextInt();
        } else {
            return ret;
        }
        if (stdin.hasNextInt()) {
            Ci = stdin.nextInt();
        } else {
            return ret;
        }
        if (com1.compareTo("+")==0) {
            board[Ri-1][Ci-1] = 1;
        } else if (com1.compareTo("x")==0) {
            board[Ri-1][Ci-1] = 2;
        } else if (com1.compareTo("o")==0) {
            board[Ri-1][Ci-1] = 3;
        }
        }
        solveSmall(board);
 
    }
 
    ret=1;
        return ret;
     }
 
     private void printboard(int[][] board) {
    for (int i=0; i<board.length; i++) {
        for (int j=0; j<board[i].length; j++) {
        switch(board[i][j]) {
        case 0:
            System.err.print(".");
            break;
        case 1:
            System.err.print("+");
            break;
        case 2:
            System.err.print("x");
            break;
        case 3:
            System.err.print("o");
            break;
        default:
            System.err.print("?");
            break;
        }
        }
        System.err.println();
    }
     }
 
     private boolean checkboard1(int[][] board, int i, int j) {
    int N = board.length;
    int[][] directions1 = { {0,1}, {1,0}, {0,-1}, {-1,0} };
    int[][] directions2 = { {1,1}, {1,-1}, {-1,-1}, {-1,1} };
 
    if (board[i][j] == 1) {
        boolean badFlag=false;
        c001: for (int dir=0; dir<4; dir++) {
        for (int k=1; k<N; k++) {
            logInfo("dir=%1$d, k=%2$d",dir,k);
            int ni = i+k*directions2[dir][0];
            int nj = j+k*directions2[dir][1];
            if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
            break;
            }
            if (board[ni][nj] != 2 && board[ni][nj] != 0) {
            logInfo("badFlag=true when ni=%1$d, nj=%2$d",ni,nj);
            badFlag=true;
            break c001;
            }
        }
        }
        if (badFlag) {
        return false;
        } else {
        return true;
        }
    } else if (board[i][j]==2) {
        boolean badFlag=false;
        c002: for (int dir=0; dir<4; dir++) {
        for (int k=1; k<N; k++) {
            logInfo("dir=%1$d, k=%2$d",dir,k);
            int ni = i+k*directions1[dir][0];
            int nj = j+k*directions1[dir][1];
            if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
            break;
            }
            if (board[ni][nj] != 1 && board[ni][nj] != 0) {
            logInfo("badFlag=true when ni=%1$d, nj=%2$d, board[ni][nj]=%3$d",ni,nj,board[ni][nj]);
            badFlag=true;
            break c002;
            }
        }
        }
        if (badFlag) {
        return false;
        } else {
        return true;
        }
    } else if (board[i][j]==3) {
        boolean badFlag=false;
        c003: for (int dir=0; dir<4; dir++) {
        for (int k=1; k<N; k++) {
            int ni = i+k*directions2[dir][0];
            int nj = j+k*directions2[dir][1];
            if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
            break;
            }
            if (board[ni][nj] != 2 && board[ni][nj] != 0) {
            badFlag=true;
            break c003;
            }
        }
        }
        if (badFlag) {
        return false;
        }
        c004: for (int dir=0; dir<4; dir++) {
        for (int k=1; k<N; k++) {
            int ni = i+k*directions1[dir][0];
            int nj = j+k*directions1[dir][1];
            if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
            break;
            }
            if (board[ni][nj] != 1 && board[ni][nj] != 0) {
            badFlag=true;
            break c004;
            }
        }
        }
        if (badFlag) {
        return false;
        } else {
        return true;
        }
        
    }
    return true;
     }
 
     private int boardStylePoint(int[][] board) {
    int N = board.length;
    int value = 0;
    for (int x = 0; x<N; x++) {
        for (int y=0; y<N; y++) {
        if (board[x][y] == 1 || board[x][y] == 2) {
            value ++;
        } else if (board[x][y] == 3) {
            value += 2;
        }
        }
    }
    return value;
     }
 
     int backtracking1_value = Integer.MIN_VALUE;
     int[][] backtracking1_bestBoard = null;
     private void backtracking1(int[][] board, int i, int j) {
    int N = board.length;
    if (i>=N) {
        
        int value = boardStylePoint(board);
        if (value > backtracking1_value) {
        backtracking1_value = value;
        backtracking1_bestBoard = new int[N][N];
        for (int x = 0; x<N; x++) {
            for (int y=0; y<N; y++) {
            backtracking1_bestBoard[x][y] = board[x][y];
            }
        }
        }
        return;
    }
    if (board[i][j] == 0) {
        
        for (int i1=3; i1>=0; i1--) {
        board[i][j] = i1;
        logInfo("backtracking1: i=%1$d, j=%2$d",i,j);
        if (logger != null) {
            printboard(board);
        }
        if (checkboard1(board,i,j)) {
            if (j+1 >= N) {
            backtracking1(board, i+1, 0);
            } else {
            backtracking1(board, i, j+1);
            }
        } else {
            logInfo("board failed");
        }
        }
        board[i][j]=0;
    } else if (board[i][j] == 1 || board[i][j] == 2) {
        
        int oldValue = board[i][j];
        board[i][j] = 3;
        logInfo("backtracking1: i=%1$d, j=%2$d",i,j);
        if (logger != null) {
        printboard(board);
        }
        if (checkboard1(board,i,j)) {
        if (j+1 >= N) {
            backtracking1(board, i+1, 0);
        } else {
            backtracking1(board, i, j+1);
        }
        }
        board[i][j] = oldValue;
        logInfo("backtracking1: i=%1$d, j=%2$d",i,j);
        if (logger != null) {
        printboard(board);
        }
        if (checkboard1(board,i,j)) {
        if (j+1 >= N) {
            backtracking1(board, i+1, 0);
        } else {
            backtracking1(board, i, j+1);
        }
        }
    } else {
        logInfo("backtracking1: i=%1$d, j=%2$d",i,j);
        if (logger != null) {
        printboard(board);
        }
        if (checkboard1(board,i,j)) {
        if (j+1 >= N) {
            backtracking1(board, i+1, 0);
        } else {
            backtracking1(board, i, j+1);
        }
        }
    }       
     }
     
     
     private void solveSmall(int[][] board) {
    int N = board.length;
    int[][] oldBoard = new int[N][N];
    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
        oldBoard[i][j] = board[i][j];
        }
    }
    
 
    if (N<=5) {
        backtracking1_value = Integer.MIN_VALUE;
        backtracking1_bestBoard = null;
        backtracking1(board,0,0);
        output(oldBoard, backtracking1_bestBoard);
        return ;
    }
 
    boolean hasO = false;
    for (int i=0; i<N; i++) {
        if (board[0][i] == 3 || board[0][i] == 2) {
        board[0][i] = 3;
        hasO = true;
        break;
        }
    }
    if (!hasO) {
        board[0][0] = 3;
        hasO = true;
    }
    int oCol = -1;
    for (int i=0; i<N; i++) {
        if (board[0][i]==3) {
        oCol = i;
        break;
        }
    }
    for (int i=0; i<N; i++) {
        if (i==oCol) {
        continue;
        }
        board[0][i] = 1;
    }
    
    int k1=0;
    for (int i=1; i<N; i++) {
        if (k1==oCol) {
        k1++;
        }
        board[i][k1] = 2;
        k1++;
    }
    for (int i=1; i<N-1; i++) {
        board[N-1][i] = 1;
    }
    if (oCol == N-1) {
        board[N-1][N-2] = 3;
    }
    output(oldBoard, board);
     }
 
     int output_N=0;
     
     private void output(int[][] oldBoard, int[][] board) {
    int N = board.length;
    output_N++;
    int c=0;
    for (int x=0; x<N; x++) {
        for (int y=0; y<N; y++) {
        if (oldBoard[x][y] != board[x][y]) {
            c++;
        }
        }
    }
    System.out.format("Case #%1$d: %2$d %3$d%n", output_N, boardStylePoint(board), c);
    for (int x=0; x<N; x++) {
        for (int y=0; y<N; y++) {
        if (oldBoard[x][y] != board[x][y]) {
            if (board[x][y] == 1) {
            System.out.print("+");
            } else if (board[x][y] == 2) {
            System.out.print("x");
            } else if (board[x][y] == 3) {
            System.out.print("o");
            } else {
            System.out.print("?");
            }
            System.out.format(" %1$d %2$d%n", x+1, y+1);
        }
        }
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
    int[][] testboard = { {0,2,0}, {1,1,1}, {2,0,0} };
    printboard(testboard);
    boolean result1 = checkboard1(testboard,0,1);
    System.out.println(result1);
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
