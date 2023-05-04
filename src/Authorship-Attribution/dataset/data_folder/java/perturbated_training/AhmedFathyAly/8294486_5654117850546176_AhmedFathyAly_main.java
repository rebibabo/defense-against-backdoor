import java.io.*;
 import java.util.StringTokenizer;
 
 public class Main {
 
     
     InputReader in;
     BufferedWriter out;
     StringTokenizer tok;
     StringBuilder ans;
 
     
     int n, r, o, y, g, b, v;
     boolean[][][][][] dp;
     boolean[][][][][] solved;
 
 
     public static void main(String[] args) throws IOException {
         Main sol = new Main();
         sol.begin();
     }
 
     private void begin() throws IOException {
         
         boolean file = true;
         if (file)
             in = new InputReader(new FileInputStream("B-small-attempt0 (1).in"));
         else
             in = new InputReader(System.in);
         ans = new StringBuilder();
 
         int nCases = in.nextInt();
         for (int cas = 1; cas <= nCases; cas++) {
             n = in.nextInt();
             r = in.nextInt();
             o = in.nextInt();
             y = in.nextInt();
             g = in.nextInt();
             b = in.nextInt();
             v = in.nextInt();
             String str = solve();
             ans.append(String.format("Case #%d: %s\n", cas, str));
         }
         System.out.println(ans.toString());
 
         
         out = new BufferedWriter(new FileWriter("output.txt"));
         out.write(ans.toString());
         out.flush();
         out.close();
     }
 
     private String solve() {
         StringBuilder strb = new StringBuilder();
         for (int i = 0; i <= 2; i++) {
             String ans = f(i);
             if (ans != null)
                 return ans;
         }
         return "IMPOSSIBLE";
     }
 
     private String f(int start) {
         int[] count = {r, y, b};
         char[] chars = {'R', 'Y', 'B'};
         if (count[start] == 0)
             return null;
         StringBuilder strb = new StringBuilder();
         int curr = start;
 
         while (true) {
             
             strb.append(chars[curr]);
             count[curr]--;
 
             
             int maxCount = 0;
             int other = -1;
             for (int i = 0; i <= 2; i++)
                 if (i != curr && count[i] > maxCount) {
                     other = i;
                     maxCount = count[i];
                 }
             if (other == -1)
                 if (count[curr] == 0)
                     break;
                 else
                     return null;
             curr = other;
         }
 
         String result = strb.toString();
         if (result.charAt(0) == result.charAt(result.length() - 1))
             return null;
         return strb.toString();
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