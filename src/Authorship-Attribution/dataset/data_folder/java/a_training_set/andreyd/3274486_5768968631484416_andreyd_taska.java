import java.io.BufferedInputStream;
 import java.text.NumberFormat;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class TaskA {
 
     public static void main(String[] args) {
         NumberFormat nf = NumberFormat.getInstance(Locale.US);
         nf.setMinimumFractionDigits(7);
         nf.setMaximumFractionDigits(7);
         nf.setGroupingUsed(false);
         long time = System.currentTimeMillis();
         Scanner sc = new Scanner(new BufferedInputStream(System.in));
         int t = sc.nextInt();
         sc.nextLine();
         for (int i = 1; i <= t; i++) {
             int n = sc.nextInt();
             int k = sc.nextInt();
             Cyl[] c = new Cyl[n];
             for (int j = 0; j < n; j++) {
                 c[j] = new Cyl();
                 c[j].r = sc.nextInt();
                 c[j].h = sc.nextInt();
                 c[j].prod = 2 * c[j].r * c[j].h;
                 c[j].num = j;
             }
             Cyl[] prods = Arrays.copyOf(c, n);
             Arrays.sort(c, new Comparator<Cyl>() {
                 @Override
                 public int compare(Cyl o1, Cyl o2) {
                     return Long.compare(o2.r, o1.r);
                 }
             });
             Arrays.sort(prods, new Comparator<Cyl>() {
                 @Override
                 public int compare(Cyl o1, Cyl o2) {
                     return Long.compare(o2.prod, o1.prod);
                 }
             });
             long max = 0L;
             for (int j = 0; j <= n-k; j++) {
                 long currS = c[j].r * c[j].r + c[j].prod;
                 int count = 1;
                 int ind = 0;
                 while (count < k) {
                     if (c[j].num != prods[ind].num) {
                         currS += prods[ind].prod;
                         count++;
                     }
                     ind++;
                 }
                 max = Math.max(max, currS);
             }
             System.err.println(max);
             print(i, nf.format(max * Math.PI));
         }
         sc.close();
         System.err.println(System.currentTimeMillis() - time);
     }
     
     private static void print(int caseNum, String answer) {
         System.out.println("Case #" + caseNum + ": " + answer);
     }
     
     static class Cyl {
         long r;
         int h;
         long prod;
         int num;
     }
 
 }
