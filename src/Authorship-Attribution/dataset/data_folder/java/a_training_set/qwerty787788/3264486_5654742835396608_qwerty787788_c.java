import java.io.*;
 import java.util.*;
 import java.util.Map.Entry;
 
 public class C {
    FastScanner in;
    PrintWriter out;
 
    void add(TreeMap<Long, Long> map, long key, long value) {
        if (value == 0) {
            return;
        }
        Long current = map.get(key);
        if (current == null) {
            map.put(key, value);
        } else {
            map.put(key, value + current);
        }
    }
 
    void solveOneTest() {
        long n = in.nextLong();
        long k = in.nextLong();
        TreeMap<Long, Long> count = new TreeMap<Long, Long>();
        count.put(n, 1L);
        while (k > 0) {
            Entry<Long, Long> current = count.floorEntry(Long.MAX_VALUE);
            long size = current.getKey();
            count.remove(size);
            k -= current.getValue();
            if (k <= 0) {
                out.println((size / 2) + " " + ((size - 1) / 2));
                return;
            }
            add(count, (size / 2), current.getValue());
            add(count, ((size - 1) / 2), current.getValue());
        }
    }
 
    void solve() {
        int tc = in.nextInt();
        for (int t = 0; t < tc; t++) {
            System.err.println("test " + t);
            out.print("Case #" + (t + 1) + ": ");
            solveOneTest();
        }
    }
 
    void run() {
        try {
            in = new FastScanner(new File("C.in"));
            out = new PrintWriter(new File("C.out"));
 
            solve();
 
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    void runIO() {
 
        in = new FastScanner(System.in);
        out = new PrintWriter(System.out);
 
        solve();
 
        out.close();
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        public FastScanner(InputStream f) {
            br = new BufferedReader(new InputStreamReader(f));
        }
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                String s = null;
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null)
                    return null;
                st = new StringTokenizer(s);
            }
            return st.nextToken();
        }
 
        boolean hasMoreTokens() {
            while (st == null || !st.hasMoreTokens()) {
                String s = null;
                try {
                    s = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null)
                    return false;
                st = new StringTokenizer(s);
            }
            return true;
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
 
    public static void main(String[] args) {
        new C().run();
    }
 }