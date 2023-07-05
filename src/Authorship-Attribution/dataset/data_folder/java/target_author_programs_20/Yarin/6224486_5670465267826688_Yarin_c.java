package qual;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.util.ArrayList;
 
 public class C {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("qual/C-small-0.in"), new FileOutputStream("qual/C-small-0.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new C().solve(io);
         }
         io.close();
     }
 
     int[][] mulTable = new int[][] {
         new int[]{1, 2, 3, 4},
         new int[]{2, -1, 4, -3},
         new int[]{3, -4, -1, 2},
         new int[]{4, 3, -2, -1}};
 
     private int sign(int a) {
         return a > 0 ? 1 : -1;
     }
 
     private int mul(int a, int b) {
         
         return mulTable[Math.abs(a) - 1][Math.abs(b) - 1] * sign(a) * sign(b);
     }
 
     private String conv(int t) {
         char c = "1ijk".charAt(Math.abs(t) - 1);
         if (t > 0) return "" + c;
         return "-" + c;
     }
 
     private int conv(char c) {
         switch (c) {
             case '1' : return 1;
             case 'i': return 2;
             case 'j': return 3;
             case 'k': return 4;
         }
         throw new RuntimeException();
     }
 
     private static final int MAX_LEN = 1000000;
 
     private void solve(Kattio io) {
         int n = io.getInt(), x = io.getInt();
         String s = io.getWord();
         ArrayList<Integer> t = new ArrayList<>();
         while (t.size() < MAX_LEN && x > 0) {
             for (int i = 0; i < s.length(); i++) {
                 t.add(conv(s.charAt(i)));
             }
             x--;
         }
         int seval = eval(s);
         while (x > 0) {
             t.add(seval);
             x--;
         }
 
 
         boolean[][][] poss = new boolean[t.size() + 1][4][9];
         poss[t.size()][3][5] = true;
 
         for (int i = t.size(); i >= 0; i--) {
             for (int matched = 2; matched >= 0; matched--) {
                 for (int cur = -4; cur <= 4; cur++) {
                     if (cur == 0) continue;
                     if (cur - 2 == matched) {
                         poss[i][matched][cur+4] |= poss[i][matched + 1][5];
                     }
                     if (i < t.size()) {
                         int next = mul(cur, t.get(i));
                         poss[i][matched][cur + 4] |= poss[i + 1][matched][next + 4];
                     }
                 }
             }
         }
 
         io.println(poss[0][0][5] ? "YES" : "NO");
 
 
     }
 
     private boolean go(int pos, int matched, int cur, ArrayList<Integer> t) {
         if (matched + 2 == cur && go(pos, matched+1, 1, t)) return true;
         if (pos == t.size()) {
             return matched == 3;
         }
         if (matched == 3) return false;
 
         return go(pos+1, matched, mul(cur, t.get(pos)), t);
     }
 
     private int eval(String s) {
         int cur = conv(s.charAt(0));
         for (int i = 1; i < s.length(); i++) {
             cur = mul(cur, conv(s.charAt(i)));
         }
         return cur;
     }
 }
