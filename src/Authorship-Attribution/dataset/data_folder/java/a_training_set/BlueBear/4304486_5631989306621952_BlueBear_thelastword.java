import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.math.BigInteger;
 import java.util.Scanner;
 
 public class TheLastWord {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("A-small-attempt0.in"));
         PrintStream cout = new PrintStream("A-small-attempt0.out");
 
 
 
 
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
             StringBuilder ans = new StringBuilder();
 
             String s = cin.next();
             String[] f = new String[s.length()];
             f[0] = "" + s.charAt(0);
             for (int i = 1; i < s.length(); i++) {
                 String hehe = f[i - 1] + s.charAt(i);
                 String haha = s.charAt(i) + f[i - 1];
                 if (hehe.compareTo(haha) > 0) f[i] = hehe;
                 else f[i] = haha;
             }
             ans.append(f[s.length() - 1]);
 
             cout.printf("Case #%d: %s%n", _case, ans.toString());
         }
 
         cin.close();
         cout.close();
     }
 }
