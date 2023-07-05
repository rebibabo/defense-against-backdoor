package year2016.qualification;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class CountingSheep {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("A-small-attempt0.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int N = in.nextInt();
             String answer;
             if (N == 0) {
                 answer = "INSOMNIA";
             } else {
                 answer = Long.toString(solve(N));
             }
             out.println("Case #"+(t+1)+": " +answer);
         }
 
         out.close();
     }
 
     private static long solve(int N) {
         int seenCount = 0;
         boolean[] seen = new boolean[10];
         long factor = 0;
         while (seenCount < 10) {
             factor++;
             long number = factor*N;
             while (number != 0) {
                 int digit = (int)(number%10);
                 number /= 10;
                 if (!seen[digit]) {
                     seenCount++;
                     seen[digit] = true;
                 }
             }
         }
         return factor*N;
     }
     
 }
