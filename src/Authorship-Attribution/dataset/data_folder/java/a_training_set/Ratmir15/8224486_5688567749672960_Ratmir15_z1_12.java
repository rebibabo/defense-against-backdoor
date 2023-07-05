package c2015_r1_2;
 
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 
 public class Z1_12 {
 
     public static void main(String[] args) throws Exception {
         FileWriter fw = new FileWriter("C:\\output.txt");
         BufferedWriter out = new BufferedWriter(fw);
         
         String pathname = "C:\\Users\\YC14rp1\\Downloads\\A-small-attempt4.in";
         
         Scanner scanner = new Scanner(new File(pathname));
         int tn = scanner.nextInt();
         scanner.nextLine();
         int[] xx = new int[1000001];
         Set<Integer> all = new HashSet<Integer>();
         for (int x =1;x<=1000000;x++) {
             all.add(x);
         }
         Set<Integer> current = new HashSet<Integer>();
         current.add(1);
         xx[1] = 1;
         int step = 2;
         while (all.size()>0) {
             all.removeAll(current);
             Set<Integer> next = new HashSet<Integer>();
             for (int x : current) {
                 String xs = String.valueOf(x);
                 String ns = new StringBuilder(xs).reverse().toString();
                 int reverse = Integer.valueOf(ns);
                 if (all.contains(reverse)) {
                     next.add(reverse);
                     if (xx[reverse]==0) {
                         xx[reverse] = step;
                     }
                 }
                 if (all.contains(x+1)) {
                     next.add(x+1);
                     if (xx[x+1]==0) {
                         xx[x + 1] = step;
                     }
                 }
             }
             current = next;
             step++;
         }
         for (int ti = 1; ti <= tn; ti++) {
             int n = scanner.nextInt();
             int x = xx[n];
             String s;
             if (x==Integer.MAX_VALUE) {
                 s = "Case #" + ti + ": NOT POSSIBLE";
             } else {
                 s = "Case #" + ti + ": "+x;
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
