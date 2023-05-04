package year2016.round2;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class FreeformFactory {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("D-small-attempt1.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int N = in.nextInt();
             boolean[][] skill = new boolean[N][N];
             for (int n=0; n<N; n++) {
                 String s = in.next();
                 for (int i=0; i<N; i++) {
                     skill[n][i] = s.charAt(i) == '1';
                 }
             }
             int limit = 1 << N*N;
             int best = N*N;
             main: for (int mask=0; mask<limit; mask++) {
                 int cost = 0;
                 for (int n=0; n<N; n++) {
                     for (int i=0; i<N; i++) {
                         boolean knows = ((1 << (n*N+i)) & mask) != 0;
                         if (skill[n][i]) {
                             if (!knows) {
                                 continue main;
                             }
                         } else {
                             if (knows) {
                                 cost++;
                             }
                         }
                     }
                 }
 
                 for (int s=0; s<N; s++) {
                     int first = -1;
                     int scount = 0;
                     for (int n=0; n<N; n++) {
                         boolean knows = ((1 << (n*N+s)) & mask) != 0;
                         if (knows) {
                             first = n;
                             scount++;
                         }
                     }
                     if (first == -1) {
                         continue main;
                     }
                     for (int i=0; i<N; i++) {
                         boolean knows = ((1 << (first*N+i)) & mask) != 0;
                         if (knows) {
                             scount--;
                         }
                     }
                     if (scount != 0) {
                         continue main;
                     }
                     for (int n=0; n<N; n++) {
                         boolean knows = ((1 << (n*N+s)) & mask) != 0;
                         if (knows) {
                             for (int i=0; i<N; i++) {
                                 boolean fknows = ((1 << (first*N+i)) & mask) != 0;
                                 knows =  ((1 << (n*N+i)) & mask) != 0;
                                 if (fknows != knows) {
                                     continue main;
                                 }
                             }
                         }
                     }
                 }
                 
                 best = Math.min(cost, best);
             }
             out.println("Case #"+(t+1)+": "+best);
         }
 
         out.close();
     }
     
 }
