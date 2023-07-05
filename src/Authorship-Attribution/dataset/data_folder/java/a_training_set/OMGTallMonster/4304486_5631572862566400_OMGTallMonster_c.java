import java.util.*;
 
 class C {
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       int T = scan.nextInt();
       scan.nextLine();
       for (int i = 1; i <= T; i++) {
          System.out.printf("Case #%d: %s\n", i, solve(scan));
       }
    }
 
    public static int markCycles(int[] adj, int[] c) {
       int maxCycle = -1;
       for (int startNdx = 0; startNdx < adj.length; startNdx++) {
          if (c[startNdx] != 0) continue;
 
          ArrayList<Integer> hist = new ArrayList<Integer>();
          int tmp = startNdx;
          while (!hist.contains(tmp)) {
             hist.add(tmp);
             tmp = adj[tmp];
          }
 
          int cycleLen = hist.size()-hist.indexOf(tmp);
 
          for (int i : hist) {
             c[i] = -1;
          }
 
          c[tmp] = cycleLen;
          for (int tmp2 = adj[tmp]; tmp2 != tmp; tmp2 = adj[tmp2]) {
             c[tmp2] = cycleLen;
          }
          maxCycle = Math.max(maxCycle,cycleLen);
       }
       return maxCycle;
    }
 
    public static boolean isValidPath(int[] adj, int[] c, int[] p, int start) {
       int tmp = start;
       while (c[tmp] == -1 && p[tmp] != -1) {
          tmp = adj[tmp];
       }
       return c[tmp] == 2 && p[tmp] == 0;
    }
 
    public static void markAllPaths(int[] adj, int[] c, int[] p) {
       for (int startNdx = 0; startNdx < adj.length; startNdx++) {
          if (c[startNdx] != -1) continue;
 
          ArrayList<Integer> hist = new ArrayList<Integer>();
          int tmp = startNdx;
          while (c[tmp] == -1 && p[tmp] == 0) {
             hist.add(tmp);
             tmp = adj[tmp];
          }
          if (p[tmp] > 0 || c[tmp] == 2) {
             for (int i = 0; i < hist.size(); i++) {
                p[hist.get(i)] = hist.size()-i+p[tmp];
             }
          } else {
             for (int i : hist) p[i] = -1;
          }
       }
    }
 
    public static int markMaxPath(int[] adj, int[] c, int[] p) {
       int max = -1, maxNdx = -1;
       for (int i = 0; i < p.length; i++) {
          if (p[i] > 0 && !isValidPath(adj,c,p,i)) {
             p[i] = -1;
          } else {
             if (p[i] > max) {
                max = p[i];
                maxNdx = i;
             }
          }
       }
 
       if (max > 0) {
          int tmp = maxNdx;
          while (c[tmp] == -1) {
             p[tmp] = -1;
             tmp = adj[tmp];
          }
          p[tmp] = -1;
       }
       return max;
    }
 
    public static int countTwoLoops(int[] c) {
       int out = 0;
       for (int i : c) if (i == 2) out++;
       return out;
    }
 
    public static Object solve(Scanner scan) {
       int n = scan.nextInt();
       int[] adj = new int[n];
       for (int i = 0; i < n; i++) {
          adj[i] = scan.nextInt()-1;
       }
 
       int[] cycles = new int[n], paths = new int[n];
       int maxCycle = markCycles(adj, cycles);
       markAllPaths(adj, cycles, paths);
       int sum = 0, maxPath;
       do {
          maxPath = markMaxPath(adj, cycles, paths);
          if (maxPath > 0) {
             sum += maxPath;
          }
       } while (maxPath > 0);
       
       return Math.max(maxCycle,sum+countTwoLoops(cycles));
    }
 }
