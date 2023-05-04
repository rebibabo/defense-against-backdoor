package roud1a;
 
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.LinkedList;
 import java.util.StringTokenizer;
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
         int Hd = Integer.parseInt(st.nextToken());
         int Ad = Integer.parseInt(st.nextToken());
         int Hk = Integer.parseInt(st.nextToken());
         int Ak = Integer.parseInt(st.nextToken());
         int B = Integer.parseInt(st.nextToken());
         int D = Integer.parseInt(st.nextToken());
 
         LinkedList<State> sp = new LinkedList<>();
         sp.add(new State(Hd, Hk, Ad, Ak, 0));
         HashSet<State> seen = new HashSet<>();
 
         while (!sp.isEmpty()) {
             State s = sp.pollFirst();
             
 
             if (s.hd == 0) {
                 seen.add(s);
                 continue;
             }
             if (s.hk == 0) {
                 
                 return "" + s.dist;
             }
 
             if (seen.contains(s)) {
                 continue;
             }
             seen.add(s);
 
             sp.add(new State(s.hd - (s.hk - s.ad <= 0 ? 0 : s.ak), s.hk - s.ad, s.ad, s.ak, s.dist + 1));
             sp.add(new State(s.hd - (s.hk == 0 ? 0 : s.ak), s.hk, s.ad + B, s.ak, s.dist + 1));
             sp.add(new State(s.hd - (s.hk == 0 ? 0 : s.ak - D), s.hk, s.ad, s.ak - D, s.dist + 1));
             if (s.hd < Hd) {
                 sp.add(new State(Hd - (s.hk == 0 ? 0 : s.ak), s.hk, s.ad, s.ak, s.dist + 1));
             }
 
         }
 
         return "IMPOSSIBLE";
     }
 
     private static final class State {
         int hd;
         int hk;
         int ad;
         private final int ak;
         int dist;
 
         public State(int hd, int hk, int ad, int ak, int dist) {
             this.ad = ad;
             this.ak = Math.max(0, ak);
             this.hd = Math.max(0, hd);
             this.hk = Math.max(0, hk);
             this.dist = dist;
         }
 
         @Override
         public String toString() {
             return "State{" +
                     "hd=" + hd +
                     ", hk=" + hk +
                     ", ad=" + ad +
                     ", ak=" + ak +
                     ", dist=" + dist +
                     '}';
         }
 
         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
 
             State state = (State) o;
 
             if (hd != state.hd) return false;
             if (hk != state.hk) return false;
             if (ad != state.ad) return false;
             return ak == state.ak;
 
         }
 
         @Override
         public int hashCode() {
             int result = hd;
             result = 31 * result + hk;
             result = 31 * result + ad;
             result = 31 * result + ak;
             return result;
         }
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
