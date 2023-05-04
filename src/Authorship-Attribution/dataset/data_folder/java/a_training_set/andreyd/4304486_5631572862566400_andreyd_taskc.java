import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskC {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 1; i <= t; i++) {
             int n = sc.nextInt();
             int[] friends = readInts(sc, n);
             
             int maxCount = 0;
             boolean[] seen = new boolean[n+1];
             for (int j = 1; j <= n; j++) {
                 if (!seen[j]) {
                     seen[j] = true;
                     int currentCount = walk(friends, j);
                     if (currentCount > maxCount) {
                         maxCount = currentCount;
                     }
                 }
             }
             print(i, maxCount);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static int walk(int[] friends, int start) {
         boolean[] seen = new boolean[friends.length];
         seen[start] = true;
         int count = 0;
         int current = start;
         while (true) {
             if (!seen[friends[current]]) {
                 count++;
                 current = friends[current];
                 seen[current] = true;
             } else {
                 int len = loopLength(friends, current);
                 if (len > 2) {
                     return len;
                 }
                 int countCurrent = countRefs(friends, current);
                 if (countCurrent > 1) {
                     return count + 2;
                 } else {
                     if (count > 1) {
                         return count + 1;
                     } else {
                         int countFCurrent = countRefs(friends, friends[current]);
                         if (countFCurrent > 1) {
                             return 3;
                         } else {
                             return 2;
                         }
                     }
                 }
             }
         }
     }
     
     private static int countRefs(int[] friends, int to) {
         int count = 0;
         for (int i = 1; i < friends.length; i++) {
             if (friends[i] == to) {
                 count++;
             }
         }
         return count;
     }
     
     private static int loopLength(int[] friends, int start) {
         int len = 1;
         int curr = friends[start];
         while (curr != start) {
             len++;
             curr = friends[curr];
         }
         return len;
     }
     
     private static void print(int caseNum, int answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
     
     private static int[] readInts(Scanner sc, int n) {
         int[] result = new int[n+1];
         for (int i = 1; i <= n; i++) {
             result[i] = sc.nextInt();
         }
         return result;
     }
 
 }
