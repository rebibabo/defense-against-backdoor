package round1b;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 import java.io.PrintWriter;
 
 public class p1 {
 
     public static void main(String[]args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(System.out);
         int T = Integer.parseInt(br.readLine());
         for(int i=1;i<=T;i++) {
             pw.println("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static String solve(BufferedReader br) throws IOException {
         StringTokenizer st = new StringTokenizer(br.readLine());
         int D = Integer.parseInt(st.nextToken());
         int K = Integer.parseInt(st.nextToken());
         double maxTime = 0;
         for (int i = 0; i < K; i++) {
             st = new StringTokenizer(br.readLine());
             int ki = Integer.parseInt(st.nextToken());
             int si = Integer.parseInt(st.nextToken());
             double dur = (D - ki) / (1.0 * si);
             maxTime = Math.max(dur, maxTime);
         }
         return "" + (D / maxTime);
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
