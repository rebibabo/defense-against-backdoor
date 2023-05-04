package year2017.round2;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class FreshChocolate {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("A-small-attempt0.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int N = in.nextInt();
             int P = in.nextInt();
             int[] R = new int[P];
             for (int n=0; n<N; n++) {
                 int G = in.nextInt();
                 R[G%P]++;
             }
             int answer;
             if (P == 2) {
                 answer = R[0];
                 
                 int min = R[1]/2;
                 answer += min;
                 R[1] -= 2*min;
                 if (R[1] > 0) answer++;
             } else if (P == 3) {
                 answer = R[0];
                 int min = Math.min(R[1], R[2]);
                 answer += min;
                 R[1] -= min;
                 R[2] -= min;
                 
                 min = R[1]/3;
                 answer += min;
                 R[1] -= 3*min;
                 
                 min = R[2]/3;
                 answer += min;
                 R[2] -= 3*min;
                 if (R[1] + R[2] > 0) answer++;
             } else {
                 if (P != 4) throw new IllegalArgumentException();
                 answer = R[0];
                 
                 int min = Math.min(R[1], R[3]);
                 answer += min;
                 R[1] -= min;
                 R[3] -= min;
                 
                 min = R[2]/2;
                 answer += min;
                 R[2] -= 2*min;
                 
                 min = Math.min(R[2], R[1]/2);
                 answer += min;
                 R[2] -= min;
                 R[1] -= 2*min;
                 
                 min = Math.min(R[2], R[3]/2);
                 answer += min;
                 R[2] -= min;
                 R[3] -= 2*min;
                 
                 min = R[1]/4;
                 R[1] -= 4*min;
                 answer += min;
                 
                 min = R[3]/4;
                 R[3] -= 4*min;
                 answer += min;
                 if (R[1] + R[2] + R[3] > 0) answer++;
             }
             
             out.println("Case #"+(t+1)+": " + answer);
         }
 
         out.close();
     }
     
 }
