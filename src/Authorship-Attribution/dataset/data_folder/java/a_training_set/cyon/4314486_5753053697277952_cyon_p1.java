package rc;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.*;
 import java.io.PrintWriter;
 
 public class p1 {
 
     public static void main(String[]args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(System.out);
         int T = Integer.parseInt(br.readLine());
         for(int i=1;i<=T;i++) {
             pw.println("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static String solve(BufferedReader br) throws IOException {
         int N = Integer.parseInt(br.readLine());
         int[] v = new int[N];
         StringTokenizer st = new StringTokenizer(br.readLine());
         List<P> ss = new ArrayList<>();
         for(int i=0;i<N;i++) {
             ss.add(new P(i, Integer.parseInt(st.nextToken())));
         }
         StringBuilder bob = new StringBuilder();
         while(ss.size() > 2) {
             Collections.sort(ss);
             P last = ss.get(ss.size()-1);
             bob.append((char)('A' + last.idx)).append(" ");
             if (last.v == 1) {
                 ss.remove(ss.size()-1);
             }
             else {
                 last.v--;
             }
         }
         
         if (ss.size() > 0) {
             int iter = ss.get(0).v;
             char A = (char)('A' + ss.get(0).idx);
             char B = (char)('A' + ss.get(1).idx);
             while(iter > 0) {
                 iter--;
                 bob.append(A).append(B).append(" ");
             }
         }
         return bob.toString();
     }
 
     private static final class P implements Comparable<P>{
         int v, idx;
 
         public P(int idx, int v) {
             this.v = v;
             this.idx = idx;
         }
 
         @Override
         public int compareTo(P o) {
             if (v != o.v) return v - o.v;
             return idx - o.idx;
         }
 
         @Override
         public String toString() {
             return "P{" +
                     "v=" + v +
                     ", idx=" + idx +
                     '}';
         }
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
