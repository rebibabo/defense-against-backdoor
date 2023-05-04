import java.io.*;
 import java.util.*;
 
 public class Sample implements Runnable {
   private static final String NAME = "cake";
 
   private  StreamTokenizer in;
 
   int nextInt() throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong() throws Exception {
     in.nextToken();
     return (long) in.nval;
   }
 
   @Override
   public void run() {
     try {
       
       BufferedReader in = new BufferedReader(new FileReader(new File(NAME + ".in")));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = Integer.parseInt(in.readLine());
 
       for (int test = 1; test <= tests; test++) {
         String[] s = in.readLine().split(" ");
         int n = Integer.parseInt(s[0]);
         int m = Integer.parseInt(s[1]);
         String[] c = new String[n];
         boolean[] e = new boolean[n];
         for (int i = 0; i < n; i++) {
           c[i] = in.readLine();
           for (int j = 0; j < m; j++) {
             if (c[i].charAt(j) != '?') {
               e[i] = true;
             }
           }
         }
         char[][] res = new char[n][m];
         int lastN = -1;
         for (int i = 0; i < n; i++) {
           if (e[i]) {
             int lastM = -1;
             for (int j = 0; j < m; j++) {
               if (c[i].charAt(j) != '?') {
                 for (int jj = lastM + 1; jj <= j; jj++) {
                   res[i][jj] = c[i].charAt(j);
                 }
                 lastM = j;
               }
             }
             for (int j = lastM + 1; j < m; j++) {
               res[i][j] = c[i].charAt(lastM);
             }
             for (int ii = lastN + 1; ii < i; ii++) {
               for (int j = 0; j < m; j++) {
                 res[ii][j] = res[i][j];
               }
             }
             lastN = i;
           }
         }
         for (int ii = lastN + 1; ii < n; ii++) {
           for (int j = 0; j < m; j++) {
             res[ii][j] = res[lastN][j];
           }
         }
         out.println("Case #" + test + ":");
         for (int i = 0; i < n; i++) {
           for (int j = 0; j < m; j++) {
             out.print(res[i][j]);
           }
           out.println();
         }
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Sample()).start();
   }
 }
