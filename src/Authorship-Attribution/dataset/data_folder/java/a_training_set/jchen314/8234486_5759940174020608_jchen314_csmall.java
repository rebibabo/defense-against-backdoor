import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 
 public class CSmall {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             int N = sc.nextInt();
             sc.nextLine();
             Map<String, Integer> appearances = new HashMap<String, Integer>();
             for (int i = 0; i < N; i++) {
                 String line = sc.nextLine();
                 for (String word : line.split(" ")) {
                     if (!appearances.containsKey(word)) {
                         appearances.put(word, (1 << i));
                     } else {
                         appearances.put(word, (1 << i) | appearances.get(word));
                     }
                 }
             }
             int min = Integer.MAX_VALUE;
             for (int i = 0; i < (1 << (N - 2)); i++) {
                 int bitmask = (i << 2) | 1;
                 int count = 0;
                 for (int appearanceMask : appearances.values()) {
                     int intersection = (bitmask & appearanceMask);
                     if (!(intersection == 0 || intersection == appearanceMask)) {
                         count++;
                     }
                 }
                 min = Math.min(count, min);
             }
 
             System.out.println("Case #" + caseNum + ": " + min);
         }
     }
 }
