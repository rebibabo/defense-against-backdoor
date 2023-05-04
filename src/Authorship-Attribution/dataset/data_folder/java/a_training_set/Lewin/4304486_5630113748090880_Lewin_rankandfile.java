import java.io.*;
 import java.util.*;
 
 public class rankandfile {
   private static InputReader in;
   private static PrintWriter out;
   public static boolean SUBMIT = true;
   public static final String NAME = "B-small-attempt1";
   
   static class List implements Comparable<List> {
     public int[] x;
     public List(int[] x) {
       this.x = x;
     }
     public int compareTo(List other) {
       for (int i = 0; i < x.length; i++) {
         if (x[i] != other.x[i])
           return x[i] - other.x[i];
       }
       return 0;
     }
   }
 
   private static void main2() throws IOException {
     int n = in.nextInt();
     int m = 2*n-1;
     int[] freq = new int[2501];
     for (int i = 0; i < m; i++) {
       for (int j = 0; j < n; j++) {
         freq[in.nextInt()]++;
       }
     }
     int[] ans = new int[n];
     int idx = 0;
     for (int j = 0; j <= 2500; j++) {
       if (freq[j] % 2 == 1)
         ans[idx++] = j;
     }
     out.print(ans[0]);
     for (int i = 1; i < n; i++) out.print(" "+ans[i]);
     out.println();
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
