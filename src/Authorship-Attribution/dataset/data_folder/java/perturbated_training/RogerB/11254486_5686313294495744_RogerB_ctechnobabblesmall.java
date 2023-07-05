package Round1B;
 
 import java.util.Scanner;
 
 public class CTechnobabbleSmall {
     
     static boolean canFake(String[][] topics, boolean[] isFake, int index) {
         String firstWord = topics[index][0];
         String lastWord = topics[index][1];
         boolean canFakeFirst = false;
         boolean canFakeLast = false;
         for (int x = 0; x < topics.length; x++) {
             if (x != index && !isFake[x]) {
                 if (topics[x][0].equals(firstWord)) canFakeFirst = true;
                 if (topics[x][1].equals(lastWord)) canFakeLast = true;
             }
         }
         return canFakeFirst && canFakeLast;
     }
     
     static int count(boolean[] isFake) {
         int count = 0;
         for (int x = 0; x < isFake.length; x++) if (isFake[x]) count++;
         return count;
     }
     
     static int maxFake(String[][] topics, boolean[] isFake, int startIndex) {
         if (startIndex >= topics.length) return count(isFake);
         int greatest = maxFake(topics, isFake, startIndex + 1);
         if (canFake(topics, isFake, startIndex)) {
             isFake[startIndex] = true;
             int alternateCount = maxFake(topics, isFake, startIndex + 1);
             isFake[startIndex] = false;
             greatest = Math.max(greatest, alternateCount);
         }
         return greatest;
     }
     
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int topicCount = sc.nextInt();
             String[][] topics = new String[topicCount][];
             for (int x = 0; x < topicCount; x++) topics[x] = new String[] {sc.next(), sc.next()};
             System.out.printf("Case #%d: %d%n", t, maxFake(topics, new boolean[topicCount], 0));
         }
         sc.close();
     }
 }
