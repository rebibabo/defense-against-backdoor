import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class Logging {
 
     static class Point {
         long x, y;
 
         public Point(long x, long y) {
             this.x = x;
             this.y = y;
         }
 
         long cross(Point that) {
             return Long.signum(x * that.y - y * that.x);
         }
 
         @Override
         public String toString() {
             return String.format("(%d, %d)", x, y);
         }
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("C-small-attempt1.in"));
         PrintStream cout = new PrintStream("C-small-attempt1.out");
         
         
         
         
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
 
             int n = cin.nextInt();
             Point[] p = new Point[n];
             for (int i = 0; i < n; i++)
                 p[i] = new Point(cin.nextLong(), cin.nextLong());
 
             cout.printf("Case #%d:%n", _case);
             for (int i = 0; i < n; i++) {
                 int min = Integer.MAX_VALUE;
                 if (n == 0)
                     min = 0;
                 else {
                     for (int j = 0; j < n; j++)
                         if (p[i].x != p[j].x || p[i].y != p[j].y) {
                             Point v1 = new Point(p[j].x - p[i].x, p[j].y - p[i].y);
 
                             int cnt1 = 0, cnt2 = 0;
                             for (int k = 0; k < n; k++) {
                                 Point v2 = new Point(p[k].x - p[i].x, p[k].y - p[i].y);
                                 long sign = v1.cross(v2);
                                 if (sign > 0)
                                     cnt1++;
                                 else if (sign < 0)
                                     cnt2++;
                             }
                             min = Math.min(min, cnt1);
                             min = Math.min(min, cnt2);
                         }
                     if (min == Integer.MAX_VALUE)
                         min = 0;
                 }
                 cout.printf("%d%n", min);
             }
         }
 
         cin.close();
         cout.close();
     }
 }
