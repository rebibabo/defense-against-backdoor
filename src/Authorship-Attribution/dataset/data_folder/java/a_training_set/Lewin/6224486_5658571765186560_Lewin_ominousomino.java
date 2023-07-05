import java.io.*;
 import java.util.*;
 
 public class ominousomino {
   private static InputReader in;
   private static PrintWriter out;
   public static boolean SUBMIT = true;
   public static final String NAME = "D-small-attempt7";
 
   public static int gcd(int a, int b) {
     return b == 0 ? a : gcd(b,a%b);
   }
   
   private static void main2() throws IOException {
     int X = in.nextInt();
     int R = in.nextInt();
     int C = in.nextInt();
     
     int a = Math.min(R,C), b = Math.max(R,C);
     if ((R * C) % X != 0 || X >= 7 || (1+2*a) <= X || b < X || (X > a && gcd(X,a) > 1)) {
       out.println("RICHARD");
     } else {
       out.println("GABRIEL");
     }
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
     System.out.println(numCases);
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
