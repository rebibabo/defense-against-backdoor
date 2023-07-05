import java.io.*;
 import java.util.*;
 
 public class CopyOfC {
    FastScanner in;
    PrintWriter out;
 
    int getId(HashMap<String, Integer> hm, String s) {
        if (hm.containsKey(s)) {
            return hm.get(s);
        }
        hm.put(s, hm.size());
        return hm.size() - 1;
    }
 
    void solve() {
        int tc = in.nextInt();
        for (int t = 0; t < tc; t++) {
            out.print("Case #" + (t + 1) + ": ");
            int n = in.nextInt();
            String[][] s = new String[n][];
            for (int i = 0; i < n; i++) {
                try {
                    String tmp = in.br.readLine();
                    s[i] = tmp.split(" ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            int nn = n - 2;
            int result = Integer.MAX_VALUE;
            HashMap<String, Integer> ids = new HashMap<>();
            int[][] all = new int[n][];
            for (int i = 0; i < n; i++) {
                all[i] = new int[s[i].length];
                for (int j = 0; j < all[i].length; j++) {
                    all[i][j] = getId(ids, s[i][j]);
                }
            }
            boolean[] isFr = new boolean[ids.size()];
            boolean[] isEn = new boolean[ids.size()];
            for (int st = 0; st < 1 << nn; st++) {
                Arrays.fill(isEn, false);
                Arrays.fill(isFr, false);
                for (int ss : all[0]) {
                    isEn[ss] = true;
                }
                for (int ss : all[1]) {
                    isFr[ss] = true;
                }
                for (int i = 0; i < nn; i++) {
                    for (int ss : all[i + 2]) {
                        if (((1 << i) & st) != 0) {
                            isEn[ss] = true;
                        } else {
                            isFr[ss] = true;
                        }
                    }
                }
                int cur = 0;
                for (int i = 0; i < isEn.length; i++) {
                    cur += isEn[i] && isFr[i] ? 1 : 0;
                }
                result = Math.min(result, cur);
            }
            out.println(result);
            System.err.println((t + 1) + "/" + tc + " done");
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
        new CopyOfC().run();
    }
 }