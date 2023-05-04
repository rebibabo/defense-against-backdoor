import java.io.File;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.List;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 public class C {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
 
     void solve() {
         int n = sc.nextInt();
         List<Hiker> hikers = new ArrayList<Hiker>();
         for (int i = 0; i < n; ++i) {
             int di = sc.nextInt();
             int h = sc.nextInt();
             int mi = sc.nextInt();
             for (int k = 0; k < h; ++k) {
                 hikers.add(new Hiker(di, mi + k));
             }
         }
         Collections.sort(hikers, new Comparator<Hiker>() {
             @Override
             public int compare(Hiker o1, Hiker o2) {
                 return o1.m - o2.m;
             }
         });
 
         if (hikers.size() == 1) {
             System.out.println(0);
             return;
         }
 
         boolean ok = true;
         Hiker fast = hikers.get(0);
         Hiker slow = hikers.get(1);
         double tf = fast.m + (360.0 - fast.d) / 360.0 * fast.m;
         double ts = (360.0 - slow.d) / 360.0 * slow.m;
         if (tf - ts <= 1e-10) {
             ok = false;
         }
 
         System.out.printf("%d\n", ok ? 0 : 1);
     }
 
     static class Hiker {
         int d;
         int m;
 
         Hiker(int d, int m) {
             this.d = d;
             this.m = m;
         }
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
         String file = "C-small-1-attempt0";
 
         redirectToFile(file);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             System.out.printf("Case #%s: ", caseNumber);
             C template = new C();
             template.caseNumber = caseNumber;
             template.solve();
             System.out.flush();
         }
 
         sc.close();
     }
 
     @SuppressWarnings("UnusedDeclaration")
     static void redirectToFile(String file) throws Exception {
         System.setOut(new PrintStream(file + ".out"));
     }
 }
