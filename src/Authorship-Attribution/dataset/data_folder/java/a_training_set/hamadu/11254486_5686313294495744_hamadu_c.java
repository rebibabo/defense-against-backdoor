import java.io.IOException;
 import java.io.InputStream;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.InputMismatchException;
 import java.util.Map;
 
 
 public class C {
 
     public static void main(String[] args) {
         InputReader in = new InputReader(System.in);
         PrintWriter out = new PrintWriter(System.out);
 
         int T = in.nextInt();
         for (int t = 1 ; t <= T ; t++) {
 
             int n = in.nextInt();
             String[][] words = new String[n][2];
             for (int i = 0; i < n ; i++) {
                 for (int j = 0; j < 2 ; j++) {
                     words[i][j] = in.nextToken();
                 }
             }
             out.println(String.format("Case #%d: %d", t, solve(words)));
         }
         out.flush();
     }
 
     private static int solve(String[][] words) {
         int n = words.length;
         Map<String,Integer> left = new HashMap<>();
         Map<String,Integer> right = new HashMap<>();
         for (String[] wd : words) {
             if (!left.containsKey(wd[0])) {
                 left.put(wd[0], left.size());
             }
             if (!right.containsKey(wd[1])) {
                 right.put(wd[1], right.size());
             }
         }
         int[][] pairs = new int[n][2];
         for (int i = 0; i < n ; i++) {
             pairs[i][0] = left.get(words[i][0]);
             pairs[i][1] = right.get(words[i][1]);
         }
 
         int[] dp = new int[1<<n];
         for (int i = 0; i < (1<<n); i++) {
             int knowLeft = 0;
             int knowRight = 0;
             for (int j = 0; j < n ; j++) {
                 if ((i & (1<<j)) == 0) {
                     continue;
                 }
                 knowLeft |= 1<<pairs[j][0];
                 knowRight |= 1<<pairs[j][1];
             }
             for (int j = 0; j < n ; j++) {
                 if ((i & (1<<j)) != 0) {
                     continue;
                 }
                 if ((knowLeft & (1<<pairs[j][0])) >= 1 && (knowRight & (1<<pairs[j][1])) >= 1) {
                     dp[i|(1<<j)] = Math.max(dp[i|(1<<j)], dp[i]+1);
                 } else {
                     dp[i|(1<<j)] = Math.max(dp[i|(1<<j)], dp[i]);
                 }
             }
         }
         return dp[(1<<n)-1];
     }
 
     static class InputReader {
         private InputStream stream;
         private byte[] buf = new byte[1024];
         private int curChar;
         private int numChars;
 
         public InputReader(InputStream stream) {
             this.stream = stream;
         }
 
         private int next() {
             if (numChars == -1)
                 throw new InputMismatchException();
             if (curChar >= numChars) {
                 curChar = 0;
                 try {
                     numChars = stream.read(buf);
                 } catch (IOException e) {
                     throw new InputMismatchException();
                 }
                 if (numChars <= 0)
                     return -1;
             }
             return buf[curChar++];
         }
 
         public char nextChar() {
             int c = next();
             while (isSpaceChar(c))
                 c = next();
             if ('a' <= c && c <= 'z') {
                 return (char) c;
             }
             if ('A' <= c && c <= 'Z') {
                 return (char) c;
             }
             throw new InputMismatchException();
         }
 
         public String nextToken() {
             int c = next();
             while (isSpaceChar(c))
                 c = next();
             StringBuilder res = new StringBuilder();
             do {
                 res.append((char) c);
                 c = next();
             } while (!isSpaceChar(c));
             return res.toString();
         }
 
         public int nextInt() {
             int c = next();
             while (isSpaceChar(c))
                 c = next();
             int sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = next();
             }
             int res = 0;
             do {
                 if (c < '0' || c > '9')
                     throw new InputMismatchException();
                 res *= 10;
                 res += c-'0';
                 c = next();
             } while (!isSpaceChar(c));
             return res * sgn;
         }
 
         public long nextLong() {
             int c = next();
             while (isSpaceChar(c))
                 c = next();
             long sgn = 1;
             if (c == '-') {
                 sgn = -1;
                 c = next();
             }
             long res = 0;
             do {
                 if (c < '0' || c > '9')
                     throw new InputMismatchException();
                 res *= 10;
                 res += c-'0';
                 c = next();
             } while (!isSpaceChar(c));
             return res * sgn;
         }
 
         public boolean isSpaceChar(int c) {
             return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
         }
 
         public interface SpaceCharFilter {
             public boolean isSpaceChar(int ch);
         }
     }
 
     static void debug(Object... o) {
         System.err.println(Arrays.deepToString(o));
     }
 }
