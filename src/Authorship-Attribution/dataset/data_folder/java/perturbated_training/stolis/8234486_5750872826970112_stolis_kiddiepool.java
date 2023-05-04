package year2015.round2;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class KiddiePool {
     
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.ENGLISH);
         File inputFile = new File("B-small-attempt1.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int N = in.nextInt();
             double V = in.nextDouble();
             double X = in.nextDouble();
             boolean ok = true;
             double answer = 0;
             if (N == 1) {
                 double R = in.nextDouble();
                 double C = in.nextDouble();
                 if (C == X) {
                     answer = V/R;
                 } else {
                     ok = false;
                 }
             } else {
                 double R1 = in.nextDouble();
                 double C1 = in.nextDouble();
                 double R2 = in.nextDouble();
                 double C2 = in.nextDouble();
                 if (C1 > C2) {
                     double temp = C1;
                     C1 = C2;
                     C2 = temp;
                     temp = R1;
                     R1 = R2;
                     R2 = temp;
                 }
                 if (X == C1) {
                     if (X == C2) {
                         answer = V/Math.max(R1, R2);
                     } else {
                         answer = V/R1;
                     }
                 } else {
                     if (X == C2) {  
                         answer = V/R2;
                     } else if (X < C1 || C2 < X) {
                         ok = false;
                     } else {
                         double ratio = R1*(X-C1)/(R2*(C2-X));
                         double t1 = V/(R1+R1*(X-C1)/(C2-X));
                         double t2 = ratio*t1;
                         if (t1 < 0 || t2 < 0) throw new RuntimeException();
                         answer = Math.max(t1,t2);
                     }
                 }
             }
             
             String s = ok ? String.format("%.8f", answer) : "IMPOSSIBLE";
             out.println("Case #"+(t+1)+": "+s);
         }
 
         out.close();
     }
     
 }
