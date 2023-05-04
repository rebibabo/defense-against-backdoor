import java.io.BufferedInputStream;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 
 public class TaskB {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 0; i < t; i++) {
             int b = sc.nextInt();
             int n = sc.nextInt();
             int[] m = readInts(sc, b);
             if (b == 1) {
                 print(i+1, 1);
                 continue;
             }
 
 
 
 
 
 
 
 
 
 
 
             int lcm = lcm(m);
             int number = 0;
             for (int j = 0; j < b; j++) {
                 number += lcm / m[j];
             }
             n = n % number;
             if (n == 0) {
                 n = number;
             }
             PriorityQueue<Barber> queue = new PriorityQueue<Barber>(b, new Comparator<Barber>() {
                 @Override
                 public int compare(Barber o1, Barber o2) {
                     if (o1.finishTime != o2.finishTime) {
                         return o1.finishTime - o2.finishTime;
                     }
                     else {
                         return o1.number - o2.number;
                     }
                 }
             });
             for (int j = 0; j < b; j++) {
                 queue.add(new Barber(j, 0));
             }
             Barber curr = null;
             for (int j = 0; j < n; j++) {
                 curr = queue.poll();
                 curr.finishTime += m[curr.number];
                 queue.add(curr);
             }
             print(i+1, curr.number + 1);
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
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
     
     private static class Barber {
         public int number;
         public int finishTime;
         public Barber(int number, int finishTime) {
             this.number = number;
             this.finishTime = finishTime;
         }
     }
     
     private static int lcm(int[] array) {
         int result = lcm(array[0], array[1]);
         for (int i = 2; i < array.length; i++) {
             result = lcm(result, array[i]);
         }
         return result;
     }
     
     private static int lcm(int a, int b) {
         return a * b / gcd(a, b); 
     }
     
     private static int gcd(int a, int b) {
         if (b > a) {
             int t = a;
             a = b;
             b = t;
         }
         if (a % b == 0) {
             return b;
         }
         return gcd(b, a % b);
     }
 
 }
