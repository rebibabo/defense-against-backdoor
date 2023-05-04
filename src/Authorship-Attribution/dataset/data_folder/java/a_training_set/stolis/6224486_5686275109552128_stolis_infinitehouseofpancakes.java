package year2015.qualification;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class InfiniteHouseOfPancakes {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("B-small-attempt1.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int D = in.nextInt();
             int[] P = new int[D];
             for (int d=0; d<D; d++) {
                 P[d] = in.nextInt();
             }
             int min = 1000;
             for (int time=1; time<=1000; time++) {
                 int specials = 0;
                 for (int p : P) {
                     specials += (p-1)/time;
                 }
                 min = Math.min(time+specials, min);
             }
             
             out.println("Case #"+(t+1)+": "+min);
         }
 
         out.close();
     }
     
 }
