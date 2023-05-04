import java.io.*;
 
 public class Pegman implements Runnable {
   private static final String NAME = "pegman";
 
   private  StreamTokenizer in;
 
   int nextInt() throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong() throws Exception {
     in.nextToken();
     return (long) in.nval;
   }
 
   String next() throws Exception {
     in.nextToken();
     return in.sval;
   }
 
   @Override
   public void run() {
     try {
       
       BufferedReader in = new BufferedReader(new FileReader(new File(NAME + ".in")));
       
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         String[] ss = in.readLine().split(" ");
         int n = Integer.parseInt(ss[0]);
         int m = Integer.parseInt(ss[1]);
         String[] s = new String[n];
         for (int i = 0; i < n; i++) {
           s[i] = in.readLine();
         }
         boolean[][] l = new boolean[n][m];
         boolean[][] r = new boolean[n][m];
         boolean[][] u = new boolean[n][m];
         boolean[][] d = new boolean[n][m];
         for (int i = 0; i < n; i++) {
           int j = 0;
           while (j < m && s[i].charAt(j) == '.') {
             j++;
           }
           if (j < m) {
             l[i][j] = true;
           }
           j = m - 1;
           while (j >= 0 && s[i].charAt(j) == '.') {
             j--;
           }
           if (j >= 0) {
             r[i][j] = true;
           }
         }
         for (int j = 0; j < m; j++) {
           int i = 0;
           while (i < n && s[i].charAt(j) == '.') {
             i++;
           }
           if (i < n) {
             u[i][j] = true;
           }
           i = n - 1;
           while (i >= 0 && s[i].charAt(j) == '.') {
             i--;
           }
           if (i >= 0) {
             d[i][j] = true;
           }
         }
         int res = 0;
         boolean bad = false;
         for (int i = 0; i < n; i++) {
           for (int j = 0; j < m; j++) {
             boolean imp = l[i][j] && r[i][j] & u[i][j] & d[i][j];
             if (imp) {
               bad = true;
             } else {
               if (s[i].charAt(j) == '^' && u[i][j]) res++;
               if (s[i].charAt(j) == '>' && r[i][j]) res++;
               if (s[i].charAt(j) == 'v' && d[i][j]) res++;
               if (s[i].charAt(j) == '<' && l[i][j]) res++;
             }
           }
         }
         if (bad) {
           out.println("Case #" + test + ": IMPOSSIBLE");
         } else {
           out.println("Case #" + test + ": " + res);
         }
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Pegman()).start();
   }
 }
