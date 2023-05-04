package c2015_r1_2;
 
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Scanner;
 
 public class Z3_1 {
 
     private static class Point {
         int x,y;
 
         public Point(int x, int y) {
             this.x = x;
             this.y = y;
         }
 
         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
 
             Point point = (Point) o;
 
             if (x != point.x) return false;
             if (y != point.y) return false;
 
             return true;
         }
 
         @Override
         public int hashCode() {
             int result = x;
             result = 31 * result + y;
             return result;
         }
 
         @Override
         public String toString() {
             return "P{" +
                     "x=" + x +
                     ", y=" + y +
                     '}';
         }
     }
 
     private static int calcHappy(List<Point> points) {
         int res = 0;
         for (int i=0;i<points.size();i++) {
             for (int j=i+1;j<points.size();j++) {
                 if (i!=j) {
                     Point point = points.get(i);
                     Point t = points.get(j);
                     if (point.x==t.x && Math.abs(point.y-t.y)==1) {
                         res++;
                     }
                     if (point.y==t.y && Math.abs(point.x-t.x)==1) {
                         res++;
                     }
 
                 }
             }
         }
         return res;
     }
 
     public static void main(String[] args) throws Exception {
         FileWriter fw = new FileWriter("C:\\output.txt");
         BufferedWriter out = new BufferedWriter(fw);
         
         String pathname = "C:\\Users\\YC14rp1\\Downloads\\B-small-attempt3.in";
         
         
         Scanner scanner = new Scanner(new File(pathname));
         int tn = scanner.nextInt();
         scanner.nextLine();
         for (int ti = 1; ti <= tn; ti++) {
             int r = scanner.nextInt();
             int c = scanner.nextInt();
             int n = scanner.nextInt();
             int l = r*c;
             int min = Integer.MAX_VALUE;
             for (int mask=0;mask<2<<l;mask++) {
                 int cnt = 0;
                 List<Point> points = new ArrayList<Point>();
                 for (int i = 0; i < l; i++) {
                     if ((mask & 1 << i) != 0) {
                         cnt++;
                         int x = i % r;
                         int y = (i-x)/r;
                         points.add(new Point(x,y));
                     }
                 }
                 if (cnt==n) {
                     int hap = calcHappy(points);
                     if (hap<min) {
                         min = hap;
                     }
                 }
             }
             String s;
             if (min==Integer.MAX_VALUE) {
                 s = "Case #" + ti + ": NOT POSSIBLE";
             } else {
                 s = "Case #" + ti + ": "+min;
             }
             System.out.println(s);
             out.write(s);
             out.write("\n");
         }
         out.close();
     }
 
     private static int[][] read2DArray(Scanner scanner, int n, int m) {
         int [][]m1 = new int[n][m];
         for (int i=0;i<n;i++) {
             for (int j=0;j<m;j++) {
                 m1[i][j] = scanner.nextInt();
             }
         }
         return m1;
     }
 
     
     private static int[] readIntArray(Scanner scanner, int n) {
         int [] a = new int[n];
         for (int i=0;i<n;i++) {
             a[i] = scanner.nextInt();
         }
         return a;
     }
 
     
     private static void readGraphAsMatrix(Scanner scanner, int n, int[][] matrix) {
         for (int i=0;i<n-1;i++) {
             int x = scanner.nextInt();
             int y = scanner.nextInt();
             matrix[x-1][y-1] = 1;
             matrix[y-1][x-1] = 1;
         }
     }
 
     
     private static String[] readArray(Scanner scanner, int n) {
         String[] l2 = new String[n];
         for (int i=0;i<n;i++) {
             l2[i] = scanner.next();
         }
         return l2;
     }
 
     
     private static HashSet<String> readSet(Scanner scanner, int n) {
         String[] l1 = new String[n];
         HashSet<String> set1 = new HashSet<String>();
         for (int i=0;i<n;i++) {
             l1[i] = scanner.next();
             set1.add(l1[i]);
         }
         return set1;
     }
 
 }
