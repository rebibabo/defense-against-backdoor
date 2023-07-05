import java.io.File;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class C {
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         Scanner in = new Scanner(new File("problem.in"));
         PrintStream out = new PrintStream(new File("problem.out"));
 
         int T = in.nextInt();
 
         for (int test = 1; test <= T; test++) {
             int n = in.nextInt();
             int k = in.nextInt();
 
             double u = in.nextDouble();
 
             double[] p = new double[n];
             for (int i = 0; i < n; i++) {
                 p[i] = in.nextDouble();
             }
 
 
             double min = 1.0;
             for (int i = 0; i < n; i++) {
                 if (p[i] < min) {
                     min = p[i];
                 }
             }
 
 
             while (u > 0 && min < 1.0) {
                 int count = 0;
                 double ceil = 1.0;
 
                 for (int i = 0; i < n; i++) {
                     if (p[i] == min) {
                         count++;
                     } else if (p[i] > min && p[i] < ceil) {
                         ceil = p[i];
                     }
                 }
 
 
                 double add = (ceil - min) * count;
 
                 if (u >= add) {
                     for (int i = 0; i < n; i++) {
                         if (p[i] == min) {
                             p[i] = ceil;
                         }
                     }
                 } else {
                     add = u;
                     for (int i = 0; i < n; i++) {
                         if (p[i] == min) {
                             p[i] += add / count;
                         }
                     }
                 }
 
                 u -= add;
                 min = ceil;
             }
 
             double result = 1.0;
             for (int i = 0; i < n; i++) {
                 result *= p[i];
             }
 
             out.printf("Case #%d: %f\n", test, result);
         }
     }
 }
