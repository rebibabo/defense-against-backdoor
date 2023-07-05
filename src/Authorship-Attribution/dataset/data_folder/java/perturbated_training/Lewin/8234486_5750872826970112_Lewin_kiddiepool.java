import java.io.*;
 import java.util.*;
 
 public class kiddiepool {
   private static InputReader in;
   private static PrintWriter out;
   public static boolean SUBMIT = true;
   public static final String NAME = "B-small-attempt3";
   
   static class Item {
     public double R;
     public long C;
     public Item(double R, long C) {
       this.R = R;
       this.C = C;
     }
   }
 
   private static void main2() throws IOException {
     int N = in.nextInt();
     long V = in.nextDouble();
     long X = in.nextDouble();
     Item[] it = new Item[N];
     for (int i = 0; i < N; i++) {
       it[i] = new Item(in.nextDouble(), in.nextDouble());
     }
     double ans = -1;
     double alleq = 0;
     double aR = 0, aC = 0;
     double bR = 0, bC = 0;
     for (int i = 0; i < N; i++) {
       if (it[i].C == X) {
         alleq += it[i].R;
       } else if (it[i].C > X) {
         aC = (it[i].C * it[i].R + aC * aR) / (it[i].R + aR);
         aR += it[i].R;
       } else {
         bC = (it[i].C * it[i].R + bC * bR) / (it[i].R + bR);
         bR += it[i].R;
       }
     }
     if (bR == 0 || aR == 0) {
       if (alleq == 0) {
         out.println("IMPOSSIBLE");
         return;
       }
       ans = V / alleq;
     } else {
       double va = (X - bC) / (aC - bC);
       double vb = 1 - va;
       
       double ta = va / aR;
       double tb = vb / bR;
       
       ans = Math.max(ta, tb) * V;
     }
     if (ans == -1) out.println("IMPOSSIBLE");
     else out.printf("%.9f\n", ans);
   }
 
   public static void main(String[] args) throws IOException {
     if (SUBMIT) {
       in = new InputReader(new FileInputStream(new File(NAME + ".in")));
       out = new PrintWriter(new BufferedWriter(new FileWriter(NAME + ".out")));
     } else {
       in = new InputReader(System.in);
       out = new PrintWriter(System.out, true);
     }
 
     int numCases = in.nextInt();
     for (int test = 1; test <= numCases; test++) {
       out.print("Case #" + test + ": ");
       main2();
     }
 
     out.close();
     System.exit(0);
   }
 
   static class InputReader {
     public BufferedReader reader;
     public StringTokenizer tokenizer;
 
     public InputReader(InputStream stream) {
       reader = new BufferedReader(new InputStreamReader(stream), 32768);
       tokenizer = null;
     }
 
     public String next() {
       while (tokenizer == null || !tokenizer.hasMoreTokens()) {
         try {
           tokenizer = new StringTokenizer(reader.readLine());
         } catch (IOException e) {
           throw new RuntimeException(e);
         }
       }
       return tokenizer.nextToken();
     }
 
     public int nextInt() {
       return Integer.parseInt(next());
     }
     
     public int nextDouble() {
       String[] q = in.next().split("\\.");
       return Integer.parseInt(q[0])*10000+Integer.parseInt(q[1]);
     }
   }
 }
