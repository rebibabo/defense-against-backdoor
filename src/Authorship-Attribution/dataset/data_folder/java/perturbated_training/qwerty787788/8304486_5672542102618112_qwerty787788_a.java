import java.io.*;
 import java.util.*;
 
 public class A {
     FastScanner in;
     PrintWriter out;
 
     void go2(char[] tmp, int[] cnt, int alr, HashSet<String> was, int moreZeros) {
         if (alr == tmp.length) {
             go(tmp, was);
             return;
         }
         if (moreZeros > 0) {
             tmp[alr] = '0';
             go2(tmp, cnt, alr + 1, was, moreZeros - 1);
         }
         for (int i = 0; i < cnt.length; i++) {
             if (cnt[i] > 0) {
                 tmp[alr] = (char) ('1' + i);
                 cnt[i]--;
                 go2(tmp, cnt, alr + 1, was, moreZeros);
                 cnt[i]++;
             }
         }
     }
 
     void go(char[] s, HashSet<String> was) {
         String tmp = new String(s);
         if (was.contains(tmp)) {
             return;
         }
         was.add(tmp);
         int n = s.length;
         int[] cnt = new int[n];
         for (int i = 0; i < n; i++) {
             cnt[i] = s[i] - '0';
         }
         int sum = 0;
         for (int i = 0; i < n; i++) {
             sum += cnt[i];
         }
         if (sum > n) {
             return;
         }
         go2(new char[n], cnt, 0, was, n - sum);
     }
 
     void solve() {
         int tc = in.nextInt();
         for (int t = 0; t < tc; t++) {
             out.print("Case #" + (t + 1) + ": ");
             char[] s = in.next().toCharArray();
             HashSet<String> was = new HashSet<>();
             go(s, was);
             out.println(was.size());
             System.err.println((t + 1) + "/" + tc + " done");
         }
     }
 
     void run() {
         try {
             in = new FastScanner(new File("A.in"));
             out = new PrintWriter(new File("A.out"));
 
             solve();
 
             out.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
     }
 
     void runIO() {
         in = new FastScanner(System.in);
         out = new PrintWriter(System.out);
 
         solve();
 
         out.close();
     }
 
     class FastScanner {
         BufferedReader br;
         StringTokenizer st;
 
         public FastScanner(File f) {
             try {
                 br = new BufferedReader(new FileReader(f));
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
         }
 
         public FastScanner(InputStream f) {
             br = new BufferedReader(new InputStreamReader(f));
         }
 
         String next() {
             while (st == null || !st.hasMoreTokens()) {
                 String s = null;
                 try {
                     s = br.readLine();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 if (s == null)
                     return null;
                 st = new StringTokenizer(s);
             }
             return st.nextToken();
         }
 
         boolean hasMoreTokens() {
             while (st == null || !st.hasMoreTokens()) {
                 String s = null;
                 try {
                     s = br.readLine();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 if (s == null)
                     return false;
                 st = new StringTokenizer(s);
             }
             return true;
         }
 
         int nextInt() {
             return Integer.parseInt(next());
         }
 
         long nextLong() {
             return Long.parseLong(next());
         }
 
         double nextDouble() {
             return Double.parseDouble(next());
         }
     }
 
     public static void main(String[] args) {
         new A().run();
     }
 }