import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskA {
     
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 0; i < t; i++) {
             int n = sc.nextInt();
             int[] counts = readInts(sc, n);
             int maxInd = max(counts, -1);
             int secondInd = max(counts, maxInd);
             StringBuilder sb = new StringBuilder();
             printLetters(sb, maxInd, counts[maxInd] - counts[secondInd]);
             counts[maxInd] = counts[secondInd];
             for (int j = 0; j < n; j++) {
                 if (j != maxInd && j != secondInd) {
                     printLetters(sb, j, counts[j]);
                 }
             }
             String s = " " + letter(maxInd) + letter(secondInd);
             for (int j = 0; j < counts[maxInd]; j++) {
                 sb.append(s);
             }
             print(i+1, sb.toString());
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static char letter(int ind) {
         return (char) ('A' + ind);
     }
     
     private static void printLetters(StringBuilder sb, int ind, int count) {
         String s = " " + letter(ind) + letter(ind);
         for (int i = 0; i < count / 2; i++) {
             sb.append(s);
         }
         if (count % 2 == 1) {
             sb.append(' ').append(letter(ind));
         }
     }
     
     private static int max(int[] array, int missing) {
         int maxInd = missing == 0 ? 1 : 0;
         for (int i = 1; i < array.length; i++) {
             if (i != missing) {
                 if (array[i] > array[maxInd]) {
                     maxInd = i;
                 }
             }
         }
         return maxInd;
     }
     
     private static void print(int caseNum, String s) {
         System.out.println("Case #" + caseNum + ":" + s);
     }
     
     private static int[] readInts(Scanner sc, int n) {
         int[] result = new int[n];
         for (int i = 0; i < n; i++) {
             result[i] = sc.nextInt();
         }
         return result;
     }
 
 }
