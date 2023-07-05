
 
 import java.awt.geom.Line2D;
 import java.io.*;
 import java.util.*;
 
 import static java.lang.Math.*;
 import static java.lang.Integer.*;
 
 public class CS {
    static Scanner sc = null;
    static BufferedReader br = null;
    static PrintWriter out = null;
    static PrintStream sysout = System.out;
    static Random rnd = new Random();
    
    int INF = Integer.MAX_VALUE / 10;
    double DF = 0.0000000001;
    
    long b = 1;
    int N = 0;
    int M = 0;
    
 
    
    public void solve() throws Exception{
        String s = br.readLine();
        N = Integer.parseInt(s);
 
        println("");
        int[][] ps = new int[N][2];
        for(int i =0; i < N; i++){
            s = br.readLine();
            String[] sp = s.split(" ");
            ps[i][0] = Integer.parseInt(sp[0]);
            ps[i][1] = Integer.parseInt(sp[1]);
        }
        
        if(N == 1){
            println(0);
            return;
        }
        else if(N == 2){
            println(0);
            println(0);
            return;
        }
        
        for(int i = 0; i < N; i++){
            int ans = N;
            
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    if(j == i || k == i || j == k){
                        continue;
                    }
                    P a = new P(ps[j][0] - ps[i][0], ps[j][1] - ps[i][1]);
                    P b = new P(ps[k][0] - ps[i][0], ps[k][1] - ps[i][1]);
                    double kaku = angle(a, b);
                    
                    double kakudo = rtod(kaku);
                    if(kakudo - EPS > 180){
                        continue;
                    }
                    int cnt = 0;
                    for(int l = 0; l < N; l++){
                        if(l == i || l == j || l == k) continue;
                        P c = new P(ps[l][0] - ps[i][0], ps[l][1] - ps[i][1]);
                        double kakuc = angle(a, c);
                        
                        double kakudoc = rtod(kakuc);
                        if(kakudoc - EPS > kakudo){
                            cnt++;
                        }
                    }
                    ans = Math.min(cnt, ans);
                }
            }
            println(ans);
            
        }
    }
    
    
    public static void main(String[] args) throws Exception{
        File file = new File("C-small-attempt1.in");
        if(file.exists()){
            System.setIn(new BufferedInputStream(new FileInputStream(file)));
        }
        else{
            throw new Exception("can't find a input file : " + file.getAbsolutePath());
        }
        
        br = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fw = new FileWriter(new File("output.txt"));
        out = new PrintWriter(fw);
        
        CS b = new CS();
        int T = 0;
        if(sc != null){
            T = sc.nextInt();
        }
        else{
            T = parseInt(br.readLine());
        }
        int t = 1;
        while(t <= T){
            out.print("Case #" + t + ":");
            System.out.print("Case #" + t + ": ");
            if(t == 15){
                System.out.print("");
            }
            b.solve();
            t++;
        }
        out.close();
        fw.close();
    }
    
    void print(int i){
        out.print(i + "");
        System.out.print(i);
    }
    void println(int i){
        out.println(i + "");
        System.out.println(i);
    }
    void print(String s){
        out.print(s);
        System.out.print(s);
    }
    void println(String s){
        out.println(s);
        System.out.println(s);
    }
    void print(long i){
        out.print(i + "");
        System.out.print(i);
    }
    void println(long i){
        out.println(i + "");
        System.out.println(i);
    }
    
    
 
    final double EPS = 0.00000000001;
    
    
    public class P{
        public double x = 0;
        public double y = 0;
        public P(){}
        public P(double x, double y){
            this.x = x;
            this.y = y;
        }
        public P(P p){
            x = p.x;
            y = p.y;
        }
        
        public P add(P p){
            P np = new P(this);
            np.x += p.x;
            np.y += p.y;
            return np;
        }
        public P subtract(P p){
            P np = new P(this);
            np.x -= p.x;
            np.y -= p.y;
            return np;
        }
        public P mul(P p){
            P np = new P();
            np.x = x * p.x - y * p.y;
            np.y = x * p.y + y * p.x;
            return np;
        }
        
        public P divide(P z){
            double norm_z = norm(z);
            double re_tmp = (x * z.x + y * z.y) / norm_z;
            double im_tmp = (y * z.x - x * z.y) / norm_z;
            P p = new P(re_tmp, im_tmp);
            return p;
        }
 
        public P add(double d){
            P np = new P(this);
            np.x += d;
            np.y += d;
            return np;
        }
        public P mul(double d){
            P np = new P(this);
            np.x *= d;
            np.y *= d;
            return np;
        }
        public P divide(double d){
            P np = new P(this);
            np.x /= d;
            np.y /= d;
            return np;
        }
    }
    
    public double real(P p){
        return p.x;
    }
    public double imag(P p){
        return p.y;
    }
    
    
    public class L {
        public P a = null;
        public P b = null;
        
        public L(P a, P b){
            this.a = a;
            this.b = b;
        }
        public L(double x1, double y1, double x2, double y2){
            a = new P(x1, y1);
            b = new P(x2, y2);
        }
    }
    
    class C{
        P p;
        double r = 0;
        
        public C(P p, double r){
            this.p = p;
            this.r = r;
        }
        
        public C(double x, double y, double r){
            p = new P(x, y);
            this.r = r;
        }
        
        boolean contain(C c){
            if(dist(this, c) + c.r + EPS <= r){
                return true;
            }
            return false;
        }
        
        boolean contain(P p){
            if(dist(p, this.p) + EPS <= r ){
                return true;
            }
            return false;
        }
        
        boolean contain(double x, double y){
            if(dist(x, y, p.x, p.y) + EPS <= r ){
                return true;
            }
            return false;
        }
        
        
 
    }
    
    
    List<P> cc_cross(C c1, C c2) {
        List<P> res = new ArrayList<P>();
        double d = dist(c1.p, c2.p);
        if(d > c1.r + c2.r + EPS){
            return res;
        }
        
        boolean cf = true;
        if(Math.abs(d - (c1.r + c2.r)) <= EPS){
            cf = false;
        }
        
        double rc = (d*d + c1.r*c1.r - c2.r*c2.r) / (2*d);
        double rs = sqrt(c1.r*c1.r - rc*rc);
     
        P diff = vectr(c1.p, c2.p).divide(d);
 
        res.add(c1.p.add(diff.mul(new P(rc, rs))));
        if(cf) res.add(c1.p.add(diff.mul(new P(rc, -rs))));
        return res;
    }
    
    
    List<P> cl_cross(C c, L l) {
          P h = projection(l, c.p);
          double d = dist(h,  c.p);
          List<P> res = new ArrayList<P>();
          if(d < c.r - EPS){
            P x = vectr(l.a, l.b).divide(dist(l.a, l.b)).mul(sqrt(c.r*c.r - d*d));
            res.add(h.add(x));
            res.add(h.subtract(x));
          }else if(d < c.r + EPS){
            res.add(h);
          }
          return res;
    }
    
    
    
    public class G {
        List<P> ps = new ArrayList<P>();
        public int size(){
            return ps.size();
        }
        public P get(int i){
            return ps.get(i);
        }
        public void add(double x, double y){
            P p = new P(x, y);
            ps.add(p);
        }
        public void add(P p){
            ps.add(p);
        }
        public void addNew(P p){
            P np = new P(p);
            ps.add(np);
        }
    }
    
    public P prev(G g, int i){
        if(i == 0) i = g.size()-1;
        else i--;
        return g.get(i);
    }
    
    public P curr(G g, int i){
        i %= g.size();
        return g.get(i);
    }
    
    public P next(G g, int i){
        i++;
        return curr(g, i);
    }
    
    
    
    
    public double marume(double d){
        if(Math.abs(d) < EPS){
            return 0;
        }
        return d;
    }
    
    
    
    public double dot(P a, P b){
        return a.x * b.x + a.y * b.y;
    }
    
    
    public double cross(P a, P b){
        return a.x * b.y - b.x * a.y;
    }
    
    
    public P vectr(P a, P b){
        P p = new P();
        p.x = b.x - a.x;
        p.y = b.y - a.y;
        return p;
    }
    
    
    public double dist(P p){
        return Math.sqrt(p.x * p.x + p.y * p.y);
    }
    
    double dist(C c1, C c2){
        double dx = c1.p.x - c2.p.x;
        double dy = c1.p.y - c2.p.y;
        return sqrt(dx * dx + dy * dy);
    }
    
    public double norm(P p){
        return p.x * p.x + p.y * p.y;
    }
    
    
    public double abs(P p){
        return dist(p);
    }
    
    
    public double dist(P a, P b){
        return dist(a.x, a.y, b.x, b.y);
    }
    
    
    public double dist(double x1, double y1, double x2, double y2){
        double x = x2 - x1;
        double y = y2 - y1;
        return Math.sqrt(x * x + y * y);
    }
    
    public P projection(L l, P p) {
          double t = dot(p.subtract(l.a), l.a.subtract(l.b)) / norm(l.a.subtract(l.b));
          P r = l.a.subtract(l.b);
          r.x *= t;
          r.y *= t;
          return l.a.add((r));
    }
    public P reflection(L l, P p) {
        P t = new P(2, 0);
        P q = projection(l, p).subtract(p);
        t = t.mul(q);
      return p.add(t);
    }
    
    public boolean intersectLL(L l, L m) {
          return Math.abs(cross(l.b.subtract(l.a), m.b.subtract(m.a))) > EPS || 
                  Math.abs(cross(l.b.subtract(l.a), m.a.subtract(l.a))) < EPS;   
    }
    
    public boolean intersectLS(L l, L s) {
      return cross(l.b.subtract(l.a), s.a.subtract(l.a))*       
             cross(l.b.subtract(l.a), s.b.subtract(l.a)) < EPS; 
    }
    
    
    public boolean intersectLP(L l, P p) {
      return Math.abs(cross(l.b.subtract(p), l.a.subtract(p))) < EPS;
    }
        
        
    public boolean intersectSS(L s, L t) {
      return ccw(s.a,s.b,t.a)*ccw(s.a,s.b,t.b) <= 0 &&
             ccw(t.a,t.b,s.a)*ccw(t.a,t.b,s.b) <= 0;
    }
        
    public boolean intersectSS2(L s, L t) { 
        if (ccw(s.a, s.b, t.a) == 0) {
              int c = ccw(s.a,s.b,t.b);
              if (s.a == t.a) {
                if (c!=-2&&c > 0) return false;
              } else if (s.b == t.a) {
                if (c!=2&&c > 0) return false;
              } else if (Math.abs(c)==1) return false;
        }
        if (ccw(s.a, s.b, t.a) == 0) {
              int c = ccw(s.a,s.b,t.a);
              if (s.a == t.b) {
                if (c!=-2&& c > 0) return false;
              } else if (s.b == t.b) {
                if (c!=2&&c > 0) return false;
              } else if (Math.abs(c)==1) return false;
        }
      return ccw(s.a,s.b,t.a)*ccw(s.a,s.b,t.b) <= 0 &&
             ccw(t.a,t.b,s.a)*ccw(t.a,t.b,s.b) <= 0;
    }
    
    public boolean isPara(L s, L t){
        double a1 = angle(s) % Math.PI;
        double a2 = angle(t) % Math.PI;
        if(Math.abs(a1-a2) < EPS) return true;
        return false;
    }
        
        
    public boolean intersectSP(L s, P p) {
      return abs(s.a.subtract(p)) + abs(s.b.subtract(p)) - abs(s.b.subtract(s.a)) < EPS; 
    }
    
    
    
    
    
    int ccw(P a, P b, P c) {
        double cp = crossProduct(a, b, c);
        if(cp != 0){
            if(cp > 0) return 1; 
            else return -1;
        }
        
        b = vectr(a, b);
        c = vectr(a, c);
 
        if (dot(b, c) < 0)     return +2;       
        if (norm(b) < norm(c)) return -2;       
          return 0;                    
    }
    
    
    public double crossProduct(P p1, P p2, P p3){
        double ret = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
        if(Math.abs(ret) < EPS){
            ret = 0;
        }
        return ret;
    }
    
    
    public double crossProduct(double[] p1, double[] p2, double[]p3){
        double ret = (p2[0] - p1[0]) * (p3[1] - p1[1]) - (p2[1] - p1[1]) * (p3[0] - p1[0]);
        
        if(Math.abs(ret) < EPS){
            ret = 0;
        }
        return ret;
    }
    
    
    public double area(P p1, P p2, P p3){
        return menseki3Hen(dist(p1, p2), dist(p1, p3), dist(p2, p3));
    }
    
    public double menseki3Hen(double a, double b, double c){
        double s = (a + b + c) /2;
        s = s * (s-a)*(s-b)*(s-c);
        return sqrt(s);
    }
    
    
    public double area(G g){
        List<P> ps = g.ps;
        int n = ps.size();
        double s = 0;
        for(int i = 0; i < n -1; i++){
            P p1 = ps.get(i);
            P p2 = ps.get(i+1);
            s +=  (p1.x*p2.y - p2.x * p1.y ) / 2;
        }
        P p1 = ps.get(n-1);
        P p2 = ps.get(0);
        s +=  (p1.x*p2.y - p2.x * p1.y ) / 2;
        
        return Math.abs(s);
    }
    
    
    double cc_area(C c1, C c2) {
        double d = dist(c1, c2);
        if (c1.r + c2.r <= d + EPS) {
            return 0.0;
        } else if (d <= Math.abs(c1.r - c2.r) + EPS) {
            double r = min(c1.r, c2.r);
            return r * r * PI;
        } else {
            double rc = (d*d + c1.r*c1.r - c2.r*c2.r) / (2*d);
            double theta = acos(rc / c1.r);
            double phi = acos((d - rc) / c2.r);
            return c1.r*c1.r*theta + c2.r*c2.r*phi - d*c1.r*sin(theta);
        }
    } 
    
    
    public G convex_cut(G g, L l) {
          G Q = new G();
          List<P> ps = g.ps;
          for(int i = 0; i < g.ps.size(); i++) {
            P A = ps.get(i);
            P B = i == ps.size() -1 ? ps.get(0) : ps.get(i+1);
            if (ccw(l.a, l.b, A) != -1) Q.add(A);
            if (ccw(l.a, l.b, A)*ccw(l.a, l.b, B) < 0)
              Q.ps.add(crosspoint(new L(A, B), l));
          }
          return Q;
    }
    
    
    public L bisector(P a, P b) {
        P p = new P(0.5, 0);
        P A = (a.add(b)).mul(p);
        P B = A.add(b.subtract(a).mul(new P(0, Math.PI/2)));
        return new L(A, B);
    }
    
    
    public P crosspoint(L l, L m) {
          double A = cross(vectr(l.a, l.b), vectr(m.a, m.b));
          double B = cross(vectr(l.a, l.b), vectr(m.a, l.b));
          if (marume(A) == 0 && marume(B) == 0) return m.a; 
          if (marume(A) == 0) return null; 
          P vm = vectr(m.a, m.b);
          P p = new P(m.a);
          p.x += B/A * vm.x;
          p.y += B/A * vm.y;
          return p;
    }
    
    
    public double arg(P p){
        return Math.atan2(p.y, p.x);
    }
 
    
    public double angle(L l){
        P p = l.b.subtract(l.a);
        return angle(p);
    }
    
    public double angle(P p){
        double s = Math.acos(Math.abs(p.x)/Math.sqrt(p.x*p.x+p.y*p.y));
        if(p.x >= 0 && p.y >= 0){
            return s;
        }
        else if(p.x < 0 && p.y >= 0){
            return Math.PI - s;
        }
        else if(p.x < 0 && p.y < 0){
            return Math.PI + s;
        }
        else{
            return Math.PI * 2 - s;
        }
    }
    
    
    public G voronoi_cell(G g, List<P> v, int s) {
      for(int i = 0; i < v.size(); i++)
        if (i!=s)
          g = convex_cut(g, bisector(v.get(s), v.get(i)));
      return g;
    }
    
    
    
     
    public double angle(P a, P b) {       
      double ret = angle(b)-angle(a);
      return (ret>=0) ? ret : ret + 2 * Math.PI;
    }
    
    
    double angle2(P a, P b) { 
      return Math.min(angle(a,b), angle(b,a));
    }
    
    
    double rtod(double rad) {       
      return Math.toDegrees(rad);
    }
    
    
    double dtor(double deg) {       
      return Math.toRadians(deg);
    }
    
    
    P rotate(P p, double angrad) {
        P k = new P(Math.cos(angrad), Math.sin(angrad));
      return p.mul(k);
    }
    
    
    L rotate(L l, double ang) {
      return new L(rotate(l.a, ang), rotate(l.b, ang));
    }
    
    
    G shrink_polygon(G g, double len) {
          G res = new G();
          for (int i = 0; i < g.size(); ++i) {
            P a = prev(g,i), b = curr(g,i), c = next(g,i);
            P u = (b.subtract(a)).divide(abs(b.subtract(a)));
            double th = arg((c.subtract(b)).divide(u)) * 0.5;
            P t = new P(-Math.sin(th), Math.cos(th));
            res.add( b.add(u.mul(t).mul(len).divide(Math.cos(th))) );
          }
          return res;
    }
 
 
    
    
    public int contains(G g, P p) {
          boolean in = false;
          for (int i = 0; i < g.size(); ++i) {
            P a = curr(g,i).subtract(p);
            P b = next(g,i).subtract(p);
            if (imag(a) > imag(b)){
                P tmp = a;
                a = b;
                b = tmp;
            }
            if (imag(a) <= 0 && 0 < imag(b))
              if (cross(a, b) < 0) in = !in;
            if (cross(a, b) == 0 && dot(a, b) <= 0) return 1;
          }
          return in ? 2 : 0;
    }
    
    
    public int convex_contains(G g, P p){
        return contains(g, p);
    }
 
    
    public List<P> convec_hull(P[] ps){
        List<P> list = new ArrayList<P>();
        int n = ps.length;
        Arrays.sort(ps, new Comparator<P>() {
            public int compare(P o1, P o2) {
                if(o1.x == o2.x){
                    if(o1.y > o2.y) return -1;
                    else if(o1.y < o2.y)return 1; 
                    return 0;
                }
                if(o1.x < o2.x)return -1;   
                return 1;
            }
        });
        list.add(ps[0]);
        for(int i = 1; i < n; i++){
            P p = ps[i];
 
            int k = list.size()-2;
 
            while(k >= 0 && ccw(list.get(k), p, list.get(k+1)) < 0){
                list.remove(k+1);
                k--;
            }
                
            if(i != n-1) list.add(p);
        }
        list.add(ps[n-1]);
        int t = list.size()-1;
        for(int i = n-2; i >= 0; i--){
            P p = ps[i];
 
            int k = list.size()-2;
            
            while(k >= t && ccw(list.get(k), p, list.get(k+1)) < 0){
                list.remove(k+1);
                k--;
            }
            if(i != 0) list.add(p);
        }
        
        return list;
    }
    
    
    public G interCovex(G g1, G g2){
        double[][] d1 = new double[g1.size()][2];
        double[][] d2 = new double[g2.size()][2];
        for(int i = 0; i < g1.size(); i++){
            d1[i][0] = g1.get(i).x;
            d1[i][0] = g1.get(i).y;
        }
        for(int i = 0; i < g2.size(); i++){
            d2[i][0] = g2.get(i).x;
            d2[i][0] = g2.get(i).y;
        }
        double[][] d = interConvex(d1, d2);
        G g = new G();
        for(int i = 0; i < d.length; i++){
            g.add(d[i][0], d[i][1]);
        }
        return g;
    }
    
    
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    
    private double extreme_help_dot(P p, P l){
        return dot(p, l);
    }
    
    
    
    public P extreme(List<P> ps, L l) {
      int k = 0;
      P pl = l.b.subtract(l.a);
      for (int i = 1; i < ps.size(); ++i)
        if (extreme_help_dot(ps.get(i), pl) > extreme_help_dot(ps.get(k), pl)) k = i;
      return ps.get(k);
    }
 
    public double areaPoly(double[][] co)
    {
        int n = co.length;
        double s = 0;
        for(int i = 0;i < n - 1;i++){
            s += (co[i][0]*co[i+1][1]-co[i+1][0]*co[i][1]) / 2;
        }
        s += (co[n-1][0]*co[0][1]-co[0][0]*co[n-1][1]) / 2;
        return Math.abs(s);
    }
    
    
    public double[] interLines(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy)
    {
        double eps = 1E-9;
        double aa = bx - ax;
        double cc = by - ay;
        double bb = cx - dx;
        double dd = cy - dy;
        double xx = cx - ax;
        double yy = cy - ay;
        double det = aa * dd - bb * cc;
        if(Math.abs(det) < eps)return null;
        double t = (dd * xx - bb * yy) / det;
        double u = (-cc * xx + aa * yy) / det;
        return new double[]{ax + (bx - ax) * t, ay + (by - ay) * t, t, u};
    }
    
    public double[][] interConvex(double[][] co1, double[][] co2){
        double EPS = 1E-9;
        int m = co1.length;
        int n = co2.length;
        double[] cx = new double[2*(m+n)];
        double[] cy = new double[2*(m+n)];
        int p = 0;
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                int ni = (i+1)%m;
                int nj = (j+1)%n;
                double[] il = interLines(
                        co1[i][0], co1[i][1], co1[ni][0], co1[ni][1],
                        co2[j][0], co2[j][1], co2[nj][0], co2[nj][1]
                                );
                if(il != null && il[2] > -EPS && il[2] < 1+EPS && il[3] > -EPS && il[3] < 1+EPS){
                    cx[p] = il[0];
                    cy[p] = il[1];
                    p++;
                }
            }
        }
        for(int i = 0;i < m;i++){
            if(isOnIn(co1[i][0], co1[i][1], co2)){
                cx[p] = co1[i][0];
                cy[p] = co1[i][1];
                p++;
            }
        }
        for(int i = 0;i < n;i++){
            if(isOnIn(co2[i][0], co2[i][1], co1)){
                cx[p] = co2[i][0];
                cy[p] = co2[i][1];
                p++;
            }
        }
        
        cx = Arrays.copyOf(cx, p);
        cy = Arrays.copyOf(cy, p);
        convexSort(cx, cy);
        
        double[][] rc = new double[p][];
        for(int i = 0;i < p;i++){
            rc[i] = new double[]{cx[i], cy[i]};
        }
        return rc;
    }
    
    public void convexSort(double[] sx, double[] sy)
    {
        double gx = 0, gy = 0;
        for(double x : sx)gx += x;
        for(double y : sy)gy += y;
        int n = sx.length;
        gx /= n; gy /= n;
        
        final double[] th = new double[n];
        for(int i = 0;i < n;i++){
            th[i] = Math.atan2(sy[i]-gy, sx[i]-gx);
        }
        Integer[] ord = new Integer[n];
        for(int i = 0;i < n;i++)ord[i] = i;
        Arrays.sort(ord, new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return Double.compare(th[a], th[b]);
            }
        });
        
        double[] cx = new double[n];
        double[] cy = new double[n];
        System.arraycopy(sx, 0, cx, 0, n);
        System.arraycopy(sy, 0, cy, 0, n);
        for(int i = 0;i < n;i++){
            sx[i] = cx[ord[i]];
            sy[i] = cy[ord[i]];
        }
    }
 
    public boolean isOnIn(double x, double y, double[][] co){
        int n = co.length;
        int cc = Line2D.relativeCCW(co[n-1][0], co[n-1][1], co[0][0], co[0][1], x, y);
        if(cc == 0)return true;
        for(int i = 0;i < n-1;i++){
            int ccc = Line2D.relativeCCW(co[i][0], co[i][1], co[i+1][0], co[i+1][1], x, y);
            if(ccc == 0)return true;
            if(cc != ccc)return false;
        }
        return true;
    }
 
 
 }
