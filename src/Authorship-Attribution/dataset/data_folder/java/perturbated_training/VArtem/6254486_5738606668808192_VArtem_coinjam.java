import java.io.*;
 import java.math.BigInteger;
 import java.util.*;
 
 public class CoinJam {
 
     final int MAX = 1000;
 
     public void solve() {
         int n = in.nextInt(), j = in.nextInt();
         Map<String, List<Integer>> results = new HashMap<>();
         Random rng = new Random(123);
         while (results.size() < j) {
             String s = "1";
             for (int i = 1; i < n - 1; i++) {
                 s += (char) ('0' + rng.nextInt(2));
             }
             s += "1";
 
             List<Integer> divs = new ArrayList<>();
             for (int base = 2; base <= 10; base++) {
                 BigInteger bigInt = new BigInteger(s, base);
                 int div = -1;
                 for (int i = 2; i <= MAX; i++) {
                     if (bigInt.mod(BigInteger.valueOf(i)).equals(BigInteger.ZERO)) {
                         div = i;
                         break;
                     }
                 }
                 if (div == -1) {
                     break;
                 } else {
                     divs.add(div);
                 }
             }
             if (divs.size() == 10 - 2 + 1) {
                 results.put(s, divs);
             }
         }
 
         out.println();
         for (Map.Entry<String, List<Integer>> entry : results.entrySet()) {
             out.print(entry.getKey());
             for (int div : entry.getValue()) {
                 out.print(" " + div);
             }
             out.println();
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
         new CoinJam().run();
     }
 }
