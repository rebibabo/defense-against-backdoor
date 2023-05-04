import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.ArrayList;
 import java.util.Arrays;
 
 public class Joy implements Runnable {
   private static final String NAME = "joy";
 
   private  StreamTokenizer in;
 
   int nextInt() throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong() throws Exception {
     in.nextToken();
     return (long) in.nval;
   }
 
   int[] dx = new int[] { 1, -1, 0, 0 };
   int[] dy = new int[] { 0, 0, 1, -1 };
 
   int r;
   int c;
   String[] g;
   int nb;
   int ne;
   int[][] num;
   int[][] h;
   boolean[][] b;
   ArrayList<ArrayList<Integer>> t;
 
   @Override
   public void run() {
     try {
       
       BufferedReader in = new BufferedReader(new FileReader(new File(NAME + ".in")));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         String[] ss = in.readLine().split(" ");
         r = Integer.parseInt(ss[0]);
         c = Integer.parseInt(ss[1]);
         g = new String[r];
         for (int i = 0; i < r; i++) {
           g[i] = in.readLine();
         }
         nb = 0;
         ne = 0;
         num = new int[r][c];
 
         for (int i = 0; i < r; i++) {
           for (int j = 0; j < c; j++) {
             char cc = g[i].charAt(j);
             if (cc == '|' || cc == '-') {
               num[i][j] = nb++;
             }
             if (cc == '.') {
               num[i][j] = ne++;
             }
           }
         }
 
         h = new int[ne][2];
         for (int i = 0; i < ne; i++) {
           h[i] = new int[] { -1, -1 };
         }
 
         b = new boolean[nb][2];
 
         t = new ArrayList<>();
         for (int i = 0; i < nb * 2; i++) {
           t.add(new ArrayList<>());
         }
 
         for (int i = 0; i < r; i++) {
           for (int j = 0; j < c; j++) {
             char cc = g[i].charAt(j);
             if (cc == '|' || cc == '-') {
               ArrayList<Integer> t0 = trace(i, j, 0);
               ArrayList<Integer> t1 = trace(i, j, 1);
               if (t0 == null || t1 == null) {
                 b[num[i][j]][0] = false;
               } else {
                 b[num[i][j]][0] = true;
                 t.get(2 * num[i][j]).addAll(t0);
                 t.get(2 * num[i][j]).addAll(t1);
               }
               for (Integer e : t.get(2 * num[i][j])) {
                 h[e][0] = num[i][j];
               }
 
               t0 = trace(i, j, 2);
               t1 = trace(i, j, 3);
               if (t0 == null || t1 == null) {
                 b[num[i][j]][1] = false;
               } else {
                 b[num[i][j]][1] = true;
                 t.get(2 * num[i][j] + 1).addAll(t0);
                 t.get(2 * num[i][j] + 1).addAll(t1);
               }
               for (Integer e : t.get(2 * num[i][j] + 1)) {
                 h[e][1] = num[i][j];
               }
             }
           }
         }
 
         boolean res = true;
         for (int i = 0; i < nb; i++) {
           if (!b[i][0] && !b[i][1]) {
             res = false;
           }
         }
 
         boolean[] u = new boolean[ne];
         int[] rb = new int[nb];
         Arrays.fill(rb, -1);
 
         if (res) {
           int curN = ne;
           while (res && curN != 0) {
             boolean found = false;
             int first = -1;
             for (int i = 0; i < ne; i++) {
               if (!u[i]) {
                 if (first == -1) {
                   first = i;
                 }
 
                 if (h[i][0] == -1 && h[i][1] == -1) {
                   res = false;
                   break;
                 }
 
                 if (h[i][0] == -1 && h[i][1] != -1) {
                   u[i] = true;
                   curN--;
                   int b = h[i][1];
                   rb[b] = 1;
                   for (Integer e : t.get(2 * b + 1)) {
                     if (!u[e]) {
                       curN--;
                       u[e] = true;
                     }
                   }
                   for (Integer e : t.get(2 * b)) {
                     h[e][0] = -1;
                   }
                   found = true;
                   break;
                 }
 
                 if (h[i][0] != -1 && h[i][1] == -1) {
                   u[i] = true;
                   curN--;
                   int b = h[i][0];
                   rb[b] = 0;
                   for (Integer e : t.get(2 * b)) {
                     if (!u[e]) {
                       curN--;
                       u[e] = true;
                     }
                   }
                   for (Integer e : t.get(2 * b + 1)) {
                     h[e][0] = -1;
                   }
                   found = true;
                   break;
                 }
 
               }
             }
 
             if (!res) {
               break;
             }
 
             if (!found) {
               u[first] = true;
               curN--;
               int b = h[first][1];
               rb[b] = 1;
               for (Integer e : t.get(2 * b + 1)) {
                 if (!u[e]) {
                   curN--;
                   u[e] = true;
                 }
               }
               for (Integer e : t.get(2 * b)) {
                 h[e][0] = -1;
               }
             }
           }
         }
 
         if (!res) {
           out.println("Case #" + test + ": IMPOSSIBLE");
         } else {
           out.println("Case #" + test + ": POSSIBLE");
           for (int i = 0; i < r; i++) {
             String rs = "";
             for (int j = 0; j < c; j++) {
               char cc = g[i].charAt(j);
               if (cc == '|' || cc == '-') {
                 int bb = num[i][j];
                 if (rb[bb] == 0 || (rb[bb] == -1 && b[bb][0])) {
                   rs += "|";
                 } else {
                   rs += "-";
                 }
               } else {
                 rs += cc;
               }
             }
             out.println(rs);
           }
         }
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   private ArrayList<Integer> trace(int i, int j, int d) {
     int ii = i + dx[d];
     int jj = j + dy[d];
     ArrayList<Integer> path = new ArrayList<>();
     boolean ok = true;
     while (0 <= ii && ii < r && 0 <= jj && jj < c) {
       char cur = g[ii].charAt(jj);
       if (cur == '|' || cur == '-') {
         ok = false;
         break;
       }
       if (cur == '#') {
         break;
       }
       if (cur == '.') {
         path.add(num[ii][jj]);
       }
       ii += dx[d];
       jj += dy[d];
     }
     if (!ok) {
       return null;
     } else {
       return path;
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Joy()).start();
   }
 }