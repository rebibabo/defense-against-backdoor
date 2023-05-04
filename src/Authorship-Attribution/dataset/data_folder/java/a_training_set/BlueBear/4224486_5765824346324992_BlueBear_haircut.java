import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class Haircut {
 
     static int gcd(int a, int b) {
         return b == 0 ? a : gcd(b, a % b);
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("B-small-attempt0.in"));
         PrintStream cout = new PrintStream("B-small-attempt0.out");
         
         
         
         
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
 
             int n = cin.nextInt();
             int total = cin.nextInt() - 1;
             int[] a = new int[n];
             int lcm = 1;
             for (int i = 0; i < n; i++) {
                 a[i] = cin.nextInt();
                 lcm = (lcm * a[i]) / gcd(lcm, a[i]);
             }
             int M = 0;
             for (int i = 0; i < n; i++)
                 M += lcm / a[i];
 
             total %= M;
             int[] available = new int[n];
             for (int i = 0; i < total; i++) {
                 int index = 0;
                 for (int j = 0; j < n; j++)
                     if (available[j] < available[index])
                         index = j;
                 available[index] += a[index];
             }
             int ans = 0;
             for (int i = 0; i < n; i++)
                 if (available[i] < available[ans])
                     ans = i;
 
             cout.printf("Case #%d: %d%n", _case, ans + 1);
         }
 
         cin.close();
         cout.close();
     }
 
 }
