package round3;
 
 import java.util.Scanner;
 
 public class C {
     static int MOD = 24;
 
     static int espera(int arr, int leav) {
         int ret = leav - arr;
         if (ret < 0) ret += MOD;
         return ret;
     }
     
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int cases = sc.nextInt();
         for (int caze = 1; caze <= cases; caze++) {
             int ans = 0;
             int c = sc.nextInt();
             int[][] ins = new int[c][2], outs = new int[c][2];
             for (int i = 0; i < c; i++) {
                 ins[i][0] = ins[i][1] = outs[i][0] = outs[i][1] = -1;
             }
             for (int i = 0; i < 2 * c; i++) {
                 int from = i / 2;
                 int to = sc.nextInt() - 1;
                 int sale = sc.nextInt();
                 int demora = sc.nextInt();
                 outs[from][i % 2] = sale;
                 int idx = 0;
                 if (ins[to][idx] != -1) idx++;
                 ins[to][idx] = (sale + demora) % MOD;
                 ans += demora;
             }
             for (int i = 1; i < c; i++) {
                 int a = espera(ins[i][0], outs[i][0]) + espera(ins[i][1], outs[i][1]);
                 int b = espera(ins[i][0], outs[i][1]) + espera(ins[i][1], outs[i][0]);
                 ans += Math.min(a, b);
             }
             int a = espera(0, outs[0][0]) + espera(ins[0][1], outs[0][1]);
             int b = espera(0, outs[0][1]) + espera(ins[0][1], outs[0][0]);
             int c2 = espera(0, outs[0][0]) + espera(ins[0][0], outs[0][1]);
             int d = espera(0, outs[0][1]) + espera(ins[0][0], outs[0][0]);
             ans += Math.min(Math.min(a,b), Math.min(c2, d));
             System.out.println("Case #" + caze + ": " + ans);
         }
     }
 }
