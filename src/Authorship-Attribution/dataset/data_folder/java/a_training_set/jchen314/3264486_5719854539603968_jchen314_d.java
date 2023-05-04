import java.util.ArrayDeque;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Queue;
 import java.util.Scanner;
 
 public class D {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             int N = sc.nextInt();
             int M = sc.nextInt();
 
             boolean[][] a = new boolean[N + 1][N + 1]; 
             boolean[][] b = new boolean[N + 1][N + 1]; 
 
             boolean[] rows = new boolean[N + 1];
             boolean[] cols = new boolean[N + 1];
 
             boolean[] ul = new boolean[2 * N]; 
             boolean[] dl = new boolean[2 * N]; 
 
             int points = 0;
 
             for (int i = 0; i < M; i++) {
                 char ch = sc.next().charAt(0);
                 int r = sc.nextInt();
                 int c = sc.nextInt();
 
                 if (ch == 'x' || ch == 'o') {
                     a[r][c] = true;
                     rows[r] = true;
                     cols[c] = true;
                     points++;
                 }
                 if (ch == '+' || ch == 'o') {
                     b[r][c] = true;
                     ul[r + c - 1] = true;
                     dl[r - c + N] = true;
                     points++;
                 }
             }
 
             int[][] edits = new int[N + 1][N + 1];
             int r = 1, c = 1;
             while (true) {
                 while (r <= N && rows[r]) {
                     r++;
                 }
                 while (c <= N && cols[c]) {
                     c++;
                 }
                 if (r > N || c > N) {
                     break;
                 }
                 edits[r][c] += 1;
                 r++;
                 c++;
                 points++;
             }
 
             PushRelabel pr = new PushRelabel(4 * N); 
             for (int i = 1; i < 2 * N; i++) {
                 pr.AddEdge(0, i, 1);
                 pr.AddEdge(2 * N + i, 2 * N, 1);
             }
             for (int i = 1; i < 2 * N; i++) {
                 if (ul[i]) {
                     continue;
                 }
                 for (int j = 1; j < 2 * N; j++) {
                     if (dl[j]) {
                         continue;
                     }
                     if ((i + j + N + 1) % 2 != 0) {
                         continue;
                     }
                     int sum = i + 1;
                     int diff = j - N;
                     r = (sum + diff) / 2;
                     c = (sum - diff) / 2;
                     if (1 <= r && r <= N && 1 <= c && c <= N) {
                         pr.AddEdge(i, 2 * N + j, 1);
                     }
                 }
             }
             pr.GetMaxFlow(0, 2 * N);
             for (List<Edge> edges : pr.G) {
                 for (Edge e : edges) {
                     if (e.flow <= 0 || e.from == 0 || e.to == 2 * N) {
                         continue;
                     }
                     int sum = e.from + 1;
                     int diff = e.to - 3 * N;
                     r = (sum + diff) / 2;
                     c = (sum - diff) / 2;
                     edits[r][c] += 2;
                     points++;
                 }
             }
             List<String> list = new ArrayList<>();
             for (r = 1; r <= N; r++) {
                 for (c = 1; c <= N; c++) {
                     if (edits[r][c] == 0) {
                         continue;
                     }
                     int value = edits[r][c];
                     if (a[r][c]) {
                         value++;
                     }
                     if (b[r][c]) {
                         value += 2;
                     }
                     if (value == 1) {
                         list.add("x " + r + " " + c);
                     }
                     if (value == 2) {
                         list.add("+ " + r + " " + c);
                     }
                     if (value == 3) {
                         list.add("o " + r + " " + c);
                     }
                 }
             }
             System.out.println("Case #" + caseNum + ": " + points + " " + list.size());
             for (String str : list) {
                 System.out.println(str);
             }
         }
     }
 
     private static class Edge {
         int from, to, cap, flow, index;
 
         public Edge(int from, int to, int cap, int flow, int index) {
             this.from = from;
             this.to = to;
             this.cap = cap;
             this.flow = flow;
             this.index = index;
         }
     }
 
     private static class PushRelabel {
         int N;
         List<Edge>[] G;
         long[] excess;
         boolean[] active;
         int[] dist, count;
         Queue<Integer> Q;
 
         PushRelabel(int N) {
             this.N = N;
             G = new List[N];
             for (int i = 0; i < N; i++) {
                 G[i] = new ArrayList<Edge>();
             }
             excess = new long[N];
             dist = new int[N];
             active = new boolean[N];
             count = new int[2 * N];
             Q = new ArrayDeque<Integer>();
         }
 
         void AddEdge(int from, int to, int cap) {
             G[from].add(new Edge(from, to, cap, 0, G[to].size()));
             if (from == to) {
                 G[from].get(G[from].size() - 1).index++;
             }
             G[to].add(new Edge(to, from, 0, 0, G[from].size() - 1));
         }
 
         void Enqueue(int v) {
             if (!active[v] && excess[v] > 0) {
                 active[v] = true;
                 Q.offer(v);
             }
         }
 
         void Push(Edge e) {
             int amt = (int) Math.min(excess[e.from], e.cap - e.flow);
             if (dist[e.from] <= dist[e.to] || amt == 0) {
                 return;
             }
             e.flow += amt;
             G[e.to].get(e.index).flow -= amt;
             excess[e.to] += amt;
             excess[e.from] -= amt;
             Enqueue(e.to);
         }
 
         void Gap(int k) {
             for (int v = 0; v < N; v++) {
                 if (dist[v] < k) {
                     continue;
                 }
                 count[dist[v]]--;
                 dist[v] = Math.max(dist[v], N + 1);
                 count[dist[v]]++;
                 Enqueue(v);
             }
         }
 
         void Relabel(int v) {
             count[dist[v]]--;
             dist[v] = 2 * N;
             for (int i = 0; i < G[v].size(); i++) {
                 if (G[v].get(i).cap - G[v].get(i).flow > 0) {
                     dist[v] = Math.min(dist[v], dist[G[v].get(i).to] + 1);
                 }
             }
             count[dist[v]]++;
             Enqueue(v);
         }
 
         void Discharge(int v) {
             for (int i = 0; excess[v] > 0 && i < G[v].size(); i++) {
                 Push(G[v].get(i));
             }
             if (excess[v] > 0) {
                 if (count[dist[v]] == 1) {
                     Gap(dist[v]);
                 } else {
                     Relabel(v);
                 }
             }
         }
 
         long GetMaxFlow(int s, int t) {
             count[0] = N - 1;
             count[N] = 1;
             dist[s] = N;
             active[s] = active[t] = true;
             for (int i = 0; i < G[s].size(); i++) {
                 excess[s] += G[s].get(i).cap;
                 Push(G[s].get(i));
             }
 
             while (!Q.isEmpty()) {
                 int v = Q.poll();
                 active[v] = false;
                 Discharge(v);
             }
 
             long totflow = 0;
             for (int i = 0; i < G[s].size(); i++) {
                 totflow += G[s].get(i).flow;
             }
             return totflow;
         }
     };
 }
