package year2017.round2;
 
 import java.awt.Point;
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Scanner;
 
 public class BeamingWithJoy {
     static Map<Point,Integer> map;
 
     public static void main(String[] args) throws Exception {
         File inputFile = new File("C-small-attempt0.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t = 0; t < T; t++) {
             map = new HashMap<Point,Integer>();
             int R = in.nextInt();
             int C = in.nextInt();
             char[][] grid = new char[R][];
             for (int r=0; r<R; r++) {
                 String s = in.next();
                 if (s.length() != C) throw new IllegalArgumentException();
                 grid[r] = s.toCharArray();
             }
             int index = 0;
             for (int r=0; r<R; r++) {
                 for (int c=0; c<C; c++) {
                     if (grid[r][c] == '|' | grid[r][c] == '-') {
                         grid[r][c] = '-';
                         map.put(new Point(r,c), index++);
                     }
                 }
             }
             int beams = index;
             List<Integer>[] g = new List[2*beams];
             for (int i=0; i<g.length; i++) {
                 g[i] = new ArrayList<Integer>();
             }
             boolean ok = true;
             for (int r=0; r<R; r++) {
                 for (int c=0; c<C; c++) {
                     if (grid[r][c] == '.') {
                         int horizontal = -1;
                         int cc = c-1;
                         while (cc >= 0 && grid[r][cc] == '.') cc--;
                         if (cc >= 0 && grid[r][cc] == '-') {
                             horizontal = map.get(new Point(r,cc));
                         }
                         cc = c+1;
                         while (cc < C && grid[r][cc] == '.') cc++;
                         if (cc < C && grid[r][cc] == '-') {
                             horizontal = map.get(new Point(r,cc));
                         }
                         
                         int vertical = -1;
                         int rr = r-1;
                         while (rr >= 0 && grid[rr][c] == '.') rr--;
                         if (rr >=0 && grid[rr][c] == '-') {
                             vertical = map.get(new Point(rr,c));
                         }
                         rr = r+1;
                         while (rr < R && grid[rr][c] == '.') rr++;
                         if (rr < R && grid[rr][c] == '-') {
                             vertical = map.get(new Point(rr,c));
                         }
                         
                         if (horizontal == -1) {
                             if (vertical == -1) {
                                 ok = false;
                             } else {
                                 
                                 g[vertical+beams].add(vertical);
                             }
                         } else {
                             if (vertical == -1) {
                                 
                                 g[horizontal].add(horizontal+beams);
                             } else {
                                 
                                 g[vertical+beams].add(horizontal+beams);
                                 g[horizontal].add(vertical);
                             }
                         }
                     }
                 }
             }
             int[] enforced = new int[beams];
             for (int r=0; r<R; r++) {
                 int lastBeam = -1;
                 for (int c=0; c<C; c++) {
                     if (grid[r][c] == '-') {
                         int nextBeam = map.get(new Point(r,c));
                         if (lastBeam == -1) {
                             lastBeam = nextBeam;
                         } else {
                             
                             g[lastBeam+beams].add(lastBeam);
                             g[nextBeam+beams].add(nextBeam);
                             if (enforced[lastBeam] == 1 || enforced[lastBeam] == 0) {
                                 enforced[lastBeam] = 1;
                             } else {
                                 ok = false;
                             }
                             if (enforced[nextBeam] == 1 || enforced[nextBeam] == 0) {
                                 enforced[nextBeam] = 1;
                             } else {
                                 ok = false;
                             }
                         }
                     } else if (grid[r][c] == '#') {
                         lastBeam = -1;
                     }
                 }
             }
             for (int c=0; c<C; c++) {
                 int lastBeam = -1;
                 for (int r=0; r<R; r++) {
                     if (grid[r][c] == '-') {
                         int nextBeam = map.get(new Point(r,c));
                         if (lastBeam == -1) {
                             lastBeam = nextBeam;
                         } else {
                             
                             g[lastBeam].add(lastBeam+beams);
                             g[nextBeam].add(nextBeam+beams);
                             if (enforced[lastBeam] == -1 || enforced[lastBeam] == 0) {
                                 enforced[lastBeam] = -1;
                             } else {
                                 ok = false;
                             }
                             if (enforced[nextBeam] == -1 || enforced[nextBeam] == 0) {
                                 enforced[nextBeam] = -1;
                             } else {
                                 ok = false;
                             }
                         }
                     } else if (grid[r][c] == '#') {
                         lastBeam = -1;
                     }
                 }
             }
             boolean[] solution = solve2Sat(g);
             ok &= (solution != null);
             for (int n=0; n<beams; n++) {
                 if (enforced[n] != 0) {
                     if (enforced[n] == 1 && !solution[n]) ok = false;
                     if (enforced[n] == -1 && solution[n]) ok = false;
                 }
             }
             
             out.println("Case #" + (t + 1) + ": " + (ok ? "POSSIBLE" : "IMPOSSIBLE"));
             if (ok) {
                 for (int r=0; r<R; r++) {
                     for (int c=0; c<C; c++) {
                         if (grid[r][c] == '-') {
                             int idx = map.get(new Point(r,c));
                             out.print(solution[idx] ? '|' : '-');
                         } else {
                             out.print(grid[r][c]);
                         }
                     }
                     out.println();
                 }
             }
         }
 
         out.close();
     }
 
     static void dfs1(List<Integer>[] graph, boolean[] used, List<Integer> order, int u) {
         used[u] = true;
         for (int v : graph[u]) {
             if (!used[v]) {
                 dfs1(graph, used, order, v);
             }
         }
         order.add(u);
     }
 
     static void dfs2(List<Integer>[] reverseGraph, int[] comp, int u, int color) {
         comp[u] = color;
         for (int v : reverseGraph[u]) {
             if (comp[v] == -1) {
                 dfs2(reverseGraph, comp, v, color);
             }
         }
     }
 
     public static boolean[] solve2Sat(List<Integer>[] graph) {
         int n = graph.length;
         boolean[] used = new boolean[n];
         List<Integer> order = new ArrayList<Integer>();
         for (int i = 0; i < n; ++i) {
             if (!used[i]) {
                 dfs1(graph, used, order, i);
             }
         }
 
         List<Integer>[] reverseGraph = new List[n];
         for (int i=0; i<n; i++) {
             reverseGraph[i] = new ArrayList<Integer>();
         }
 
         for (int i = 0; i < n; i++) {
             for (int j : graph[i]) {
                 reverseGraph[j].add(i);
             }
         }
 
         int[] comp = new int[n];
         Arrays.fill(comp, -1);
         for (int i = 0, color = 0; i < n; ++i) {
             int u = order.get(n - i - 1);
             if (comp[u] == -1) {
                 dfs2(reverseGraph, comp, u, color++);
             }
         }
 
         for (int i = 0; i < n; ++i) {
             if (comp[i] == comp[i ^ 1]) {
                 return null;
             }
         }
 
         boolean[] res = new boolean[n / 2];
         for (int i = 0; i < n; i += 2) {
             res[i / 2] = comp[i] > comp[i ^ 1];
         }
         return res;
     }
 
     public static void maink(String[] args) {
         int n = 2;
         List<Integer>[] g = new List[n];
         for (int i=0; i<n; i++) {
             g[i] = new ArrayList<Integer>();
         }
 
     
         
         
         
         
 
 
 
         boolean[] solution = solve2Sat(g);
         System.out.println(Arrays.toString(solution));
     }
 
 }
