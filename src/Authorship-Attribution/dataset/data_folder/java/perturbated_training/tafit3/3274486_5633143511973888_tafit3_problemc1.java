import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.DoubleSummaryStatistics;
 import java.util.Map;
 import java.util.NavigableMap;
 import java.util.TreeMap;
 
 public class ProblemC1 {
     private static final double EPS = 1e-9;
     BufferedReader rd;
 
     ProblemC1() throws IOException {
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
         int[] a = intarr();
         int n = a[0];
         int k = a[1];
         double u = pdouble();
         double[] p = doublearr();
 
         NavigableMap<Double, Integer> m = new TreeMap<>();
         for(double d: p) {
             m.put(d, m.getOrDefault(d, 0)+1);
         }
 
         while(u > EPS && (m.size() > 1 || (m.keySet().iterator().next() - 1d < -EPS))) {
             Map.Entry<Double, Integer> e = m.pollFirstEntry();
             double goal;
             int goalCnt;
             if(!m.isEmpty()) {
                 Map.Entry<Double, Integer> next = m.pollFirstEntry();
                 goal = next.getKey();
                 goalCnt = next.getValue();
             } else {
                 goal = 1d;
                 goalCnt = 0;
             }
             double diff = goal - e.getKey();
             int cnt = e.getValue();
             if(diff * cnt > u) {
                 double prob = e.getKey() + (u / cnt);
                 u = 0;
                 if(prob == goal) {
                     m.put(prob, cnt + goalCnt);
                 } else {
                     m.put(prob, cnt);
                     m.put(goal, goalCnt);
                 }
             } else {
                 u -= diff * cnt;
                 m.put(goal, cnt + goalCnt);
             }
         }
 
         double res = 1.0;
         for(Map.Entry<Double, Integer> e: m.entrySet()) {
             double prob = e.getKey();
             int cnt = e.getValue();
             for(int i=0;i<cnt;i++) {
                 res *= prob;
             }
         }
         return Double.toString(res);
     }
 
     private double pdouble() throws IOException {
         return pdouble(rd.readLine());
     }
 
     private double pdouble(String s) {
         return Double.parseDouble(s);
     }
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
     }
 
     private int[] intarr() throws IOException {
         return intarr(rd.readLine());
     }
 
     private int[] intarr(String s) {
         String[] q = split(s);
         int n = q.length;
         int[] a = new int[n];
         for(int i=0;i<n;i++) {
             a[i] = Integer.parseInt(q[i]);
         }
         return a;
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
         new ProblemC1();
     }
 }
