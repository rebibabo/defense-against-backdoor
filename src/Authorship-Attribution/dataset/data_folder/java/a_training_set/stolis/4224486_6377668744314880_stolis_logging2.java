package year2015.round1a;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class Logging2 {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("C-small-attempt2.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             System.err.println(t);
             int N = in.nextInt();
             Point[] P = new Point[N];
             for (int n=0; n<N; n++) {
                 int x = in.nextInt();
                 int y = in.nextInt();
                 P[n] = new Point(x,y);
             }
             int[] result = new int[N];
             Arrays.fill(result, N-1);
             for (int n=0; n<N; n++) {
                 for (int m=n+1; m<N; m++) {
                     int left = 0;
                     int right = 0;
                     for (int x=0; x<N; x++) {
                         if (x != n && x != m) {
                             int side = side(P[n], P[m], P[x]);
                             if (side == 1) {
                                 left++;
                             } else if (side == -1) {
                                 right++;
                             }
                         }
                     }
                     int min = Math.min(left,right);
                     result[n] = Math.min(result[n],min);
                     result[m] = Math.min(result[m],min);
                 }
             }
             
             out.println("Case #"+(t+1)+":");
             for (int answer : result) {
                 out.println(answer);
             }
         }
 
         out.close();
     }
 
     public static int side(Point a, Point b, Point c){
          return (int)Math.signum((b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x));
     }
 
     static class Point {
         long x;
         long y;
         
         Point(long x, long y) {
             this.x = x;
             this.y = y;
         }
     }
     
 }
