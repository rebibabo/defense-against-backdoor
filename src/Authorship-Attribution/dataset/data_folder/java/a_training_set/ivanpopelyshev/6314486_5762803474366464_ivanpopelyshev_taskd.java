package D;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.text.DecimalFormat;
 import java.util.ArrayList;
 
 
 public class TaskD implements Runnable {
     String filename = "D";
     
 
 
     String suffix = "-small-attempt2";
     
 
     int N;
     Point[] p;
 
     public void solve() throws Exception {
         N = iread();
         p = new Point[N];
         for (int i = 0; i < p.length; i++) {
             p[i] = new Point(iread(), iread(), iread());
         }
 
         Point norm = new Point(0, 0, 0);
         ArrayList<Point> allNorms = new ArrayList<Point>();
         ArrayList<Point> norms = new ArrayList<Point>();
         for (int i = 0; i < N; i++) {
             for (int j = i + 1; j < N; j++) {
                 Point a = p[i], b = p[j];
                 norm.x = a.y * b.z - a.z * b.y;
                 norm.y = a.z * b.x - a.x * b.z;
                 norm.z = a.x * b.y - a.y * b.x;
 
 
                 norms.clear();
                 if (norm.x != 0 || norm.y != 0 || norm.z != 0) {
                     norms.add(norm);
                 }
                 if (a.x != b.x || a.y != b.y || a.z != b.z) {
                     allNorms.add(new Point(a.x + b.x, a.y + b.y, a.z + b.z));
                 }
 
                 if (check(norms)) {
                     fout.write("NO");
                     return;
                 }
             }
         }
 
         norms.clear();
         norms.addAll(allNorms);
         for (int i = 0; i < N; i++) norms.add(p[i]);
 
         if (check(norms)) {
             fout.write("NO");
             return;
         }
 
         fout.write("YES");
     }
 
     ArrayList<Point> zeros = new ArrayList<Point>();
 
     boolean check(ArrayList<Point> norms) {
         for (int t = 0; t < norms.size(); t++) {
             Point norm2 = norms.get(t);
             int m1 = 0, m2 = 0;
             zeros.clear();
             for (int k = 0; k < N; k++) {
                 Point c = p[k];
                 long d = c.x * norm2.x + c.y * norm2.y + c.z * norm2.z;
                 if (d < 0) m1++;
                 if (d > 0) m2++;
                 if (d == 0) zeros.add(c);
             }
             if (m1 == 0 || m2 == 0) {
                 if (zeros.size() <= 1) {
                     return true;
                 }
 
                 Point tt = new Point(0, 0, 0);
 
                 for (int u1 = 0; u1 < zeros.size(); u1++) {
                     for (int u2 = u1 + 1; u2 < zeros.size(); u2++) {
                         tt.x = zeros.get(u1).x + zeros.get(u2).x;
                         tt.y = zeros.get(u1).y + zeros.get(u2).y;
                         tt.z = zeros.get(u1).z + zeros.get(u2).z;
                         norm2 = tt;
 
                         m1 = 0;
                         m2 = 0;
 
                         for (int v = 0; v < zeros.size(); v++) {
                             Point c = zeros.get(v);
                             long d = c.x * norm2.x + c.y * norm2.y + c.z * norm2.z;
                             if (d <= 0) m1++;
                             if (d >= 0) m2++;
                         }
 
                         if (m1 == 0 || m2 == 0) {
                             return true;
                         }
                     }
                 }
 
             }
         }
         return false;
     }
 
     class Point {
         long x, y, z;
 
         Point(long x, long y, long z) {
             this.x = x;
             this.y = y;
             this.z = z;
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
 
     public void gcj_solve() throws Exception {
         int tests = iread();
         for (int i = 1; i <= tests; i++) {
             fout.write("Case #" + i + ": ");
             solve();
             fout.write("\n");
             fout.flush();
         }
     }
 
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
         new Thread(null, new TaskD(), "warmup.Awesome", 1 << 25).start();
     }
 }
