import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 public class ProblemB {
     private static final String IMP = "IMPOSSIBLE";
     BufferedReader rd;
 
     ProblemB() throws IOException {
         rd = new BufferedReader(new InputStreamReader(System.in));
         compute();
     }
 
     private void compute() throws IOException {
         int n = pint();
         for(int i=0;i<n;i++) {
             out("Case #" + (i + 1) + ": " + solve());
         }
     }
 
     private String solve() throws IOException {
         String[] h = split(rd.readLine());
         int n = pint(h[0]);
         double v = Double.parseDouble(h[1]);
         double x = Double.parseDouble(h[2]);
         double[][] rc = new double[n][];
         for(int i=0;i<n;i++) {
             rc[i] = doublearr();
         }
         String res;
         if(n == 1) {
             if(Math.abs(x - rc[0][1]) < 1e-6) {
                 res = Double.toString(v/rc[0][0]);
             } else {
                 res = IMP;
             }
         } else {
             if(Math.abs(rc[0][1] - rc[1][1]) < 1e-6) {
                 if(Math.abs(x - rc[0][1]) < 1e-6) {
                     double v1 = v * (rc[0][0] / (rc[0][0] + rc[1][0]));
                     double t = v1 / rc[0][0];
                     res = Double.toString(t);
                 } else {
                     res = IMP;
                 }
             } else if(Math.min(rc[0][1],rc[1][1]) <= x && x <= Math.max(rc[0][1],rc[1][1])) {
                 double v1 = v*(x-rc[1][1])/(rc[0][1]-rc[1][1]);
                 double v2 = v-v1;
                 double t1 = v1/rc[0][0];
                 double t2 = v2/rc[1][0];
                 res = Double.toString(Math.max(t1,t2));
             } else {
                 res = IMP;
             }
         }
         return res;
     }
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
     }
 
     private double[] doublearr() throws IOException {
         return doublearr(rd.readLine());
     }
 
     private double[] doublearr(String s) {
         String[] q = split(s);
         int n = q.length;
         double[] a = new double[n];
         for(int i=0;i<n;i++) {
             a[i] = Double.parseDouble(q[i]);
         }
         return a;
     }
 
     public String[] split(String s) {
         if(s == null) {
             return new String[0];
         }
         int n = s.length();
         int start = -1;
         int end = 0;
         int sp = 0;
         boolean lastWhitespace = true;
         for(int i=0;i<n;i++) {
             char c = s.charAt(i);
             if(isWhitespace(c)) {
                 lastWhitespace = true;
             } else {
                 if(lastWhitespace) {
                     sp++;
                 }
                 if(start == -1) {
                     start = i;
                 }
                 end = i;
                 lastWhitespace = false;
             }
         }
         if(start == -1) {
             return new String[0];
         }
         String[] res = new String[sp];
         int last = start;
         int x = 0;
         lastWhitespace = true;
         for(int i=start;i<=end;i++) {
             char c = s.charAt(i);
             boolean w = isWhitespace(c);
             if(w && !lastWhitespace) {
                 res[x++] = s.substring(last,i);
             } else if(!w && lastWhitespace) {
                 last = i;
             }
             lastWhitespace = w;
         }
         res[x] = s.substring(last,end+1);
         return res;
     }
 
     private boolean isWhitespace(char c) {
         return c==' ' || c=='\t';
     }
 
     private static void out(Object x) {
         System.out.println(x);
     }
 
     public static void main(String[] args) throws IOException {
         new ProblemB();
     }
 }
