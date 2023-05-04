package exo3;
 
 import java.util.Map.Entry;
 import java.util.NavigableMap;
 import java.util.TreeMap;
 
 public class Exo extends Base {
 
    public static void main(String[] argv) {

        configSmall(0, false);
        try {
            int testCaseCount = input().nextInt();
            for (int i = 1; i <= testCaseCount; ++i) {
                output().println("Case #" + i + ": " + solveTestCase());
            }
        }
        finally {
            done();
        }
    }
    private static String solveTestCase() {
 
        long N = input().nextLong();
        long K = input().nextLong();
 
        NavigableMap<Long, Long> state = new TreeMap<>();
        state.put(N, 1L);
 
        while (true) {
 
            Entry<Long, Long> entry = state.lastEntry();
            long maxSpace = entry.getKey();
            long slotCount = entry.getValue();
 
            Pair pair = new Pair(maxSpace);
 
            if (K <= slotCount) {
                return pair.max + " " + pair.min;
            }
 
            K -= slotCount;
            state.remove(maxSpace);
 
            state.put(pair.max, state.getOrDefault(pair.max, 0L) + slotCount);
            state.put(pair.min, state.getOrDefault(pair.min, 0L) + slotCount);
        }
    }
 
    private static class Pair {
        public final long max;
        public final long min;
 
        public Pair(long space) {
            max = space / 2;
            min = max - 1 + (space % 2);
        }
    }   
 }
