import java.util.*;
 import java.io.*;
 
 public class C {
 
    int n;
    int ans;
 
    class Edge {
        int to, start, len;
        boolean vis;
 
        public Edge(int to, int start, int len) {
            super();
            this.to = to;
            this.start = start;
            this.len = len;
        }
 
    }
 
    List<Edge>[] graph;
 
    void go(int u, int len, int cur) {
        if (u == 0 && len == 2 * n) {
            ans = Math.min(ans, cur);
            return;
        }
 
        for (Edge e : graph[u]) {
            if (!e.vis) {
                e.vis = true;
                int day = cur / 24;
                if (cur % 24 > e.start) {
                    day++;
                }
                go(e.to, len + 1, day * 24 + e.start + e.len);
                e.vis = false;
            }
        }
    }
 
    void solve() {
        n = in.nextInt();
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        System.err.println(n);
 
        for (int i = 0; i < 2 * n; i++) {
            int to = in.nextInt() - 1;
            int start = in.nextInt(), len = in.nextInt();
            graph[i / 2].add(new Edge(to, start, len));
        }
        ans = Integer.MAX_VALUE;
        go(0, 0, 0);
        out.println(ans);
    }
 
    FastScanner in;
    PrintWriter out;
 
    void run() {
        in = new FastScanner("input.txt");
        try {
            out = new PrintWriter("output.txt");
        } catch (FileNotFoundException e) {
        }
        int tests = in.nextInt();
        for (int i = 0; i < tests; i++) {
            long startTime = System.currentTimeMillis();
            out.printf("Case #%d: ", i + 1);
            solve();
            System.err.printf("Test #%d solved in %d ms\n", i + 1, System.currentTimeMillis() - startTime);
        }
        out.close();
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
 
        public FastScanner(String s) {
            try {
                br = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                
                e.printStackTrace();
            }
        }
 
        public String nextToken() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                }
            }
            return st.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(nextToken());
        }
 
        public long nextLong() {
            return Long.parseLong(nextToken());
        }
 
        public double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
 
    public static void main(String[] args) {
        new C().run();
    }
 }
