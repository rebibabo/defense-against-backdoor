import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayDeque;
 import java.util.Arrays;
 import java.util.Deque;
 import java.util.Scanner;
 
 
 public class C {
    static double EPS = 1e-9;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C.txt"));
        PrintWriter out = new PrintWriter(new File("C.out"));
        int cases = sc.nextInt();
        for(int t=1;t<=cases;t++){
            int N = sc.nextInt();
            int[] X = new int[N];
            int[] Y = new int[N];
            for(int a=0;a<N;a++){
                X[a]=sc.nextInt();
                Y[a]=sc.nextInt();
            }
            String answer = String.format("Case #%d:", t);
            System.out.println(answer);
            out.println(answer);
            for(int aa=0;aa<N;aa++){
                int ans = N-1;
                for(int mask = 0;mask<1<<N;mask++){
                    if(((mask>>aa)&1)==0)continue;
                    Point[] P = new Point[Integer.bitCount(mask)];
                    int i = 0;
                    for(int a=0;a<N;a++){
                        if(((mask>>a)&1)==1){
                            P[i++]=new Point(X[a],Y[a]);
                        }
                    }
                    if(i<=2){
                        ans = Math.min(ans,N-Integer.bitCount(mask));
                        continue;
                    }
                    Point[] RES = hull2(P);
                    for(Point p : RES){
                        if(p.x==X[aa]&&p.y==Y[aa]){
                            ans = Math.min(ans,N-Integer.bitCount(mask));
                        }
                    }
                }
                
                
                System.out.println(ans);
                out.println(ans);
            }
            
            
        }
        out.close();
    }
    
    static class Point implements Comparable<Point>{
        double x, y;
        Point(double xx, double yy){
            x=xx;
            y=yy;
        }
        public Point sub(Point o) {
            return new Point(this.x-o.x,this.y-o.y);
        }
        public double cross(Point p) {
            return x*p.y-p.x*y;
        }
        @Override
        public int compareTo(Point o) {
            if(this.x==o.x)return (int)Math.signum(this.y-o.y);
            return (int)Math.signum(this.x-o.x);
            
        }
        
        
    }
 
    private static double dist(double x1, double y1, double x2, double y2) {
        
        double cx = x1 - x2;
        double cy = y1 - y2;
        cx *= cx;
        cy *= cy;
        return Math.sqrt(cx + cy);
    }
 
    public static Point[] hull2(Point[] p){
        int n = p.length; Arrays.sort(p);
        Deque<Point> b=  new ArrayDeque<Point>(), u = new ArrayDeque<Point>();
        for(int i=0;i<n;b.add(p[i++]))
            while(b.size()>1&&rightTurn(p[i],b));
        for(int i=n-1;i>=0;u.add(p[i--]))
            while(u.size()>1&&rightTurn(p[i],u));
        u.removeFirst(); u.removeLast(); b.addAll(u);
        return b.toArray(new Point[0]);
    }
 
    private static boolean rightTurn(Point p, Deque<Point> h) {
        Point r = h.removeLast();
        return (p.sub(r).cross(r.sub(h.getLast()))>EPS)?true:!h.add(r);
    }
 
 
 }
