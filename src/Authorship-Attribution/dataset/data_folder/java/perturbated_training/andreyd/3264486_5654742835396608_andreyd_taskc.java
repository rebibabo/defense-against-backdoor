import java.io.BufferedInputStream;
 import java.util.Map.Entry;
 import java.util.Scanner;
 import java.util.TreeMap;
 
 public class TaskC {
 
     public static void main(String[] args) {
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         for (int i = 1; i <= t; i++) {
             long n = sc.nextLong();
             long k = sc.nextLong();
             CountMap map = new CountMap();
             map.add(n, 1L);
             while (k > 0) {
                 Entry<Long, Long> biggest = map.biggestEntry();
                 Long min = biggest.getKey() % 2 == 0 ? biggest.getKey() / 2 - 1 : biggest.getKey() / 2;
                 Long max = biggest.getKey() / 2;
                 if (biggest.getValue() >= k) {
                     print(i, max, min);
                     break;
                 } else {
                     k -= biggest.getValue();
                     map.remove(biggest.getKey());
                     map.add(min, biggest.getValue());
                     map.add(max, biggest.getValue());
                 }
             }
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     
     private static class CountMap {
         private final TreeMap<Long, Long> map = new TreeMap<>();
         
         public void add(Long key, Long value) {
             Long curr = map.get(key);
             if (curr == null) {
                 map.put(key, value);
             } else {
                 map.put(key, curr + value);
             }
         }
         
         public void sub(Long key, Long value) {
             Long curr = map.get(key);
             if (curr == null) {
                 System.err.println("No value for " + key);
             } else {
                 map.put(key, curr - value);
             }
         }
         
         public Entry<Long, Long> biggestEntry() {
             return map.lastEntry();
         }
         
         public Long remove(Long key) {
             return map.remove(key);
         }
         
         public Long get(Long key) {
             return map.get(key);
         }
     }
     
     private static void print(int caseNum, Long max, Long min) {
         System.out.println("Case #" + caseNum + ": " + max + " " + min);
     }
 
 }