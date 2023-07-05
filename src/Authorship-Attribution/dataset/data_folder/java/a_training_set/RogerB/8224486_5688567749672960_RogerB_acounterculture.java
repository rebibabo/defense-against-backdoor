package Round1B;
 
 import java.util.Scanner;
 import java.util.HashSet;
 import java.util.LinkedList;
 
 public class ACounterCulture {
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int toCount = sc.nextInt();
             HashSet<Integer> visited = new HashSet<Integer>();
             LinkedList<Integer> queue = new LinkedList<Integer>();
             visited.add(1);
             queue.add(1);
             int queueSize = 1;
             int stepsTaken = 1;
             int currItem = 0;
             while (true) {
                 int number = queue.poll();
                 if (number == toCount) {
                     System.out.printf("Case #%d: %d%n", t, stepsTaken);
                     break;
                 }
                 int next = number + 1;
                 if (!visited.contains(next)) {
                     visited.add(next);
                     queue.add(next);
                 }
                 int flipped = flip(number);
                 if (!visited.contains(flipped)) {
                     visited.add(flipped);
                     queue.add(flipped);
                 }
                 currItem++;
                 if (currItem == queueSize) {
                     stepsTaken++;
                     currItem = 0;
                     queueSize = queue.size();
                 }
             }
         }
         sc.close();
     }
     static int flip (int toFlip) {
         int length = (int)Math.ceil(Math.log10(toFlip + 1));
         int flipped = 0;
         for (int x = 0; x < length; x++) {
             int digit = (int) ((toFlip % (Math.pow(10, x + 1))) / Math.pow(10, x));
             flipped += digit * Math.pow(10, length - x - 1);
         }
         return flipped;
     }
 }
