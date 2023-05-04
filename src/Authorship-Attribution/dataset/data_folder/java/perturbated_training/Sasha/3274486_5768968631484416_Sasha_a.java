import java.io.File;
 import java.io.PrintStream;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class A {
     private static class Pancake {
         double r;
         double h;
         double s1;
         double s2;
 
         private Pancake(double r, double h) {
             this.r = r;
             this.h = h;
             this.s1 = Math.PI * r * r;
             this.s2 = 2 * Math.PI * r * h;
         }
 
     }
 
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         Scanner in = new Scanner(new File("problem.in"));
         PrintStream out = new PrintStream(new File("problem.out"));
 
         int T = in.nextInt();
 
         for (int test = 1; test <= T; test++) {
             int n = in.nextInt();
             int k = in.nextInt();
             Pancake[] pancakes = new Pancake[n];
 
             for (int i = 0; i < n; i++) {
                 pancakes[i] = new Pancake(in.nextDouble(), in.nextDouble());
             }
 
             Arrays.sort(pancakes, new Comparator<Pancake>() {
                 @Override
                 public int compare(Pancake o1, Pancake o2) {
                     return Double.compare(o2.s2, o1.s2);
                 };
             });
 
             double maxS1 = pancakes[0].s1, minS2 = pancakes[0].s2, result = pancakes[0].s2;
             for (int i=1; i<k; i++) {
                 maxS1 = Math.max(maxS1, pancakes[i].s1);
                 minS2 = Math.min(minS2, pancakes[i].s2);
 
                 result += pancakes[i].s2;
             }
 
             result += maxS1;
 
 
 
             double a = 0;
             for (int i=k; i<n; i++) {
                 a = Math.max(a, pancakes[i].s1 + pancakes[i].s2);
             }
 
             if (a - maxS1 - minS2 > 0) {
                 result += a - maxS1 - minS2;
             }
 
 
             out.printf("Case #%d: %f\n", test, result);
         }
     }
 }
