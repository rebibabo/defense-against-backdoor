import java.io.*;
 import java.util.*;
 
 public class Biligual2 {
 
    FastScanner in;
    PrintWriter out;
 
    void solve() {
        int n = in.nextInt();
 
        List<String>[] a = new List[n];
        Map<String, Integer> all = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String line = null;
            try {
                line = in.br.readLine();
            } catch (IOException e) {
                
                e.printStackTrace();
            }
            String[] as = line.split(" ");
            a[i] = new ArrayList<>();
            for (String b : as) {
                a[i].add(b);
                all.put(b, -1);
            }
        }
 
        int num = 0;
        for (String t : all.keySet()) {
            all.put(t, num++);
        }
 
        int N = n + 2 * num;
 
        graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
 
        for (int i = 0; i < n; i++) {
            for (String word : a[i]) {
                int pos = all.get(word);
 
                addEdge(i, pos + n, INF);
                addEdge(n + num + pos, i, INF);
            }
        }
        for (int i = 0; i < num; i++) {
            addEdge(n + i, n + num + i, 1);
        }
        
        int ans = 0;
        vis = new boolean[N];
        while (dfs(0)) {
            ans++;
            Arrays.fill(vis, false);
        }
        out.println(ans);
    }
 
    boolean dfs(int u) {
        if (u == 1) {
            return true;
        }
        vis[u] = true;
        for (Edge v : graph[u]) {
            if (v.flow < v.cap && !vis[v.to]) {
                boolean t = dfs(v.to);
                if (t) {
                    v.flow++;
                    v.rev.flow--;
                    return true;
                }
            }
        }
        return false;
    }
 
    boolean[] vis;
 
    int INF = Integer.MAX_VALUE / 3;
 
    List<Edge>[] graph;
 
    void addEdge(int from, int to, int cap) {
        Edge st = new Edge(from, to, cap);
        Edge rev = new Edge(to, from, 0);
        st.rev = rev;
        rev.rev = st;
        graph[from].add(st);
        graph[to].add(rev);
    }
 
    class Edge {
        int from, to, flow, cap;
        Edge rev;
 
        public Edge(int from, int to, int cap) {
            super();
            this.from = from;
            this.to = to;
            this.cap = cap;
        }
    }
 
    void run() {
        try {
            in = new FastScanner("input.txt");
            out = new PrintWriter("output.txt");
            int T = in.nextInt();
            for (int i = 1; i <= T; i++) {
                long time = System.currentTimeMillis();
                out.printf("Case #%d: ", i);
                solve();
                System.err.println("Test #" + i + " done in "
                        + (System.currentTimeMillis() - time) + " ms");
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner(String s) {
            try {
                br = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                
                e.printStackTrace();
            }
        }
 
        String nextToken() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() {
            return Integer.parseInt(nextToken());
        }
 
        long nextLong() {
            return Long.parseLong(nextToken());
        }
 
        double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
 
    public static void main(String[] args) {
        new Biligual2().run();
    }
 }
