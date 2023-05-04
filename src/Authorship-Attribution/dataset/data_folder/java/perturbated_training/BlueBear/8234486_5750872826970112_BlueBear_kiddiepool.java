import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.util.Scanner;
 
 public class KiddiePool {
 
   static int n;
   static BigDecimal V, X;
   static BigDecimal[] R, C;
   static final double EPS = 1E-8;
 
   public static void main(String[] args) throws FileNotFoundException {
     Scanner cin = new Scanner(new File("B-small-attempt1.in"));
     PrintStream cout = new PrintStream("B-small-attempt1.out");
     
     
     
     
 
     int _case = 0;
 
     for (int T = cin.nextInt(); T > 0; T--) {
       _case++;
 
       n = cin.nextInt();
       V = cin.nextBigDecimal();
       X = cin.nextBigDecimal();
       R = new BigDecimal[n];
       C = new BigDecimal[n];
       for (int i = 0; i < n; i++) {
         R[i] = cin.nextBigDecimal();
         C[i] = cin.nextBigDecimal();
       }
 
       boolean ok = true;
       double ans = 1E300;
 
       if (n == 1) {
         if (X.equals(C[0]))
           ans = V.doubleValue() / R[0].doubleValue();
         else
           ok = false;
       } else {
         if (C[0].equals(C[1])) {
           if (C[0].equals(X)) {
             ans = V.doubleValue() / (C[0].add(C[1])).doubleValue();
           } else
             ok = false;
         } else if (C[0].min(C[1]).compareTo(X) > 0 || C[0].max(C[1]).compareTo(X) < 0)
           ok = false;
         else {
           double t0 = V.multiply(C[1].subtract(X)).doubleValue() / R[0].multiply(C[1].subtract(C[0])).doubleValue();
           double t1 = V.multiply(C[0].subtract(X)).doubleValue() / R[1].multiply(C[0].subtract(C[1])).doubleValue();
           if (t0 < 0 || t1 < 0)
             ok = false;
           else
             ans = Math.max(t0, t1);
         }
       }
 
       if (ok)
         cout.printf("Case #%d: %.10f%n", _case, ans);
       else
         cout.printf("Case #%d: IMPOSSIBLE%n", _case);
     }
 
     cin.close();
     cout.close();
   }
 }
