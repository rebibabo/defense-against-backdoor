import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 
 public class ProblemB {
     BufferedReader rd;
     int len;
 
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
         String[] s = split(rd.readLine());
         int abs = 100000;
         int m = 100000;
         int n = 100000;
         len = s[0].length();
         int z = 9;
         int u = 1;
         while(u < len) {
             z = z*10+9;
             u++;
         }
         for(int i=0;i<=z;i++) {
             for(int j=0;j<=z;j++) {
                 if(matches(s[0],fmt(i)) && matches(s[1],fmt(j))) {
                     int a = Math.abs(i-j);
                     if(a < abs || (a == abs && i < m) || (a == abs && i == m && j < n)) {
                         abs = a;
                         m = i;
                         n = j;
                     }
                 }
             }
         }
 
         return fmt(m)+" "+fmt(n);
     }
 
     private String fmt(int x) {
         StringBuilder buf = new StringBuilder();
         buf.append(x);
         while(buf.length() < len) {
             buf.insert(0,'0');
         }
         return buf.toString();
     }
 
     private boolean matches(String s, String z) {
         for(int i=0;i<s.length();i++) {
             if(s.charAt(i)!='?' && s.charAt(i) != z.charAt(i)) {
                 return false;
             }
         }
         return true;
     }
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
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
