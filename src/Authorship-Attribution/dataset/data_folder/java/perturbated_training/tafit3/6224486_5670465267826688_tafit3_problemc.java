import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.HashMap;
 import java.util.Map;
 
 public class ProblemC {
     BufferedReader rd;
     private static final int[][] MUL = new int[][] {
             { 1,2,3,4 },
             { 2,-1,4,-3 },
             { 3,-4,-1,2 },
             { 4,3,-2,-1 }
     };
     private static final Map<Integer, Map<Integer, Integer>> DIV = new HashMap<>();
 
     static {
         int[][] m = new int[8][8];
         int[] map = new int[] { 1, 2, 3, 4, -1, -2, -3, -4 };
         for(int i=0;i<8;i++) {
             for(int j=0;j<8;j++) {
                 m[i][j] = mul(map[i],map[j]);
             }
         }
         for(int i=0;i<8;i++) {
             Map<Integer, Integer> sdiv = new HashMap<>();
             DIV.put(map[i], sdiv);
             for(int j=0;j<8;j++) {
                 int res = 0;
                 for(int k=0;k<8;k++) {
                     if(mul(map[j],map[k])==map[i]) {
                         res = map[k];
                         break;
                     }
                 }
                 sdiv.put(map[j], res);
             }
         }
     }
 
     private ProblemC() throws IOException {
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
         long x = longarr()[1];
         String s = rd.readLine();
         int n = s.length();
         char[] sc = s.toCharArray();
         char[] c = new char[(int)(n*x)];
         for(int i=0;i<x;i++) {
             System.arraycopy(sc,0,c,i*n,n);
         }
         return proceed(c);
     }
 
     private String proceed(char[] c) {
         int n = c.length;
         int[] map = new int[200];
         map['1'] = 1;
         map['i'] = 2;
         map['j'] = 3;
         map['k'] = 4;
         int[] p = new int[n+1];
         p[0] = 1;
         for(int i=0;i<n;i++) {
             p[i+1] = mul(p[i],map[c[i]]);
         }
         boolean ok = false;
         if(p[n] == -1) {
             for(int i=0;i<=n-2;i++) {
                 if(p[i]==2) {
                     for(int j=i+1;j<=n-1;j++) {
                         if(div(p[j],2) == 3) {
                             ok = true;
                             break;
                         }
                     }
                     if(ok) break;
                 }
             }
         }
         return ok?"YES":"NO";
     }
 
     private static int div(int a, int b) {
         return DIV.get(a).get(b);
     }
 
     private static int mul(int a, int b) {
         int sa = Math.abs(a)-1;
         int sb = Math.abs(b)-1;
         int c = 1;
         if((a < 0 && b > 0) || (a > 0 && b < 0)) {
             c = -1;
         }
         return c*MUL[sa][sb];
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
 
     private String[] split(String s) {
         int n = s.length();
         int sp = 0;
         for(int i=0;i<n;i++) {
             if(s.charAt(i)==' ') {
                 sp++;
             }
         }
         String[] res = new String[sp+1];
         int last = 0;
         int x = 0;
         for(int i=0;i<n;i++) {
             char c = s.charAt(i);
             if(c == ' ') {
                 res[x++] = s.substring(last,i);
                 last = i+1;
             }
         }
         res[x] = s.substring(last,n);
         return res;
     }
 
     private static void out(Object x) {
         System.out.println(x);
     }
 
     public static void main(String[] args) throws IOException {
         new ProblemC();
     }
 }
