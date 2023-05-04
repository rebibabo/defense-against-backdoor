import java.io.BufferedInputStream;
 import java.util.ArrayList;
 import java.util.Scanner;
 import java.util.TreeMap;
 
 
 public class TaskB {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 1; i <= t; i++) {
             int n = sc.nextInt();
             int[] heights = readInts(sc, n);
             int[] counts = new int[2501];
             for (int h : heights) {
                 counts[h]++;
             }
             ArrayList<Integer> answer = new ArrayList<>(n);
             for (int j = 1; j < 2501; j++) {
                 if (counts[j] % 2 == 1) {
                     answer.add(j);
                     if (answer.size() == n) {
                         break;
                     }
                 }
             }
             print(i, answer);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, ArrayList<Integer> answer) {
         StringBuilder sb = new StringBuilder();
         sb.append("Case #").append(caseNum).append(":");
         for (Integer i : answer) {
             sb.append(' ').append(i);
         }
         System.out.println(sb.toString());
     }
     
     private static int[] readInts(Scanner sc, int n) {
         int[] result = new int[2*n*n - n];
         for (int i = 0; i < 2*n*n - n; i++) {
             result[i] = sc.nextInt();
         }
         return result;
     }
 
 }
