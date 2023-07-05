import java.io.File;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Locale;
 import java.util.Map;
 import java.util.Scanner;
 
 
 @SuppressWarnings("ForLoopReplaceableByForEach")
 public class C {
     @SuppressWarnings("FieldCanBeLocal")
     private static int caseNumber;
     private static Scanner scan;
     private Point[] original;
     private int n;
     private Map<Integer, List<Point>> pointsByMask;
 
     void solve() {
         n = scan.nextInt();
         original = new Point[n];
         for (int i = 0; i < n; ++i) {
             original[i] = new Point(scan.nextInt(), scan.nextInt(), i);
         }
 
         pointsByMask = new HashMap<Integer, List<Point>>();
         for (int mask = 1, end = 1 << n; mask < end; ++mask) {
             List<Point> points = new ArrayList<Point>();
             for (int i = 0; i < n; ++i) {
                 if (((1 << i) & mask) != 0) {
                     points.add(original[i]);
                 }
             }
             Collections.sort(points, new Comparator<Point>() {
                 @Override
                 public int compare(Point o1, Point o2) {
                     int res = o1.x - o2.x;
                     if (res != 0) {
                         return res;
                     }
 
                     return o1.y - o2.y;
                 }
             });
             pointsByMask.put(mask, points);
         }
 
         System.out.printf("\n");
         for (int tree = 0; tree < n; ++tree) {
             int ans = n - 1;
             for (int mask = 0, end = 1 << n; mask < end; ++mask) {
                 if (onHull(mask, tree)) {
                     ans = Math.min(ans, n - Integer.bitCount(mask));
                 }
             }
 
             System.out.printf("%d\n", ans);
         }
     }
 
     boolean onHull(int mask, int tree) {
         mask |= 1 << tree;
 
         
         List<Point> points = pointsByMask.get(mask);
 
         Point[] hull = buildHull(points);
         for (Point point : hull) {
             if (point.ind == tree) {
                 return true;
             }
         }
         return false;
     }
 
     static long cross(Point o, Point a, Point b) {
         return (a.x - o.x) * (b.y - o.y) - (a.y - o.y) * (b.x - o.x);
     }
 
     static Point[] buildHull(List<Point> points) {
 
         if (points.size() <= 1) {
             return new Point[] {points.get(0)};
         }
 
         int n = points.size();
         int k = 0;
 
         Point[] hull = new Point[2 * n];
 
         for (int i = 0; i < n; ++i) {
             while (k >= 2 && cross(hull[k - 2], hull[k - 1], points.get(i)) < 0) {
                 k--;
             }
             hull[k++] = points.get(i);
         }
 
         for (int i = n - 2, t = k + 1; i >= 0; i--) {
             while (k >= t && cross(hull[k - 2], hull[k - 1], points.get(i)) < 0) {
                 k--;
             }
             hull[k++] = points.get(i);
         }
 
         if (k > 1) {
             hull = Arrays.copyOfRange(hull, 0, k - 1);
         }
 
         return hull;
     }
 
     static class Point {
         int x;
         int y;
         int ind;
 
         Point(int x, int y, int ind) {
             this.x = x;
             this.y = y;
             this.ind = ind;
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         String file = "C-small-attempt2";
 
 
 
         redirectToFile(file);
 
         String inFile = file + ".in";
         scan = new Scanner(new File(inFile));
 
         int cases = scan.nextInt();
         for (caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             System.out.printf("Case #%s: ", caseNumber);
             new C().solve();
             System.out.flush();
         }
 
         scan.close();
     }
 
     static void redirectToFile(String file) throws Exception {
         System.setOut(new PrintStream(file + ".out"));
     }
 }
