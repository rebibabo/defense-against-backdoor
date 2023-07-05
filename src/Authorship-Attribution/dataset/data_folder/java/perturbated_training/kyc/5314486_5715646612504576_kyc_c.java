package CodeJam;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.PrintStream;
 import java.nio.file.Files;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.Set;
 import org.junit.Test;
 import com.google.common.collect.ImmutableList;
 import com.google.common.collect.Multimap;
 import com.google.common.collect.Multimaps;
 import lib.Graph;
 import lib.IPoint;
 
 public class C extends CodeJamCommons {
 
     int h, w;
     char[][] grid;
     List<IPoint> beams;
     List<IPoint> empties;
     Multimap<IPoint, String> facts;
     Graph<String> graph;
 
     @SuppressWarnings("resource")
     @Test
     public void test() throws Exception {
         Scanner in = new Scanner(new FileInputStream(new File(ROOT, "C-small-attempt0.in")));
         File file = new File(ROOT, "data.out");
         PrintStream out = new PrintStream(new FileOutputStream(file));
 
         int numCases = in.nextInt();
         for (int n = 0; n < numCases; n++) {
             h = in.nextInt();
             w = in.nextInt();
             grid = new char[h][];
             for (int i = 0; i < h; i++)
                 grid[i] = in.next().toCharArray();
 
             beams = list();
             empties = list();
             for (int i = 0; i < h; i++)
                 for (int j = 0; j < w; j++)
                     if (grid[i][j] == '-' || grid[i][j] == '|')
                         beams.add(new IPoint(i, j));
                     else if (grid[i][j] == '.')
                         empties.add(new IPoint(i, j));
 
             facts = mmap();
             graph = Graph.create();
             for (int k = 0; k < beams.size(); k++) {
                 IPoint beam = beams.get(k);
                 {
                     for (int[] horiz : new int[][] {{1, 0}, {-1, 0}}) {
                         int i = beam.x, j = beam.y;
                         int di = horiz[0], dj = horiz[1];
                         do {
                             i += di; j += dj;
                             if (i < 0 || i >= h || j < 0 || j >= w || grid[i][j] == '#')
                                 break;
                             if (grid[i][j] == '|' || grid[i][j] == '-') {
                                 graph.addDirectedEdge("v" + k, "h" + k, 1);
                                 break;
                             }
                             if (grid[i][j] == '.')
                                 facts.put(new IPoint(i, j), "h" + k);
                         } while (true);
                     }
                 }
                 {
                     for (int[] horiz : new int[][] {{0, 1}, {0, -1}}) {
                         int i = beam.x, j = beam.y;
                         int di = horiz[0], dj = horiz[1];
                         do {
                             i += di; j += dj;
                             if (i < 0 || i >= h || j < 0 || j >= w || grid[i][j] == '#')
                                 break;
                             if (grid[i][j] == '|' || grid[i][j] == '-') {
                                 graph.addDirectedEdge("h" + k, "v" + k, 1);
                                 break;
                             }
                             if (grid[i][j] == '.')
                                 facts.put(new IPoint(i, j), "v" + k);
                         } while (true);
                     }
                 }
             }
             for (IPoint key : facts.keySet()) {
                 List<String> vs = ImmutableList.copyOf(facts.get(key));
                 if (vs.size() == 1)
                     graph.addDirectedEdge(not(vs.get(0)), vs.get(0), 1);
                 else if (vs.size() == 2) {
                     graph.addDirectedEdge(not(vs.get(0)), vs.get(1), 1);
                     graph.addDirectedEdge(not(vs.get(1)), vs.get(0), 1);
                 } else
                     die();
             }
 
 
             boolean bad = false;
             List<List<String>> scComponents = getSCComponents(graph);
             for (List<String> comp : scComponents)
                 for (int k = 0; k < beams.size(); k++)
                     if (comp.contains("h" + k) && comp.contains("v" + k))
                         bad = true;
 
             out.printf("Case #%d: ", n + 1);
             if (bad)
                 out.println("IMPOSSIBLE");
             else {
                 Map<String, Integer> section = map();
                 for (int i = 0; i < scComponents.size(); i++)
                     for (String s : scComponents.get(i))
                         section.put(s, i);
                 Graph<Integer> big = Graph.create();
                 for (Map.Entry<String, String> e : graph.neighbors.entries())
                     big.addDirectedEdge(section.get(e.getKey()), section.get(e.getValue()), 1);
                 List<String> topSort = list();
                 Graph<Integer> invBig = Graph.create();
                 invBig.vertices.addAll(big.vertices);
                 Multimaps.invertFrom(big.neighbors, invBig.neighbors);
                 List<Integer> s = list();
                 for (int v : big.vertices)
                     if (invBig.neighbors.get(v).isEmpty())
                         s.add(v);
                 while (!s.isEmpty()) {
                     int v = removeLast(s);
                     topSort.addAll(scComponents.get(v));
                     for (int ne : big.neighbors.get(v)) {
                         invBig.neighbors.remove(ne, v);
                         if (invBig.neighbors.get(ne).isEmpty())
                             s.add(ne);
                     }
                 }
                 for (int k = 0; k < beams.size(); k++) {
                     IPoint beam = beams.get(k);
                     if (topSort.indexOf("h" + k) < topSort.indexOf("v" + k))
                         grid[beam.x][beam.y] = '|';
                     else
                         grid[beam.x][beam.y] = '-';
                 }
                 out.println("POSSIBLE");
                 for (int i = 0; i < h; i++)
                     out.println(grid[i]);
             }
         }
 
         out.close();
         System.out.printf("Output to %s (first 10 lines):\n", file);
         Files.lines(file.toPath()).limit(10).forEach(System.out::println);
     }
 
     String not(String s) {
         if (s.startsWith("h"))
             return "v" + s.substring(1);
         else if (s.startsWith("v"))
             return "h" + s.substring(1);
         throw die();
     }
 
     
     public void DFS(Graph<String> graph, String v, Set<String> visited, List<String> comp)
     {
         visited.add(v);
         for (String n : graph.neighbors.get(v)) {
             if (!visited.contains(n))
                 DFS(graph, n, visited, comp);
         }
         comp.add(v);
     }
     
     public List<String> fillOrder(Graph<String> graph, Set<String> visited)
     {
         List<String> order = new ArrayList<>();
 
         for (String v : graph.vertices)
             if (!visited.contains(v))
                 DFS(graph, v, visited, order);
         return order;
     }
     
     public List<List<String>> getSCComponents(Graph<String> graph)
     {
         Set<String> visited = set();
         List<String> order = fillOrder(graph, visited);
         
         Graph<String> reverseGraph = Graph.create();
         reverseGraph.vertices.addAll(graph.vertices);
         Multimaps.invertFrom(graph.neighbors, reverseGraph.neighbors);
         
         visited = set();
         
         Collections.reverse(order);
 
         
         List<List<String>> SCComp = new ArrayList<>();
         for (int i = 0; i < order.size(); i++)
         {
             
             String v = order.get(i);
             if (!visited.contains(v))
             {
                 List<String> comp = new ArrayList<>();
                 DFS(reverseGraph, v, visited, comp);
                 SCComp.add(comp);
             }
         }
         return SCComp;
     }
 }
