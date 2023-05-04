package round3;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.Random;
 import java.util.Set;
 
 public class B {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
 
         io = new Kattio(new FileInputStream("round3/B-small-attempt0.in"), new FileOutputStream("round3/B-small-0.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new B().solve(io);
         }
         io.close();
     }
 
     Random random;
     int n;
     int[] pre;
     char[] let;
     boolean[] taken;
     Set<String> orders;
 
 
     private void simulate(int left, StringBuilder sb) {
         if (left == 0) {
             orders.add(sb.toString());
             return;
         }
 
         ArrayList<Integer> candidates = new ArrayList<Integer>();
         for (int i = 1; i <= n; i++) {
             if (!taken[i] && (pre[i] == 0 || taken[pre[i]]))
                 candidates.add(i);
         }
         int r = candidates.get(random.nextInt(candidates.size()));
         sb.append((char)(' '+r));
         taken[r] = true;
         simulate(left-1, sb);
     }
 
     private void solve(Kattio io) {
         random = new Random(0);
         n = io.getInt();
         pre = new int[n+1];
         for (int i = 1; i <= n; i++) {
             pre[i] = io.getInt();
         }
         String s = io.getWord();
         let = new char[n+1];
         for (int i = 0; i < n; i++) {
             let[i+1] = s.charAt(i);
         }
 
         orders = new HashSet<String>();
         for (int j = 0; j < 10000; j++) {
             taken = new boolean[n+1];
             simulate(n, new StringBuilder());
         }
         System.out.println("possible orders: " + orders.size());
         ArrayList<String> list = new ArrayList<>(orders.size());
         for (String order : orders) {
             StringBuilder sb = new StringBuilder();
             for (int i = 0; i < order.length(); i++) {
                 sb.append(let[order.charAt(i)-' ']);
             }
             list.add(sb.toString());
         }
 
         int m = io.getInt();
         for (int i = 0; i < m; i++) {
             String coolWord = io.getWord();
             int cnt = 0;
             for (String w : list) {
                 if (w.contains(coolWord)) cnt++;
             }
 
             if (i > 0) io.print(" ");
             io.print(String.format("%.6f", (double) cnt / orders.size()));
             io.flush();
 
         }
         io.println();
     }
 }
