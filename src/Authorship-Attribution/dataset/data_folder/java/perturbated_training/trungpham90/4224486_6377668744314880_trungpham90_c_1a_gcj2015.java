
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.PriorityQueue;
 import java.util.StringTokenizer;
 
 
 
 public class C_1A_GCJ2015 {
 
     public static long MOD = 1000000007;
     static int[] X = {0, 1};
     static int[] Y = {1, 0};
     static int[][][] dp;
 
     public static void main(String[] args) throws FileNotFoundException {
         PrintWriter out = new PrintWriter(new FileOutputStream(new File(
                 "output.txt")));
         
         Scanner in = new Scanner();
         int T = in.nextInt();
 
         for (int t = 0; t < T; t++) {
             int n = in.nextInt();
             Point[] data = new Point[n];
             for (int i = 0; i < n; i++) {
                 data[i] = new Point(in.nextInt(), in.nextInt());
             }
 
             out.println("Case #" + (t + 1) + ":");
             for (int i = 0; i < n; i++) {
 
                 if (n <= 3) {
                     out.println(0);
                 } else {
                     int min = n;
                     for (int j = 1; j < (1 << n); j++) {
                         ArrayList<Point> list = new ArrayList();
                         for (int k = 0; k < n; k++) {
                             if (((1 << k) & j) != 0) {
                                 list.add(data[k]);
                             }
                         }
                         
                         if (Integer.bitCount(j) >= 3) {
                             ArrayList<Point> result = execute(list);
                             boolean ok = false;
                             for (Point p : result) {
                                 if (equalsPoint(p, data[i])) {
                                     ok = true;
                                     if (min > n - Integer.bitCount(j)) {
                                         min = n - Integer.bitCount(j);
                                     }
                                     break;
                                 }
                             }
 
                             for (int k = 0; k < result.size() && !ok; k++) {
                                 int h = (k + 1) % result.size();
                                 if (!equalsPoint(result.get(k), result.get(h)) && onSegment(data[i], result.get(k), result.get(h))) {
 
                                     if (min > n - Integer.bitCount(j)) {
                                         min = n - Integer.bitCount(j);
                                     }
                                     ok = true;
                                     break;
                                 }
 
                             }
 
 
                         } else {
                             if (min > n - Integer.bitCount(j)) {
                                 min = n - Integer.bitCount(j);
                             }
                         }
                     }
 
                     out.println(min);
                 }
             }
         }
         out.close();
     }
 
     public static boolean onSegment(Point c, Point a, Point b) {
         long crossproduct = (c.y - a.y) * (b.x - a.x) - (c.x - a.x) * (b.y - a.y);
         if (crossproduct != 0) {
             return false;
         }
 
         long dotproduct = (c.x - a.x) * (b.x - a.x) + (c.y - a.y) * (b.y - a.y);
         if (dotproduct < 0) {
             return false;
         }
 
 
         long squaredlengthba = (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
         if (dotproduct > squaredlengthba) {
             return false;
         }
         return true;
 
 
     }
 
     public static boolean equalsPoint(Point a, Point b) {
         return a.x == b.x && a.y == b.y;
     }
 
     public static ArrayList<Point> execute(ArrayList<Point> points) {
         ArrayList<Point> xSorted = (ArrayList<Point>) points.clone();
         Collections.sort(xSorted, new XCompare());
 
         int n = xSorted.size();
 
         Point[] lUpper = new Point[n];
 
         lUpper[0] = xSorted.get(0);
         lUpper[1] = xSorted.get(1);
 
         int lUpperSize = 2;
 
         for (int i = 2; i < n; i++) {
             lUpper[lUpperSize] = xSorted.get(i);
             lUpperSize++;
 
             while (lUpperSize > 2 && !rightTurn(lUpper[lUpperSize - 3], lUpper[lUpperSize - 2], lUpper[lUpperSize - 1])) {
                 
                 lUpper[lUpperSize - 2] = lUpper[lUpperSize - 1];
                 lUpperSize--;
             }
         }
 
         Point[] lLower = new Point[n];
 
         lLower[0] = xSorted.get(n - 1);
         lLower[1] = xSorted.get(n - 2);
 
         int lLowerSize = 2;
 
         for (int i = n - 3; i >= 0; i--) {
             lLower[lLowerSize] = xSorted.get(i);
             lLowerSize++;
 
             while (lLowerSize > 2 && !rightTurn(lLower[lLowerSize - 3], lLower[lLowerSize - 2], lLower[lLowerSize - 1])) {
                 
                 lLower[lLowerSize - 2] = lLower[lLowerSize - 1];
                 lLowerSize--;
             }
         }
 
         ArrayList<Point> result = new ArrayList<Point>();
 
         for (int i = 0; i < lUpperSize; i++) {
             result.add(lUpper[i]);
         }
 
         for (int i = 1; i < lLowerSize - 1; i++) {
             result.add(lLower[i]);
         }
 
         return result;
     }
 
     private static boolean rightTurn(Point a, Point b, Point c) {
         return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x) > 0;
     }
 
     private static class XCompare implements Comparator<Point> {
 
         @Override
         public int compare(Point o1, Point o2) {
             return (new Long(o1.x)).compareTo(new Long(o2.x));
         }
     }
 
     public static int[] KMP(String val) {
         int i = 0;
         int j = -1;
         int[] result = new int[val.length() + 1];
         result[0] = -1;
         while (i < val.length()) {
             while (j >= 0 && val.charAt(j) != val.charAt(i)) {
                 j = result[j];
             }
             j++;
             i++;
             result[i] = j;
         }
         return result;
 
     }
 
     public static boolean nextPer(int[] data) {
         int i = data.length - 1;
         while (i > 0 && data[i] < data[i - 1]) {
             i--;
         }
         if (i == 0) {
             return false;
         }
         int j = data.length - 1;
         while (data[j] < data[i - 1]) {
             j--;
         }
         int temp = data[i - 1];
         data[i - 1] = data[j];
         data[j] = temp;
         Arrays.sort(data, i, data.length);
         return true;
     }
 
     public static int digit(long n) {
         int result = 0;
         while (n > 0) {
             n /= 10;
             result++;
         }
         return result;
     }
 
     public static double dist(long a, long b, long x, long y) {
         double val = (b - a) * (b - a) + (x - y) * (x - y);
         val = Math.sqrt(val);
         double other = x * x + a * a;
         other = Math.sqrt(other);
         return val + other;
 
 
 
     }
 
     public static class Point {
 
         long x, y;
 
         public Point(int start, int end) {
             this.x = start;
             this.y = end;
         }
 
         public String toString() {
             return x + " " + y;
         }
 
         @Override
         public boolean equals(Object obj) {
             if (obj == null) {
                 return false;
             }
             if (getClass() != obj.getClass()) {
                 return false;
             }
             final Point other = (Point) obj;
             if (this.x != other.x) {
                 return false;
             }
             if (this.y != other.y) {
                 return false;
             }
             return true;
         }
     }
 
     public static class FT {
 
         long[] data;
 
         FT(int n) {
             data = new long[n];
         }
 
         public void update(int index, long value) {
             while (index < data.length) {
                 data[index] += value;
                 index += (index & (-index));
             }
         }
 
         public long get(int index) {
             long result = 0;
             while (index > 0) {
                 result += data[index];
                 index -= (index & (-index));
             }
             return result;
 
         }
     }
 
     public static long gcd(long a, long b) {
         if (b == 0) {
             return a;
         }
         return gcd(b, a % b);
     }
 
     public static long pow(long a, long b) {
         if (b == 0) {
             return 1;
         }
         if (b == 1) {
             return a;
         }
         long val = pow(a, b / 2);
         if (b % 2 == 0) {
             return val * val % MOD;
         } else {
             return val * (val * a % MOD) % MOD;
 
 
         }
     }
 
     static class Scanner {
 
         BufferedReader br;
         StringTokenizer st;
 
         public Scanner() throws FileNotFoundException {
             
             
             br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C-small-attempt2.in"))));
         }
 
         public String next() {
 
             while (st == null || !st.hasMoreTokens()) {
                 try {
                     st = new StringTokenizer(br.readLine());
                 } catch (Exception e) {
                     throw new RuntimeException();
                 }
             }
             return st.nextToken();
         }
 
         public long nextLong() {
             return Long.parseLong(next());
         }
 
         public int nextInt() {
             return Integer.parseInt(next());
         }
 
         public double nextDouble() {
             return Double.parseDouble(next());
         }
 
         public String nextLine() {
             st = null;
             try {
                 return br.readLine();
             } catch (Exception e) {
                 throw new RuntimeException();
             }
         }
 
         public boolean endLine() {
             try {
                 String next = br.readLine();
                 while (next != null && next.trim().isEmpty()) {
                     next = br.readLine();
                 }
                 if (next == null) {
                     return true;
                 }
                 st = new StringTokenizer(next);
                 return st.hasMoreTokens();
             } catch (Exception e) {
                 throw new RuntimeException();
             }
         }
     }
 }
