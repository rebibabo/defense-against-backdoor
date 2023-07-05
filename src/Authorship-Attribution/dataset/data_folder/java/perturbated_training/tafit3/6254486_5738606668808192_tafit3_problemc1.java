import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 public class ProblemC1 {
     BufferedReader rd;
 
     ProblemC1() throws IOException {
         rd = new BufferedReader(new InputStreamReader(System.in));
         compute();
     }
 
     private void compute() throws IOException {
         rd.readLine();
         long[] nj = longarr();
         out("Case #1:");
         if(nj[0] == 16) {
             small(nj[1]);
         } else {
             large();
         }
     }
 
     private void small(long cnt) {
         long y = 1000_0000_0000_0001L;
         long z = Long.parseLong(Long.toString(y),2);
         int f = 0;
         while(f < cnt) {
             String s = Long.toString(z,2);
             StringBuilder b = new StringBuilder();
             int j = 2;
             while(j <= 10) {
                 long u = Long.parseLong(s, j);
                 long v = div(u);
                 if(v == 1) {
                     break;
                 } else {
                     if(b.length() > 0) {
                         b.append(' ');
                     }
                     b.append(v);
                 }
                 j++;
             }
             if(j > 10) {
                 f++;
                 out(s+" "+b);
             }
             z += 2;
         }
     }
 
     private void large() {
 
     }
 
     private static long div(long x) {
         if(x < 4 || x%2 == 0) {
             return (x == 2 || x == 3)?1:2;
         }
         for(long i=3;i*i<=x;i+=2) {
             if(x%i==0) {
                 return i;
             }
         }
         return 1;
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
         new ProblemC1();
     }
 }
