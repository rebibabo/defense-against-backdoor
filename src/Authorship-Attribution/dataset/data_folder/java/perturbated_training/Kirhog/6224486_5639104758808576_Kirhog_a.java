import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 public class A {
     @SuppressWarnings("FieldCanBeLocal")
     private static int caseNumber;
     private static Scanner scan;
 
     void solve() {
         int smax = scan.nextInt();
         int[] s = new int[smax + 1];
 
         char[] sAr = scan.nextLine().trim().toCharArray();
         for (int i = 0; i <= smax; ++i) {
             s[i] = sAr[i] - '0';
         }
 
         int res = 0;
         int cur = s[0];
         for (int i = 1; i <= smax; ++i) {
             if (s[i] > 0) {
                 if (cur < i) {
                     res += i - cur;
                     cur = i;
                 }
 
                 cur += s[i];
             }
         }
         System.out.printf("%s\n", res);
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         String file = "test";
 
         redirectToFile(file);
 
         String inFile = file + ".in";
         scan = new Scanner(new File(inFile));
 
         int cases = scan.nextInt();
         for (caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             System.out.printf("Case #%s: ", caseNumber);
             new A().solve();
             System.out.flush();
         }
 
         scan.close();
     }
 
     static void redirectToFile(String file) throws Exception {
         System.setOut(new PrintStream(file + ".out"));
     }
 }
