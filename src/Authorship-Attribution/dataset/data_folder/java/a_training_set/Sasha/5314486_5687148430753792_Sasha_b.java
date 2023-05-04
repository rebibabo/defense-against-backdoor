import java.io.File;
 import java.io.PrintStream;
 import java.lang.reflect.Array;
 import java.util.*;
 
 public class B {
 
     private static final int HALF = 12 * 60;
     private static final int FULL = 24 * 60;
 
     private static class Interval implements Comparable<Interval> {
         int s;
         int f;
         boolean first;
 
         Interval(int s, int f, boolean first) {
             this.s = s;
             this.f = f;
             this.first = first;
         }
 
         @Override
         public int compareTo(Interval o) {
             return Integer.compare(this.s, o.s);
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         Scanner in = new Scanner(new File("problem.in"));
         PrintStream out = new PrintStream(new File("problem.out"));
 
         int T = in.nextInt();
 
         for (int test = 1; test <= T; test++) {
             int n = in.nextInt();
             int c = in.nextInt();
             int m = in.nextInt();
 
             int[][] count = new int[c][n];
 
             for (int i=0; i<m; i++) {
                 int p = in.nextInt() - 1;
                 int b = in.nextInt() - 1;
 
                 count[b][p]++;
             }
 
             int r = 0;
 
             for (int i=0; i<c; i++) {
                 int t = 0;
 
                 for (int j=0; j<n; j++) {
                     t += count[i][j];
                 }
                 if (t > r) {
                     r = t;
                 }
             }
 
 
             int t = 0;
             for (int j=0; j<n; j++) {
                 for (int i=0; i<c; i++) {
                     t += count[i][j];
                 }
 
                 int r1 = t / (j+1);
                 if (t % (j+1) != 0) {
                     r1++;
                 }
                 if (r1 > r) {
                     r = r1;
                 }
             }
 
             int v = 0;
             for (int j=n-1; j>=0; j--) {
                 t = 0;
                 for (int i=0; i<c; i++) {
                     t += count[i][j];
                 }
                 if (t > r) {
                     v += t - r;
                 }
 
             }
 
 
             out.printf("Case #%d: %d %d\n", test, r, v);
 
         }
     }
 }
