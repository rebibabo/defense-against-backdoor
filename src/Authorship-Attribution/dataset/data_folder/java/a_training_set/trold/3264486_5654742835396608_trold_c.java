
 import java.io.*;
 import java.util.*;
 
 public class C {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    int[] getInts(BufferedReader in) throws IOException {
        String[] words = in.readLine().split(" ");
        int[] ret = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
        return ret;
    }
 
    void add(Map<Long,Long> map, long key, long value) {
        Long old = map.get(key);
        if (old != null) value += old;
        map.put(key, value);
    }
 
    String compute(long N, long K) {
        long len1 = -1, len2 = -1;
        TreeMap<Long,Long> map = new TreeMap<>();
        map.put(N, 1l);
        while (K > 0) {
            Map.Entry<Long,Long> top = map.pollLastEntry();
            long len = top.getKey();
            long count = top.getValue();
            len1 = len / 2;
            len2 = (len - 1) / 2;
            add(map, len1, count);
            add(map, len2, count);
            K -= count;
        }
        return len1 + " " + len2;
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                int[] param = getInts(in);
                out.printf("Case #%d: %s\n", t, compute(param[0], param[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main(String[] args) {
        new C().run(args);
    }
 }
