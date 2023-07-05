import java.io.*;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class Main {
 
     
     InputReader in;
     BufferedWriter out;
     StringTokenizer tok;
     StringBuilder ans;
 
     
     private int n;
     double[] speed, max;
     double[][] dist;
     private double[] dp;
 
     public static void main(String[] args) throws IOException {
         Main sol = new Main();
         sol.begin();
     }
 
     private void begin() throws IOException {
         
         boolean file = true;
         if (file)
             in = new InputReader(new FileInputStream("C-small-attempt0.in"));
         else
             in = new InputReader(System.in);
         ans = new StringBuilder();
 
 
         int nCases = in.nextInt();
         for (int cas = 1; cas <= nCases; cas++){
             n = in.nextInt();
             in.nextInt();
             speed = new double[n];
             max = new double[n];
             dist = new double[n][n];
             for (int i = 0; i < n; i++){
                 max[i] = in.nextInt();
                 speed[i] = in.nextInt();
             }
             for (int i = 0; i < n; i++)
                 for (int j = 0;j < n; j++)
                     dist[i][j] = in.nextInt();
             in.nextInt();
             in.nextInt();
             double min = solve();
             ans.append(String.format("Case #%d: %.12f\n", cas, min));
         }
         System.out.println(ans.toString());
 
         
         out = new BufferedWriter(new FileWriter("output.txt"));
         out.write(ans.toString());
         out.flush();
         out.close();
     }
 
     private double solve() {
         dp = new double[n];
         Arrays.fill(dp, -1);
         return f(0);
     }
 
     private double f(int city) {
         if (city == n - 1)
             return 0;
         if (dp[city] != -1)
             return dp[city];
 
         
         double min = 1000000000000.0;
         double rem = max[city];
         double time = 0;
         int currCity = city;
         for (int nextCity = city+1; nextCity < n; nextCity++){
             time += dist[currCity][nextCity] / speed[city];
             rem -= dist[currCity][nextCity];
             if (rem < 0)
                 break;
             currCity = nextCity;
             min = Math.min(min, time + f(nextCity));
         }
 
         return dp[city] = min;
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