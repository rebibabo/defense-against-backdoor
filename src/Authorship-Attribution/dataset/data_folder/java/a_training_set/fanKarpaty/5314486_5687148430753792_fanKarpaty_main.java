import java.io.*;
 import java.util.Scanner;
 
 public class Main {
 
     public static void main(String[] args) throws IOException {
         System.setOut(new PrintStream(new File("output.txt")));
         Scanner in = new Scanner(new FileInputStream(new File("input.txt")));
 
         int T = in.nextInt();
 
         for (int t = 1; t <= T; t++) {
             int n = in.nextInt();
             int c = in.nextInt();
             int m = in.nextInt();
             int[][] p = new int[n][c];
             int count[] = new int[c];
 
             for (int i = 0; i < m;i++) {
                 int pi =  in.nextInt() - 1;
                 int b = in.nextInt() - 1;
                 p[pi][b]++;
                 count[b]++;
             }
            
             int ret = Math.max(count[0], count[1]);
             int d = 0;
 
             for (int i = 0; i < n; i++) {
                 if (p[i][0] == 0 || p[i][1] == 0)continue;
                 int j = 0;
                 if (p[i][1] - (count[0] - p[i][0]) < p[i][0] - (count[1] - p[i][1])) {
                     j = 1;
                 }
                 for (int jj = 0; jj < c; jj++) {
                     int countDif = 0;
                     if (count[j] > count[1-j]) {
                         countDif = count[j] - count[1 - j];
                     }
                     if (p[i][j] - countDif > count[1-j] - p[i][1-j]) {
                         if (i == 0) {
                             int dif = p[i][j] - countDif - (count[1-j] - p[i][1-j]);
                             ret += dif;
                             p[i][j] -= dif;
                         } else {
                             int dif = p[i][j] - countDif - (count[1-j] - p[i][1-j]);
                             d += dif;
                             p[i][j] -= dif;
                         }
                     }
 
                     j = (j + 1) % c;
                 }
             }
             System.out.println("Case #" + t + ": " + ret + " " + d);
 
         }
     }
 
 }
