import java.io.*;
 import java.util.StringTokenizer;
 
 public class Main {
 
     
     InputReader in;
     BufferedWriter out;
     StringTokenizer tok;
     StringBuilder ans;
 
     
     int n;
     int[] max;
     int[][][] dp;
 
     public static void main(String[] args) throws IOException {
         Main sol = new Main();
         sol.begin();
     }
 
     private void begin() throws IOException {
         
         boolean file = true;
         if (file)
             in = new InputReader(new FileInputStream("B-small-attempt0.in"));
         else
             in = new InputReader(System.in);
         ans = new StringBuilder();
 
         
         int nCases = in.nextInt();
         for (int cas = 1; cas <= nCases; cas++){
             char[] str = in.next().toCharArray();
             n = str.length;
             max = new int[n];
             for (int i = 0; i < n; i++)
                 max[i] = str[i] - '0';
             String ansStr = solve();
             ans.append(String.format("Case #%d: %s\n", cas, ansStr));
         }
         System.out.println(ans.toString());
 
         
         out = new BufferedWriter(new FileWriter("output.txt"));
         out.write(ans.toString());
         out.flush();
         out.close();
     }
 
     private String solve() {
         dp = new int[n][10][2];
         for (int i = 0; i < n; i++)
             for (int j = 0; j < 10; j++)
                 for (int k = 0; k < 2; k++)
                     dp[i][j][k] = -1;
 
         StringBuilder strb = new StringBuilder();
         int prev = 0;
         int lowered = 0;
         for (int pos = 0; pos < n; pos++){
             int digit = f(pos, prev, lowered);
             if (digit < max[pos])
                 lowered = 1;
             prev = digit;
             if (! (strb.length() == 0 && digit == 0))
                 strb.append((char)('0' + digit));
         }
 
         return strb.toString();
     }
 
     private int f(int pos, int prev, int lowered) {
         
         if (pos == n)
             return 0;
         if (dp[pos][prev][lowered] != -1)
             return dp[pos][prev][lowered];
 
         
         int maxDigit = lowered == 1 ? 9 : max[pos];
         int minDigit = prev;
         int ans = -1;
         for (int digit = maxDigit; digit >= minDigit; digit--){
             int newLowered = lowered;
             if (digit < max[pos])
                 newLowered = 1;
             if (f(pos+1, digit, newLowered) != -1){
                 ans = digit;
                 break;
             }
         }
 
 
         return dp[pos][prev][lowered] = ans;
     }
 
 }
 
 class InputReader {
     BufferedReader reader;
     StringTokenizer tok;
 
     public InputReader(InputStream stream) {
         reader = new BufferedReader(new InputStreamReader(stream), 32768);
         tok = new StringTokenizer("");
     }
 
     public String next() {
         while (!tok.hasMoreTokens())
             try {
                 tok = new StringTokenizer(reader.readLine());
             } catch (IOException e) {
                 e.printStackTrace();
             }
         return tok.nextToken();
     }
 
     public int nextInt() {
         return Integer.parseInt(next());
     }
 }