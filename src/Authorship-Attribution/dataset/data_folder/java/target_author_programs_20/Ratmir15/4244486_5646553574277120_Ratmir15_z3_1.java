package c2015.r1_3;
 
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Scanner;
 
 public class Z3_1 {
 
     public static void main(String[] args) throws Exception {
         FileWriter fw = new FileWriter("C:\\output.txt");
         BufferedWriter out = new BufferedWriter(fw);
         
         String pathname = "C:\\Users\\YC14rp1\\Downloads\\C-small-attempt3.in";
         
         Scanner scanner = new Scanner(new File(pathname));
         int tn = scanner.nextInt();
         scanner.nextLine();
         for (int ti = 1; ti <= tn; ti++) {
             int c = scanner.nextInt();
             int d = scanner.nextInt();
             int v = scanner.nextInt();
             int[] arr = readArray(scanner, d);
             List<Integer> x = new ArrayList<Integer>();
             for (int i1 : arr) {
                 x.add(i1);
             }
             boolean ch_main = true;
             for (int sum = 1; sum <= v; sum++) {
                 ch_main = ch_main && check(d, sum, x);
             }
             int res = -1;
             if (ch_main) {
                 res = 0;
             }
             if (ti==39) {
                 System.out.println("x");
             }
             if (res == -1) {
                 for (int i = 1; i <= v; i++) {
                     if (!x.contains(i)) {
                         List<Integer> toCheck = new ArrayList<Integer>();
                         for (int i1 : arr) {
                             toCheck.add(i1);
                         }
                         toCheck.add(i);
                         boolean check = true;
                         for (int sum = 1; sum <= v; sum++) {
                             check = check && check(d + 1, sum, toCheck);
                         }
                         if (check) {
                             res = 1;
                         }
                     }
                 }
             }
             if (res == -1) {
                 for (int i = 1; i <= v; i++) {
                     if (!x.contains(i)) {
                         for (int i1 = i + 1; i1 <= v; i1++) {
                             if (!x.contains(i1)) {
                                 List<Integer> toCheck = new ArrayList<Integer>();
                                 for (int ix1 : arr) {
                                     toCheck.add(ix1);
                                 }
                                 toCheck.add(i);
                                 toCheck.add(i1);
                                 boolean check = true;
                                 for (int sum = 1; sum <= v; sum++) {
                                     check = check && check(d + 2, sum, toCheck);
                                 }
                                 if (check) {
                                     res = 2;
                                 }
                             }
                         }
                     }
                 }
             }
             if (res == -1) {
                 for (int i = 1; i <= v; i++) {
                     if (!x.contains(i)) {
                         for (int i1 = i + 1; i1 <= v; i1++) {
                             if (!x.contains(i1)) {
                                 for (int i2 = i1 + 1; i2 <= v; i2++) {
                                     if (!x.contains(i2)) {
                                         List<Integer> toCheck = new ArrayList<Integer>();
                                         for (int ix1 : arr) {
                                             toCheck.add(ix1);
                                         }
                                         toCheck.add(i);
                                         toCheck.add(i1);
                                         toCheck.add(i2);
                                         boolean check = true;
                                         for (int sum = 1; sum <= v; sum++) {
                                             check = check && check(d + 3, sum, toCheck);
                                         }
                                         if (check) {
                                             res = 3;
                                         }
                                     }
                                 }
                             }
                         }
                     }
                 }
             }
             if (res == -1) {
                 for (int i = 1; i <= v; i++) {
                     if (!x.contains(i)) {
                         for (int i1 = i + 1; i1 <= v; i1++) {
                             if (!x.contains(i1)) {
                                 for (int i2 = i1 + 1; i2 <= v; i2++) {
                                     if (!x.contains(i2)) {
                                         for (int i3 = i2 + 1; i3 <= v; i3++) {
                                             if (!x.contains(i3)) {
                                                 List<Integer> toCheck = new ArrayList<Integer>();
                                                 for (int ix1 : arr) {
                                                     toCheck.add(ix1);
                                                 }
                                                 toCheck.add(i);
                                                 toCheck.add(i1);
                                                 toCheck.add(i2);
                                                 toCheck.add(i3);
                                                 boolean check = true;
                                                 for (int sum = 1; sum <= v; sum++) {
                                                     check = check && check(d + 4, sum, toCheck);
                                                 }
                                                 if (check) {
                                                     res = 4;
                                                 }
                                             }
                                         }
                                     }
                                 }
                             }
                         }
                     }
                 }
             }
             if (res == -1) {
                 for (int i = 1; i <= v; i++) {
                     if (!x.contains(i)) {
                         for (int i1 = i + 1; i1 <= v; i1++) {
                             if (!x.contains(i1)) {
                                 for (int i2 = i1 + 1; i2 <= v; i2++) {
                                     if (!x.contains(i2)) {
                                         for (int i3 = i2 + 1; i3 <= v; i3++) {
                                             if (!x.contains(i3)) {
                                                 for (int i4 = i3 + 1; i4 <= v; i4++) {
                                                     if (!x.contains(i4)) {
                                                         List<Integer> toCheck = new ArrayList<Integer>();
                                                         for (int ix1 : arr) {
                                                             toCheck.add(ix1);
                                                         }
                                                         toCheck.add(i);
                                                         toCheck.add(i1);
                                                         toCheck.add(i2);
                                                         toCheck.add(i3);
                                                         toCheck.add(i4);
                                                         boolean check = true;
                                                         for (int sum = 1; sum <= v; sum++) {
                                                             check = check && check(d + 5, sum, toCheck);
                                                         }
                                                         if (check) {
                                                             res = 5;
                                                         }
                                                     }
                                                 }
                                             }
                                         }
                                     }
                                 }
                             }
                         }
                     }
                 }
             }
             if (res==-1) {
                 System.out.println("dfd");
             }
             String s;
             s = "Case #" + ti + ": " + res;
             System.out.println(s);
             out.write(s);
             out.write("\n");
         }
         out.close();
     }
 
     public static boolean check(int l, int sumToCheck, List<Integer> array) {
         int min = Integer.MAX_VALUE;
         for (int mask = 0; mask < 2 << l; mask++) {
             int cnt = 0;
             for (int i = 0; i < l; i++) {
                 if ((mask & 1 << i) != 0) {
                     cnt += array.get(i);
                 }
             }
             if (cnt == sumToCheck) {
                 return true;
             }
         }
         return false;
     }
 
     private static int[][] read2DArray(Scanner scanner, int n, int m) {
         int[][] m1 = new int[n][m];
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < m; j++) {
                 m1[i][j] = scanner.nextInt();
             }
         }
         return m1;
     }
 
     
     private static int[] readIntArray(Scanner scanner, int n) {
         int[] a = new int[n];
         for (int i = 0; i < n; i++) {
             a[i] = scanner.nextInt();
         }
         return a;
     }
 
     
     private static void readGraphAsMatrix(Scanner scanner, int n, int[][] matrix) {
         for (int i = 0; i < n - 1; i++) {
             int x = scanner.nextInt();
             int y = scanner.nextInt();
             matrix[x - 1][y - 1] = 1;
             matrix[y - 1][x - 1] = 1;
         }
     }
 
     
     private static int[] readArray(Scanner scanner, int n) {
         int[] l2 = new int[n];
         for (int i = 0; i < n; i++) {
             l2[i] = scanner.nextInt();
         }
         return l2;
     }
 
     
     private static HashSet<String> readSet(Scanner scanner, int n) {
         String[] l1 = new String[n];
         HashSet<String> set1 = new HashSet<String>();
         for (int i = 0; i < n; i++) {
             l1[i] = scanner.next();
             set1.add(l1[i]);
         }
         return set1;
     }
 
 }
