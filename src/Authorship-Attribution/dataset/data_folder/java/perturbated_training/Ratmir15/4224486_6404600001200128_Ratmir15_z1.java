package c2015_c.c2015_r1.x;
 
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.HashSet;
 import java.util.Scanner;
 
 
 public class z1 {
 
     public static void main(String[] args) throws Exception {
         FileWriter fw = new FileWriter("C:\\output.txt");
         BufferedWriter out = new BufferedWriter(fw);
         
         String pathname = "C:\\Users\\YC14rp1\\Downloads\\A-small-attempt0 (1).in";
         
         Scanner scanner = new Scanner(new File(pathname));
         int tn = scanner.nextInt();
         scanner.nextLine();
         for (int ti = 1; ti <= tn; ti++) {
             int n = scanner.nextInt();
             int[] l2 = readArray(scanner, n);
             
             
             int s1 = 0;
             int mx = 0;
             for (int i=1;i<n;i++) {
                 if (l2[i]<l2[i-1]) {
                     int delta = l2[i - 1] - l2[i];
                     s1 += delta;
                     if (delta>mx) {
                         mx = delta;
                     }
                 }
             }
             int mx2 = 0;
             int cr=0;
             for (int i=1;i<n;i++) {
                 cr = l2[i-1];
                 if (l2[i-1]>mx) {
                     mx2+=mx;
                 } else {
                     mx2 +=l2[i-1];
                 }
             }
             String s;
             s = "Case #" + ti + ": "+s1+" "+mx2;
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
 
     
     private static int[] readArray(Scanner scanner, int n) {
         int[] l2 = new int[n];
         for (int i=0;i<n;i++) {
             l2[i] = scanner.nextInt();
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
