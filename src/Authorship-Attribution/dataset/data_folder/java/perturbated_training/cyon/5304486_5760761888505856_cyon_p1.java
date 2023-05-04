package roud1a;
 
 
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
             pw.print("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static String solve(BufferedReader br) throws IOException {
         StringTokenizer st = new StringTokenizer(br.readLine());
         int R = Integer.parseInt(st.nextToken());
         int C = Integer.parseInt(st.nextToken());
         String[]grid = new String[R];
         for (int i = 0; i < R; i++) {
             grid[i] = br.readLine();
         }
         LinkedList<L> q = new LinkedList<>();
         for (int i = 0; i < R; i++) {
             for (int j = 0; j < C; j++) {
                 char letter = grid[i].charAt(j);
                 if (letter != '?') {
                     q.add(new L(i, j, letter));
                 }
             }
         }
         StringBuilder[] bob = new StringBuilder[R];
         for (int i = 0; i < R; i++) {
             bob[i] = new StringBuilder(grid[i]);
         }
         int crow = 0;
         LinkedList<P> spps = new LinkedList<>();
         List<Integer> rows = new ArrayList<>();
 
         while (!q.isEmpty()) {
             debug("iter", q);
             L first = q.pollFirst();
 
             while (!q.isEmpty() && q.peekFirst().i == first.i) {
                 spps.add(new P(q.peekFirst().j-1, first.letter, crow));
                 first = q.pollFirst();
             }
 
             spps.add(new P(C-1, first.letter, crow));
             rows.add(crow);
             if (!q.isEmpty()) {
                 crow = q.peekFirst().i;
             }
         }
         rows.add(R - 1);
         debug(spps);
 
         for (int i=0;i<rows.size()-1;++i) {
             int f = rows.get(i);
             int t = rows.get(i + 1);
 
             int csp = 0;
             while (!spps.isEmpty() && spps.peekFirst().crow == f) {
                 debug("iter2", spps);
                 P sp = spps.pollFirst();
                 for (int j = f; j <= t; j++) {
                     for (int k = csp; k <= sp.sp; k++) {
                         bob[j].setCharAt(k, sp.l);
                     }
                 }
                 csp = sp.sp+1;
             }
         }
 
         StringBuilder res = new StringBuilder("\n");
         for (int i = 0; i < R; i++) {
             res.append(bob[i].toString()).append("\n");
         }
         return res.toString();
     }
 
     private static final class P {
         int sp;
         char l;
         private final int crow;
 
         public P(int sp, char l, int crow) {
             this.sp = sp;
             this.l = l;
             this.crow = crow;
         }
 
         @Override
         public String toString() {
             return "P{" +
                     "sp=" + sp +
                     ", l=" + l +
                     ", crow=" + crow +
                     '}';
         }
     }
 
     private static final class L {
         int i, j;
         char letter;
 
         public L(int i, int j, char letter) {
             this.i = i;
             this.j = j;
             this.letter = letter;
         }
 
         @Override
         public String toString() {
             return "L{" +
                     "i=" + i +
                     ", j=" + j +
                     ", letter=" + letter +
                     '}';
         }
     }
 
     public static void debug(Object...args) {
         
     }
 }
