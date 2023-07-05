import static java.lang.Double.parseDouble;
 import static java.lang.Integer.parseInt;
 import static java.lang.Long.parseLong;
 import static java.lang.Math.min;
 import static java.lang.System.exit;
 import static java.util.Arrays.copyOf;
 import static java.util.Arrays.fill;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.StringTokenizer;
 
 public class B {
 
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;
 
    static class Edge {
        final int to, id;
        Edge pair;
        boolean forward;
        int cap;
 
        Edge(int to, int id) {
            this.to = to;
            this.id = id;
        }
    }
 
    static List<Edge> edges[];
    static boolean bad;
    static boolean seen[];
    static int depth[];
 
    static int dfs(int cur, Edge curEdge, int curDepth) {
        seen[cur] = true;
        depth[cur] = curDepth;
        int res = curDepth;
        for (Edge e: edges[cur]) {
            if (e == curEdge) {
                continue;
            }
            int next = e.to;
            if (seen[next]) {
                if (depth[next] < curDepth) {
                    res = min(res, depth[next]);
                    e.forward = true;
                }
            } else {
                e.forward = true;
                res = min(res, dfs(next, e.pair, curDepth + 1));
            }
        }
        if (curEdge != null && res == curDepth) {
            bad = true;
        }
        return res;
    }
 
    static class Flow {
 
        static int n;
        static int edgesLen[], edges[][], capsLen, caps[];
 
        static void init() {
            edgesLen = new int[n];
            edges = new int[n][6];
            capsLen = 0;
            caps = new int[6];
        }
 
        static int addEdge(int from, int to, int capacity, int reverseCapacity) {
            if (caps.length == capsLen) {
                caps = copyOf(caps, (capsLen << 1) + 2);
            }
            caps[capsLen] = capacity;
            caps[capsLen + 1] = reverseCapacity;
            if (edges[from].length == edgesLen[from]) {
                edges[from] = copyOf(edges[from], (edgesLen[from] << 1) + 2);
            }
            edges[from][edgesLen[from]] = to;
            edges[from][edgesLen[from] + 1] = capsLen;
            edgesLen[from] += 2;
            if (edges[to].length == edgesLen[to]) {
                edges[to] = copyOf(edges[to], (edgesLen[to] << 1) + 2);
            }
            edges[to][edgesLen[to]] = from;
            edges[to][edgesLen[to] + 1] = capsLen + 1;
            edgesLen[to] += 2;
            capsLen += 2;
            return capsLen - 2;
        }
 
        static int scaledDinic(int source, int sink) {
            int dist[] = new int[n];
            int curEdge[] = new int[n];
            int queue[] = new int[n];
            int flow = 0;
            for (int scale = 1 << 30; scale > 0; scale >>= 1) {
                while (true) {
                    fill(dist, -1);
                    dist[source] = 0;
                    queue[0] = source;
                    int queueHead = 0;
                    int queueTail = 1;
                    do {
                        int cur = queue[queueHead++];
                        int ndist = dist[cur] + 1;
                        int e[] = edges[cur];
                        for (int i = 0, en = edgesLen[cur]; i < en; i += 2) {
                            if (caps[e[i + 1]] < scale) {
                                continue;
                            }
                            int to = e[i];
                            if (dist[to] < 0) {
                                dist[to] = ndist;
                                queue[queueTail++] = to;
                            }
                        }
                    } while (queueHead < queueTail);
                    if (dist[sink] < 0) {
                        break;
                    }
                    fill(curEdge, 0);
                    int stackSize = 0;
                    int cur = source;
                    int eNum = 0;
                    cur: while (true) {
                        if (cur == sink) {
                            int curFlow = Integer.MAX_VALUE;
                            for (int i = 0; i < stackSize; i++) {
                                int c = queue[i];
                                curFlow = min(curFlow, caps[edges[c][curEdge[c] + 1]]);
                            }
                            for (int i = 0; i < stackSize; i++) {
                                int c = queue[i];
                                int cap = edges[c][curEdge[c] + 1];
                                caps[cap] -= curFlow;
                                caps[cap ^ 1] += curFlow;
                            }
                            flow += curFlow;
                            stackSize = 0;
                            cur = source;
                            eNum = curEdge[source];
                            continue;
                        }
                        int ndist = stackSize + 1;
                        int e[] = edges[cur];
                        for (int en = edgesLen[cur]; eNum < en; eNum += 2) {
                            int next = e[eNum];
                            if (caps[e[eNum + 1]] >= scale && dist[next] == ndist) {
                                curEdge[cur] = eNum;
                                queue[stackSize++] = cur;
                                cur = next;
                                eNum = curEdge[cur];
                                continue cur;
                            }
                        }
                        curEdge[cur] = eNum;
                        if (stackSize == 0) {
                            break;
                        }
                        cur = queue[--stackSize];
                        eNum = curEdge[cur] + 2;
                    }
                }
            }
            return flow;
        }
    }
 
    static void solve() throws Exception {
        int n = nextInt();
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int m = nextInt();
        for (int i = 0; i < m; i++) {
            int a = nextInt() - 1;
            int b = nextInt() - 1;
            Edge ea = new Edge(b, i);
            Edge eb = new Edge(a, ~i);
            ea.pair = eb;
            eb.pair = ea;
            edges[a].add(ea);
            edges[b].add(eb);
        }
        seen = new boolean[n];
        depth = new int[n];
        bad = false;
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                dfs(i, null, 0);
                if (bad) {
                    printCase();
                    out.println("IMPOSSIBLE");
                    return;
                }
            }
        }
        Flow.n = n + 2;
        Flow.init();
        int totalBalance = 0;
        for (int i = 0; i < n; i++) {
            int balance = 0;
            for (Edge e: edges[i]) {
                if (e.forward) {
                    e.cap = Flow.addEdge(i, e.to, n * n - 1, 0);
                    ++balance;
                } else {
                    --balance;
                }
            }
            if (balance > 0) {
                Flow.addEdge(i, n + 1, balance, 0);
                totalBalance += balance;
            } else {
                Flow.addEdge(n, i, -balance, 0);
            }
        }
        if (Flow.scaledDinic(n, n + 1) != totalBalance) {
            throw new AssertionError();
        }
        int ans[] = new int[m];
        for (int i = 0; i < n; i++) {
            for (Edge e: edges[i]) {
                if (e.forward) {
                    if (e.id < 0) {
                        ans[~e.id] = -Flow.caps[e.cap + 1] - 1;
                    } else {
                        ans[e.id] = Flow.caps[e.cap + 1] + 1;
                    }
                }
            }
        }
        printCase();
        for (int i = 0; i < m; i++) {
            if (i > 0) {
                out.print(' ');
            }
            out.print(ans[i]);
        }
        out.println();
    }
 
    static void printCase() {
        out.print("Case #" + test + ": ");
    }
 
    static void printlnCase() {
        out.println("Case #" + test + ":");
    }
 
    static int nextInt() throws IOException {
        return parseInt(next());
    }
 
    static long nextLong() throws IOException {
        return parseLong(next());
    }
 
    static double nextDouble() throws IOException {
        return parseDouble(next());
    }
 
    static String next() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }
 
    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new OutputStreamWriter(System.out));
            int tests = nextInt();
            for (test = 1; test <= tests; test++) {
                solve();
            }
            in.close();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
 }