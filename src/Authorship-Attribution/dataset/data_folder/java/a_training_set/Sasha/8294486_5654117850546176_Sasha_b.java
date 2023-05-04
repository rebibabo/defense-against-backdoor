import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class B {
 
 
     private static String chain(char c1, char c2, int k) {
         StringBuilder buffer = new StringBuilder();
         for (int i = 0; i < k; i++) {
             buffer.append(c1).append(c2);
         }
         return buffer.toString();
     }
 
 
     public static void main(String[] args) throws Exception {
         Locale.setDefault(Locale.US);
 
         Scanner in = new Scanner(new File("problem.in"));
         PrintStream out = new PrintStream(new File("problem.out"));
 
         int T = in.nextInt();
 
         for (int test = 1; test <= T; test++) {
             int N = in.nextInt();
             int Red = in.nextInt();
             int Orange = in.nextInt();
             int Yellow = in.nextInt();
             int Green = in.nextInt();
             int Blue = in.nextInt();
             int Violet = in.nextInt();
 
             boolean impossible = false;
             String result = null;
 
             if (Green > Red || Orange > Blue || Violet > Yellow) {
                 impossible = true;
             }
 
             if (Green > 0 && Green == Red) {
                 if (Green + Red == N) {
                     result = chain('R', 'G', Red);
                 } else {
                     impossible = true;
                 }
             }
 
             if (Orange > 0 && Orange == Blue) {
                 if (Orange + Blue == N) {
                     result = chain('B', 'O', Blue);
                 } else {
                     impossible = true;
                 }
             }
 
             if (Violet > 0 && Violet == Yellow) {
                 if (Violet + Yellow == N) {
                     result = chain('Y', 'V', Yellow);
                 } else {
                     impossible = true;
                 }
             }
 
 
             if (result == null && !impossible) {
 
 
                 StringBuilder buffer = new StringBuilder();
                 char prev = 0;
                 if (Red > 0) {
                     Red--;
                     buffer.append('R');
                     prev = 'R';
                 } else if (Yellow > 0) {
                     Yellow--;
                     buffer.append('Y');
                     prev = 'Y';
                 } else if (Blue > 0) {
                     Blue--;
                     buffer.append('B');
                     prev = 'B';
                 }
 
 
                 while (Red + Yellow + Blue > 0) {
                     int max = 0;
                     char next = 0;
 
                     if (prev != 'R' && Red > max) {
                         max = Red;
                         next = 'R';
                     }
                     if (prev != 'Y' && Yellow > max) {
                         max = Yellow;
                         next = 'Y';
                     }
                     if (prev != 'B' && Blue > max) {
                         next = 'B';
                     }
 
                     if (next == 'R') {
                         Red--;
                         while (Green > 0) {
                             buffer.append("RG");
                             Green--;
                             Red--;
                         }
                     } else if (next == 'Y') {
                         Yellow--;
                         while (Violet > 0) {
                             buffer.append("YV");
                             Violet--;
                             Yellow--;
                         }
                     } else if (next == 'B') {
                         Blue--;
                         while (Orange > 0) {
                             buffer.append("BO");
                             Orange--;
                             Blue--;
                         }
                     } else {
                         impossible = true;
                         break;
                     }
                     buffer.append(next);
                     prev = next;
                 }
                 if (!impossible) {
                     result = buffer.toString();
                     if (result.charAt(0) == result.charAt(result.length() - 1)) {
                         impossible = true;
                     }
                 }
             }
 
 
             out.printf("Case #%d: %s\n", test, impossible ? "IMPOSSIBLE" : result);
         }
     }
 }
