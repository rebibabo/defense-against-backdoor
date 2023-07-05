import java.util.ArrayDeque;
 import java.util.ArrayList;
 import java.util.Deque;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.Scanner;
 import java.util.Set;
 
 public class C {
     enum Direction {
         UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);
         private final int r, c;
 
         private Direction(int r, int c) {
             this.r = r;
             this.c = c;
         }
     }
 
     static char[][] grid;
     static int R, C;
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         outer: for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             R = sc.nextInt();
             C = sc.nextInt();
             sc.nextLine();
 
             List<Point> lasers = new ArrayList<>();
             Set<Point> empty = new HashSet<>();
             grid = new char[R][C];
             for (int i = 0; i < R; i++) {
                 String line = sc.nextLine();
                 for (int j = 0; j < C; j++) {
                     grid[i][j] = line.charAt(j);
                     if (grid[i][j] == '-' || grid[i][j] == '|') {
                         lasers.add(new Point(i, j));
                     }
                     if (grid[i][j] == '.') {
                         empty.add(new Point(i, j));
                     }
                 }
             }
 
             System.out.print("Case #" + caseNum + ": ");
 
             Map<Point, Set<Point>> verticals = new HashMap<>();
             Map<Point, Set<Point>> horizontals = new HashMap<>();
 
             Map<Pair, Set<Pair>> edges = new HashMap<>();
             Map<Pair, Set<Pair>> inEdges = new HashMap<>();
 
             for (Point laser : lasers) {
                 Set<Point> up = new HashSet<>();
                 Set<Point> down = new HashSet<>();
                 Set<Point> left = new HashSet<>();
                 Set<Point> right = new HashSet<>();
                 boolean canVertical = walk(laser.r, laser.c, Direction.UP, up)
                         && walk(laser.r, laser.c, Direction.DOWN, down);
                 if (canVertical) {
                     Set<Point> vertical = new HashSet<>();
                     vertical.addAll(up);
                     vertical.addAll(down);
                     verticals.put(laser, vertical);
                 }
                 boolean canHorizontal = walk(laser.r, laser.c, Direction.LEFT, left)
                         && walk(laser.r, laser.c, Direction.RIGHT, right);
                 if (canHorizontal) {
                     Set<Point> horizontal = new HashSet<>();
                     horizontal.addAll(up);
                     horizontal.addAll(down);
                     horizontals.put(laser, horizontal);
                 }
                 if (!canVertical && !canHorizontal) {
                     System.out.println("IMPOSSIBLE");
                     continue outer;
                 }
                 if (!canVertical) {
                     edges.computeIfAbsent(new Pair(laser, true), k -> new HashSet<>());
                     edges.get(new Pair(laser, true)).add(new Pair(laser, false));
                     inEdges.computeIfAbsent(new Pair(laser, false), k -> new HashSet<>());
                     inEdges.get(new Pair(laser, false)).add(new Pair(laser, true));
                 }
                 if (!canHorizontal) {
                     edges.computeIfAbsent(new Pair(laser, false), k -> new HashSet<>());
                     edges.get(new Pair(laser, false)).add(new Pair(laser, true));
                     inEdges.computeIfAbsent(new Pair(laser, true), k -> new HashSet<>());
                     inEdges.get(new Pair(laser, true)).add(new Pair(laser, false));
                 }
             }
 
             Map<Point, List<Pair>> covering = new HashMap<>();
             for (Point p : empty) {
                 covering.put(p, new ArrayList<>());
             }
             for (Map.Entry<Point, Set<Point>> entry : verticals.entrySet()) {
                 Pair laser = new Pair(entry.getKey(), true);
                 for (Point p : entry.getValue()) {
                     covering.get(p).add(laser);
                 }
             }
             for (Map.Entry<Point, Set<Point>> entry : horizontals.entrySet()) {
                 Pair laser = new Pair(entry.getKey(), false);
                 for (Point p : entry.getValue()) {
                     covering.get(p).add(laser);
                 }
             }
 
             for (Point p : empty) {
                 List<Pair> list = covering.get(p);
                 if (list.isEmpty()) {
                     System.out.println("IMPOSSIBLE");
                     continue outer;
                 }
                 if (list.size() > 2) {
                     throw new RuntimeException();
                 }
                 Pair a = list.get(0);
                 Pair b = list.size() == 1 ? a : list.get(1);
 
                 Pair notA = new Pair(a.p, !a.b);
                 Pair notB = new Pair(b.p, !b.b);
 
                 edges.computeIfAbsent(notA, k -> new HashSet<>());
                 edges.computeIfAbsent(notB, k -> new HashSet<>());
                 edges.get(notA).add(b);
                 edges.get(notB).add(a);
 
                 inEdges.computeIfAbsent(a, k -> new HashSet<>());
                 inEdges.computeIfAbsent(b, k -> new HashSet<>());
                 inEdges.get(a).add(notB);
                 inEdges.get(b).add(notA);
             }
 
             LinkedList<Pair> L = new LinkedList<>();
             Set<Pair> visited = new HashSet<>();
             Deque<Pair> stack = new ArrayDeque<>(edges.keySet());
             stack.addAll(inEdges.keySet());
             for (Pair p : stack) {
                 dfs(edges, inEdges, p, L, visited);
             }
 
             Map<Pair, Integer> component = new HashMap<>();
             List<List<Pair>> c = new ArrayList<>();
             int componentNum = -1;
             for (Pair p : L) {
                 if (component.containsKey(p)) {
                     continue;
                 }
                 componentNum++;
                 c.add(new ArrayList<>());
                 Deque<Pair> queue = new ArrayDeque<>();
                 queue.offer(p);
                 while (!queue.isEmpty()) {
                     Pair q = queue.poll();
                     if (component.containsKey(q)) {
                         continue;
                     }
                     component.put(q, componentNum);
                     c.get(componentNum).add(q);
                     for (Pair node : inEdges.computeIfAbsent(q, k -> new HashSet<>())) {
                         if (component.containsKey(node)) {
                             continue;
                         }
                         queue.add(node);
                     }
                 }
             }
             for (Point p : lasers) {
                 Pair vertical = new Pair(p, true);
                 Pair horizontal = new Pair(p, false);
                 if (component.getOrDefault(vertical, -1) == component.getOrDefault(horizontal, -2)) {
                     System.out.println("IMPOSSIBLE");
                     continue outer;
                 }
             }
             Map<Point, Boolean> assignments = new HashMap<>();
             for (int i = c.size() - 1; i >= 0; i--) {
                 for (Pair p : c.get(i)) {
                     if (assignments.containsKey(p.p) && assignments.get(p.p) != p.b) {
                         continue;
                     }
                     assignments.put(p.p, p.b);
                 }
             }
             System.out.println("POSSIBLE");
             for (int i = 0; i < R; i++) {
                 for (int j = 0; j < C; j++) {
                     if (grid[i][j] == '-' || grid[i][j] == '|') {
                         Boolean isVertical = assignments.getOrDefault(new Point(i, j), true);
                         if (isVertical) {
                             System.out.print('|');
                         } else {
                             System.out.print('-');
                         }
                     } else {
                         System.out.print(grid[i][j]);
                     }
                 }
                 System.out.println();
             }
         }
     }
 
     private static void dfs(Map<Pair, Set<Pair>> edges, Map<Pair, Set<Pair>> inEdges, Pair p, LinkedList<Pair> L,
             Set<Pair> visited) {
         if (visited.contains(p)) {
             return;
         }
         visited.add(p);
         for (Pair next : edges.computeIfAbsent(p, k -> new HashSet<>())) {
             if (!visited.contains(next)) {
                 dfs(edges, inEdges, next, L, visited);
             }
         }
         L.addFirst(p);
     }
 
     private static boolean walk(int r, int c, Direction d, Set<Point> output) {
         int nextR = r + d.r;
         int nextC = c + d.c;
         if (0 > nextR || nextR >= R) {
             return true;
         }
         if (0 > nextC || nextC >= C) {
             return true;
         }
 
         char next = grid[nextR][nextC];
         if (next == '-' || next == '|') {
             return false;
         }
         Direction nextDir = d;
         if (next == '.') {
             output.add(new Point(nextR, nextC));
         }
         if (next == '#') {
             return true;
         }
         if (next == '/') {
             switch (d) {
             case UP:
                 nextDir = Direction.RIGHT;
                 break;
             case DOWN:
                 nextDir = Direction.LEFT;
                 break;
             case LEFT:
                 nextDir = Direction.DOWN;
                 break;
             case RIGHT:
                 nextDir = Direction.UP;
                 break;
             }
         }
         if (next == '\\') {
             switch (d) {
             case UP:
                 nextDir = Direction.LEFT;
                 break;
             case DOWN:
                 nextDir = Direction.RIGHT;
                 break;
             case LEFT:
                 nextDir = Direction.UP;
                 break;
             case RIGHT:
                 nextDir = Direction.DOWN;
                 break;
             }
         }
         return walk(nextR, nextC, nextDir, output);
     }
 
     private static class Point {
         private final int r, c;
 
         public Point(int r, int c) {
             this.r = r;
             this.c = c;
         }
 
         @Override
         public int hashCode() {
             final int prime = 31;
             int result = 1;
             result = prime * result + c;
             result = prime * result + r;
             return result;
         }
 
         @Override
         public boolean equals(Object obj) {
             if (this == obj) {
                 return true;
             }
             if (obj == null) {
                 return false;
             }
             if (getClass() != obj.getClass()) {
                 return false;
             }
             Point other = (Point) obj;
             if (c != other.c) {
                 return false;
             }
             if (r != other.r) {
                 return false;
             }
             return true;
         }
 
         @Override
         public String toString() {
             return String.format("(%s, %s)", r, c);
         }
     }
 
     private static class Pair {
         private final Point p;
         private final boolean b;
 
         public Pair(Point p, boolean vertical) {
             this.p = p;
             this.b = vertical;
         }
 
         @Override
         public int hashCode() {
             final int prime = 31;
             int result = 1;
             result = prime * result + ((p == null) ? 0 : p.hashCode());
             result = prime * result + (b ? 1231 : 1237);
             return result;
         }
 
         @Override
         public boolean equals(Object obj) {
             if (this == obj) {
                 return true;
             }
             if (obj == null) {
                 return false;
             }
             if (getClass() != obj.getClass()) {
                 return false;
             }
             Pair other = (Pair) obj;
             if (p == null) {
                 if (other.p != null) {
                     return false;
                 }
             } else if (!p.equals(other.p)) {
                 return false;
             }
             if (b != other.b) {
                 return false;
             }
             return true;
         }
 
         @Override
         public String toString() {
             return "Pair [p=" + p + ", b=" + b + "]";
         }
     }
 }
