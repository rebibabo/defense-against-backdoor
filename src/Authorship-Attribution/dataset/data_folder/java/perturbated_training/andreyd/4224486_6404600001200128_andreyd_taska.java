import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskA {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 0; i < t; i++) {
             int n = sc.nextInt();
             int[] m = readInts(sc, n);
             int answer1 = 0;
             for (int j = 1; j < n; j++) {
                 if (m[j] < m[j-1]) {
                     answer1 += (m[j-1] - m[j]);
                 }
             }
             int answer2 = 0;
             int maxSpeed = 0;
             for (int j = 1; j < n; j++) {
                 if (m[j] < m[j-1] && m[j-1] - m[j] > maxSpeed) {
                     maxSpeed = m[j-1] - m[j];
                 }
             }
             for (int j = 1; j < n; j++) {
                 if (m[j-1] > maxSpeed) {
                     answer2 += maxSpeed;
                 }
                 else {
                     answer2 += m[j-1];
                 }
             }            
             print(i+1, answer1, answer2);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, int answer1, int answer2) {
         System.out.println("Case #" + caseNum + ": " + answer1 + " " +answer2);
     }
 
     
     private static int[] readInts(Scanner sc, int n) {
         int[] result = new int[n];
         for (int i = 0; i < n; i++) {
             result[i] = sc.nextInt();
         }
         return result;
     }
 }
