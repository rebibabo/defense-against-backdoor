import java.util.*;
 import java.io.*;
 
 public class C {
 
    class TwoSAT {
        List<Integer>[] graph;
        List<Integer>[] revGraph;
        int[] comp;
        int N;
 
        public TwoSAT(int n) {
            n *= 2;
            this.N = n;
            graph = new List[n];
            revGraph = new List[n];
            comp = new int[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                revGraph[i] = new ArrayList<>();
            }
        }
 
        void addImplication(int from, int to) {
            graph[from].add(to);
            revGraph[to].add(from);
        }
        
        void addFalse(int var) {
            addImplication(var, (var + N / 2) % N);
        }
        
        void addOr(int v1, int v2) {
            addImplications((v1 + N / 2) % N, v2);
            addImplications((v2 + N / 2) % N, v1);
        }
 
        public void addImplications(int i, int j) {
            addImplication(i, j);
            addImplication((j + N / 2) % N, (i + N / 2) % N);
        }
 
        List<Integer> order = new ArrayList<>();
 
        void dfs(int u) {
            comp[u] = 1;
            for (int v : graph[u]) {
                if (comp[v] == 0) {
                    dfs(v);
                }
            }
            order.add(u);
        }
 
        void dfsRev(int u, int c) {
            comp[u] = c;
            for (int v : revGraph[u]) {
                if (comp[v] == -1) {
                    dfsRev(v, c);
                }
            }
        }
 
        void solve() {
            for (int i = 0; i < N; i++) {
                if (comp[i] == 0) {
                    dfs(i);
                }
            }
            Collections.reverse(order);
            Arrays.fill(comp, -1);
            int comps = 0;
 
            for (int i : order) {
                if (comp[i] == -1) {
                    dfsRev(i, comps++);
                }
            }
        }
    }
 
    
    int[] dx = new int[] { 1, 0, -1, 0 };
    int[] dy = new int[] { 0, 1, 0, -1 };
 
    int[] slashMirror = new int[] {3, 2, 1, 0};
    int[] backslashMirror = new int[] {1, 0, 3, 2};
    
    private boolean[][][] getVisited(char[][] a, int i, int j) {
        int r = a.length, c = a[0].length;
        boolean[][][] result = new boolean[2][r][c];
        for (int startDir = 0; startDir < 4; startDir++) {
            int cx = i + dx[startDir], cy = j + dy[startDir];
            
            int dir = startDir;
            for (int IT = 0; IT < r * c * 4; IT++) {
                if (cx < 0 || cx >= r || cy < 0 || cy >= c || a[cx][cy] == '#') {
                    break;
                }
                
                result[startDir % 2][cx][cy] = true;
                if (a[cx][cy] == '/') {
                    dir = slashMirror[dir];
                } else if (a[cx][cy] == '\\') {
                    dir = backslashMirror[dir];
                }
                cx += dx[dir];
                cy += dy[dir];
            }
        }
        return result;
    }
 
    void solve() {
        int r = in.nextInt(), c = in.nextInt();
        char[][] a = new char[r][c];
        for (int i = 0; i < r; i++) {
            a[i] = in.nextToken().toCharArray();
        }
        int[][] id = new int[r][c];
        int cnt = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (a[i][j] == '|' || a[i][j] == '-') {
                    id[i][j] = cnt++;
                    a[i][j] = '-';
                } else {
                    id[i][j] = -1;
                }
            }
        }
        int N = cnt;
        TwoSAT sat = new TwoSAT(N);
 
        List<Integer>[][] cover = new List[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cover[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (id[i][j] >= 0) {
                    boolean[][][] t = getVisited(a, i, j);
 
                    
                    for (int d = 0; d < 2; d++) {
                        boolean good = true;
                        
                        for (int x = 0; x < r; x++) {
                            for (int y = 0; y < c; y++) {
                                if (t[d][x][y] && id[x][y] >= 0) {
                                    good = false;
                                }
                            }
                        }
                        if (!good) {
                            sat.addFalse(id[i][j] + d * N);
                        } else {
                            for (int x = 0; x < r; x++) {
                                for (int y = 0; y < c; y++) {
                                    if (t[d][x][y] && a[x][y] == '.') {
                                        cover[x][y].add(id[i][j] + d * N);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (a[i][j] != '.') {
                    continue;
                }
                if (cover[i][j].size() > 2) {
                    throw new AssertionError();
                }
                if (cover[i][j].size() == 0) {
                    out.println("IMPOSSIBLE");
                    return;
                }
                if (cover[i][j].size() == 1) {
                    int var = cover[i][j].get(0);
                    sat.addImplication((var + N) % (2 * N), var);
                } else {
                    int var1 = cover[i][j].get(0);
                    int var2 = cover[i][j].get(1);
                    sat.addOr(var1, var2);
                }
            }
        }
 
        sat.solve();
        for (int i = 0; i < N; i++) {
            if (sat.comp[i] == sat.comp[i + N]) {
                out.println("IMPOSSIBLE");
                return;
            }
        }
 
        out.println("POSSIBLE");
        char[][] result = new char[r][c];
        for (int i = 0; i < r; i++) {
            result[i] = a[i].clone();
            for (int j = 0; j < c; j++) {
                if (id[i][j] >= 0) {
                    int k = id[i][j];
                    if (sat.comp[k] < sat.comp[k + N]) {
                        result[i][j] = '-';
                    } else {
                        result[i][j] = '|';
                    }
                }
            }
            out.println(new String(result[i]));
        }
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
