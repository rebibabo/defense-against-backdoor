package rc;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 import java.io.PrintWriter;
 
 public class p2 {
 
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
         int B = Integer.parseInt(st.nextToken());
         int M = Integer.parseInt(st.nextToken()); 
         StringBuilder bob = new StringBuilder();
         int SZ = (B - 1) * (B - 1);
         int pw = 1 << SZ;
         boolean done = false;
         for (int i=1;i<pw;i++) {
 
             int[][]mat = new int[B][B];
             int r = 0, c = 1;
 
             for (int j=0;j<SZ;j++) {
 
                 if ( ((i >> j) & 1) == 1) {
                     mat[r][c] = 1;
                 }
                 else {
                     mat[r][c] = 0;
                 }
                 c++;
                 if (c == B) {
                     r++;
                     c = 1;
                 }
             }
 
             if (!valid(mat)) continue;
 
             long cnt = matPow(mat, M);
             if (cnt == M) {
                 bob.append("POSSIBLE\n");
                 for (int k=0;k<B;k++) {
                     for (int l=0;l<B;l++) {
                         bob.append(mat[k][l]);
                     }
                     if (k != B-1)bob.append("\n");
                 }
                 done = true;
                 break;
             }
         }
         if (!done) {
             bob.append("IMPOSSIBLE");
         }
 
         return bob.toString();
     }
 
     private static boolean valid(int[][] mat) {
 
         int B = mat.length;
         for (int i=0;i<B;i++) {
             for (int j=0;j<B;j++) {
                 if (mat[i][j] == 1 && mat[j][i] == 1) return false;
             }
         }
 
         return true;
     }
 
 
     private static long matPow(int[][] mat, int v) {
 
         long res = 0;
 
         int[][]temp0 = new int[mat.length][mat.length];
         int[][]temp1 = new int[mat.length][mat.length];
         for (int i=0;i<mat.length;i++) temp0[i][i] = 1;
 
         for (int t = 0; t < v+mat.length; t++) {
 
 
 
 
             for (int i = 0; i < mat.length; i++) {
                 for (int j = 0; j < mat[0].length; j++) {
                     temp1[i][j] = 0;
                 }
             }
 
             for (int i = 0; i < mat.length; i++) {
                 for (int j = 0; j < mat[0].length; j++) {
                     for (int k = 0; k < mat.length; k++) {
                         temp1[i][j] += temp0[i][k] * mat[k][j];
                     }
                 }
             }
             for (int i = 0; i < mat.length; i++) {
                 for (int j = 0; j < mat[0].length; j++) {
                     temp0[i][j] = temp1[i][j];
                 }
             }
 
             if (t < v) {
                 res += temp0[0][mat.length - 1];
             }
             else {
                 if (temp0[0][mat.length - 1] > 0) {
                     return -1;
                 }
             }
         }
 
         return res;
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
