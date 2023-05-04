import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class Fractiles {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("D-small-attempt0.in"));
         PrintStream cout = new PrintStream("D-small-attempt0.out");
 
 
 
 
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
             StringBuilder ans = new StringBuilder();
             long k = cin.nextLong();
             long c = cin.nextLong();
             long s = cin.nextLong();
 
             long pow = 1;
             for (int i = 0; i < c - 1; i++) pow *= k;
             for (int i = 0; i < k; i++) {
                 ans.append(" ");
                 ans.append(1 + i * pow);
             }
 
             cout.printf("Case #%d:%s%n", _case, ans.toString());
         }
 
         cin.close();
         cout.close();
     }
 }
