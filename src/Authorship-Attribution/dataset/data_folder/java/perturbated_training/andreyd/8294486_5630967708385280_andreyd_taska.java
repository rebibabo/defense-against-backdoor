import java.io.BufferedInputStream;
 import java.text.NumberFormat;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class TaskA {
 
     
     public static void main(String[] args) {
         NumberFormat nf = NumberFormat.getInstance(Locale.US);
         nf.setMinimumFractionDigits(6);
         nf.setMaximumFractionDigits(6);
         nf.setGroupingUsed(false);
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             int d = sc.nextInt();
             int n = sc.nextInt();
             double maxTime = 0;
             for (int j = 0; j < n; j++) {
                 int k = sc.nextInt();
                 int s = sc.nextInt();
                 double currTime = ((double) d - k) / s;
                 maxTime = Math.max(maxTime, currTime);
             }
             print(i, nf.format(d / maxTime));
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, String answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
 
 }
