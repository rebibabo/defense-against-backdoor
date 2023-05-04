import java.util.*;
 import java.io.*;
 
 public class B {
 
    class Edge {
        int from, to, id;
        int dir;
        int flow;
        
        public Edge(int from, int to, int id) {
            super();
            this.from = from;
            this.to = to;
            this.id = id;
        }
    }
    
    List<Edge>[] graph;
    int[] color;
    
    void dfs(int u) {
        color[u] = 1;
        for (Edge e : graph[u]) {
            int end = u ^ e.from ^ e.to;
            if (color[end] == 0) {
                if (e.from == u) {
                    e.dir = 1;
                } else {
                    e.dir = -1;
                }
                dfs(end);
            } else {
                if (e.from == u) {
                    e.dir = -1;
                } else {
                    e.dir = 1;
                }
            }
        }
    }
    
    void solve() {
        int n = in.nextInt(), m = in.nextInt();
        Edge[] edges = new Edge[m];
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(in.nextInt() - 1, in.nextInt()  -1, i);
            graph[edges[i].from].add(edges[i]);
            graph[edges[i].to].add(edges[i]);
        }
        color = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                dfs(i);
            }
        }
        
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            Edge e;
            if (edges[i].dir == 1) {
                e = new Edge(edges[i].from, edges[i].to, i);
                e.dir = 1;
            } else {
                e = new Edge(edges[i].to, edges[i].from, i);
                e.dir = -1;
            }
            edges[i] = e;
            graph[e.from].add(e);
        }
        
        int[] result = new int[m];
        for (int fix = 0; fix < m; fix++) {
            Edge[] prev = new Edge[n];
            int[] q = new int[n];
            int head = 0, tail = 0;
            int start = edges[fix].to, end = edges[fix].from;
            q[tail++] = start;
            prev[start] = edges[fix];
            while (head < tail) {
                int cur = q[head++];
                for (Edge e : graph[cur]) {
                    if (prev[e.to] == null) {
                        prev[e.to] = e;
                        q[tail++] = e.to;
                    }
                }
            }
            if (prev[end] == null) {
                out.println("IMPOSSIBLE");
                return;
            }
            
            int tmp = end;
            while (tmp != start) {
                Edge e = prev[tmp];
                result[e.id]++;
                tmp = e.from;
            }
            result[fix]++;
        }
        for (int i = 0; i < m; i++) {
            out.print(result[i] * edges[i].dir);
            out.print(' ');
        }
        out.println();
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
        new B().run();
    }
 }
