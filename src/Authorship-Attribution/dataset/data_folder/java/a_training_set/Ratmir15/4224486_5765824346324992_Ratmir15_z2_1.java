package c2015_c.c2015_r1.x;
 
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.HashSet;
 import java.util.Scanner;
 
 
 public class z2_1 {
 
     public static int NOK(int x, int n)
     {
         int j;
         if (x<n) j=n;
         else j=x;
         boolean t=true;
         while (t)
         {
             if ((j%x == 0) && (j%n == 0)) break;
             else j+=1;
         }
         return j;
     }
 
     public static void main(String[] args) throws Exception {
         FileWriter fw = new FileWriter("C:\\output2.txt");
         BufferedWriter out = new BufferedWriter(fw);
         
         String pathname = "C:\\Users\\YC14rp1\\Downloads\\B-small-attempt2.in";
         
         Scanner scanner = new Scanner(new File(pathname));
         int tn = scanner.nextInt();
         scanner.nextLine();
         for (int ti = 1; ti <= tn; ti++) {
             int n = scanner.nextInt();
             int m = scanner.nextInt();
             int[] l2 = readArray(scanner, n);
             int [] l3 = new int[n];
             int pos = 1;
             int last = -1;
             boolean flag = false;
             while (pos<=m) {
                 flag = true;
                 for (int i=0;i<n;i++) {
                     if ((l3[i]==0)) {
                         l3[i] = l2[i];
                         if (pos<=m) {
                             last = i+1;
                         }
                        
                         pos++;
                     } else {
                         flag = false;
                     }
                 }
                 for (int i=0;i<n;i++) {
                     l3[i]--;
                 }
                 if (flag && pos>n+1) {
                    
                     break;
                 }
             }
             if (flag && pos<=m) {
                 int i1 = pos - n - 1;
                 System.out.println("m "+m+ " pn "+i1+" "+ (m%i1));
                 m = m % i1;
                 if (m==0) {
                     m = i1;
                     
                 }
                 pos = 1;
                 l3 = new int[n];
                 while (pos<=m) {
                     for (int i=0;i<n;i++) {
                         if ((l3[i]==0)) {
                             l3[i] = l2[i];
                             if (pos<=m) {
                                 last = i+1;
                             }
                             
                             pos++;
                         }
                     }
                     for (int i=0;i<n;i++) {
                         l3[i]--;
                     }
                 }
             }
             String s;
             s = "Case #" + ti + ": "+last;
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
