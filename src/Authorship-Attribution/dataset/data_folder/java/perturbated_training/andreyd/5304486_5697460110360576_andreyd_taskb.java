import java.io.BufferedInputStream;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class TaskB {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             int n = sc.nextInt();
             int p = sc.nextInt();
             int[] r = readInts(sc, n);
             int[][] q = readInts(sc, n, p);
             for (int j = 0; j < n; j++) {
                 Arrays.sort(q[j]);
             }
             int[] curr = new int[n];
             Arrays.fill(curr, 0);
             boolean done = false;
             int count = 0;
             int[] nextPossible = new int[n];
             Arrays.fill(nextPossible, 0);
             int ind = n - 1;
             while (!done) {
                 int result = isValid(r, q, curr);
                 if (result < 0) {
                     count++;
                     
                     for (int j = 0; j < n; j++) {
                         if (curr[j] == p - 1) {
                             done = true;
                             break;
                         }
                         curr[j]++;
                     }
                     nextPossible = Arrays.copyOf(curr, n);
                 } else {
                     if (curr[ind] == p - 1) {
                         if (ind > 0 && curr[ind - 1] < p - 1) {
                             curr[ind - 1]++;
                             for (int j = ind; j < n; j++) {
                                 curr[j] = 0;
                             }
                             ind--;
 
                         } else {
                             done = true;
                             break;
                         }
                     } else {
                         curr[ind]++;
                     }
                 }
             }
             print(i, count);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static int isValid(int[] r, int[][] q, int[] ind) {
         int from = 0;
         int to = Integer.MAX_VALUE;
         for (int i = 0; i < r.length; i++) {
             from = Math.max(from, (int) Math.round(Math.ceil(q[i][ind[i]] / (1.1 * r[i]))));
             to = Math.min(to, (int) Math.round(Math.floor(q[i][ind[i]] / (0.9 * r[i]))));
             if (from > to) {
                 return i;
             }
         }
         return -1;
     }
     
     private static void print(int caseNum, int answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
     
     private static int[] readInts(Scanner sc, int n) {
         int[] result = new int[n];
         for (int i = 0; i < n; i++) {
             result[i] = sc.nextInt();
         }
         return result;
     }
     
     private static int[][] readInts(Scanner sc, int n, int m) {
         int[][] result = new int[n][m];
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < m; j++) {
                 result[i][j] = sc.nextInt();
             }
         }
         return result;
     }
 
 }
