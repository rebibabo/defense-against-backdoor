import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 
 public class B {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             int N = sc.nextInt();
             int C = sc.nextInt();
             int M = sc.nextInt();
 
             Map<Integer, Integer> count = new HashMap<>();
 
             int[] B = new int[M];
             int[] P = new int[M];
             for (int i = 0; i < M; i++) {
                 B[i] = sc.nextInt();
                 P[i] = sc.nextInt();
                 count.merge(B[i], 1, (a, b) -> a + b);
             }
 
             int rides = 0;
             for (int i = 1; i <= N; i++) {
                 int[] min = new int[N + 1];
                 for (int j = 0; j < M; j++) {
                     if (B[j] <= i) {
                         min[i]++;
                     }
                 }
                 rides = Math.max(rides, (min[i] + i - 1) / i);
             }
             int[] counts = new int[C + 1];
             for (int j = 0; j < M; j++) {
                 counts[P[j]]++;
                 rides = Math.max(rides, counts[P[j]]);
             }
 
             int upgrades = 0;
             for (int i : count.values()) {
                 if (i > rides) {
                     upgrades += i - rides;
                 }
             }
 
             System.out.print("Case #" + caseNum + ": ");
             System.out.println(rides + " " + upgrades);
         }
     }
 }
