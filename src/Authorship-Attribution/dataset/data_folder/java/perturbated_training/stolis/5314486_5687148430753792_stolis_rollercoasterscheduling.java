package year2017.round2;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class RollerCoasterScheduling {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("B-small-attempt0.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int N = in.nextInt();
             int C = in.nextInt();
             int M = in.nextInt();
             int[][] stats = new int[C][N];
             for (int m=0; m<M; m++) {
                 int P = in.nextInt()-1;
                 int B = in.nextInt()-1;
                 stats[B][P]++;
             }
             int rides = stats[0][0] + stats[1][0];
             int promotions = 0;
             
             
             for (int c=0; c<=1; c++) {
                 int oc = 1-c;
                 for (int r=0; r<stats[c][0]; r++) {
                     for (int i=1; i<N; i++) {
                         if (stats[oc][i] > 0) {
                             stats[oc][i]--;
                             break;
                         }
                     }
                 }
             }
             
             for (int pos=1; pos<N; pos++) {
                 int[] rem = new int[2];
                 for (int c=0; c<=1; c++) {
                     int oc = 1-c;
                     rem[c] = stats[c][pos];
                     for (int r=0; r<stats[c][pos]; r++) {
                         for (int i=pos+1; i<N; i++) {
                             if (stats[oc][i] > 0) {
                                 stats[oc][i]--;
                                 rem[c]--;
                                 rides++;
                                 break;
                             }
                         }
                     }
                 }
                 if (rem[0] > 0) {
                     if (rem[1] > 0) {
                         rides += Math.max(rem[0],rem[1]);
                         promotions += Math.min(rem[0], rem[1]);
                     } else {
                         rides += rem[0];
                     }
                 } else if (rem[1] > 0) {
                     rides += rem[1];
                 }
             }
 
             out.println("Case #"+(t+1)+": " + rides + " " + promotions);
         }
 
         out.close();
     }
     
 }
