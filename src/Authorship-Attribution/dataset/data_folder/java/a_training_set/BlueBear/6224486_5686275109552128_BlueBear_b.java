import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class B {
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("B-small-attempt2.in"));
         PrintStream cout = new PrintStream("B-small-attempt2.out");
         
         
         
         
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
             int D = cin.nextInt();
             int[] P = new int[D];
             for (int i = 0; i < D; i++)
                 P[i] = cin.nextInt();
 
             int ans = Integer.MAX_VALUE;
             int MAX = 0;
             for (int i : P)
                 if (i > MAX)
                     MAX = i;
 
             for (int W = 1; W <= MAX; W++) {
                 int cnt = 0;
                 for (int i : P)
                     cnt += (i - 1) / W;
                 ans = Math.min(ans, cnt + W);
             }
             cout.printf("Case #%d: %d%n", _case, ans);
         }
 
         cin.close();
         cout.close();
     }
 }