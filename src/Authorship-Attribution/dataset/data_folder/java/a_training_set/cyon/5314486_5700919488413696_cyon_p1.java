package round2;
 
 
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
         int N = Integer.parseInt(st.nextToken());
         int P = Integer.parseInt(st.nextToken());
         int[] pcnt = new int[P];
         st = new StringTokenizer(br.readLine());
         for (int i = 0; i < N; i++) {
             int a = Integer.parseInt(st.nextToken());
             pcnt[a % P]++;
         }
         int cnt = 0;
         int cur = 0;
         for (int i = 0; i < N; i++) {
             int need = (P - cur) % P;
             if (pcnt[need] > 0) {
                 cur = 0;
                 pcnt[need]--;
             }
             else {
                 for (int j = 0; j < P; j++) {
                     if (pcnt[j] > 0) {
                         pcnt[j]--;
                         cur = (j + cur) % P;
                         if (i != N - 1) cnt++;
                         break;
                     }
                 }
             }
         }
         
         return "" + (N - cnt);
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
