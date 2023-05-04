package round1b;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.*;
 import java.io.PrintWriter;
 
 public class p3 {
 
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
         StringTokenizer st = new StringTokenizer(br.readLine());
         int N = Integer.parseInt(st.nextToken());
         int R = Integer.parseInt(st.nextToken());
         int O = Integer.parseInt(st.nextToken());
         int Y = Integer.parseInt(st.nextToken());
         int G = Integer.parseInt(st.nextToken());
         int B = Integer.parseInt(st.nextToken());
         int V = Integer.parseInt(st.nextToken());
 
         StringBuilder bob = new StringBuilder();
         for (int i=0;i<N;i++) {
             bob.append("?");
         }
         List<CI> c = new ArrayList<>();
         c.add(new CI('R', R));
         c.add(new CI('Y', Y));
         c.add(new CI('B', B));
         Collections.sort(c);
 
         int j = 0;
         for (int i = 0; i < c.get(0).num && j < N; j+=2, i++) {
             bob.setCharAt(j, c.get(0).col);
         }
         j--;
         int  i = 0;
         for (; i < c.get(1).num && j < N; j+= 2, i++) {
             bob.setCharAt(j, c.get(1).col);
         }
 
         for (int l = 0; i < c.get(1).num; l++) {
             if (bob.charAt(l) == '?') {
                 bob.setCharAt(l, c.get(1).col);
                 i++;
             }
         }
         i = 0;
         for (int l = 0; i < c.get(2).num; l++) {
             if (bob.charAt(l) == '?') {
                 bob.setCharAt(l, c.get(2).col);
                 i++;
             }
         }
         if (!check(bob)) {
             return "IMPOSSIBLE";
         }
         return bob.toString();
     }
 
     private static boolean check(StringBuilder bob) {
         for (int i=0;i<bob.length();++i) {
             char l = (i == 0) ? bob.charAt(bob.length() - 1) : bob.charAt(i - 1);
             char r = (i == bob.length() - 1) ? bob.charAt(0) : bob.charAt(i + 1);
             char c = bob.charAt(i);
             if (c == '?' || c == l || c == r) return false;
         }
         return true;
     }
 
     private static final class CI implements Comparable<CI> {
 
         char col;
         int num;
 
         public CI(char col, int num) {
             this.col = col;
             this.num = num;
         }
 
         @Override
         public int compareTo(CI o) {
             return -Integer.compare(num, o.num);
         }
 
         @Override
         public String toString() {
             return "CI{" +
                     "col=" + col +
                     ", num=" + num +
                     '}';
         }
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
