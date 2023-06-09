
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.BitSet;
 import java.util.Calendar;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.PriorityQueue;
 import java.util.SortedSet;
 import java.util.Stack;
 import java.util.StringTokenizer;
 import java.util.TreeMap;
 import java.util.TreeSet;
 
 
 public class B_GCJ2016_1B {
 
     public static long MOD = 1000000007;
 
     public static void main(String[] args) throws FileNotFoundException {
          PrintWriter out = new PrintWriter(new FileOutputStream(new File(
          "output.txt")));
         
         Scanner in = new Scanner();
         int t = in.nextInt();
         for (int z = 0; z < t; z++) {
             String a = in.next();
             String b = in.next();
             int compare = 0;
             int index = -1;
             for (int i = 0; i < a.length(); i++) {
                 if (a.charAt(i) != '?' && b.charAt(i) != '?' && a.charAt(i) != b.charAt(i)) {
                     compare = Character.compare(a.charAt(i), b.charAt(i));
                     index = i;
                     break;
                 }
             }
             
             if (compare == 0) {
                 String X = "", Y = "";
                 for (int i = 0; i < a.length(); i++) {
                     if (a.charAt(i) != '?') {
                         X += a.charAt(i);
                         Y += a.charAt(i);
                     } else if (b.charAt(i) != '?') {
                         X += b.charAt(i);
                         Y += b.charAt(i);
                     } else {
                         X += '0';
                         Y += '0';
                     }
                 }
                 out.println("Case #" + (z + 1) + ": " + X + " " + Y);
             } else {
                 BigInteger x = null, y = null;
                 String X = null, Y = null;
                 StringBuilder builder = new StringBuilder();
                 BigInteger result = null;
                 for (int i = 0; i < index; i++) {
                     if (a.charAt(i) == '?' || b.charAt(i) == '?') {
                         
                         if (b.charAt(i) != '0' && a.charAt(i) != '9') {
                             StringBuilder c = new StringBuilder(builder);
                             StringBuilder d = new StringBuilder(builder);
                             if (b.charAt(i) != '?') {
                                 c.append((char) (b.charAt(i) - 1));
                                 d.append(b.charAt(i));
                             } else if (a.charAt(i) != '?') {
                                 c.append(a.charAt(i));
                                 d.append((char) (a.charAt(i) + 1));
                             } else {
                                 c.append('0');
                                 d.append('1');
                             }
                             for (int j = i + 1; j < a.length(); j++) {
                                 if (a.charAt(j) != '?') {
                                     c.append(a.charAt(j));
                                 } else {
                                     c.append('9');
                                 }
                                 if (b.charAt(j) != '?') {
                                     d.append(b.charAt(j));
                                 } else {
                                     d.append('0');
                                 }
                             }
                             BigInteger C = new BigInteger(c.toString());
                             BigInteger D = new BigInteger(d.toString());
                             BigInteger diff = D.subtract(C);
                             if (x == null || diff.compareTo(result) < 0 || (diff.compareTo(result) == 0 && (C.compareTo(x) < 0 || (C.compareTo(x) == 0 && D.compareTo(y) < 0)))) {
                                 x = C;
                                 y = D;
                                 X = c.toString();
                                 Y = d.toString();
                                 result = diff;
                             }
                             
                         }
 
                         
                         if (a.charAt(i) != '0' && b.charAt(i) != '9') {
                             StringBuilder c = new StringBuilder(builder);
                             StringBuilder d = new StringBuilder(builder);
                             if (b.charAt(i) != '?') {
                                 c.append((char) (b.charAt(i) + 1));
                                 d.append(b.charAt(i));
                             } else if (a.charAt(i) != '?') {
                                 c.append(a.charAt(i));
                                 d.append((char) (a.charAt(i) - 1));
                             } else {
                                 c.append('1');
                                 d.append('0');
                             }
                             for (int j = i + 1; j < a.length(); j++) {
                                 if (a.charAt(j) != '?') {
                                     c.append(a.charAt(j));
                                 } else {
                                     c.append('0');
                                 }
                                 if (b.charAt(j) != '?') {
                                     d.append(b.charAt(j));
                                 } else {
                                     d.append('9');
                                 }
                             }
                             BigInteger C = new BigInteger(c.toString());
                             BigInteger D = new BigInteger(d.toString());
                             BigInteger diff = C.subtract(D);
                             if (x == null || diff.compareTo(result) < 0 || (diff.compareTo(result) == 0 && (C.compareTo(x) < 0 || (C.compareTo(x) == 0 && D.compareTo(y) < 0)))) {
                                 x = C;
                                 y = D;
                                 X = c.toString();
                                 Y = d.toString();
                                 result = diff;
                             }
                             
                         }
                     }
                     if (a.charAt(i) != '?') {
                         builder.append(a.charAt(i));
                     } else if (b.charAt(i) != '?') {
                         builder.append(b.charAt(i));
                     } else {
                         builder.append('0');
                     }
 
                 }
                 StringBuilder c = new StringBuilder(builder);
                 StringBuilder d = new StringBuilder(builder);
                 c.append(a.charAt(index));
                 d.append(b.charAt(index));
                 
                 if (compare < 0) {
                     for (int j = index + 1; j < a.length(); j++) {
                         if (a.charAt(j) != '?') {
                             c.append(a.charAt(j));
                         } else {
                             c.append('9');
                         }
                         if (b.charAt(j) != '?') {
                             d.append(b.charAt(j));
                         } else {
                             d.append('0');
                         }
                     }
                 } else {
                     for (int j = index + 1; j < a.length(); j++) {
                         if (a.charAt(j) != '?') {
                             c.append(a.charAt(j));
                         } else {
                             c.append('0');
                         }
                         if (b.charAt(j) != '?') {
                             d.append(b.charAt(j));
                         } else {
                             d.append('9');
                         }
                     }
                 }
                 BigInteger C = new BigInteger(c.toString());
                 BigInteger D = new BigInteger(d.toString());
                 BigInteger diff;
                 if (compare < 0) {
                     diff = D.subtract(C);
                 } else {
                     diff = C.subtract(D);
                 }
                 if (x == null || diff.compareTo(result) < 0 || (diff.compareTo(result) == 0 && (C.compareTo(x) < 0 || (C.compareTo(x) == 0 && D.compareTo(y) < 0)))) {
                     x = C;
                     y = D;
                     X = c.toString();
                     Y = d.toString();
                     result = diff;
                 }
                 out.println("Case #" + (z + 1) + ": " + X + " " + Y);
             }
         }
         out.close();
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
 
     public static class Point implements Comparable<Point> {
 
         int x, y;
 
         public Point(int start, int end) {
             this.x = start;
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
             return Integer.compare(x, o.x);
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
 
     public static long pow(long a, long b, long MOD) {
         if (b == 0) {
             return 1;
         }
         if (b == 1) {
             return a;
         }
         long val = pow(a, b / 2, MOD);
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
             
             
              br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("B-small-attempt0.in"))));
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
