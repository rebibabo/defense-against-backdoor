import java.io.File;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class B {
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         Scanner in = new Scanner(new File("problem.in"));
         PrintStream out = new PrintStream(new File("problem.out"));
 
         int T = in.nextInt();
 
         for (int test = 1; test <= T; test++) {
             int N = in.nextInt();
             int P = in.nextInt();
             int[] R = new int[N];
             for (int i = 0; i < N; i++) {
                 R[i] = in.nextInt();
             }
             int[][] Q = new int[N][P];
             for (int i = 0; i < N; i++) {
                 for (int j = 0; j < P; j++) {
                     Q[i][j] = in.nextInt();
                 }
             }
             for (int i = 0; i < N; i++) {
                 Arrays.sort(Q[i]);
             }
             boolean stop = false;
             int[] index = new int[N];
             int result = 0;
             while (!stop) {
                 boolean possible = false;
                 int left = Math.max((int) (Q[0][index[0]] / 1.1 / R[0]) - 1, 1);
                 int right = (int) (Q[0][index[0]] / 0.9 / R[0]) + 1;
 
                 for (int k = left; k <= right; k++) {
                     possible = true;
                     for (int i = 0; i < N; i++) {
                         if (R[i] * k * 0.9 > Q[i][index[i]] || Q[i][index[i]] > R[i] * k * 1.1) {
                             possible = false;
                             break;
                         }
                     }
                     if (possible) {
                         break;
                     }
                 }
                 if (possible) {
                     result++;
                     for (int i = 0; i < N; i++) {
                         index[i]++;
                         if (index[i] == P) {
                             stop = true;
                         }
                     }
                 } else {
                     int t = 0;
                     for (int i = 1; i < N; i++) {
                         if (Q[i][index[i]] / R[i] < Q[t][index[t]] / R[t]) {
                             t = i;
                         }
                     }
                     index[t]++;
                     if (index[t] == P) {
                         stop = true;
                     }
 
                 }
 
             }
 
             out.printf("Case #%d: %d\n", test, result);
         }
     }
 }
