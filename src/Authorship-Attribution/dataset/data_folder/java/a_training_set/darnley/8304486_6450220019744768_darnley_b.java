import java.io.*;
 import java.util.*;
 import java.util.concurrent.*;
 
 public class B {
    String NO = "IMPOSSIBLE";
    
    String solve() {
        mark = new boolean[n];
        par = new int[n];
        hei = new int[n];
        parEdge = new Edge[n];
        Arrays.fill(par, -1);
        for (int i = 0; i < n; i++) {
            if (mark[i]) {
                continue;
            }
            dfs(i);
        }
        for (int i = 0; i < n; i++) {
            for (Edge edge : nei[i]) {
                if (edge.tree || edge.rev) {
                    continue;
                }
                int a = edge.from;
                int b = edge.to;
                Edge lng = edge;
                if (hei[a] > hei[b]) {
                    int t = a; a = b; b = t;
                    lng = edge.back;
                }
                while (b != a) {
                    parEdge[b].inc();
                    b = par[b];
                }
                lng.back.inc();
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (allEdges[i].val == 0) {
                return NO;
            }
            ans.append(" ").append(allEdges[i].val);
        }
        return ans.toString().trim();
    }
    
    void dfs(int v) {
        mark[v] = true;
        for (Edge e : nei[v]) {
            int u = e.to;
            if (mark[u]) {
                continue;
            }
            par[u] = v;
            hei[u] = hei[v] + 1;
            parEdge[u] = e;
            e.tree = true;
            e.back.tree = true;
            dfs(u);
        }
    }
 
    int n, m;
    boolean[] mark;
    int[] par;
    Edge[] parEdge;
    Edge[] allEdges;
    int[] hei;
    ArrayList<Edge>[] nei;
    
    public B(Scanner in) {
        n = in.nextInt();
        m = in.nextInt();
        nei = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            nei[i] = new ArrayList<>();
        }
        allEdges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            Edge edge = new Edge(a, b, i, false);
            Edge edge2 = new Edge(b, a, i, true);
            nei[a].add(edge);
            nei[b].add(edge2);
            edge.back = edge2;
            edge2.back = edge;
            allEdges[i] = edge;
        }
    }
    
    static class Edge {
        int from, to;
        int id;
        boolean rev;
        boolean tree;
        Edge back;
        int val;
        
        public Edge(int from, int to, int id, boolean rev) {
            this.from = from;
            this.to = to;
            this.id = id;
            this.rev = rev;
        }
 
        public void inc() {
            val++;
            back.val--;
        }
    }
    
    public static void main(String[] args) throws Exception {
        int nThreads = 4;
        
        String fileNameSuffix = "";
        String formatOut = "Case #%2$d: %1$s";
        String formatSystemOut = formatOut;
 
 
 
 
        
        String fileName = B.class.getSimpleName().replaceFirst("_.*", "").toLowerCase() + fileNameSuffix;
        String inputFileName = fileName + ".in";
        String outputFileName = fileName + ".out";
        
        Locale.setDefault(Locale.US);
        Scanner in = new Scanner(new File(inputFileName));
        PrintWriter out = new PrintWriter(outputFileName);
        int tests = in.nextInt(); in.nextLine();
        nThreads = Math.min(nThreads, tests);
        @SuppressWarnings("unchecked")
        Callable<String>[] callables = new Callable[tests];
        for (int t = 0; t < tests; t++) {
            final B testCase = new B(in);
            final int testCaseNumber = t + 1;
            callables[t] = new Callable<String>() {
                @Override
                public String call() {
                    String answer = testCase.solve();
                    System.out.println(String.format(formatSystemOut, answer, testCaseNumber));
                    return String.format(formatOut, answer, testCaseNumber);
                }
            };
        }
        try {
            if (nThreads > 1) {
                ExecutorService executor = Executors.newFixedThreadPool(4);
                @SuppressWarnings("unchecked")
                Future<String>[] outputs = new Future[tests];
                for (int t = 0; t < tests; t++) {
                    outputs[t] = executor.submit(callables[t]);
                }
                for (int t = 0; t < tests; t++) {
                    out.println(outputs[t].get());
                }
                executor.shutdown();
            } else {
                for (int t = 0; t < tests; t++) {
                    out.println(callables[t].call());
                }
            }
        } catch (Exception e) {
            System.out.flush();
            System.err.flush();
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("COMPLETE");
        in.close();
        out.close();
    }
 }
