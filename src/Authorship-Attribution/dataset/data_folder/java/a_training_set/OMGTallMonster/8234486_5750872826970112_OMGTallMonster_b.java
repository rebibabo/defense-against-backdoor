import java.util.*;
 
 class B {
    public static class Source {
       double r, x;
       public Source(double R, double X) { r=R; x=X; }
       public String toString() { return r+","+x; }
    }
 
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d: %s\n", i, solve(scan));
       }
    }
 
    public static Object solve(Scanner scan) {
       int N = scan.nextInt();
       double v = scan.nextDouble(), x = scan.nextDouble();
       Source[] sources = new Source[N];
       boolean bUp = false, bDown = false;
       for (int i = 0; i < N; i++) {
          Source s = new Source(scan.nextDouble(), scan.nextDouble());
          sources[i] = s;
          if (s.x >= x) {
             bUp = true;
          }
          if (s.x <= x) {
             bDown = true;
          }
       }
 
       if (!bUp || !bDown) {
          return "IMPOSSIBLE";
       }
 
       if (N == 2) {
          Source a = sources[0], b = sources[1];
          if (a.x == b.x) {
             N = 1;
             a.r += b.r;
          } else {
             double t1 = v*(x-a.x)/(b.r*(b.x-a.x));
             double t0 = (v-t1*b.r)/a.r;
             return String.format("%.9ef",Math.max(t0,t1));
          }
       }
 
       if (N == 1) {
          Source s = sources[0];
          
          double t = v/s.r;
          return String.format("%.9ef",t);
       }
       return "???";
    }
 }
