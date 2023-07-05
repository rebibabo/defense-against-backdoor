package year2015.qualification;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class OminousOmino {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("D-small-attempt0.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int X = in.nextInt();
             int R = in.nextInt();
             int C = in.nextInt();
             boolean richard;
             if (X == 1) {
                 richard = false;
             } else if (X == 2) {
                 richard = (R*C % 2 != 0);
             } else if (X == 3) {
                 richard = (R*C % 3 != 0) || Math.min(R,C) == 1;
             } else if (X == 4) {
                 richard = (R*C % 4 != 0) || Math.min(R,C) <= 2;
             } else if (X == 5) {
                 richard = (R*C % 5 != 0) || Math.min(R,C) <= 3;
             } else if (X == 6) {
                 richard = (R*C % 6 != 0) || Math.min(R,C) <= 3;
             } else {
                 richard = true;
             }
             out.println("Case #"+(t+1)+": "+(richard ? "RICHARD" : "GABRIEL"));
         }
 
         out.close();
     }
     
 }
