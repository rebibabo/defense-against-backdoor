package B;
 
 import org.jetbrains.annotations.NotNull;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.Arrays;
 
 
 public class TaskB implements Runnable {
     String filename = "B";
 
 
     String suffix = "-small-attempt1";
     
 
     int INF = 1000000000;
 
     public void solve() throws Exception {
         int S = iread();
         N = iread();
         Sb = BigInteger.valueOf(S);
         ops = new Op[N];
         for (int i = 0; i < N; i++) {
             ops[i] = new Op();
             String s = readword();
             int x = iread();
             ops[i].x = x;
             if (s.equals("+")) {
                 if (x > 0)
                     ops[i].opCode = 0;
                 else ops[i].opCode = 1;
             }
             if (s.equals("-")) {
                 if (x > 0)
                     ops[i].opCode = 1;
                 else ops[i].opCode = 0;
                 ops[i].x = -x;
             }
             if (s.equals("*")) ops[i].opCode = 2;
             if (s.equals("/")) ops[i].opCode = 3;
             if (s.equals("*") && x == 0) ops[i].opCode = 4;
             ops[i].xb = BigInteger.valueOf(ops[i].x);
         }
 
         strange();
 
         order = new int[7];
         was = new int[7];
         Arrays.fill(order, -1);
         Arrays.fill(was, -1);
         best = null;
         rec(0);
         fout.write(best.n + " " + best.d);
     }
 
     void strange() {
         int min = -INF;
         for (int i = 0; i < N; i++) {
             if (ops[i].opCode == 2 && ops[i].x < 0) {
                 min = Math.max(min, ops[i].x);
             }
         }
         if (min > -INF) {
             for (int i = 0; i < N; i++) {
                 if (ops[i].opCode == 2 && ops[i].x == min) {
                     ops[i].opCode = 5;
                     break;
                 }
             }
         }
 
         min = -INF;
         for (int i = 0; i < N; i++) {
             if (ops[i].opCode == 3 && ops[i].x < 0) {
                 min = Math.max(min, ops[i].x);
             }
         }
         if (min > -INF) {
             for (int i = 0; i < N; i++) {
                 if (ops[i].opCode == 3 && ops[i].x == min) {
                     ops[i].opCode = 6;
                     break;
                 }
             }
         }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
     }
 
 
     void doIt() {
         Quote q = new Quote();
         q.n = Sb;
         Arrays.sort(ops);
         for (int i = 0; i < ops.length; i++) {
             q.doOp(ops[i]);
         }
         q.norm();
         if (best == null || q.compareTo(best) > 0) {
             best = q;
         }
     }
 
     int N;
     BigInteger Sb;
     Op[] ops;
     Quote best;
     int[] order;
     int[] was;
 
     void rec(int x) {
         if (x == order.length) {
             doIt();
             return;
         }
         for (int i = 0; i < order.length; i++) {
             if (was[i] == -1) {
                 was[i] = x;
                 order[x] = i;
 
                 rec(x + 1);
 
                 was[i] = -1;
                 order[x] = -1;
             }
         }
     }
 
     class Op implements Comparable<Op> {
         int opCode;
         int x;
         BigInteger xb;
 
         @Override
         public int compareTo(@NotNull Op o) {
             return was[o.opCode] - was[opCode];
         }
     }
 
     class Quote implements Comparable<Quote> {
         BigInteger d = BigInteger.ONE, n = BigInteger.ZERO;
 
         Quote doOp(Op op) {
             if (op.opCode <= 1) {
                 n = n.add(d.multiply(op.xb));
             }
             if (op.opCode == 2 || op.opCode == 5) {
                 n = n.multiply(op.xb);
             }
             if (op.opCode == 3 || op.opCode == 6 || op.opCode == 7 ) {
                 d = d.multiply(op.xb);
             }
             if (op.opCode == 4) {
                 d = BigInteger.ONE;
                 n = BigInteger.ZERO;
             }
             return this;
         }
 
         Quote norm() {
             BigInteger gcd = d.abs().gcd(n.abs());
             d = d.divide(gcd);
             n = n.divide(gcd);
             if (d.compareTo(BigInteger.ZERO) < 0) {
                 d = d.negate();
                 n = n.negate();
             }
             return this;
         }
 
         @Override
         public int compareTo(@NotNull Quote o) {
             return n.multiply(o.d).compareTo(o.n.multiply(d));
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
         new Thread(null, new TaskB(), "warmup.Awesome", 1 << 25).start();
     }
 }
