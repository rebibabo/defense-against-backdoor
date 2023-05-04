import java.io.OutputStream;
 import java.io.FilenameFilter;
 import java.util.Locale;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.FileInputStream;
 import java.io.File;
 import java.io.InputStream;
 import java.util.Arrays;
 import java.util.PriorityQueue;
 import java.util.HashMap;
 import java.util.InputMismatchException;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.AbstractCollection;
 import java.util.Map;
 import java.io.OutputStreamWriter;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.util.Iterator;
 import java.io.BufferedWriter;
 import java.util.Set;
 import java.io.IOException;
 import java.util.List;
 import java.io.Writer;
 import java.io.InputStream;
 
 
 public class Main {
     public static void main(String[] args) {
         Locale.setDefault(Locale.US);
         InputStream inputStream;
         try {
             final String regex = "B-(small|large).*[.]in";
             File directory = new File(".");
             File[] candidates = directory.listFiles(new FilenameFilter() {
                 public boolean accept(File dir, String name) {
                     return name.matches(regex);
                 }
             });
             File toRun = null;
             for (File candidate : candidates) {
                 if (toRun == null || candidate.lastModified() > toRun.lastModified())
                     toRun = candidate;
             }
             inputStream = new FileInputStream(toRun);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         OutputStream outputStream;
         try {
             outputStream = new FileOutputStream("b.out");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         InputReader in = new InputReader(inputStream);
         OutputWriter out = new OutputWriter(outputStream);
         TaskB solver = new TaskB();
         int testCount = Integer.parseInt(in.next());
         for (int i = 1; i <= testCount; i++)
             solver.solve(i, in, out);
         out.close();
     }
 
     static class TaskB {
         public void solve(int testNumber, InputReader in, OutputWriter out) {
             out.printFormat("Case #%d: ", testNumber);
             int seatsCnt = in.readInt();
             int menCnt = in.readInt();
             int ticketsCnt = in.readInt();
             int[] who = new int[ticketsCnt];
             int[] place = new int[ticketsCnt];
             for (int i = 0; i < ticketsCnt; i++) {
                 place[i] = in.readInt() - 1;
                 who[i] = in.readInt() - 1;
             }
             solveSmall(seatsCnt, menCnt, ticketsCnt, who, place, out);
             if (true) return;
 
 
             int[] howMuchHeBought = new int[menCnt];
             for (int i = 0; i < ticketsCnt; i++) {
                 howMuchHeBought[who[i]]++;
             }
             Map<Integer, Integer>[] ticketsCountsForMen = new Map[menCnt];
             for (int i = 0; i < menCnt; i++) {
                 ticketsCountsForMen[i] = new HashMap<>();
             }
             for (int i = 0; i < ticketsCnt; i++) {
                 Integer before = ticketsCountsForMen[who[i]].get(place[i]);
                 if (before == null) before = 0;
                 ticketsCountsForMen[who[i]].put(place[i], before + 1);
             }
             int L = 1, R = ticketsCnt, ans = ticketsCnt + 1;
             while (L <= R) {
                 int ridesCnt = (L + R) / 2;
                 MinCostMaxFlow mf = constructFlowObject(seatsCnt, menCnt, howMuchHeBought, ticketsCountsForMen, ridesCnt);
                 mf.findMinCostMaxFlow();
                 if (mf.totalFlow == ticketsCnt) {
                     ans = ridesCnt;
                     R = ridesCnt - 1;
                 } else {
                     L = ridesCnt + 1;
                 }
             }
             if (ans == ticketsCnt + 1) {
                 throw new RuntimeException();
             }
             MinCostMaxFlow mf = constructFlowObject(seatsCnt, menCnt, howMuchHeBought, ticketsCountsForMen, ans);
             mf.findMinCostMaxFlow();
             out.printLine(ans + " " + mf.totalCost);
         }
 
         private void solveSmall(int seatsCnt, int menCnt, int ticketsCnt, int[] who, int[] place, OutputWriter out) {
             if (menCnt > 2) {
 
 
                 throw new RuntimeException();
 
             }
             List<Integer> places1 = new ArrayList<>();
             List<Integer> places2 = new ArrayList<>();
             for (int i = 0; i < ticketsCnt; i++) {
                 if (who[i] == 0) {
                     places1.add(place[i]);
                 } else {
                     places2.add(place[i]);
                 }
             }
 
 
 
 
 
 
 
 
 
 
 
 
 
             List<Integer>[] g = new List[ticketsCnt];
             for (int i = 0; i < ticketsCnt; i++) {
                 g[i] = new ArrayList<>();
             }
             for (int i = 0; i < places1.size(); i++) {
                 for (int j = 0; j < places2.size(); j++) {
                     int p1 = places1.get(i);
                     int p2 = places2.get(j);
                     if (p1 != p2) {
                         g[i].add(places1.size() + j);
                         g[places1.size() + j].add(i);
                     }
                 }
             }
             TaskB.KuhnAlgorithm kuhn = new TaskB.KuhnAlgorithm();
             kuhn.n = ticketsCnt;
             kuhn.a = g;
             kuhn.vis = new int[ticketsCnt];
             kuhn.p = new int[ticketsCnt];
             kuhn.solve();
             int good = 0;
             Set<Integer> remains = new HashSet<>();
             int bad1 = 0;
             int bad2 = 0;
             boolean[] used = new boolean[ticketsCnt];
             for (int i = 0; i < ticketsCnt; i++) {
                 if (used[i]) continue;
                 if (kuhn.p[i] != -1) {
                     good++;
                     used[i] = used[kuhn.p[i]] = true;
                 } else {
                     int v = i;
                     if (v < places1.size()) {
                         bad1++;
                         remains.add(places1.get(v));
                     } else {
                         bad2++;
                         remains.add(places2.get(v - places1.size()));
                     }
                 }
             }
             if (bad1 + bad2 + 2 * good != ticketsCnt) {
                 throw new RuntimeException();
             }
             int rides, promotions;
             if (bad1 > 0 && bad2 > 0) {
                 if (remains.size() > 1) {
                     throw new RuntimeException();
                 }
                 if (remains.iterator().next() == 0) {
                     rides = bad1 + bad2 + good;
                     promotions = 0;
                 } else {
                     rides = Math.max(bad1, bad2) + good;
                     promotions = Math.min(bad1, bad2);
                 }
             } else {
                 rides = bad1 + bad2 + good;
                 promotions = 0;
             }
 
 
             out.printLine(rides + " " + promotions);
         }
 
         private MinCostMaxFlow constructFlowObject(int seatsCnt, int menCnt, int[] howMuchHeBought, Map<Integer, Integer>[] ticketsCountsForMen, int ridesCnt) {
             MinCostMaxFlow mf = new MinCostMaxFlow(2 + menCnt + seatsCnt);
             mf.source = menCnt + seatsCnt;
             mf.sink = menCnt + seatsCnt + 1;
             for (int man = 0; man < menCnt; man++) {
                 mf.addEdge(mf.source, man, howMuchHeBought[man], 0);
             }
             for (int seat = 0; seat < seatsCnt; seat++) {
                 mf.addEdge(menCnt + seat, mf.sink, ridesCnt, 0);
             }
             for (int man = 0; man < menCnt; man++) {
                 int promotions = 0;
                 for (int seat = seatsCnt - 1; seat >= 0; seat--) {
                     Integer cnt = ticketsCountsForMen[man].get(seat);
                     if (cnt == null) {
                         if (promotions > 0) {
                             mf.addEdge(man, menCnt + seat, Math.min(promotions, ridesCnt), 1);
                         }
                     } else {
                         mf.addEdge(man, menCnt + seat, cnt, 0);
                         if (promotions > 0) {
                             mf.addEdge(man, menCnt + seat, Math.min(promotions, ridesCnt), 1);
                         }
                         promotions += cnt;
                     }
                 }
             }
             return mf;
         }
 
         static class KuhnAlgorithm {
             int n;
             List<Integer>[] a;
             int timer;
             int[] vis;
             int[] p;
 
             boolean dfs(int x) {
                 if (vis[x] == timer) return false;
                 vis[x] = timer;
                 
                 for (int y : a[x]) {
                     if (p[y] == -1) {
                         p[y] = x;
                         return true;
                     }
                 }
                 for (int y : a[x]) {
                     if (p[y] != -1 && dfs(p[y])) {
                         p[y] = x;
                         return true;
                     }
                 }
                 return false;
             }
 
             void solve() {
                 Arrays.fill(p, -1);
                 Arrays.fill(vis, 0);
                 timer = 0;
                 for (int i = 0; i < n; i++) {
                     timer++;
                     dfs(i);
                 }
             }
 
         }
 
         class MinCostMaxFlow {
             final int INF = (int) 1e9;
             int n;
             int source;
             int sink;
             List<Integer>[] a;
             List<Edge> edges;
             int[] d;
             int[] p;
             int[] phi;
             PriorityQueue<Vertex> q;
             int totalFlow;
             int totalCost;
 
             public MinCostMaxFlow(int n) {
                 this.n = n;
                 d = new int[n];
                 p = new int[n];
                 phi = new int[n];
                 a = new List[n];
                 for (int i = 0; i < n; i++) {
                     a[i] = new ArrayList<Integer>();
                 }
                 edges = new ArrayList<Edge>();
                 q = new PriorityQueue<Vertex>(n);
             }
 
             public void addEdge(int x, int y, int cap, int cost) {
                 a[x].add(edges.size());
                 edges.add(new Edge(x, y, cap, cost));
                 a[y].add(edges.size());
                 edges.add(new Edge(y, x, 0, -cost));
             }
 
             public void findMinCostMaxFlow() {
                 totalFlow = 0;
                 totalCost = 0;
                 while (true) {
                     if (!dijkstra()) break;
                     recalc();
                 }
             }
 
             private boolean dijkstra() {
                 Arrays.fill(d, INF);
                 Arrays.fill(p, -1);
                 q.clear();
                 d[source] = 0;
                 q.add(new Vertex(source, 0));
                 while (!q.isEmpty()) {
                     Vertex v = q.poll();
                     int x = v.x;
                     if (v.d > d[x]) continue;
                     for (int id : a[x]) {
                         Edge e = edges.get(id);
                         if (e.flow < e.cap) {
                             int y = e.y;
                             int newD = d[x] + e.cost + phi[x] - phi[y];
                             if (d[y] > newD) {
                                 d[y] = newD;
                                 p[y] = id;
                                 q.add(new Vertex(y, newD));
                             }
                         }
                     }
                 }
                 return d[sink] < INF;
             }
 
             private void recalc() {
                 int flow = INF;
                 for (int k = sink; k != source; ) {
                     int id = p[k];
                     Edge e = edges.get(id);
                     flow = Math.min(flow, e.cap - e.flow);
                     k = e.x;
                 }
                 totalFlow += flow;
                 for (int k = sink; k != source; ) {
                     int id = p[k];
                     Edge e = edges.get(id);
                     totalCost += flow * e.cost;
                     e.flow += flow;
                     edges.get(id ^ 1).flow -= flow;
                     k = e.x;
                 }
                 for (int i = 0; i < n; i++) {
                     phi[i] = d[i];
                 }
             }
 
             class Edge {
                 int x;
                 int y;
                 int cap;
                 int cost;
                 int flow;
 
                 public Edge(int x, int y, int cap, int cost) {
                     this.x = x;
                     this.y = y;
                     this.cap = cap;
                     this.cost = cost;
                     this.flow = 0;
                 }
 
             }
 
             class Vertex implements Comparable<Vertex> {
                 int x;
                 int d;
 
                 public Vertex(int x, int d) {
                     this.x = x;
                     this.d = d;
                 }
 
 
                 public int compareTo(Vertex o) {
                     if (d == o.d) return x - o.x;
                     return d - o.d;
                 }
 
             }
 
         }
 
     }
 
     static class InputReader {
         private InputStream stream;
         private byte[] buf = new byte[1024];
         private int curChar;
         private int numChars;
         private InputReader.SpaceCharFilter filter;
 
         public InputReader(InputStream stream) {
             this.stream = stream;
         }
 
         public int read() {
             if (numChars == -1) {
                 throw new InputMismatchException();
             }
             if (curChar >= numChars) {
                 curChar = 0;
                 try {
                     numChars = stream.read(buf);
                 } catch (IOException e) {
                     throw new InputMismatchException();
                 }
                 if (numChars <= 0) {
                     return -1;
                 }
             }
             return buf[curChar++];
         }
 
         public int readInt() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = read();
             }
             int res = 0;
             do {
                 if (c < '0' || c > '9') {
                     throw new InputMismatchException();
                 }
                 res *= 10;
                 res += c - '0';
                 c = read();
             } while (!isSpaceChar(c));
             return res * sgn;
         }
 
         public String readString() {
             int c = read();
             while (isSpaceChar(c)) {
                 c = read();
             }
             StringBuilder res = new StringBuilder();
             do {
                 if (Character.isValidCodePoint(c)) {
                     res.appendCodePoint(c);
                 }
                 c = read();
             } while (!isSpaceChar(c));
             return res.toString();
         }
 
         public boolean isSpaceChar(int c) {
             if (filter != null) {
                 return filter.isSpaceChar(c);
             }
             return isWhitespace(c);
         }
 
         public static boolean isWhitespace(int c) {
             return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
         }
 
         public String next() {
             return readString();
         }
 
         public interface SpaceCharFilter {
             public boolean isSpaceChar(int ch);
 
         }
 
     }
 
     static class OutputWriter {
         private final PrintWriter writer;
 
         public OutputWriter(OutputStream outputStream) {
             writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
         }
 
         public OutputWriter(Writer writer) {
             this.writer = new PrintWriter(writer);
         }
 
         public void print(Object... objects) {
             for (int i = 0; i < objects.length; i++) {
                 if (i != 0) {
                     writer.print(' ');
                 }
                 writer.print(objects[i]);
             }
         }
 
         public void printLine(Object... objects) {
             print(objects);
             writer.println();
         }
 
         public void printFormat(String format, Object... objects) {
             writer.printf(format, objects);
         }
 
         public void close() {
             writer.close();
         }
 
     }
 }
 
