package c2015.r1_3;
 
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Map;
 import java.util.Scanner;
 
 public class Z2_1 {
 
     public static void main(String[] args) throws Exception {
         FileWriter fw = new FileWriter("C:\\output.txt");
         BufferedWriter out = new BufferedWriter(fw);
         
         String pathname = "C:\\Users\\YC14rp1\\Downloads\\B-small-attempt0.in";
         
         Scanner scanner = new Scanner(new File(pathname));
         int tn = scanner.nextInt();
         scanner.nextLine();
         for (int ti = 1; ti <= tn; ti++) {
             int k = scanner.nextInt();
             int l = scanner.nextInt();
             int s = scanner.nextInt();
             String alphabet = scanner.next();
             String word = scanner.next();
             boolean flag = true;
             for (int i=0;i<word.length();i++) {
                 String substring = word.substring(i, i+1);
                 if (!alphabet.contains(substring)) {
                     flag = false;
                 }
             }
             Map<Character, Integer> map = new HashMap<Character, Integer>();
             for (int i=0;i<alphabet.length();i++) {
                 Integer integer = map.get(alphabet.charAt(i));
                 if (integer==null) {
                     integer = 0;
                 }
                 integer++;
                 map.put(alphabet.charAt(i), integer);
             }
             Double res;
             if (flag) {
                 double pow = Math.pow(alphabet.length(), s);
                 long cnt = 0;
                 long max = 0;
                 System.out.println(pow);
                 for (int i=0;i< pow;i++) {
                     String ss = "";
                     int cursor = i;
                     for (int j=0;j<s;j++) {
                         int x = cursor % alphabet.length();
                         ss = ss + alphabet.charAt(x);
                         cursor = cursor/alphabet.length();
                     }
                     int xx = 0;
                     int start = s - word.length();
                     for (int j=0;j<= start;j++) {
                         String substring = ss.substring(j, j + word.length());
                         if (word.equals(substring)) {
                             cnt++;
                             xx++;
                         }
                     }
                     
                     
                     
                     if (xx>max) {
                         max = xx;
                     }
                 }
                 System.out.println(cnt);
                 System.out.println(max);
                 res = max - (cnt/pow);
             } else {
                 res = 0.0;
             }
             String s11;
             s11 = "Case #" + ti + ": "+res;
             System.out.println(s11);
             out.write(s11);
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
