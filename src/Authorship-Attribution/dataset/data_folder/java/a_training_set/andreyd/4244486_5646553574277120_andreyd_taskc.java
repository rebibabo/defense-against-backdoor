import java.io.BufferedInputStream;
 import java.util.Arrays;
 import java.util.Scanner;
 
 
 public class TaskC {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 1; i <= t; i++) {
             int c = sc.nextInt();
             int d = sc.nextInt();
             int v = sc.nextInt();
             int[] coins = readInts(sc, d);
             Arrays.sort(coins);
             int answer = 0;
             if (c == 1) {
                 boolean[] reachable = new boolean[v+1];
                 for (int j = 0; j < d; j++) {
                     add(reachable, coins[j]);
                 }
                 for (int j = 1; j <= v; j++) {
                     if (!reachable[j]) {
                         add(reachable, j);
                         answer++;
                     }
                 }
             }
             print(i, answer);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void add(boolean[] reachable, int newCoin) {
         for (int i = reachable.length - 1; i > 0; i--) {
             if (reachable[i] && i + newCoin < reachable.length) {
                 reachable[i + newCoin] = true;
             }
         }
         reachable[newCoin] = true;
     }
     
     private static void print(int caseNum, long answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
     
     private static int[] readInts(Scanner sc, int n) {
         int[] result = new int[n];
         for (int i = 0; i < n; i++) {
             result[i] = sc.nextInt();
         }
         return result;
     }
 
 }
