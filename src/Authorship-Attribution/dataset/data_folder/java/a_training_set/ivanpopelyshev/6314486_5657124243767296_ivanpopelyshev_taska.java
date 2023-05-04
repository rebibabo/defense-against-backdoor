package A;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 import java.util.Random;
 
 
 public class TaskA implements Runnable {
     String filename = "A";
 
 
     String suffix = "-small-attempt1";
     
 
 
         final int MAX = 1000001;
 
 
     public void solve() throws Exception {
         for (int i = 0; i < MAX; i++) {
             arr[i].clear();
         }
 
         N = iread();
         d = new Dice[N];
         for (int i = 0; i < N; i++) {
             d[i] = new Dice();
             d[i].num = i;
             for (int j = 0; j < 6; j++) {
                 int c = iread();
                 for (int z = 0; z < j; z++) {
                     if (d[i].x[z] == c) {
                         c = -1;
                         break;
                     }
                 }
                 d[i].x[j] = c;
                 if (c >= 0) {
                     arr[c].add(d[i]);
                 }
             }
         }
 
         
 
         int ans = 0;
 
         left = 1;
         for (int cur = 1; cur < MAX; cur++) {
             sol[cur] = null;
 
             ID++;
             Dice d = khun(cur);
             int nxt = cur;
             if (d != null) {
                 nxt = d.best;
             }
             while (left <= nxt) {
                 if (sol[left] != null) {
                     sol[left].cur = 0;
                     sol[left] = null;
                 }
                 left++;
             }
 
             sol[cur] = d;
 
             int x = cur;
             while (d != null) {
                 int y = d.cur;
                 d.cur = x;
                 sol[x] = d;
                 d = d.next;
                 x = y;
             }
             ans = Math.max(ans, cur - left + 1);
         }
         fout.write(ans + "");
     }
 
     Dice khun(int x) {
         if (was[x] == ID) {
             return bb[x];
         }
 
         ArrayList<Dice> cand = arr[x];
 
         was[x] = ID;
         bb[x] = null;
         Dice best = null;
 
         for (int i = 0; i < cand.size(); i++) {
             Dice a = cand.get(i);
 
             if (a.cur == 0) {
                 a.best = 0;
                 a.id = ID;
                 a.next = null;
                 return a;
             }
         }
 
         for (int i = 0; i < cand.size(); i++) {
             Dice a = cand.get(i);
 
             if (a.id != ID) {
                 a.id = ID;
                 a.best = a.cur;
                 a.next = khun(a.cur);
                 if (a.next != null) {
                     if (a.next.best < a.cur) {
                         a.best = a.next.best;
                     } else {
                         a.next = null;
                     }
                 } else {
                     a.best = a.cur;
                 }
 
                 if (best == null || a.best < best.best) {
                     best = a;
                     if (best.best == 0) break;
                 }
             }
         }
 
         bb[x] = best;
         return best;
     }
 
     int N;
     Dice[] d;
     Dice[] sol;
     int left;
     int ID = 0;
     int[] was;
     Dice[] bb;
 
     class Dice {
         int[] x = new int[6];
         int num;
         int cur;
         int id;
         int best;
         Dice next;
     }
 
     void init() {
         was = new int[MAX + 1];
         bb = new Dice[MAX + 1];
         arr = new ArrayList[MAX];
         for (int i = 0; i < MAX; i++) {
             arr[i] = new ArrayList<Dice>();
         }
         sol = new Dice[MAX + 1];
     }
 
     ArrayList<Dice>[] arr;
 
     public void gcj_solve() throws Exception {
         init();
         int tests = iread();
         for (int i = 1; i <= tests; i++) {
             fout.write("Case #" + i + ": ");
             solve();
             fout.write("\n");
             fout.flush();
         }
     }
 
 
     BufferedReader fin;
     BufferedWriter fout;
     StringBuilder sb = new StringBuilder();
 
     public String readword() throws Exception {
         sb.setLength(0);
         int ch = fin.read();
         while (ch <= ' ' && ch >= 0) {
             ch = fin.read();
         }
         while (ch > ' ') {
             sb.append((char) ch);
             ch = fin.read();
         }
         return sb.toString();
     }
 
     public int iread() throws Exception {
         return Integer.parseInt(readword());
     }
 
     public long lread() throws Exception {
         return Long.parseLong(readword());
     }
 
     public double dread() throws Exception {
         return Double.parseDouble(readword());
     }
 
     DecimalFormat df = new DecimalFormat("0.000000000");
 
     @Override
     public void run() {
         try {
             fin = new BufferedReader(new FileReader(filename + suffix + ".in"));
             fout = new BufferedWriter(new FileWriter(filename + suffix + ".out"));
             gcj_solve();
             fout.flush();
         } catch (Exception e) {
             e.printStackTrace();
             System.exit(1);
         }
     }
 
     public static void main(String[] args) {
         new Thread(null, new TaskA(), "warmup.Awesome", 1 << 25).start();
     }
 }
