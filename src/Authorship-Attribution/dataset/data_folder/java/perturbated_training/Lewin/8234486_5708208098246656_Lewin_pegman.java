import java.io.*;
 import java.util.*;
 
 public class pegman {
   private static InputReader in;
   private static PrintWriter out;
   public static boolean SUBMIT = true;
   public static final String NAME = "A-small-attempt0";
 
   private static void main2() throws IOException {
     int R = in.nextInt(), C = in.nextInt();
     char[][] grid = new char[R][C];
     for (int i = 0; i < R; i++) grid[i] = in.next().toCharArray();
     int count = 0;
     for (int i = 0; i < R; i++) {
       for (int j = 0; j < C; j++) {
         if (grid[i][j] != '.') {
           if (grid[i][j] == '<') count++;
           break;
         }
       }
       for (int j = C-1; j >= 0; j--) {
         if (grid[i][j] != '.') {
           if (grid[i][j] == '>') count++;
           break;
         }
       }
     }
     for (int i = 0; i < C; i++) {
       for (int j = 0; j < R; j++) {
         if (grid[j][i] != '.') {
           if (grid[j][i] == '^') count++;
           break;
         }
       }
       for (int j = R-1; j >= 0; j--) {
         if (grid[j][i] != '.') {
           if (grid[j][i] == 'v') count++;
           break;
         }
       }
     }
     
     for (int i = 0; i < R; i++) {
       for (int j = 0; j < C; j++) {
         if (grid[i][j] == '.') continue;
         boolean ok = false;
         for (int k = 0; k < R; k++) {
           if (k == i) continue;
           if (grid[k][j] != '.') ok = true;
         }
         for (int k = 0; k < C; k++) {
           if (k == j) continue;
           if (grid[i][k] != '.') ok = true;
         }
         
         if (!ok) {
           out.println("IMPOSSIBLE");
           return;
         }
       }
     }
     
     out.println(count);
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
