import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Map;
 import java.util.NavigableMap;
 import java.util.TreeMap;
 
 import static java.lang.Math.min;
 
 public class ProblemC4 {
     BufferedReader rd;
 
     ProblemC4() throws IOException {
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
         long[] a = longarr();
         long n = a[0];
         long k = a[1];
         long[] res = solve(n, k);
         return res[0] + " " + res[1];
     }
 
     private long[] solve(long n, long k) {
         NavigableMap<Long, Long> g = new TreeMap<>();
         g.put(n, 1L);
         long steps = 0;
         k--;
         while(steps < k && !g.isEmpty()) {
             Map.Entry<Long, Long> e = g.lastEntry();
             g.remove(e.getKey());
             long z = e.getKey();
             long v = e.getValue();
             long c = min(v, k-steps);
             if(c < v) {
                 add(g, z, v-c);
             }
             if(z > 1) {
                 if(z % 2 == 0) {
                     add(g, z/2, c);
                     if(z/2 - 1 > 0) {
                         add(g, z / 2 - 1, c);
                     }
                 } else {
                     add(g, z/2, c*2);
                 }
             }
             steps += c;
         }
         if(g.isEmpty()) {
             return new long[2];
         }
         long last = g.lastKey();
         long y = last / 2;
         if(last % 2 == 0) {
             return new long[] { y, y - 1 };
         } else {
             return new long[] { y, y };
         }
     }
 
     private void add(Map<Long, Long> m, long key, long diff) {
         m.put(key, m.getOrDefault(key, 0L) + diff);
     }
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
     }
 
     private long[] longarr() throws IOException {
         return longarr(rd.readLine());
     }
 
     private long[] longarr(String s) {
         String[] q = split(s);
         int n = q.length;
         long[] a = new long[n];
         for(int i=0;i<n;i++) {
             a[i] = Long.parseLong(q[i]);
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
         new ProblemC4();
     }
 }
