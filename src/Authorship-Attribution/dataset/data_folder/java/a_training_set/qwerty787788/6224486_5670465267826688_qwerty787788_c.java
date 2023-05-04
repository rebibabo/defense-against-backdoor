import java.io.*;
 import java.util.*;
 
 public class C {
    FastScanner in;
    PrintWriter out;
 
    final String pattern = "1ijk";
    final String[][] mul = new String[][] { { "1", "i", "j", "k" },
            { "i", "-1", "k", "-j" }, { "j", "-k", "-1", "i" },
            { "k", "j", "-i", "-1" }, };
 
    String mul(String s, String t) {
        if (s.length() > 1) {
            String res = mul(s.substring(1), t);
            if (res.length() == 1) {
                return "-" + res;
            }
            return res.substring(1);
        }
        if (t.length() > 1) {
            String res = mul(s, t.substring(1));
            if (res.length() == 1) {
                return "-" + res;
            }
            return res.substring(1);
        }
        return mul[pattern.indexOf(s.charAt(0))][pattern.indexOf(t.charAt(0))];
    }
 
    String pow(String s, long power) {
        if (power == 1) {
            return s;
        }
        String tmp = pow(s, power / 2);
        tmp = mul(tmp, tmp);
        if (power % 2 == 1) {
            tmp = mul(tmp, s);
        }
        return tmp;
    }
 
    String solve(String s) {
        String res = s.substring(0, 1);
        for (int i = 1; i < s.length(); i++) {
            res = mul(res, s.substring(i, i + 1));
        }
        return res;
    }
 
    int findFirst(String s, String need) {
        String res = s.substring(0, 1);
        for (int i = 1; i < s.length(); i++) {
            if (res.equals(need)) {
                return i;
            }
            res = mul(res, s.substring(i, i + 1));
        }
        return s.length();
    }
    
    int findLast(String s, String need) {
        String res = s.substring(0, 1);
        for (int i = 1; i < s.length(); i++) {
            if (res.equals(need)) {
                return i;
            }
            res = mul(s.substring(i, i + 1), res);
        }
        return s.length();
    }
 
    void solve() {
        int tc = in.nextInt();
        for (int t = 0; t < tc; t++) {
            out.print("Case #" + (t + 1) + ": ");
            int l = in.nextInt();
            long x = in.nextLong();
            String s = in.next();
            String need = solve("ijk");
            String have = pow(solve(s), x);
            if (!have.equals(need)) {
                out.println("NO");
            } else {
                final int MAX = 10;
                String myCopy = "";
                for (int i = 0; i < x; i++) {
                    myCopy = myCopy + s;
                }
                int iLength = findFirst(myCopy, "i");
                int kLength = findLast(new StringBuilder(myCopy).reverse()
                        .toString(), "k");
                if (iLength == myCopy.length() || kLength == myCopy.length()
                        || iLength + kLength > x * l) {
                    out.println("NO");
                } else {
                    out.println("YES");
                }
            }
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
        new C().run();
    }
 }