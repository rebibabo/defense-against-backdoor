package year2016.qualification;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class Fractiles {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("D-small-attempt0.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             long K = in.nextInt();
             long C = in.nextInt();
             long S = in.nextInt();
             String answer;
             long need = (K+C-1)/C;
             if (S >= need) {
                 StringBuilder sb = new StringBuilder();
                 long index = 0;
                 for (int n=0; n<need; n++) {
                     long value = 0;
                     long pow = 1;
                     for (int c=0; c<C; c++) {
                         value += index*pow;
                         index++;
                         index %= K;
                         pow *= K;
                     }
                     value++;
                     sb.append(' ').append(value);
                 }
                 answer = sb.toString();
             } else {
                 answer = " IMPOSSIBLE";
             }
             
             out.println("Case #"+(t+1)+":"+answer);
         }
 
         out.close();
     }
     
 }
