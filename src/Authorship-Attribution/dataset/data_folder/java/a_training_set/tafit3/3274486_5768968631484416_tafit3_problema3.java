import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.PriorityQueue;
 
 import static java.lang.Math.max;
 
 public class ProblemA3 {
     BufferedReader rd;
 
     ProblemA3() throws IOException {
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
         PriorityQueue<int[]> byRadius = new PriorityQueue<>((x, y) -> Integer.compare(x[0],y[0]));
         for (int i = 0; i < n; i++) {
             byRadius.add(intarr());
         }
 
         double best = 0;
         double sum = 0;
         PriorityQueue<Double> hq = new PriorityQueue<>(Double::compare);
         while (!byRadius.isEmpty()) {
             int[] pancake = byRadius.poll();
             double side = (2 * Math.PI * pancake[0]) * pancake[1];
             if (hq.size() == k - 1) {
                 double top = (Math.PI * pancake[0]) * pancake[0];
                 best = max(best, top + sum + side);
             }
 
             hq.add(side);
             sum += side;
             while (hq.size() > k - 1) {
                 sum -= hq.poll();
             }
         }
         return Double.toString(best);
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
         new ProblemA3();
     }
 }
