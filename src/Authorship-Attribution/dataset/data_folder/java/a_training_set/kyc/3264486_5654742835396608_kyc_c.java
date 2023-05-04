package codejam2017q;
 
 import java.util.TreeMap;
 import org.junit.Test;
 import lib.CodeJamCommons;
 
 public class C extends CodeJamCommons {
 
     @Test
     public void run() throws Exception {
         file("C-small-1-attempt0");
         int numCases = in.nextInt();
         for (int n = 0; n < numCases; n++) {
             long N = in.nextLong();
             long K = in.nextLong();
             TreeMap<Long, Long> map = new TreeMap<>();
             map.put(N, 1L);
             while (true) {
                 long size = map.lastKey();
                 long num = map.get(size);
                 map.remove(size);
                 if (K <= num) {
                     out.printf("Case #%d: ", n + 1);
                     out.println(size / 2 + " " + (size - 1) / 2);
                     break;
                 }
                 K -= num;
                 addToMap(size / 2, num, map);
                 addToMap((size - 1) / 2, num, map);
             }
         }
     }
 
     void addToMap(long key, long val, TreeMap<Long, Long> map) {
         if (map.containsKey(key))
             map.put(key, map.get(key) + val);
         else
             map.put(key, val);
     }
 }
