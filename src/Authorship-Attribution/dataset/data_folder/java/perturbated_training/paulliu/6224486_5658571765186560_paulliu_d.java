
 
 
 import java.util.*;
 import java.io.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     ArrayList< ArrayList < Ominoes > > ominoes;
     
     private void init() {
    int[][] dir = { {1,0}, {-1,0}, {0,-1}, {0,1} };
    ominoes = new ArrayList<ArrayList<Ominoes> > ();
    ArrayList<Ominoes> o1 = new ArrayList<Ominoes>();
    Ominoes o1_1 = new Ominoes();
    o1_1.points.add(new Point(0,0));
    o1_1.shift();
    o1.add(o1_1);
    ominoes.add(o1);
 
    for (int i=1; i<7; i++) {
        ArrayList<Ominoes> currentOminoesList = new ArrayList<Ominoes>();
        
        for (int j=0; j<ominoes.get(i-1).size(); j++) {
        Ominoes previousOminoes = ominoes.get(i-1).get(j);
        for (int k=0; k<previousOminoes.getN(); k++) {
            Point previousOminoesPoint = previousOminoes.points.get(k);
            for (int l=0; l<4; l++) {
            Point newPoint = new Point(previousOminoesPoint.x+dir[l][0], previousOminoesPoint.y+dir[l][1]);
            if (previousOminoes.points.contains(newPoint)) {
                continue;
            }
            
            Ominoes newOminoes = new Ominoes(previousOminoes);
            newOminoes.points.add(newPoint);
            newOminoes.shift();
            boolean isUniqFlag = true;
            for (Ominoes ooo : currentOminoesList) {
                if (ooo.equalsIgnoreDirection(newOminoes)) {
                isUniqFlag=false;
                break;
                }
            }
            if (isUniqFlag) {
                currentOminoesList.add(newOminoes);
            }
            
            }
        }
        }
        ominoes.add(currentOminoesList);
    }
    if (logger != null) {
        for (int i=0; i<ominoes.size(); i++) {
        logInfo("ominoes[%1$d].size() = %2$d",i,ominoes.get(i).size());
        }
        for (int i=0; i<ominoes.size(); i++) {
        for (int j=0; j<ominoes.get(i).size(); j++) {
            String s;
            s = ominoes.get(i).get(j).print1();
            System.err.println(s);
        }
        }
    }
     }
 
     
     private int input() {
    int ret=0;
    String com1;
    int T=0;
 
    if (stdin.hasNextInt()) {
        T = stdin.nextInt();
    } else {
        return ret;
    }
 
    for (int t1=0; t1<T; t1++) {
        int X=0;
        int R=0;
        int C=0;
        if (stdin.hasNextInt()) {
        X = stdin.nextInt();
        } else {
        return ret;
        }
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
        solve(X,R,C);
    }
 
    ret=0;
        return ret;
     }
 
     private void printBoard(int[][] board) {
    for (int i=0; i<board.length; i++) {
        for (int j=0; j<board[i].length; j++) {
        System.err.print(Integer.toString(board[i][j],10+26));
        }
        System.err.println();
    }
     }
     
 
     private boolean solve_R(int[][] board, int leftPieces, ArrayList<Ominoes> oms, int startUsingPiece) {
    if (leftPieces == 0) {
        
        if (logger != null) {
        logInfo("leftPieces = %1$d",leftPieces);
        printBoard(board);
        }
        return true;
    }
    for (int i=startUsingPiece; i<oms.size(); i++) {
        Ominoes o = oms.get(i);
        for (int m1=0; m1<2; m1++) {
        for (int m2=0; m2<4; m2++) {
            
            int width = o.getWidth();
            int height = o.getHeight();
            for (int y=0; y+height-1<board.length; y++) {
            for (int x=0; x+width-1<board[y].length; x++) {
                boolean canPutFlag=true;
                for (Point p : o.points) {
                if (board[p.y+y][p.x+x] != 0) {
                    canPutFlag=false;
                    break;
                }
                }
                if (canPutFlag) {
                for (Point p : o.points) {
                    board[p.y+y][p.x+x] = leftPieces;
                }
                boolean result1;
                result1 = solve_R(board, leftPieces-1, oms, i);
                if (result1) {
                    return true;
                }
                for (Point p : o.points) {
                    board[p.y+y][p.x+x] = 0;
                }
                }
            }
            }
            o.turn();
        }
        o.flip();
        }
    }
    return false;
     }
 
     private boolean solve_R(int[][] board, ArrayList<Ominoes> oms, int index) {
    Ominoes must = oms.get(index);
    int R = board.length;
    int C = board[0].length;
    int leftPieces = R*C/must.getN();
 
    Ominoes o = must;
    for (int m1=0; m1<2; m1++) {
        for (int m2=0; m2<4; m2++) {
        int width = o.getWidth();
        int height = o.getHeight();
        for (int y=0; y+height-1<board.length; y++) {
            for (int x=0; x+width-1<board[y].length; x++) {
            boolean canPutFlag=true;
            for (Point p : o.points) {
                if (board[p.y+y][p.x+x] != 0) {
                canPutFlag=false;
                break;
                }
            }
            if (canPutFlag) {
                for (Point p : o.points) {
                board[p.y+y][p.x+x] = leftPieces;
                }
                boolean result1;
                result1 = solve_R(board, leftPieces-1, oms, 0);
                if (result1) {
                return true;
                }
                for (Point p : o.points) {
                board[p.y+y][p.x+x] = 0;
                }
            }
            }
        }
        o.turn();
        }
        o.flip();
    }
    return false;
     }
     
     private void solve(int X,int R,int C) {
    if (X>=7) {
        
        output(true);
        return;
    }
    if (R*C % X != 0) {
        output(true);
        return;
    }
    if (R < X && C < X) {
        output(true);
        return;
    }
 
    int[][] board = new int[R][C];
 
    ArrayList<Ominoes> oms = ominoes.get(X-1);
    for (int i=0; i<oms.size(); i++) {
        for (int r=0; r<R; r++) {
        for (int c=0; c<C; c++) {
            board[r][c]=0;
        }
        }
        if (solve_R(board,oms,i)) {
        } else {
        if (logger != null) {
            logInfo("Cannot put %1$d-th Ominoes",i);
            System.err.println(oms.get(i).print1());
        }
        output(true);
        return;
        }
    }
    output(false);
     }
 
     private int output_N=0;
     
     private void output(boolean isRichard) {
    output_N++;
    System.out.format("Case #%1$d: %2$s%n",output_N,isRichard?"RICHARD":"GABRIEL");
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
 
 class MyMath {
     public static long sqrt(long n) {
    long right=3037000499L;
    long left=1;
    if (n>right*right) {
        return -(right+1)-1;
    }
    if (n==0) {
        return 0;
    }
    if (n==1) {
        return 1;
    }
    if (n<0) {
        return -1;
    }
    if (n/2 < right) {
        right = n/2;
    }
    while (left<=right) {
        long m = (left+right)/2;
        long m2 = m*m;
        if (m2 == n) {
        return m;
        }
        if (m2 < n) {
        left=m+1;
        } else {
        right=m-1;
        }
    }
    return -(left)-1;
     }
 }
 
 class Point implements Comparable<Point> {
     public int x;
     public int y;
 
     public Point(int X,int Y) {
    this.x = X;
    this.y = Y;
     }
 
     public Point() {
    this.x=0;
    this.y=0;
     }
 
     
     public int compareTo(Point other)     
     {
    if( x == other.x) {
        if (y > other.y) {
        return 1;
        } else if (y<other.y) {
        return -1;
        }
        return 0;
    } else {
        if (x > other.x) {
        return 1;
        } else if (x<other.x) {
        return -1;
        }
        return 0;
    }
     }
 
     public boolean equals(Object po) {
    if (!(po instanceof Point)) {
        return false;
    }
    Point p = (Point)(po);
    return (p.x==x) && (p.y==y);
     }
  
     
     public double exterior( Point p)
     {
    return x * p.y - y * p.x;
     }
 
     public Point add( Point p)
     {
    return new Point( x + p.x, y + p.y );
     }
 
     
     public Point subtract( Point p)
     {
    return new Point( x - p.x, y - p.y );
     }
 
     public double distance(Point b) {
    return Math.hypot(x-b.x, y-b.y);
     }
  
     public String toString()
     {
    StringBuffer sb = new StringBuffer();
    sb.append("Point[x=");
    sb.append(x);
    sb.append(",y=");
    sb.append(y);
    sb.append("]");
    return sb.toString();
     }
 }
 
 class Ominoes {
     public ArrayList<Point> points;
 
     public Ominoes() {
    points = new ArrayList<Point>();
     }
 
     public Ominoes(Ominoes o) {
    points = new ArrayList<Point>(o.points.size());
    for (int i=0; i<o.points.size(); i++) {
        Point p1 = o.points.get(i);
        points.add(new Point(p1.x, p1.y));
    }
     }
 
     public int getN() {
    return points.size();
     }
 
     public void shift() {
    int minX=Integer.MAX_VALUE;
    int minY=Integer.MAX_VALUE;
    for (Point p : points) {
        if (p.x < minX) {
        minX = p.x;
        }
        if (p.y < minY) {
        minY = p.y;
        }
    }
    for (int i=0; i<points.size(); i++) {
        Point p = points.get(i);
        Point np = new Point(p.x-minX, p.y-minY);
        points.set(i,np);
    }
    Collections.sort(points);
     }
     
     public void flip() {
    shift();
    for (int i=0; i<points.size(); i++) {
        Point p = points.get(i);
        Point np = new Point(-p.x, p.y);
        points.set(i,np);
    }
    shift();
     }
 
     public void turn() {
    shift();
    for (int i=0; i<points.size(); i++) {
        Point p = points.get(i);
        Point np = new Point(-p.y, p.x);
        points.set(i,np);
    }
    shift();
     }
 
     public boolean equals(Ominoes o) {
    if (o.getN() != getN()) {
        return false;
    }
    for (int i=0; i<points.size(); i++) {
        Point p1 = points.get(i);
        Point p2 = o.points.get(i);
        if (p1.compareTo(p2) != 0) {
        return false;
        }
    }
    return true;
     }
 
     public boolean equalsIgnoreDirection(Ominoes o) {
    shift();
    boolean ret=false;
    for (int i=0; i<2; i++) {
        for (int j=0; j<4; j++) {
        if (!ret && this.equals(o)) {
            ret = true;
        }
        this.turn();
        }
        this.flip();
    }
    return ret;
     }
 
     public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("Ominoes[points=[");
    for (int i=0; i<points.size(); i++) {
        if (i>0) {
        sb.append(",");
        }
        sb.append(points.get(i).toString());
    }
    sb.append("]]");
    return sb.toString();
     }
 
     public int getWidth() {
    int maxX=0;
    int maxY=0;
    for (Point p : points) {
        if (p.x > maxX) {
        maxX = p.x;
        }
        if (p.y > maxY) {
        maxY = p.y;
        }
    }
    return maxX+1;
     }
     public int getHeight() {
    int maxX=0;
    int maxY=0;
    for (Point p : points) {
        if (p.x > maxX) {
        maxX = p.x;
        }
        if (p.y > maxY) {
        maxY = p.y;
        }
    }
    return maxY+1;
     }
 
     public String print1() {
    shift();
    int maxX=0;
    int maxY=0;
    for (Point p : points) {
        if (p.x > maxX) {
        maxX = p.x;
        }
        if (p.y > maxY) {
        maxY = p.y;
        }
    }
    StringBuffer sb = new StringBuffer();
    for (int i=0; i<=maxY; i++) {
        for (int j=0; j<=maxX; j++) {
        Point p1 = new Point(j,i);
        if (points.contains(p1)) {
            sb.append("#");
        } else {
            sb.append(" ");
        }
        }
        sb.append(String.format("%n"));
    }
    return sb.toString();
     }
     
 }
