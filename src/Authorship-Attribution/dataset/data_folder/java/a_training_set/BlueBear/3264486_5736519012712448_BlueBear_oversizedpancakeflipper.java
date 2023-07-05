import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 import java.util.stream.Stream;
 
 public class OversizedPancakeFlipper {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("A-small-attempt0.in"));
         PrintStream cout = new PrintStream("A-small-attempt0.out");
         
         
 
 
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
             char[] s = cin.next().toCharArray();
             int k = cin.nextInt();
             int cnt = 0;
             for (int i = 0; i + k <= s.length; i++) {
                 if (s[i] == '-') {
                     for (int j = 0; j < k; j++)
                         s[i + j] = s[i + j] == '+' ? '-' : '+';
                     cnt++;
                 }
             }
 
             boolean allPlus = true;
             for (char ch : s)
                 if (ch == '-') {
                     allPlus = false;
                     break;
                 }
             String ans;
             if (allPlus) ans = "" + cnt;
             else ans = "IMPOSSIBLE";
 
             cout.printf("Case #%d: %s%n", _case, ans);
         }
 
         cin.close();
         cout.close();
     }
 }
