import java.util.*;
 import java.io.*;
 
 public class B {
    FastScanner in;
    PrintWriter out;
 
    public class MinCostMaxFlowGraph {
        public class Edge {
            int from;
            int to;
            public int flow;
            int cap;
            long cost;
            Edge rev;
 
            public Edge(int from, int to, int flow, int cap, long cost) {
                this.from = from;
                this.to = to;
                this.flow = flow;
                this.cap = cap;
                this.cost = cost;
            }
 
            @Override
            public String toString() {
                return "Edge [from=" + from + ", to=" + to + ", flow=" + flow + ", cap=" + cap + ", cost=" + cost + "]";
            }
 
        }
 
        int n;
        ArrayList<Edge>[] edges;
 
        public MinCostMaxFlowGraph(int n) {
            this.n = n;
            edges = new ArrayList[n];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<Edge>();
            }
        }
 
        public Edge addEdge(int from, int to, int cap, long cost) {
            Edge e1 = new Edge(from, to, 0, cap, cost);
            Edge e2 = new Edge(to, from, 0, 0, -cost);
            e1.rev = e2;
            e2.rev = e1;
            edges[from].add(e1);
            edges[to].add(e2);
            return e1;
        }
 
        void dfs(int v, boolean[] was, List<Integer> topSort) {
            was[v] = true;
            for (Edge e : edges[v]) {
                if (e.cap == e.flow || was[e.to]) {
                    continue;
                }
                dfs(e.to, was, topSort);
            }
            topSort.add(v);
        }
 
        public long[] getMinCostMaxFlow(int source, int target) {
            long[] h = new long[n];
            for (boolean changed = true; changed;) {
                changed = false;
                for (int i = 0; i < n; i++) {
                    for (Edge e : edges[i]) {
                        if (e.cap > 0 && h[e.to] > h[e.from] + e.cost) {
                            h[e.to] = h[e.from] + e.cost;
                            changed = true;
                        }
                    }
                }
            }
            Edge[] lastEdge = new Edge[n];
            long[] d = new long[n];
            int flow = 0;
            long cost = 0;
            while (true) {
                dijkstra(source, lastEdge, d, h);
                if (d[target] == Long.MAX_VALUE) {
                    break;
                }
                int addFlow = Integer.MAX_VALUE;
                for (int v = target; v != source;) {
                    Edge e = lastEdge[v];
                    addFlow = Math.min(addFlow, e.cap - e.flow);
                    v = e.from;
                }
                cost += (d[target] + h[target] - h[source]) * addFlow;
                flow += addFlow;
                for (int v = target; v != source;) {
                    Edge e = lastEdge[v];
                    e.flow += addFlow;
                    e.rev.flow -= addFlow;
                    v = e.from;
                }
                for (int i = 0; i < n; i++) {
                    h[i] += d[i] == Long.MAX_VALUE ? 0 : d[i];
                }
            }
            return new long[] { flow, cost };
        }
 
        void dijkstra(int source, Edge[] lastEdge, final long[] d, long[] h) {
            TreeSet<Integer> ts = new TreeSet<Integer>(new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                    if (d[o1] != d[o2]) {
                        return d[o1] < d[o2] ? -1 : 1;
                    }
                    return o1 - o2;
                }
            });
            Arrays.fill(d, Long.MAX_VALUE);
            d[source] = 0;
            ts.add(source);
            while (!ts.isEmpty()) {
                int v = ts.pollFirst();
                for (Edge e : edges[v]) {
                    if (e.flow >= e.cap) {
                        continue;
                    }
                    if (d[e.to] == Long.MAX_VALUE || d[e.to] > d[e.from] + e.cost + h[e.from] - h[e.to]) {
                        if (e.cost + h[e.from] - h[e.to] < 0) {
                            throw new AssertionError();
                        }
                        ts.remove(e.to);
                        d[e.to] = d[e.from] + e.cost + h[e.from] - h[e.to];
                        lastEdge[e.to] = e;
                        ts.add(e.to);
                    }
                }
            }
        }
    }
 
    public void solve() throws IOException {
        int n = in.nextInt();
        int c = in.nextInt();
        int m = in.nextInt();
        int[] p = new int[m];
        int[] b = new int[m];
        ArrayList<Integer> p0 = new ArrayList<>();
        ArrayList<Integer> p1 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            p[i] = in.nextInt() - 1;
            b[i] = in.nextInt() - 1;
            if (b[i] == 0)
                p0.add(p[i]);
            else
                p1.add(p[i]);
        }
        if (p0.size() < p1.size()) {
            ArrayList<Integer> tmp = p0;
            p0 = p1;
            p1 = tmp;
        }
        for (int cntBus = p0.size(); cntBus <= m; cntBus++) {
            int source = 0;
            int target = 2 + p1.size() + cntBus - 1;
            MinCostMaxFlowGraph g = new MinCostMaxFlowGraph(target + 1);
            for (int i = 1; i <= p1.size(); i++) {
                g.addEdge(source, i, 1, 0);
            }
            for (int i = p1.size() + 1; i <= p1.size() + cntBus; i++) {
                g.addEdge(i, target, 1, 0);
            }
            for (int i = 1; i <= p1.size(); i++) {
                for (int j = 1; j <= cntBus; j++) {
                    if (j > p0.size() || p1.get(i - 1) != p0.get(j - 1)) {
                        g.addEdge(i, p1.size() + j, 1, 0);
                    } else if (p0.get(j - 1) != 0) {
                        g.addEdge(i, p1.size() + j, 1, 1);
                    }
                }
            }
            long[] res = g.getMinCostMaxFlow(source, target);
            if (res[0] != p1.size()) {
                continue;
            } else {
                out.println(cntBus + " " + res[1]);
                return;
            }
        }
    }
 
    public void run() {
        try {
            in = new FastScanner(new File("a.in"));
            out = new PrintWriter(new File("a.out"));
 
            int tn = in.nextInt();
            for (int i = 0; i < tn; i++) {
                System.err.println(i);
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
        new B().run();
    }
 }