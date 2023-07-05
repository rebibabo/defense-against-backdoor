import java.util.*;
 
 public class bsmall {
 
     public static void main(String[] args) {
 
         Scanner stdin = new Scanner(System.in);
         int numCases = stdin.nextInt();
 
         for (int loop=1; loop<=numCases; loop++) {
 
             int n = stdin.nextInt();
             double vol = stdin.nextDouble();
             double temp = stdin.nextDouble();
 
             double[] rates = new double[n];
             double[] temps = new double[n];
             for (int i=0; i<n; i++) {
                 rates[i] = stdin.nextDouble();
                 temps[i] = stdin.nextDouble();
             }
 
             if (n == 1) {
 
                 if (Math.abs(temps[0]-temp) > 1e-9) System.out.println("Case #"+loop+": IMPOSSIBLE");
                 else System.out.println("Case #"+loop+": "+(vol/rates[0]));
             }
 
             else {
 
                 double min = Math.min(temps[0], temps[1]);
                 double max = Math.max(temps[0], temps[1]);
 
                 if (min > temp || max < temp) System.out.println("Case #"+loop+": IMPOSSIBLE");
                 else if (Math.abs(min-temp) < 1e-9 && Math.abs(max-temp) < 1e-9)
                     System.out.println("Case #"+loop+": "+(vol/(rates[0]+rates[1])));
 
                 else if (Math.abs(min-temp) < 1e-9 && max > temp) {
                     if (temps[0] < temps[1])
                         System.out.println("Case #"+loop+": "+(vol/rates[0]));
                     else
                         System.out.println("Case #"+loop+": "+(vol/rates[1]));
                 }
 
                 else if (min < temp && Math.abs(max-temp) < 1e-9) {
                     if (temps[0] > temps[1])
                         System.out.println("Case #"+loop+": "+(vol/rates[0]));
                     else
                         System.out.println("Case #"+loop+": "+(vol/rates[1]));
 
                 }
 
                 
                 else {
 
                     if (temps[0] < temps[1]) {
                         double volLow = vol*(temps[1]-temp)/(temps[1]-temps[0]);
                         double volHigh = vol - volLow;
                         double res = Math.max(volLow/rates[0], volHigh/rates[1]);
                         System.out.println("Case #"+loop+": "+res);
                     }
                     else {
                         double volLow = vol*(temps[0]-temp)/(temps[0]-temps[1]);
                         double volHigh = vol - volLow;
                         double res = Math.max(volLow/rates[1], volHigh/rates[0]);
                         System.out.println("Case #"+loop+": "+res);
                     }
                 }
 
             }
 
 
 
 
         }
     }
 }
