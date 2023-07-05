package qualif;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;
 import java.util.StringTokenizer;
 import java.io.PrintWriter;
 import java.util.stream.Collectors;
 
 public class Fractiles {
 
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
         int K = Integer.parseInt(st.nextToken());
         int C = Integer.parseInt(st.nextToken());
         int S = Integer.parseInt(st.nextToken());
         List<Long> idxs = new ArrayList<>();
         C = Math.min(K, C);
         int q = (K + C - 1) / C;
         if (q > S) {
             return "IMPOSSIBLE";
         }
         int sf = 0;
         for (int i=0;i<q;i++) {
             long idx = 0;
             for (int j=C-1;j>=0;j--) {
                 idx += Math.min(sf, K - 1) * pow(K, j);
                 sf++;
             }
             idxs.add(idx + 1);
         }
         return idxs.stream().map(i -> i.toString()).collect(Collectors.joining(" "));
     }
 
     private static long pow(long k, int j) {
         long res = 1;
         for (int i=0;i<j;i++) {
             res *= k;
         }
         return res;
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
