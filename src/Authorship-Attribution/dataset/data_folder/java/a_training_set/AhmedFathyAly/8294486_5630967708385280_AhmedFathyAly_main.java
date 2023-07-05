import algorithms.SegmentTree;
 
 import java.io.*;
 import java.util.StringTokenizer;
 import java.util.TreeMap;
 
 public class Main {
 
     
     InputReader in;
     BufferedWriter out;
     StringTokenizer tok;
     StringBuilder ans;
 
     
     int nOther;
     double total;
     double[] start, speed;
     public static void main(String[] args) throws IOException {
         Main sol = new Main();
         sol.begin();
     }
 
     private void begin() throws IOException {
         
         boolean file = true;
         if (file)
             in = new InputReader(new FileInputStream("A-small-attempt1.in"));
         else
             in = new InputReader(System.in);
         ans = new StringBuilder();
 
         int nCases = in.nextInt();
         for (int cas = 1; cas <= nCases; cas++){
             total = in.nextInt();
             nOther = in.nextInt();
             start = new double[nOther];
             speed = new double[nOther];
             for (int k = 0; k < nOther; k++){
                 start[k] = in.nextInt();
                 speed[k] = in.nextInt();
             }
             double max = solve();
             ans.append(String.format("Case #%d: %.12f\n", cas, max));
         }
         System.out.println(ans.toString());
 
         
         out = new BufferedWriter(new FileWriter("output.txt"));
         out.write(ans.toString());
         out.flush();
         out.close();
     }
 
     private double solve() {
         double start = 0;
         double end = 100000000000000.0;
         for (int i = 0; i < 200; i++){
             double mid = (start + end) / 2.0;
             if (valid(mid))
                 start = mid;
             else
                 end = mid;
         }
         return start;
     }
 
     private boolean valid(double mid) {
         for (int i = 0; i < nOther; i++)
             if (intersects(mid, start[i], speed[i]))
                 return false;
         return true;
     }
 
     private boolean intersects(double speed1, double start2, double speed2) {
         if (speed1 == speed2)
             return false;
         double t = (start2) / (speed1 - speed2);
         double herTimeToFinish = total / speed1;
         if (t > 0 && t < herTimeToFinish)
             return true;
         return false;
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