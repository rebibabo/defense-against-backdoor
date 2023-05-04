import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 
 public class ALarge {
     @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
     private int caseNumber;
     private static Scanner sc;
     private long n;
 
     long flip(long num) {
         long res = 0;
         while (num > 0) {
             res = res * 10 + num % 10;
             num /= 10;
         }
         return res;
     }
 
     int digits(long val) {
         int res = 0;
         while (val > 0) {
             val /= 10;
             ++res;
         }
         return res;
     }
 
     void solve() {
         n = sc.nextLong();
         long res = 1;
         while (n > 1) {
             long flip;
 
             long next = getNext(n);
             while (next >= n && n != 1) {
                 
 
                 if (next == n && flip(next) < next) {
                     break;
                 }
 
                 ++res;
                 --n;
                 next = getNext(n);
             }
 
             res += n - next;
 
             flip = flip(next);
             if (flip < next) {
                 ++res;
                 next = flip;
             }
 
             n = next;
         }
 
         System.out.printf("%d\n", res);
     }
 
     long getNext(long n) {
         String nStr = Long.toString(n);
         int digits = nStr.length();
         char[] nextStr = nStr.toCharArray();
         for (int i = 0; i < digits / 2; ++i) {
             nextStr[nextStr.length - i - 1] = '0';
         }
         nextStr[nextStr.length - 1] = '1';
         return Long.parseLong(new String(nextStr));
     }
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
 
 
 
         String file = "A-small-attempt2";
 
         redirectToFile(file);
 
         String inFile = file + ".in";
         sc = new Scanner(new File(inFile));
 
         int cases = sc.nextInt();
         for (int caseNumber = 1; caseNumber <= cases; ++caseNumber) {
             System.out.printf("Case #%s: ", caseNumber);
             ALarge template = new ALarge();
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
