import java.util.*;
 
 class A {
    static class Pointer {
       char dir;
       int r, c;
       boolean hasPrev = false;
       boolean walksOffEdge = false;
       public Pointer(char dir, int r, int c) {
          this.dir = dir;
          this.r = r;
          this.c = c;
       }
    }
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d: %s\n", i, solve(scan));
       }
    }
 
    public static Object solve(Scanner scan) {
       int R = scan.nextInt(), C = scan.nextInt();
       scan.nextLine();
       Pointer[][] points = new Pointer[R][C];
       List<Pointer> lst = new ArrayList<Pointer>();
       for (int i = 0; i < R; i++) {
          char[] line = scan.nextLine().toCharArray();
          for (int j = 0; j < C; j++) {
             if (line[j] != '.') {
                Pointer p = new Pointer(line[j], i, j);
                points[i][j] = p;
                lst.add(p);
             }
          }
       }
 
       for (Pointer p : lst) {
          if (p.dir == '<') {
             foo(points, p, 0, -1);
          } else if (p.dir == '>') {
             foo(points, p, 0, 1);
          } else if (p.dir == '^') {
             foo(points, p, -1, 0);
          } else if (p.dir == 'v') {
             foo(points, p, 1, 0);
          }
       }
 
       int count = 0;
       for (Pointer p : lst) {
          System.err.println(p.hasPrev + " "+p.walksOffEdge);
          if (p.walksOffEdge) {
             if (!p.hasPrev) {
                return "IMPOSSIBLE";
             } else {
                count++;
             }
          }
       }
       return count;
    }
 
    public static void foo(Pointer[][] points, Pointer p, int dr, int dc) {
       int r = p.r + dr, c = p.c + dc;
       for (int i = 0; !p.hasPrev && i < points.length; i++) {
          if (i != p.r && points[i][p.c] != null)
             p.hasPrev = true;
       }
       for (int i = 0; !p.hasPrev && i < points[0].length; i++) {
          if (i != p.c && points[p.r][i] != null)
             p.hasPrev = true;
       }
       for ( ; c >= 0 && c < points[0].length && r >= 0 && r < points.length; c+=dc, r+=dr) {
          if (points[r][c] != null) {
             return;
          }
       }
       p.walksOffEdge = true;
    }
 }
