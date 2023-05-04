package year2015.round1a;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class Haircut {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("B-small-attempt0.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int B = in.nextInt();
             long N = in.nextLong();
             long[] M = new long[B];
             for (int b=0; b<B; b++) {
                 M[b] = in.nextLong();
             }
             long low = 0;
             long high = 1000000000000000L;
             while (low < high) {
                 long mid = (low + high) >>> 1;
                 long count = 0;
                 for (long time : M) {
                     count += mid/time;
                 }
                 if (count >= N-B) {
                     high = mid;
                 } else {
                     low = mid+1;
                 }
             }
             long count = 0;
             for (long time : M) {
                 count += low/time;
             }
             count -= N-B;
             int answer = 0;
             for (int i=B-1; i>=0; i--) {
                 if (low%M[i] == 0) {
                     if (count == 0) {
                         answer = i+1;
                         break;
                     }
                     count--;
                 }
             }
             
             out.println("Case #"+(t+1)+": "+answer);
         }
 
         out.close();
     }
     
 }
