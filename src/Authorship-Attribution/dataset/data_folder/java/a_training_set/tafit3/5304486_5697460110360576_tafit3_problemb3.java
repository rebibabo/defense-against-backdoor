import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Iterator;
 
 import static java.lang.Math.max;
 
 public class ProblemB3 {
     BufferedReader rd;
 
     ProblemB3() throws IOException {
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
         int p = a[1];
         int[] r = intarr();
         int[][] c = new int[n][];
         for(int i=0;i<n;i++) {
             c[i] = intarr();
         }
 
 
         int[][][] s = new int[2][p][];
         for(int i=0;i<n;i++) {
             for(int j=0;j<p;j++) {
                 s[i][j] = computeServingsInKit(c[i][j], r[i]);
             }
         }
 
         int res = 0;
         if(n == 1) {
             for(int i=0;i<p;i++) {
                 if(s[0][i] != null) {
                     res++;
                 }
             }
         } else {
             PermIterator it = new PermIterator(p);
             while(it.hasNext()) {
                 int[] g = it.next();
                 int cnt = 0;
                 for(int i=0;i<p;i++) {
                     if(match(s[0][i], s[1][g[i]])) {
                         cnt++;
                     }
                 }
                 res = max(res, cnt);
             }
         }
         return Integer.toString(res);
     }
 
     private boolean match(int[] a, int[] b) {
         return a != null && b != null && a[0] <= b[1] && b[0] <= a[1];
     }
 
     private int[] computeServingsInKit(int avail, int req) {
         int sFrom = searchFrom(avail, req);
         int sTo = searchTo(avail, req);
         if(sFrom != -1 && sTo != -1 && sFrom <= sTo && sFrom > 0) {
             return new int[] { sFrom, sTo };
         }
         return null;
     }
 
     private int searchFrom(int avail, int req) {
         long lo = 0;
         long hi = 1_000_000;
         while(hi - lo >= 2) {
             long mid = (lo + hi) / 2;
             long c = mid * req;
             long cTenth = c / 10;
             long cFrom = c - cTenth;
             long cTo = c + cTenth;
             if(avail < cFrom) {
                 hi = mid-1;
             } else if(avail > cTo) {
                 lo = mid+1;
             } else {
                 hi = mid;
             }
         }
         for(int k=-3;k<=3;k++) {
             long z = lo+k;
             if(z >= 0) {
                 long c = z * req;
                 long cTenth = c / 10;
                 long cFrom = c - cTenth;
                 long cTo = c + cTenth;
                 if(avail >= cFrom && avail <= cTo) {
                     return (int)z;
                 }
             }
         }
         return -1;
     }
 
     private int searchTo(int avail, int req) {
         long lo = 0;
         long hi = 1_000_000;
         while(hi - lo >= 2) {
             long mid = (lo + hi) / 2;
             long c = mid * req;
             long cTenth = c / 10;
             long cFrom = c - cTenth;
             long cTo = c + cTenth;
             if(avail < cFrom) {
                 hi = mid-1;
             } else if(avail > cTo) {
                 lo = mid+1;
             } else {
                 lo = mid;
             }
         }
         for(int k=3;k>=-3;k--) {
             long z = lo+k;
             if(z >= 0) {
                 long c = z * req;
                 long cTenth = c / 10;
                 long cFrom = c - cTenth;
                 long cTo = c + cTenth;
                 if(avail >= cFrom && avail <= cTo) {
                     return (int)z;
                 }
             }
         }
         return -1;
     }
 
     public static class PermIterator implements Iterator<int[]> {
         private final boolean[] d;
         private final int n;
         private int[][] p;
         private int act;
         private boolean nextAvail;
 
         public PermIterator(int n) {
             d = new boolean[n];
             this.n = n;
             p = new int[2][n];
             for(int i=0;i<n;i++) {
                 p[0][i] = i;
             }
             nextAvail = true;
         }
 
         @Override
         public boolean hasNext() {
             return nextAvail;
         }
 
         private void computeNext() {
             int y = n-1;
             for(int i=0;i<n;i++) d[i]=false;
             d[p[act][y]] = true;
             while(y > 0 && p[act][y] < p[act][y-1]) {
                 y--;
                 d[p[act][y]] = true;
             }
             if(y == 0) {
                 nextAvail = false;
             } else {
                 d[p[act][y-1]] = true;
                 if(y > 1) {
                     System.arraycopy(p[act],0,p[1-act],0,y-1);
                 }
                 for(int j=p[act][y-1]+1;j<n;j++) {
                     if(d[j]) {
                         p[1-act][y-1] = j;
                         d[j]=false;
                         break;
                     }
                 }
 
                 for(int j=0;j<n;j++) {
                     if(d[j]) {
                         p[1-act][y] = j;
                         y++;
                         if(y == n) {
                             break;
                         }
                     }
                 }
             }
         }
 
         @Override
         public int[] next() {
             computeNext();
             act = 1-act;
             return p[1-act];
         }
 
         @Override
         public void remove() {}
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
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
     }
 
     private static void out(Object x) {
         System.out.println(x);
     }
 
     public static void main(String[] args) throws IOException {
         new ProblemB3();
     }
 }
