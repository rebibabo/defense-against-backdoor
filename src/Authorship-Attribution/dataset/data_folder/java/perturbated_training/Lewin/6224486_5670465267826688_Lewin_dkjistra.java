import java.io.*;
 import java.util.*;
 
 public class dkjistra {
   private static InputReader in;
   private static PrintWriter out;
   public static boolean SUBMIT = true;
   public static final String NAME = "C-small-attempt2";
   
   public static int[][] table = new int[][] {
     {0,1,2,3},
     {1,0,3,2},
     {2,3,0,1},
     {3,2,1,0}
   };
   
   public static int[][] sign = new int[][] {
     {1,1,1,1},
     {1,-1,1,-1},
     {1,-1,-1,1},
     {1,1,-1,-1}
   };
   
   
   public static int[] exp(int val, int pow) {
     if (pow == 0) return new int[]{1,0};
     if (pow == 1) return new int[]{1,val};
     if (pow % 2 == 1) {
       int[] x = exp(val, pow-1);
       return new int[]{x[0] * sign[val][x[1]], table[val][x[1]]};
     } else {
       int[] x = exp(table[val][val], pow/2);
       if ((pow/2)%2 == 1) x[0] *= sign[val][val];
       return x;
     }
   }
   
   private static void main2() throws IOException {
     int L = in.nextInt(), X = in.nextInt();
     char[] c = in.next().toCharArray();
     int[] r = new int[L];
     for (int i = 0; i < L; i++) {
       r[i] = "1ijk".indexOf(""+c[i]);
     }
     int val = 0, sig = 1;
     for (int i = 0; i < L; i++) {
       sig *= sign[val][r[i]];
       val = table[val][r[i]];
     }
     
     int[] o = exp(val, X);
     if (X % 2 == 1) o[0] *= sig;
     if (o[0] != -1 || o[1] != 0) {
       out.println("NO");
       return;
     }
     
     int first = -1;
     val = 0;
     sig = 1;
     for (int i = 0; i < 100*L; i++) {
       sig *= sign[val][r[i%L]];
       val = table[val][r[i%L]];
       if (sig == 1 && val == 1) {
         first = i+1;
         break;
       }
     }
     if (first == -1) {
       out.println("NO");
       return;
     }
     
     int second = -1;
     val = 0;
     sig = 1;
     for (int i = 100*L-1; i >= 0; i--) {
       sig *= sign[r[i%L]][val];
       val = table[r[i%L]][val];
       if (sig == 1 && val == 3) {
         second = 100*L-i;
         break;
       }
     }
     
     if (second == -1) {
       out.println("NO");
       return;
     }
     
     if (first + second >= (long)L * (long)X) {
       out.println("NO");
       return;
     }
     
     out.println("YES");
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
   }
 }
