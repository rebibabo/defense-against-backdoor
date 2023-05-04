package gcj.R2_2015.C;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.BitSet;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 
 public class GCJ {
 
    public static void tr(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }
 
    public static void main(String[] args) throws Throwable {
        long start = System.currentTimeMillis();
 
        String pkg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
        String dir = "src/" + pkg;
 
        String filename = "";
 
 
        if (true) { filename = "C-small-attempt0.in"; }
 
 
        Scanner sc = null;
        try {
            sc = new Scanner(new File(dir + "/" + filename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter fout = new PrintWriter(new File(dir + "/" + filename + ".res"));
 
        GCJ obj = new GCJ();
        int TNO = sc.nextInt();
        for (int tno = 0; tno < TNO; tno++) {
            fout.write(String.format("Case #%d: ", (tno + 1)));
            obj.solve(sc, fout);
            fout.flush();
        }
        fout.flush();
        fout.close();
        long end = System.currentTimeMillis();
        tr((end - start) + "ms");
    }
 
    Map<String, Integer> mp = new HashMap<>();
    int getId(String s) {
        if (mp.containsKey(s)) return mp.get(s);
        int n = mp.size();
        mp.put(s, n);
        return n;
    }
    static final int SIZE = 3500;
 
    void solve(Scanner sc, PrintWriter fout) {
        mp.clear();
        int M = sc.nextInt() - 2;
        sc.nextLine();
 
        BitSet en = new BitSet(SIZE);
        BitSet fr = new BitSet(SIZE);
        {
            String[] tokens = sc.nextLine().split(" ");
            for (String word : tokens) {
                en.set(getId(word), true);
            }
        }
        {
            String[] tokens = sc.nextLine().split(" ");
            for (String word : tokens) {
                fr.set(getId(word), true);
            }
        }
        BitSet[] w = new BitSet[M];
        for (int i = 0; i < M; i++) {
            w[i] = new BitSet(SIZE);
            String[] tokens = sc.nextLine().split(" ");
            for (String word : tokens) {
                w[i].set(getId(word), true);
            }
        }
 
        int ans = SIZE;
        for (int set = 0; set < (1 << M); set++) {
            BitSet a = (BitSet)en.clone();
            BitSet b = (BitSet)fr.clone();
 
            for (int i = 0; i < M; i++) {
                if ((set >> i & 1) == 1) {
                    a.or(w[i]);
                } else {
                    b.or(w[i]);
                }
            }
            a.and(b);
 
            int here = a.cardinality();
            ans = Math.min(ans, here);
 
        }
        fout.println(ans);
 
    }
 }
