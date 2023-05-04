import java.io.*;
 
 public class Battle implements Runnable {
   private static final String NAME = "battle";
 
   private  StreamTokenizer in;
 
   int nextInt() throws Exception {
     in.nextToken();
     return (int) in.nval;
   }
 
   long nextLong() throws Exception {
     in.nextToken();
     return (long) in.nval;
   }
 
   private int find(int w, int c) {
     if (w == c) {
       return w;
     }
     if (w * 2 >= c) {
       return w + 1;
     }
     return find(w, c - w) + 1;
   }
 
   @Override
   public void run() {
     try {
       
       
       in = new StreamTokenizer(new BufferedReader(new FileReader(new File(NAME + ".in"))));
 
       PrintWriter out = new PrintWriter(NAME + ".out");
 
       int tests = nextInt();
 
       for (int test = 1; test <= tests; test++) {
         int r = nextInt();
         int c = nextInt();
         int w = nextInt();
         int res = find(w, c);
         res += (r - 1) * (c / w);
         out.println("Case #" + test + ": " + res);
       }
 
       out.close();
     } catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 
   public static void main(String[] args) {
     new Thread(new Battle()).start();
   }
 }
