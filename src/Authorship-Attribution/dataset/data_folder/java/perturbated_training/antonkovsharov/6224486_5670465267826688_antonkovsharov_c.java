import java.util.*;
 import java.io.*;
 
 public class C {
    FastScanner in;
    PrintWriter out;
 
    int mul(int x, int y) {
        int sign = x * y < 0 ? -1 : 1;
        x = Math.abs(x) - 1;
        y = Math.abs(y) - 1;
        int[][] res = new int[][] { { 1, 2, 3, 4 }, { 2, -1, 4, -3 },
                { 3, -4, -1, 2 }, { 4, 3, -2, -1 } };
        return res[x][y] * sign;
    }
 
    int toInt(char c) {
        if (c == 'i')
            return 2;
        else if (c == 'j')
            return 3;
        else if (c == 'k')
            return 4;
        throw new AssertionError();
    }
 
    int pw(int x, long n) {
        if (n == 0)
            return 1;
        int y = pw(x, n / 2);
        y = mul(y, y);
        if (n % 2 == 1)
            y = mul(y, x);
        return y;
    }
    
    public void solve() throws IOException {
        int l = in.nextInt();
        long x = in.nextInt();
        char[] c = in.next().toCharArray();
        int[] a = new int[l];
        int blockMul = 1;
        for (int i = 0; i < l; i++) {
            a[i] = toInt(c[i]);
            blockMul = mul(blockMul, a[i]);
        }
        int allMul = pw(blockMul, x);
        
        if (allMul != -1) {
            out.println("NO");
            return;
        }
 
        TreeSet<Integer> was = new TreeSet<>();
        allMul = 1;
        long firstI = Long.MAX_VALUE;
        findI: for (long i = 0; i < x; i++) {
            if (was.contains(allMul))
                break;
            was.add(allMul);
            for (int j = 0; j < l; j++) {
                allMul = mul(allMul, a[j]);
                if (allMul == 2) {
                    firstI = i * l + j;
                    break findI;
                }
            }
        }
 
        was.clear();
        long lastK = -1;
        allMul = 1;
        findK: for (long i = x - 1; i >= 0; i--) {
            if (was.contains(allMul))
                break;
            was.add(allMul);
            for (int j = l - 1; j >= 0; j--) {
                allMul = mul(a[j], allMul);
                if (allMul == 4) {
                    lastK = i * l + j;
                    break findK;
                }
            }
        }
        if (firstI < lastK) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            out = new PrintWriter(new File("a.out"));
 
            int tests = in.nextInt();
            for (int i = 0; i < tests; i++) {
                out.print("Case #" + (i + 1) + ": ");
                solve();
            }
 
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
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
 
    public static void main(String[] arg) {
        new C().run();
    }
 }