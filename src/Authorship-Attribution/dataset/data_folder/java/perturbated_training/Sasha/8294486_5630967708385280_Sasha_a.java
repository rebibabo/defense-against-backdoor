import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class A {
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         Scanner in = new Scanner(new File("problem.in"));
         PrintStream out = new PrintStream(new File("problem.out"));
 
         int T = in.nextInt();
 
         for (int test = 1; test <= T; test++) {
             int D = in.nextInt(), N = in.nextInt();
 
             double maxTime = 0.0;
 
             for (int i=0; i<N; i++) {
                 int k = in.nextInt();
                 int s = in.nextInt();
 
                 double t = (double) (D - k) / s;
                 if (t > maxTime) {
                     maxTime = t;
                 }
             }
 
             out.printf("Case #%d: %f\n", test, D / maxTime);
         }
     }
 }
