import java.io.*;
 import java.util.StringTokenizer;
 
 public class Main {
 
     
     InputReader in;
     BufferedWriter out;
     StringTokenizer tok;
     StringBuilder ans;
 
     
     int n, k;
     boolean state[];
     public static void main(String[] args) throws IOException {
         Main sol = new Main();
         sol.begin();
     }
 
     private void begin() throws IOException {
         
         boolean file = true;
         if (file)
             in = new InputReader(new FileInputStream("A-small-attempt0.in"));
         else
             in = new InputReader(System.in);
         ans = new StringBuilder();
 
         
         int nCases = in.nextInt();
         for (int cas = 1; cas <= nCases; cas++){
             char[] str= in.next().toCharArray();
             n = str.length;
             state = new boolean[n];
             for (int i = 0; i < n; i++)
                 state[i] = (str[i] == '+');
             k = in.nextInt();
             int result = solve();
             String resultStr = result != -1 ? String.valueOf(result) : "IMPOSSIBLE";
             ans.append(String.format("Case #%d: %s\n", cas, resultStr));
         }
 
         System.out.println(ans.toString());
 
         
         out = new BufferedWriter(new FileWriter("output.txt"));
         out.write(ans.toString());
         out.flush();
         out.close();
     }
 
     private int solve() {
         int count = 0;
 
         for (int i = 0; i < n; i++){
             if (!state[i]){
                 if (i + k -1 >= n)
                     return -1;
 
                 count++;
                 for (int j = i; j < i + k; j++)
                     state[j] = !state[j];
             }
         }
         return count;
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