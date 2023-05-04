import java.io.*;
 import java.util.Arrays;
 
 public class Monkey implements Runnable {
   private static final String NAME = "monkey";
 
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
       
       
       in = new StreamTokenizer(new BufferedReader(new FileReader(new File(NAME + ".in"))));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = nextInt();
 
       for (int test = 1; test <= tests; test++) {
         int k = nextInt();
         int l = nextInt();
         int s = nextInt();
         String kk = next();
         String ll = next();
         boolean possible = true;
         for (int i = 0; i < l; i++) {
           if (!kk.contains("" + ll.charAt(i))) {
             possible = false;
           }
         }
         if (!possible) {
           out.println("Case #" + test + ": 0.0");
           continue;
         }
 
         int maxP = 0;
         for (int i = 1; i < l; i++) {
           boolean ok = true;
           for (int j = 0; j < i; j++) {
             if (ll.charAt(j) != ll.charAt(l - i + j)) {
               ok = false;
               break;
             }
           }
           if (ok) {
             maxP = i;
           }
         }
         int max = 1 + (s - l) / (l - maxP);
 
         int[][] pref = new int[l + 1][26];
         for (int i = 0; i <= l; i++) {
           for (int c = 0; c < 26; c++) {
             int maxPP = 0;
             for (int ii = 0; ii <= i; ii++) {
               if (ii == 0 && i == l) {
                 continue;
               }
               boolean ok = true;
               for (int j = ii; j <= i; j++) {
                 char cur = j == i ? (char) ('A' + c) : ll.charAt(j);
                 if (ll.charAt(j - ii) != cur) {
                   ok = false;
                 }
               }
               if (ok) {
                 maxPP = i + 1 - ii;
                 break;
               }
             }
             pref[i][c] = maxPP;
           }
         }
 
         double[][] p = new double[s + 1][l + 1];
         double[][] e = new double[s + 1][l + 1];
         p[0][0] = 1.0;
         for (int i = 0; i <= s; i++) {
           e[i][l] = 1;
         }
 
         double[] pp = new double[26];
         for (int i = 0; i < 26; i++) {
           char c = (char) ('A' + i);
           int kkk = 0;
           for (int j = 0; j < k; j++) {
             if (kk.charAt(j) == c) kkk++;
           }
           pp[i] = 1.0 * kkk / k;
         }
         for (int i = 1; i <= s; i++) {
           for (int r = 0; r <= l; r++) {
             for (int c = 0; c < 26; c++) {
               double cur = 0;
               for (int rr = 0; rr <= l; rr++) {
                 if (pref[rr][c] == r) {
                   cur += p[i - 1][rr];
                 }
               }
               p[i][r] += pp[c] * cur;
             }
           }
           double a = 0;
           for (int r = 0; r <= l; r++) {
             a += p[i][r];
           }
           if (Math.abs(a - 1) > 1E-6) {
             throw new IllegalStateException();
           }
         }
         for (int i = 1; i <= s; i++) {
           for (int r = 0; r <= l; r++) {
             if (p[i][r] < 1E-10) {
               continue;
             }
             for (int c = 0; c < 26; c++) {
               double cur = 0;
               for (int rr = 0; rr <= l; rr++) {
                 if (pref[rr][c] == r) {
                   cur += e[i - 1][rr] * p[i - 1][rr];
                 }
               }
               e[i][r] += pp[c] * cur / p[i][r];
             }
           }
         }
         double res = 0.0;
         for (int i = 0; i <= l; i++) {
           res += p[s][i] * e[s][i];
         }
         out.println("Case #" + test + ": " + (max - res));
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Monkey()).start();
   }
 }
