package gcj2017.qual.c;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Scanner;
 import java.util.TreeMap;
 
 public class GCJ {
 
    public static void tr(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }
 
    public static void main(String[] args) throws Throwable {
        long start = System.currentTimeMillis();
 
        String pkg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
        String dir = "src/" + pkg;
 
        String filename = "";
 
 
        if (true) { filename = "C-small-1-attempt0.in"; }
 
 
        Scanner sc = null;
        try {
            sc = new Scanner(new File(dir + "/" + filename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter fout = new PrintWriter(new File(dir + "/" + filename + ".res"));
 
        GCJ obj = new GCJ();
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            fout.write(String.format("Case #%d: ", (t + 1)));
            obj.solve(sc, fout);
            fout.flush();
        }
        fout.flush();
        fout.close();
        long end = System.currentTimeMillis();
        tr((end - start) + "ms");
    }
    
    void add(TreeMap<Long,Long> map, long key, long count) {
        Long v = map.getOrDefault(key, 0L);
        map.put(key, v + count);
    }
    void sub(TreeMap<Long,Long> map, long key, long count) {
        Long v = map.getOrDefault(key, 0L);
        if (v - count > 0)
            map.put(key, v - count);
        else
            map.remove(key);
    }
    
    void solve(Scanner sc, PrintWriter out) {
        long N = sc.nextLong();
        long K = sc.nextLong();
        
        TreeMap<Long, Long> map = new TreeMap<>();
        map.put(N, 1L);
        
        long ans1 = 0, ans2 = 0;
        for (; K > 0;) {
            Entry<Long, Long> le = map.lastEntry();
            long len = le.getKey();
            long num = le.getValue();
            
            long k = Math.min(num, K);
            K -= k;
            ans1 = (len    ) / 2;
            ans2 = (len - 1) / 2;
            sub(map, len, k);
            add(map, ans1, k);
            add(map, ans2, k);
        }
        out.println(ans1 + " " + ans2);
    }
    
 }
