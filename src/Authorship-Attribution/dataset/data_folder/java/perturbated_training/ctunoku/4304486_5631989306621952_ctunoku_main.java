import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.math.BigInteger;
 import java.util.*;
 
 public class Main {
     private static StringTokenizer st;
     private static BufferedReader br;
     public static long MOD = 1000000007;
 
     public static void print(Object x) {
         System.out.println(x + "");
     }
     public static void printArr(long[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + " ");
         }
         print(s);
     }
     public static void printArr(int[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + " ");
         }
         print(s);
     }
     public static void printArr(char[] x) {
         StringBuilder s = new StringBuilder();
         for (int i = 0; i < x.length; i++) {
             s.append(x[i] + "");
         }
         print(s);
     }
     public static String join(Collection<?> x, String space) {
         if (x.size() == 0) return "";
         StringBuilder sb = new StringBuilder();
         boolean first = true;
         for (Object elt : x) {
             if (first) first = false;
             else sb.append(space);
             sb.append(elt);
         }
         return sb.toString();
     }
 
     public static String nextToken() throws IOException {
         while (st == null || !st.hasMoreTokens()) {
             String line = br.readLine();
             st = new StringTokenizer(line.trim());
         }
         return st.nextToken();
     }
     public static int nextInt() throws IOException {
         return Integer.parseInt(nextToken());
     }
     public static long nextLong() throws IOException {
         return Long.parseLong(nextToken());
     }
 
     public static void main(String[] args) throws IOException {
         
         br = new BufferedReader(new FileReader("input.txt"));
 
         int T = nextInt();
 
         for (int t = 1; t <= T; t++) {
             char[] chars = nextToken().toCharArray();
             int n = chars.length;
             String word = "" + chars[0];
             for (int i = 1; i < n; i++) {
                 int j = 0;
                 while (j < i && chars[i] == word.charAt(j))
                     j++;
                 if (j == i || chars[i] > word.charAt(j)) {
                     word = chars[i] + word;
                 } else {
                     word = word + chars[i];
                 }
             }
             System.out.printf("Case #%d: %s%n", t, word);
             
         }
 
     }
 }
