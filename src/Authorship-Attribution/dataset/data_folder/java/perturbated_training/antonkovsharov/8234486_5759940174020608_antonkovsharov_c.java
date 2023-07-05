import java.util.*;
 import java.io.*;
 
 public class C {
    FastScanner in;
    PrintWriter out;
 
    public void solve() throws IOException {
        int n = in.nextInt();
        String[][] s = new String[n][];
        int cntWord = 0;
        TreeMap<String, Integer> wordNum = new TreeMap<>();
        int[][] a = new int[n][];
        for (int i = 0; i < n; i++) {
            s[i] = in.br.readLine().split(" ");
            a[i] = new int[s[i].length];
            int p = 0;
            for (String ss : s[i]) {
                int cur = -1;
                if (!wordNum.containsKey(ss)) {
                    cur = cntWord;
                    wordNum.put(ss, cntWord++);
                } else {
                    cur = wordNum.get(ss);
                }
                a[i][p++] = cur;
            }
        }
        int ans = Integer.MAX_VALUE;
        int m = n - 2;
        int countProfiles = (1 << m);
        for (int pr = 0; pr < countProfiles; pr++) {
            int[] eng = new int[cntWord];
            int[] fre = new int[cntWord];
            for (int i = 0; i < a[0].length; i++) {
                eng[a[0][i]]++;
            }
            for (int i = 0; i < a[1].length; i++) {
                fre[a[1][i]]++;
            }
            
            for (int i = 2; i < n; i++) {
                if (((pr >> (i - 2)) & 1) != 0) {
                    for (int x : a[i]) {
                        eng[x]++;
                    }
                } else {
                    for (int x : a[i])
                        fre[x]++;
                }
            }
        
            int cur = 0;
            for (int i = 0; i < cntWord; i++) {
                if (eng[i] > 0 && fre[i] > 0) {
                    cur++;
                }
            }
            ans = Math.min(ans, cur);
        }
        out.println(ans);
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            out = new PrintWriter(new File("a.out"));
 
            int tests = in.nextInt();
            for (int i = 0; i < tests; i++) {
                out.print("Case #" + (i + 1) + ": ");
                solve();
                System.out.println("test " + i);
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