import java.io.File;
 import java.io.PrintWriter;
 import java.io.StreamTokenizer;
 import java.util.*;
 
 public class NoisyDumb implements Runnable {
   private static final String NAME = "noisy";
 
   int nextInt(StreamTokenizer in) throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong(StreamTokenizer in) throws Exception {
     in.nextToken();
     return (long) in.nval;
   }
 
   void gen(int w, int l, boolean[] u) {
     if (l == u.length) {
       count(w, u);
       return;
     }
     gen(w, l + 1, u);
     u[l] = true;
     gen(w, l + 1, u);
     u[l] = false;
   }
 
   private void count(int w, boolean[] u) {
     int n = u.length;
     if (n % w != 0) {
       throw new IllegalStateException();
     }
     int h = n / w;
     int num = 0;
     for (int i = 0; i < n; i++) {
       if (u[i]) num++;
     }
     int res = 0;
     if (w == 5 && h == 3 && num == 9) {
       System.out.println(Arrays.toString(u));
     }
     for (int i = 0; i < n; i++) {
       if (u[i]) {
         int[] a = p(w, i);
         int[] b = {a[0], a[1] + 1};
         if (b[0] < h && b[1] < w) {
           if (u[q(w, b)]) {
             res++;
           }
         }
         int[] b2 = {a[0] + 1, a[1]};
         if (b2[0] < h && b2[1] < w) {
           if (u[q(w, b2)]) {
             res++;
           }
         }
       }
     }
 
     S s = new S(w, n / w, num);
     int min = Math.min(res, solve.get(s));
     solve.put(s, min);
   }
 
   private int[] p(int w, int a) {
     return new int[]{a / w, a % w};
   }
 
   private int q(int w, int[] a) {
     return a[0] * w + a[1];
   }
 
   private Map<S, Integer> solve = new HashMap<S, Integer>();
 
   @Override
   public void run() {
     try {
       Scanner in = new Scanner(new File(NAME + ".in"));
       
       
 
       for (int i = 1; i < 20; i++) {
         for (int j = 1; j < 20; j++) {
           if ( i * j > 16) continue;
           for (int n = 0; n <= i * j; n++) {
             solve.put(new S(i, j, n), Integer.MAX_VALUE / 2);
           }
           boolean[] u = new boolean[i * j];
           gen(i, 0, u);
         }
       }
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = in.nextInt();
 
       for (int test = 1; test <= tests; test++) {
         
         int w = in.nextInt();
         int h = in.nextInt();
         int n = in.nextInt();
         if (!solve.containsKey(new S(w, h, n))) {
           throw new IllegalStateException();
         }
         out.println("Case #" + test + ": " + solve.get(new S(w, h, n)));
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new NoisyDumb()).start();
   }
 
   private class S {
     int w;
     int h;
     int n;
 
     public S(int w, int h, int n) {
       this.w = w;
       this.h = h;
       this.n = n;
     }
 
     @Override
     public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
 
       S s = (S) o;
 
       if (h != s.h) return false;
       if (n != s.n) return false;
       if (w != s.w) return false;
 
       return true;
     }
 
     @Override
     public int hashCode() {
       int result = w;
       result = 31 * result + h;
       result = 31 * result + n;
       return result;
     }
   }
 }
