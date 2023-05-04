import java.io.*;
 import java.util.StringTokenizer;
 
 public class Main {
 
     
     InputReader in;
     BufferedWriter out;
     StringTokenizer tok;
     StringBuilder ans;
 
     
     int n, p;
     int[] g;
 
     public static void main(String[] args) throws IOException {
         Main sol = new Main();
         sol.begin();
     }
 
     private void begin() throws IOException {
         
         boolean file = true;
         if (file)
             in = new InputReader(new FileInputStream("A-small-attempt0 (3).in"));
         else
             in = new InputReader(System.in);
         ans = new StringBuilder();
 
         int nCases = in.nextInt();
         for (int cas = 1; cas <= nCases; cas++) {
             n = in.nextInt();
             p = in.nextInt();
             g = new int[n];
             for (int i = 0; i < n ;i++)
                 g[i] = in.nextInt();
 
             int min = solve();
             ans.append(String.format("Case #%d: %d\n", cas, min));
         }
         System.out.println(ans.toString());
 
         
         out = new BufferedWriter(new FileWriter("output.txt"));
         out.write(ans.toString());
         out.flush();
         out.close();
     }
 
     private int solve() {
         int count[] = new int[3];
         for (int x : g) {
             count[x % p] ++;
         }
 
         if (p == 2) {
             return count[0] + (1+ count[1]) / 2;
         } else {
             int result = count[0];
             int min = Math.min(count[1], count[2]);
             result += min;
             count[1] -= min;
             count[2] -= min;
             result += Math.ceil(1.0 * count[1] / 3);
             result += Math.ceil(1.0 * count[2] / 3);
             return result;
         }
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