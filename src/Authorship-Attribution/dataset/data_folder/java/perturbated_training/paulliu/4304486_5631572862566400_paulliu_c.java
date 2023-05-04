
 
 
 import java.util.*;
 import java.io.*;
 import java.math.*;
 
 class Main {
     private Scanner stdin = new Scanner(System.in);
     private java.util.logging.Logger logger = null;
     public static String loggerName = "MainLogger";
 
     
     private void init() {
    
     }
 
     
     private int input() {
    int ret=0;
    String com1;
    int T;
 
    if (stdin.hasNextInt()) {
        T = stdin.nextInt();
    } else {
        return ret;
    }
 
    for (int t1=0; t1<T; t1++) {
        int N;
        if (stdin.hasNextInt()) {
        N = stdin.nextInt();
        } else {
        return ret;
        }
        int[] table = new int[N];
        for (int i=0; i<N; i++) {
        if (stdin.hasNextInt()) {
            table[i] = stdin.nextInt()-1;
        } else {
            return ret;
        }
        }
        solve(table);
        
    }
 
    ret=0;
        return ret;
     }
 
     
     private void solve(int[] table) {
    int N = table.length;
    int maxN = -1;
 
    for (int n1=N; n1>=1; n1--) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<n1; i++) {
        list.add(new Integer(i));
        }
        while (true) {
        for (int i=1; i<=list.size(); i++) {
            
            if (logger != null) {
            StringBuffer sb1 = new StringBuffer();
            for (int j=0; j<i; j++) {
                sb1.append(" ");
                sb1.append(list.get(j).toString());
            }
            logInfo("Check %1$s",sb1.toString());
            }
            boolean checkFlag=true;
            for (int j=0; j<i; j++) {
            int prev = (j+(i-1))%i;
            int next = (j+1)%i;
            int people = list.get(j).intValue();
            int prevpeople = list.get(prev).intValue();
            int nextpeople = list.get(next).intValue();
            if (table[people] == prevpeople || table[people]==nextpeople) {
            } else {
                checkFlag=false;
                break;
            }
            }
            if (checkFlag) {
            if (i>maxN) {
                maxN = i;
            }
            }
        }
        boolean nextFlag = Permutation.next_permutation(list);
        if (!nextFlag) {
            break;
        }
        }
    }
    output(maxN);
     }
 
     private int output_N=0;
     
     private void output(int X) {
    output_N++;
    
    System.out.format("Case #%1$d: %2$d%n",output_N,X);
     }
 
 
     
     public void logInfo(String a, Object... args) {
    if (logger != null) {
        logger.info(String.format(a,args));
    }
     }
 
     public void begin() {
    this.logger = java.util.logging.Logger.getLogger(Main.loggerName);
    if (this.logger.getLevel() != java.util.logging.Level.INFO) {
        this.logger = null;
    }
    init();
    while (input()==1) {
    }
     }
 
     public void unittest() {
    this.logger = java.util.logging.Logger.getLogger(Main.loggerName);
     }
 
     public static void main (String args[]) {
    Main myMain = new Main();
    if (args.length >= 1 && args[0].equals("unittest")) {
        myMain.unittest();
        return;
    }
    java.util.logging.Logger.getLogger(Main.loggerName).setLevel(java.util.logging.Level.SEVERE);
    for (int i=0; args!=null && i<args.length; i++) {
        if (args[i].equals("debug")) {
        java.util.logging.Logger.getLogger(Main.loggerName).setLevel(java.util.logging.Level.INFO);
        }
    }
    myMain.begin();
     }
 }
 
 
 
 class Graph <VertexType, WeightType extends java.lang.Number> {
     private java.util.LinkedHashMap<VertexType, java.lang.Integer> vertexs;
     private java.util.ArrayList<VertexType> vertexsArray;
 
     private java.util.ArrayList < java.util.LinkedHashMap < java.lang.Integer, WeightType > > edges;
 
     public Graph() {
    vertexs = new java.util.LinkedHashMap<VertexType, java.lang.Integer>();
    vertexsArray = new java.util.ArrayList<VertexType>();
    edges = new java.util.ArrayList < java.util.LinkedHashMap < java.lang.Integer, WeightType > > ();
     }
 
     
     public int size() {
    return vertexsArray.size();
     }
 
     
     public void addVertex(VertexType vertex) {
    int s = vertexs.size();
    if (!vertexs.containsKey(vertex)) {
        vertexs.put(vertex,new java.lang.Integer(s));
        vertexsArray.add(vertex);
        java.util.LinkedHashMap < java.lang.Integer, WeightType > tmp1 = new java.util.LinkedHashMap<java.lang.Integer, WeightType>();
        edges.add(tmp1);
    }
     }
 
     
     public Integer getVertexIndexInteger(VertexType vertex) {
    Integer ret = vertexs.get(vertex);
    return ret;
     }
 
     
     public int getVertexIndex(VertexType vertex) {
    int ret=-1;
    java.lang.Integer index = getVertexIndexInteger(vertex);
    if (index == null) {
        return ret;
    }
    ret = index.intValue();
    return ret;
     }
 
     
     public java.util.ArrayList<VertexType> getVertexs() {
    return vertexsArray;
     }
 
     
     public VertexType getVertexByIndex(int index) {
    if (index < 0 || index >= vertexsArray.size()) {
        return null;
    }
    return vertexsArray.get(index);
     }
 
     
     public void addEdge(VertexType from, VertexType to, WeightType weight) {
    int iFrom = getVertexIndex(from);
    java.lang.Integer iTo = getVertexIndexInteger(to);
    edges.get(iFrom).put(iTo, weight);
     }
 
     
     public WeightType getEdge(VertexType from, VertexType to) {
    int iFrom = getVertexIndex(from);
    java.lang.Integer iTo = getVertexIndexInteger(to);
    return getEdgeByIndex(iFrom, iTo);
     }
 
     
     public WeightType getEdgeByIndex(int i, Integer j) {
    return edges.get(i).get(j);
     }
 
     
     public WeightType removeEdge(VertexType from, VertexType to) {
    int iFrom = getVertexIndex(from);
    java.lang.Integer iTo = getVertexIndexInteger(to);
    return edges.get(iFrom).remove(iTo);
     }
 
     
     public java.util.Set<java.lang.Integer> getEdges(VertexType v) {
    int iv = getVertexIndex(v);
    return getEdgesByIndex(iv);
     }
 
     
     public java.util.Set<java.lang.Integer> getEdgesByIndex(int index) {
    return edges.get(index).keySet();
     }
 
     class BFSAnswer {
    public int[] father;
    public int[] depth;
    public boolean[] visited;
     }
     public BFSAnswer bfs(VertexType startVertexN) {
    return bfs(startVertexN, -1);
     }
     public BFSAnswer bfs(VertexType startVertexN, int maxDepth) {
    int N = this.size();
    BFSAnswer ret = new BFSAnswer();
    ret.father = new int[N];
    ret.depth = new int[N];
         ret.visited = new boolean[N];
    java.util.Arrays.fill(ret.father, -1);
    java.util.Arrays.fill(ret.depth, -1);
    java.util.Arrays.fill(ret.visited, false);
 
    int startVertex = getVertexIndex(startVertexN);
    ret.father[startVertex] = -2;
    ret.depth[startVertex] = 0;
    ret.visited[startVertex] = true;
 
    java.util.Queue<java.lang.Integer> queue = new java.util.LinkedList<java.lang.Integer>();
    queue.add(new java.lang.Integer(startVertex));
    while (!queue.isEmpty()) {
        java.lang.Integer fromI = queue.poll();
        if (fromI == null) {
        break;
        }
        int from = fromI.intValue();
        if (maxDepth != -1 && ret.depth[from]>=maxDepth) {
        continue;
        }
        for (java.lang.Integer i : getEdgesByIndex(from)) {
        if (i == null) {
            continue;
        }
        int to = i.intValue();
        if (ret.visited[to]) {
            continue;
        }
        ret.visited[to] = true;
        ret.depth[to] = ret.depth[from]+1;
        ret.father[to] = from;
        queue.add(i);
        }
    }
    return ret;
     }
 
     class FloydWarshallAnswer {
    public long[][] distance;
    public int[][] middle; 
 
    public int[] getPath(int startIndex,int endIndex) {
        if (middle[startIndex][endIndex]==-1) {
        return (new int[0]);
        }
        java.util.LinkedList<java.lang.Integer> r = new java.util.LinkedList<java.lang.Integer>();
        getPath_r(startIndex, endIndex, r);
        r.addFirst(new java.lang.Integer(startIndex));
        r.add(new java.lang.Integer(endIndex));
        int[] ret = new int[r.size()];
        int i=0;
        for (java.lang.Integer i1 : r) {
        ret[i]=i1.intValue();
        i++;
        }
        return ret;
    }
    private void getPath_r(int startIndex, int endIndex, java.util.LinkedList<java.lang.Integer> ans) {
        int mid = middle[startIndex][endIndex];
        if (mid < 0) {
        return;
        }
        getPath_r(startIndex, mid, ans);
        ans.add(new java.lang.Integer(mid));
        getPath_r(mid, endIndex, ans);
    }
     }
 
     public FloydWarshallAnswer floydwarshall() {
    int N = this.size();
    FloydWarshallAnswer ret = new FloydWarshallAnswer();
 
    ret.distance = new long[N][N];
    ret.middle = new int[N][N];
    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
        ret.distance[i][j]=java.lang.Long.MAX_VALUE;
        ret.middle[i][j]=-1;
        WeightType w = getEdgeByIndex(i, new Integer(j));
        if (w != null) {
            ret.distance[i][j] = w.longValue();
            ret.middle[i][j]=-2;
        }
        }
    }
    for (int i=0; i<N; i++) {
        ret.distance[i][i]=0;
    }
 
    for (int k=0; k<N; k++) {
        for (int i=0; i<N; i++) {
        if (ret.distance[i][k] == java.lang.Long.MAX_VALUE) {
            continue;
        }
        for (int j=0; j<N; j++) {
            if (ret.distance[k][j] == java.lang.Long.MAX_VALUE) {
            continue;
            }
            if (ret.distance[i][k] + ret.distance[k][j] < ret.distance[i][j]) {
            ret.distance[i][j] = ret.distance[i][k] + ret.distance[k][j];
            ret.middle[i][j]=k;
            }
        }
        }
    }
    return ret;
     }
 
     class DijkstraAnswer {
    public long[] distance=null;
    public int[] from=null;
 
    public int[] getPath(int endIndex) {
        if (from[endIndex]==-1) {
        return (new int[0]);
        }
        java.util.LinkedList<java.lang.Integer> r = new java.util.LinkedList<java.lang.Integer>();
        for (int j=endIndex; j>=0; j=from[j]) {
        r.addFirst(new java.lang.Integer(j));
        }
        int[] ret = new int[r.size()];
        int i=0;
        for (java.lang.Integer i1 : r) {
        ret[i]=i1.intValue();
        i++;
        }
        return ret;
    }
     }
     public DijkstraAnswer dijkstra(VertexType startVertex) {
    int v = getVertexIndex(startVertex);
    DijkstraAnswer ret = new DijkstraAnswer();
    int N = this.size();
    boolean[] s = new boolean[N];
    java.util.Arrays.fill(s,false);
    int[] from = new int[N];
    java.util.Arrays.fill(from, -1);
    long[] dist = new long[N];
    java.util.Arrays.fill(dist, java.lang.Long.MAX_VALUE);
    for (java.lang.Integer i : getEdgesByIndex(v)) {
        WeightType weight = getEdgeByIndex(v,i);
        dist[i.intValue()] = weight.longValue();
        from[i.intValue()] = v;
    }
    
    s[v]=true;
    dist[v]=0;
    from[v]=-2;
 
    for(int i=0; i<N-2; i++) {
        
        int u=-1;
        long min1 = java.lang.Long.MAX_VALUE;
        for (int j=0; j<N; j++) {
        if (s[j]) {
            continue;
        }
        if (dist[j] < min1) {
            min1 = dist[j];
            u=j;
        }
        }
        if (min1==java.lang.Long.MAX_VALUE) {
        u=-1;
        }
        if (u==-1) {
        break;
        }
 
        s[u]=true;
 
        for (java.lang.Integer it : getEdgesByIndex(u)) {
        WeightType weight = getEdgeByIndex(u,it);
        int w = it.intValue();
        if (s[w]) continue;
        if(dist[u] + weight.longValue() < dist[w]) {
            dist[w] = dist[u] + weight.longValue();
            from[w] = u;
        }
        }
    }
    ret.distance = dist;
    ret.from = from;
    return ret;
     }
 
     class BellmanFordAnswer extends DijkstraAnswer {
    public boolean containsNegativeCycle=false;
     }
 
     public BellmanFordAnswer bellmanford(VertexType startVertex) {
    int v = getVertexIndex(startVertex);
    BellmanFordAnswer ret = new BellmanFordAnswer();
    int N = this.size();
 
    boolean containsNegativeCycle=false;
    int[] from = new int[N];
    java.util.Arrays.fill(from, -1);
    long[] dist = new long[N];
    java.util.Arrays.fill(dist, java.lang.Long.MAX_VALUE);
 
    dist[v] = 0;
    from[v] = -2;
 
    for (int i=0; i<N; i++) {
        for (int fromVertexIndex=0; fromVertexIndex<N; fromVertexIndex++) {
        for (java.lang.Integer toVertexIndex : getEdgesByIndex(fromVertexIndex)) {
            WeightType weight = getEdgeByIndex(fromVertexIndex, toVertexIndex);
            if (i<N-1) {
            if (dist[fromVertexIndex] != Long.MAX_VALUE && dist[fromVertexIndex] + weight.longValue() < dist[toVertexIndex.intValue()]) {
                dist[toVertexIndex.intValue()] = dist[fromVertexIndex] + weight.longValue();
                from[toVertexIndex.intValue()] = fromVertexIndex;
            }
            } else {
            
            if (dist[fromVertexIndex] != Long.MAX_VALUE && dist[fromVertexIndex] + weight.longValue() < dist[toVertexIndex.intValue()]) {
                containsNegativeCycle = true;
            }
            }
        }
        }
    }
 
    ret.distance = dist;
    ret.from = from;
    ret.containsNegativeCycle = containsNegativeCycle;
    return ret;
     }
 
     class PrimAnswer {
    public int[] father = null;
    public boolean[] visited = null;
     }
 
     PrimAnswer prim() {
    int N = this.size();
    boolean[] visited = new boolean[N];
    long[] distance = new long[N];
    int[] father = new int[N];
    java.util.Arrays.fill(visited, false);
    java.util.Arrays.fill(distance, java.lang.Long.MAX_VALUE);
    java.util.Arrays.fill(father, -1);
 
    distance[0] = 0;
    father[0] = -2;
 
    for (int i=0; i<N; i++) {
        int a=-1;
        long min = java.lang.Long.MAX_VALUE;
        for (int j=0; j<N; j++) {
        if (!visited[j] && distance[j] < min) {
            a = j;
            min = distance[j];
        }
        }
 
        if (a == -1) break;
        visited[a] = true;
 
        for (Integer b : getEdgesByIndex(a)) {
        long weight = getEdgeByIndex(a, b).longValue();
        int bValue = b.intValue();
        if (!visited[bValue] && weight < distance[bValue]) {
            distance[bValue] = weight;
            father[bValue] = a;
        }
        }
    }
 
    PrimAnswer ret = new PrimAnswer();
    ret.father = father;
    ret.visited = visited;
    return ret;
     }
 
     class EdmondsKarpAnswer {
    Graph<VertexType, Number> F = null;
    long maxFlow = 0;
     }
 
     EdmondsKarpAnswer edmondskarp(VertexType s, VertexType t) {
    EdmondsKarpAnswer ret = new EdmondsKarpAnswer();
    int N = this.size();
    Graph<VertexType, Number> F = new Graph<VertexType, Number>();
    Graph<VertexType, Number> R = new Graph<VertexType, Number>();
 
    for(VertexType i : this.getVertexs()) {
        F.addVertex(i);
        R.addVertex(i);
        for (Integer jIndex : this.getEdges(i)) {
        VertexType j = this.getVertexByIndex(jIndex.intValue());
        R.addEdge(i,j,new Long(this.getEdge(i,j).longValue()));
        F.addEdge(i,j,new Long(0));
        }
    }
 
    boolean[] visited = new boolean[N];
    long[] bottleneck = new long[N];
    int[] father = new int[N];
    int sIndex = getVertexIndex(s);
    int tIndex = getVertexIndex(t);
 
    long flow=0;
    long dFlow=0;
    for (flow=0; true; ) {
        
        java.util.Arrays.fill(visited, false);
        java.util.Queue<Integer> queue = new java.util.LinkedList<Integer>();
        visited[sIndex] = true;
        father[sIndex] = sIndex;
        bottleneck[sIndex] = Long.MAX_VALUE;
        bottleneck[tIndex] = 0;
        queue.offer(new Integer(sIndex));
 
        edmondskarpBFSLoop1: while (!queue.isEmpty()) {
        int nowIndex = queue.poll().intValue();
        for (Integer j : R.getEdgesByIndex(nowIndex)) {
            if (visited[j.intValue()]) {
            continue;
            }
            long weight = R.getEdgeByIndex(nowIndex, j).longValue();
            if (weight <= 0) {
            continue;
            }
            visited[j.intValue()] = true;
            father[j.intValue()] = nowIndex;
            bottleneck[j.intValue()] = Math.min(bottleneck[nowIndex], weight);
            queue.offer(j);
 
            if (j.intValue() == tIndex) {
            break edmondskarpBFSLoop1;
            }
        }
        }
        
        dFlow = bottleneck[tIndex];
        if (dFlow <= 0) {
        break;
        }
 
        
        for (int i=father[tIndex], j=tIndex; i!=j; i=father[j=i]) {
        Number weight=null, weight2=null;
        VertexType nodei = getVertexByIndex(i);
        VertexType nodej = getVertexByIndex(j);
        weight = F.getEdge(nodei, nodej);
        if (weight == null) {
            F.addEdge(nodei, nodej, new Long(dFlow));
        } else {
            F.addEdge(nodei, nodej, new Long(dFlow+weight.longValue()));
        }
 
        weight = F.getEdge(nodei, nodej);
        F.addEdge(nodej, nodei, new Long(-weight.longValue()));
 
        weight2 = this.getEdge(nodei, nodej);
        if (weight2 == null) {
            R.addEdge(nodei, nodej, new Long(-weight.longValue()));
        } else {
            R.addEdge(nodei, nodej, new Long(weight2.longValue() - weight.longValue()));
        }
 
        weight = F.getEdge(nodej, nodei);
        weight2 = this.getEdge(nodej, nodei);
        if (weight2 == null) {
            R.addEdge(nodej, nodei, new Long(-weight.longValue()));
        } else {
            R.addEdge(nodej, nodei, new Long(weight2.longValue() - weight.longValue()));
        }
        }
        
        flow += dFlow;
    }
    ret.maxFlow = flow;
    ret.F = F;
    return ret;
     }
 
     public java.util.List<VertexType> getTopologicalAOVOrder() {
    java.util.LinkedList<VertexType> ret = new java.util.LinkedList<VertexType>();
    int n = this.size();
    int[] inDegree = new int[n];
    for (int i=0; i<n; i++) {
        java.util.Set<java.lang.Integer> edges;
        edges = getEdgesByIndex(i);
        for (Integer edge : edges) {
        inDegree[edge.intValue()]++;
        }
    }
 
    while (ret.size() < n) {
        int top=-1;
        for (int i=0; i<n; i++) {
        if (inDegree[i]==0) {
            inDegree[i] = -1;
            top = i;
            break;
        }
        }
 
        if (top == -1 && ret.size() < n) {
        return null;
        }
        ret.add(getVertexByIndex(top));
        for (Integer edge : getEdgesByIndex(top)) {
        if (inDegree[edge.intValue()] > 0) {
            inDegree[edge.intValue()]--;
        }
        }
        
    }
    return ret;
     }
 
     class HeldKarpAnswer {
    public long distance = 0;
     }
 
     HeldKarpAnswer heldkarp() throws java.lang.IndexOutOfBoundsException {
    int N=size();
    HeldKarpAnswer ans = new HeldKarpAnswer();
    ans.distance=Long.MAX_VALUE;
    if (size()==1) {
        ans.distance=0;
        return ans;
    }
    if (size()>=32) {
        throw new java.lang.IndexOutOfBoundsException(String.format("Graph size %1$d >= 32",size()));
    }
    int setSize = 1 << size();
    long[][] C = new long[setSize][size()];
    
    for (int i=0; i<C.length; i++) {
        for (int j=0; j<C[i].length; j++) {
        C[i][j]=Long.MAX_VALUE;
        }
    }
 
    for (int k=1; k<size(); k++) {
        WeightType weight = getEdgeByIndex(0,new Integer(k));
        if (weight != null) {
        C[1|(1<<k)][k] = weight.longValue();
        }
    }
 
    for (int s=3; s<=N; s++) {
        int S=0;
        for (int i=0; i<s; i++) {
        S |= (1<<i);
        }
        boolean nextSetFlag=true;
        heldKarploop01: while (nextSetFlag) {
        
        for (int k=0; k<N; k++) {
            if ((S & (1<<k)) == 0) {
            continue;
            }
            for (int m=1; m<N; m++) {
            if (m==k) {
                continue;
            }
            if ((S & (1<<m)) == 0) {
                continue;
            }
            long prev1 = C[(S & (~(1<<k)))][m];
            if (prev1 == Long.MAX_VALUE) {
                continue;
            }
            WeightType weight1 = getEdgeByIndex(m,new Integer(k));
            if (weight1 != null) {
                long ans1 = prev1 + weight1.longValue();
                if (ans1 < C[S][k]) {
                C[S][k]=ans1;
                }
            }
            }
        }
        
        nextSetFlag=false;
        int numOf1=0;
        for (int k01=N-1; k01>=0; k01--) {
            int mask1 = 1<<(k01);
            if ((S&mask1) != 0) {
            numOf1++;
            if (k01+1<N && ((S&(1<<(k01+1)))==0)) {
                S &= (~mask1);
                S |= (1<<(k01+1));
                int i;
                i=k01+1;
                for ( ; numOf1 > 0 && i<N ; i++) {
                S |= (1 << i);
                numOf1--;
                }
                for ( ; i<N; i++) {
                S &= (~(1<<i));
                }
                nextSetFlag=true;
                continue heldKarploop01;
            }
            }
        }
        }
    }
    for (int k=1; k<N; k++) {
        int S = (1<<N)-1;
        if (C[S][k] < Long.MAX_VALUE) {
        WeightType weight = getEdgeByIndex(0,new Integer(k));
        if (weight != null) {
            if (C[S][k] + weight.longValue() < ans.distance) {
            ans.distance = C[S][k] + weight.longValue();
            }
        }
        }
    }
    return ans;
     }
 
 }
 
 class Permutation {
     private static <T> void reverse(java.util.List<T> list, int i, int j) {
    while (i<j) {
        java.util.Collections.swap(list,i,j);
        i++;
        j--;
    }
     }
     public static <T extends Comparable<? super T> > boolean next_permutation(java.util.List<T> list) {
    if (list.size()<=1) {
        return false;
    }
    int i=list.size()-1;
    while (true) {
        int j = i;
        i--;
 
        if (list.get(i).compareTo(list.get(j))<0) {
        int k = list.size()-1;
        while ( !(list.get(i).compareTo(list.get(k))<0) ) {
            k--;
        }
        java.util.Collections.swap(list,i,k);
        reverse(list,j,list.size()-1);
        return true;
        }
 
        if (i==0) {
        java.util.Collections.reverse(list);
        return false;
        }
    }
     }
 
     public static boolean next_ksubset(int n,int[] set) {
    int i;
    for (i=set.length-1; i>=0; i--) {
        if (set[i]+1 < n-(set.length-1-i)) {
        break;
        }
    }
    if (i<0) {
        return false;
    }
    set[i]++;
    i++;
    for (; i<set.length; i++) {
        set[i] = set[i-1]+1;
    }
    return true;
     }
 
     public static void unittest_permutation() {
    java.util.List<Integer> list = new java.util.ArrayList<Integer>();
    for (int i=0; i<4; i++) {
        list.add(new Integer(i));
    }
    while (true) {
        boolean spaceFlag=false;
        for (Integer l : list) {
        if (spaceFlag) {
            System.out.print(" ");
        } else {
            spaceFlag=true;
        }
        System.out.print(l);
        }
        System.out.println();
        boolean result1 = next_permutation(list);
        if (!result1) {
        break;
        }
    }
     }
 
     public static void unittest_ksubset() {
    int n=5;
    int[] set = new int[3];
    for (int i=0; i<set.length; i++) {
        set[i]=i;
    }
    while (true) {
        boolean spaceFlag=false;
        for (int i=0; i<set.length; i++) {
        if (spaceFlag) {
            System.out.print(" ");
        } else {
            spaceFlag=true;
        }
        System.out.print(set[i]);
        }
        System.out.println();
        boolean result1 = next_ksubset(n,set);
        if (!result1) {
        break;
        }
    }
    
     }
 
 
     public static void unittest() {
    unittest_permutation();
    unittest_ksubset();
     }
 
 }
