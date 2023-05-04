package c2015_c.c2015_r1;
 
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Scanner;
 
 public class Z2_1 {
 
     public static final int INT = 100;
 
     public static void main(String[] args) throws Exception {
         FileWriter fw = new FileWriter("C:\\output.txt");
         BufferedWriter out = new BufferedWriter(fw);
         
         String pathname = "C:\\Users\\YC14rp1\\Downloads\\B-small-attempt0.in";
         
         Scanner scanner = new Scanner(new File(pathname));
         int tn = scanner.nextInt();
         scanner.nextLine();
         for (int ti = 1; ti <= tn; ti++) {
             int d = scanner.nextInt();
             int[] a = new int[INT];
             int sum = 0;
             for (int i=0;i<d;i++) {
                 a[i] = scanner.nextInt();
                 sum += a[i];
             }
             int minutes = getMinutes(d,a,sum);
             String s;
             s = "Case #" + ti + ": " + minutes;
             System.out.println(s);
             out.write(s);
             out.write("\n");
         }
         out.close();
     }
 
     private static int getMinutes(int d, int[] a, Integer sum) {
         if (sum==0) {
             return 0;
         }
         int[] ints = Arrays.copyOf(a, INT);
         int[] ints2 = Arrays.copyOf(a, INT);
         int mx = 0;
         int mxi = -1;
         int sum2 = sum;
         for (int i=0;i<d;i++) {
             if (ints2[i]>0) {
                 ints2[i]--;
                 sum2--;
             }
             if (ints[i]>mx) {
                 mx = ints[i];
                 mxi = i;
             }
         }
         int min = getMinutes(d, ints2, sum2) + 1;
         if (mxi>-1) {
             
             int mxx = mx/2;
             if (mx % 2==1) {
                 mxx += 1;
             }
             for (int i=2;i<=mxx;i++) {
                 ints[d] = i;
                 ints[mxi] = mx - i;
                 int m2 = getMinutes(d+1, ints, sum) + 1;
                 if (m2<min) {
                     min = m2;
                 }
             }
         }
         
         return min;
     }
 
 }
