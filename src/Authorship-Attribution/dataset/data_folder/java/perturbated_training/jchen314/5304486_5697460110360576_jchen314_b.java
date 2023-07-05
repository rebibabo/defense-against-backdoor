import java.util.Arrays;
 import java.util.Scanner;
 
 public class B {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             int N = sc.nextInt();
             int P = sc.nextInt();
             int[] R = new int[N];
             for (int i = 0; i < N; i++) {
                 R[i] = sc.nextInt();
             }
             int[][] Q = new int[N][P];
             for (int i = 0; i < N; i++) {
                 for (int j = 0; j < P; j++) {
                     Q[i][j] = sc.nextInt();
                 }
                 Arrays.sort(Q[i]);
             }
 
             int[] indexes = new int[N];
             int kits = 0;
             outer: while (indexes[0] < P) {
                 int min = (Q[0][indexes[0]] * 10 + (11 * R[0] - 1)) / (11 * R[0]);
                 int max = (Q[0][indexes[0]] * 10) / (9 * R[0]);
 
                 middle: for (int servings = min; servings <= max; servings++) {
                     for (int i = 1; i < N; i++) {
                         while (Q[i][indexes[i]] * 10 < 9 * servings * R[i]) {
                             indexes[i]++;
                             if (indexes[i] >= P) {
                                 break outer;
                             }
                         }
                         if (Q[i][indexes[i]] * 10 > 11 * servings * R[i]) {
                             continue middle;
                         }
                     }
                     kits++;
                     for (int i = 0; i < N; i++) {
                         indexes[i]++;
                         if (indexes[i] >= P) {
                             break outer;
                         }
                     }
                     continue outer;
                 }
                 indexes[0]++;
             }
 
             System.out.println("Case #" + caseNum + ": " + kits);
         }
     }
 }
