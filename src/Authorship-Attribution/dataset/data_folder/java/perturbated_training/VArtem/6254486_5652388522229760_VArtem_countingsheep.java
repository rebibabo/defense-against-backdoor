import java.io.*;
 import java.util.*;
 
 public class CountingSheep {
 
     public void solve() {
         int n = in.nextInt();
         if (n == 0) {
             out.println("INSOMNIA");
             return;
         }
         boolean[] used = new boolean[10];
         for (int i = 1; ; i++) {
             long cur = 1L * i * n;
             while (cur != 0) {
                 used[(int) (cur % 10)] = true;
                 cur /= 10;
             }
             boolean ok = true;
             for (int j = 0; j < 10; j++) {
                 ok &= used[j];
             }
             if (ok) {
                 out.println(1L * i * n);
                 break;
             }
         }
     }
 
     public void run() {
         try {
             in = new FastScanner("input.txt");
             out = new PrintWriter("output.txt");
             int tests = in.nextInt();
             for (int i = 1; i <= tests; i++) {
                 out.printf("Case #%d: ", i);
                 solve();
             }
             out.close();
         } catch (FileNotFoundException e) {
         }
     }
 
     FastScanner in;
     PrintWriter out;
 
     class FastScanner {
         BufferedReader br;
         StringTokenizer st;
 
         public FastScanner(String fileName) {
             try {
                 br = new BufferedReader(new FileReader(fileName));
             } catch (FileNotFoundException e) {
             }
         }
 
         String nextToken() {
             while (st == null || !st.hasMoreElements()) {
                 try {
                     st = new StringTokenizer(br.readLine());
                 } catch (IOException e) {
                 }
             }
             return st.nextToken();
         }
 
         int nextInt() {
             return Integer.parseInt(nextToken());
         }
 
         long nextLong() {
             return Long.parseLong(nextToken());
         }
 
         double nextDouble() {
             return Double.parseDouble(nextToken());
         }
     }
 
     public static void main(String[] args) {
         new CountingSheep().run();
     }
 }
