import java.io.*;
 import java.util.*;
 
 public class goplus {
   private static InputReader in;
   private static PrintWriter out;
   public static boolean SUBMIT = true;
   public static final String NAME = "D-small-attempt2";
 
   private static void main2() throws IOException {
     int n = in.nextInt(), l = in.nextInt();
     HashSet<String> g = new HashSet<>();
     for (int i = 0; i < n; i++) g.add(in.next());
     String b = in.next();
     if (g.contains(b)) out.println("IMPOSSIBLE");
     else {
       String s1 = "";
       String s2 = "0";
       for (int i = 0; i < l; i++) s1 += "0?";
       for (int i = 0; i < l-1; i++) s2 += "1";
       out.println(s1+" "+s2);
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
