
 
 
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
        int N=0;
        if (stdin.hasNextInt()) {
        N = stdin.nextInt();
        } else {
        return ret;
        }
        ArrayList<Point> points = new ArrayList<Point>();
        for (int n1=0; n1<N; n1++) {
        double x;
        double y;
        if (stdin.hasNextDouble()) {
            x = stdin.nextDouble();
        } else {
            return ret;
        }
        if (stdin.hasNextDouble()) {
            y = stdin.nextDouble();
        } else {
            return ret;
        }
        Point p1 = new Point(x,y);
        points.add(p1);
        }
        
        solve(points);
    }
    ret=0;
        return ret;
     }
 
     class PolarComparator implements Comparator<Point> {
    Point polar = null;
    
    public PolarComparator(Point polar) {
        this.polar = polar;
    }
    
    public int compare(Point p1, Point p2) {
        if (p1.compareTo(p2)==0) {
        return 0;
        }
        if (p1.compareTo(polar)==0) {
        return -1;
        }
        if (p2.compareTo(polar)==0) {
        return 1;
        }
        Point v1 = p1.subtract(polar);
        Point v2 = p2.subtract(polar);
        
        double e = v1.exterior(v2);
        if (e>0){
        return -1;
        } else if (e<0) {
        return 1;
        }
        if (e==0) {
        double d1 = (v1.x*v1.x+v1.y*v1.y);
        double d2 = (v2.x*v2.x+v2.y*v2.y);
        if ( d1 > d2 ) {
            return 1;
        } else if (d1 < d2) {
            return -1;
        }
        }
        return 0;
    }
     }
     
     
     private ArrayList<Point> GrahamScan(ArrayList<Point> pointsO) {
    ArrayList<Point> points = new ArrayList<Point>(pointsO);
    
    Point buttomP = null;
    if (points.size() >= 1) {
        buttomP = points.get(0);
    }
    for (int i=1; i<points.size(); i++) {
        Point pi = points.get(i);
        if (pi.y < buttomP.y || (pi.y==buttomP.y && pi.x < buttomP.x)) {
        buttomP = pi;
        }
    }
    
    PolarComparator polarComparator = null;
    if (buttomP != null) {
        polarComparator = new PolarComparator(buttomP);
    }
 
    if (polarComparator != null) {
        Collections.sort(points, polarComparator);
    }
 
    ArrayList<Point> convexHull = new ArrayList<Point>();
    for (int i=0; i<points.size() && i<2; i++) {
        convexHull.add(points.get(i));
    }
    for (int i=2; i<points.size(); i++) {
        Point pi = points.get(i);
        while (convexHull.size()>=2 && pi.subtract(convexHull.get(convexHull.size()-1)).exterior(pi.subtract(convexHull.get(convexHull.size()-2))) >= 0 ) {
        convexHull.remove(convexHull.size()-1);
        }
             convexHull.add(pi);
    }
    return convexHull;
     }
 
     private int check1(ArrayList<Point> points, int n) {
    ArrayList<Point> hull = GrahamScan(points);
    for (int i=0; i<hull.size(); i++) {
        int j=(i+1)%hull.size();
        Line edge = new Line(hull.get(i), hull.get(j));
        if (edge.isOnSegment(points.get(n))) {
        return 0;
        }
    }
    return -1;
     }
 
     private int check1(ArrayList<Point> points, Point p) {
    ArrayList<Point> hull = GrahamScan(points);
    for (int i=0; i<hull.size(); i++) {
        int j=(i+1)%hull.size();
        Line edge = new Line(hull.get(i), hull.get(j));
        if (edge.isOnSegment(p)) {
        return 0;
        }
    }
    return -1;
     }
 
     private int checkn(ArrayList<Point> points, int k, int c) {
    int[] deleteTable = new int[k];
    for (int i=0; i<k; i++) {
        deleteTable[i]=-1;
    }
    for (int i=0; i>=0 ; ) {
        if (i==0) {
        deleteTable[i]++;
        } else if (i>0) {
        if (deleteTable[i]==-1) {
            deleteTable[i] = deleteTable[i-1]+1;;
        } else {
            deleteTable[i]++;
        }
        }
        if (deleteTable[i] >= points.size()) {
        deleteTable[i]=-1;
        i--;
        continue;
        }
        i++;
        if (i>=k) {
        
        boolean containCFlag=false;
        for (int m=0; m<k; m++) {
            if (deleteTable[m] == c) {
            containCFlag=true;
            break;
            }
        }
        if (!containCFlag) {
            ArrayList<Point> newPoints = new ArrayList<Point>();
            for (int nn=0; nn<points.size(); nn++) {
            if (Arrays.binarySearch(deleteTable,nn)>=0) {
                continue;
            }
            newPoints.add(points.get(nn));
            }
            if (check1(newPoints,points.get(c))==0) {
            return k;
            }
        }
        i--;
        }
    }
    return -1;
     }
     
     private void solve(ArrayList<Point> points) {
    int[] ans = new int[points.size()];
    for (int i=0; i<ans.length; i++) {
        ans[i] = check1(points, i);
    }
    for (int i=0; i<ans.length; i++) {
        if (ans[i] != -1) {
        continue;
        }
        for (int k=1; k<=points.size(); k++) {
        int result1 = checkn(points,k,i);
        if (result1 != -1) {
            ans[i]=result1;
            break;
        }
        }
    }
    output(ans);
     }
 
     private int output_N=0;
     
     private void output(int[] ans) {
    output_N++;
    System.out.format("Case #%1$d:%n",output_N);
    for (int i=0; i<ans.length; i++) {
        System.out.println(ans[i]);
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
 class FixDoubleComparator implements java.util.Comparator<Double> {
     private double e = 1e-9;
     
     public FixDoubleComparator() {
     }
     public FixDoubleComparator(double e) {
    this.e = e;
     }
     
     public int compare(Double a, Double b) {
    return compare(a.doubleValue(), b.doubleValue());
     }
 
     public int compare(double a, double b) {
    if (Math.abs(a-b) < e) {
        return 0;
    }
    if (a<b) {
        return -1;
    }
    return 1;
     }
 
     public boolean equals(Object obj) {
    if ( ! (obj instanceof FixDoubleComparator) ) {
        return false;
    }
    FixDoubleComparator b = (FixDoubleComparator) obj;
    if (b.e==this.e) {
        return true;
    }
    return false;
     }
 
     private static FixDoubleComparator instance = null;
     public static FixDoubleComparator getInstance() {
    if (instance == null) {
        instance = new FixDoubleComparator();
    }
    return instance;
     }
 }
 
 
 class Point implements Comparable<Point> {
     public double x;
     public double y;
 
     public Point(double X,double Y) {
    this.x = X;
    this.y = Y;
     }
 
     public Point() {
    this.x=0.0;
    this.y=0.0;
     }
 
     public Point (Point o) {
    this.x = o.x;
    this.y = o.y;
     }
 
     
     public int compareTo(Point other) {
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
 
     public int compareTo(Point other, FixDoubleComparator comparator) {
    if( comparator.compare(x,other.x)==0) {
        if ( comparator.compare(y,other.y)>0) {
        return 1;
        } else if ( comparator.compare(y,other.y)<0) {
        return -1;
        }
        return 0;
    } else {
        if (comparator.compare(x, other.x)>0) {
        return 1;
        } else if (comparator.compare(x,other.x)<0) {
        return -1;
        }
        return 0;
    }
     }
 
     
     public boolean equals(Object bO) {
    if (!(bO instanceof Point)) {
        return false;
    }
    Point b = (Point)(bO);
    if (this==b) {
        return true;
    }
    if (this.compareTo(b) == 0) {
        return true;
    }
    return false;
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
 
     public Point multiply(double f) {
    return new Point (x*f, y*f);
     }
 
     public double distance(Point b) {
    return Math.hypot(x-b.x, y-b.y);
     }
 
     public double dot(Point b) {
    return this.x*b.x + this.y*b.y;
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
 
     
     public static double turn(Point p, Point q, Point r) {
    double result = (r.x-q.x) * (p.y-q.y) - (r.y-q.y) * (p.x-q.x);
    return result;
     }
 }
 
 class Line {
     public Point p1;
     public Point p2;
     public Line (Point p1, Point p2) {
    this.p1 = p1;
    this.p2 = p2;
     }
     public double length() {
    double len;
    len = Math.hypot((p1.x-p2.x),(p1.y-p2.y));
    return len;
     }
 
     public boolean isIntersect(Line line2) {
    Line line1 = this;
    double r1;
    double r2;
    double r3;
    double r4;
    r1 = Point.turn(line1.p1, line1.p2, line2.p1);
    r2 = Point.turn(line1.p1, line1.p2, line2.p2);
    r3 = Point.turn(line2.p1, line2.p2, line1.p1);
    r4 = Point.turn(line2.p1, line2.p2, line1.p2);
    if (line1.isOnSegment(line2.p1) || line1.isOnSegment(line2.p2)
        || line2.isOnSegment(line1.p1) || line2.isOnSegment(line1.p2)) {
        return true;
    }
    if (r1*r2 < 0.0 && r3*r4 < 0.0) {
        return true;
    }
    return false;
     }
 
     public boolean isIntersect(Line line2, FixDoubleComparator comparator) {
    Line line1 = this;
    double r1;
    double r2;
    double r3;
    double r4;
    r1 = Point.turn(line1.p1, line1.p2, line2.p1);
    r2 = Point.turn(line1.p1, line1.p2, line2.p2);
    r3 = Point.turn(line2.p1, line2.p2, line1.p1);
    r4 = Point.turn(line2.p1, line2.p2, line1.p2);
    if (line1.isOnSegment(line2.p1,comparator) || line1.isOnSegment(line2.p2,comparator)
        || line2.isOnSegment(line1.p1,comparator) || line2.isOnSegment(line1.p2,comparator)) {
        return true;
    }
    if (comparator.compare(r1*r2, 0.0)<0 && comparator.compare(r3*r4,0.0)<0) {
        return true;
    }
    return false;
     }
 
     
     public Point getIntersectPoint(Line b) {
    Line a = this;
    Point u = a.p1.subtract(b.p1);
    Point bVector = b.p2.subtract(b.p1);
    Point aVector = a.p2.subtract(a.p1);
    double t = bVector.exterior(u) / aVector.exterior(bVector);
    Point r1 = new Point(aVector.x*t, aVector.y*t);
    Point ans = a.p1.add(r1);
    return ans;
     }
 
     public boolean isBetween(Point p) {
    Point vP1P = p.subtract(p1);
    Point vP1P2 = p2.subtract(p1);
    Point vP2P = p.subtract(p2);
    Point vP2P1 = p1.subtract(p2);
    return (vP1P2.dot(vP1P) >= 0.0) && (vP2P1.dot(vP2P) >= 0.0);
     }
 
     public boolean isBetween(Point p, FixDoubleComparator comparator) {
    Point vP1P = p.subtract(p1);
    Point vP1P2 = p2.subtract(p1);
    Point vP2P = p.subtract(p2);
    Point vP2P1 = p1.subtract(p2);
    return (comparator.compare(vP1P2.dot(vP1P), 0.0) >= 0) && (comparator.compare(vP2P1.dot(vP2P),0.0) >= 0);
     }
 
     public boolean isOnSegment(Point p) {
    return isBetween(p) && Point.turn(p1,p,p2)==0.0;
     }
 
     public boolean isOnSegment(Point p, FixDoubleComparator comparator) {
    return isBetween(p,comparator) && (comparator.compare(Point.turn(p1,p,p2),0.0)==0);
     }
 }
 
 class Polygon {
     
     private java.util.ArrayList<Point> vertices;
 
     public Polygon() {
    vertices = new java.util.ArrayList<Point>();
     }
 
     public Polygon(Polygon p) {
    vertices = new java.util.ArrayList<Point>(p.size());
    for (Point p1 : p.vertices) {
        vertices.add(new Point(p1));
    }
     }
 
     
     public void add(Point p) {
    vertices.add(p);
     }
 
     public int size() {
    return vertices.size();
     }
 
     public java.util.ArrayList<Point> getVertices() {
    return vertices;
     }
 
     
     public boolean isInside(Point p) {
    boolean ret = false;
    for (int i=0; i<vertices.size(); i++) {
        int j = (i+1)%vertices.size();
        Line edge = new Line(vertices.get(i), vertices.get(j));
        if (edge.isOnSegment(p)) {
        return true;
        }
        if ((edge.p1.y < p.y && edge.p2.y >= p.y)
        || (edge.p2.y < p.y && edge.p1.y >= p.y)) {
        if (edge.p1.x + (p.y-edge.p1.y)/(edge.p2.y-edge.p1.y)*(edge.p2.x-edge.p1.x) < p.x) {
            ret = !ret;
        }
        }
    }
    return ret;
     }
 
     public boolean isInside(Point p, FixDoubleComparator comparator) {
    boolean ret = false;
    for (int i=0; i<vertices.size(); i++) {
        int j = (i+1)%vertices.size();
        Line edge = new Line(vertices.get(i), vertices.get(j));
        if (edge.isOnSegment(p,comparator)) {
        return true;
        }
        if ((comparator.compare(edge.p1.y, p.y)<0 && comparator.compare(edge.p2.y, p.y)>=0)
        || (comparator.compare(edge.p2.y,p.y)<0 && comparator.compare(edge.p1.y,p.y)>=0)) {
        if (comparator.compare(edge.p1.x + (p.y-edge.p1.y)/(edge.p2.y-edge.p1.y)*(edge.p2.x-edge.p1.x), p.x)<0) {
            ret = !ret;
        }
        }
    }
    return ret;
     }
     
 }
