import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.Scanner;
 
 public class C {
 
     final static int DUMMY = 0;
     final static int[][] g = { { DUMMY }, { DUMMY, 1, 2, 3, 4 }, { DUMMY, 2, -1, 4, -3 }, { DUMMY, 3, -4, -1, 2 }, { DUMMY, 4, 3, -2, -1 } };
     static int L, X;
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("C-small-attempt2.in"));
         PrintStream cout = new PrintStream("C-small-attempt2.out");
         
         
         
         
 
         int _case = 0;
         for (int T = cin.nextInt(); T > 0; T--) {
             _case++;
             L = cin.nextInt();
             X = cin.nextInt();
             String s = cin.next();
             int[] a = new int[L];
             for (int i = 0; i < L; i++)
                 a[i] = s.charAt(i) - 'i' + 2;
 
             cout.printf("Case #%d: %s%n", _case, check(a) ? "YES" : "NO");
             
         }
 
         cin.close();
         cout.close();
     }
 
     static int calc(int i, int j) {
         int sign = 1;
         sign *= Integer.signum(i);
         sign *= Integer.signum(j);
         i = Math.abs(i);
         j = Math.abs(j);
         return sign * g[i][j];
     }
 
     static int pow(int a, long n) {
         int res = 1;
         while (n > 0) {
             if (n % 2 == 1)
                 res = calc(res, a);
             a = calc(a, a);
             n /= 2;
         }
         return res;
     }
 
     static boolean check(int[] a) {
         int state = 1;
         for (int i : a)
             state = calc(state, i);
         int wholeState = pow(state, X);
         if (wholeState != -1)
             return false;
 
         int[] b = new int[4 * a.length];
         for (int i = 0; i < L; i++)
             b[i] = a[i];
         for (int i = L; i < b.length; i++)
             b[i] = b[i - L];
 
         int left = 0, leftCnt = 1;
         state = 1;
         for (; left < b.length; left++, leftCnt++) {
             state = calc(state, b[left]);
             if (state == 2)
                 break;
         }
         int right = b.length - 1, rightCnt = 1;
         state = 1;
         for (; right >= 0; right--, rightCnt++) {
             state = calc(b[right], state);
             if (state == 4)
                 break;
         }
         return left < b.length && right >= 0 && leftCnt + rightCnt < L * X;
     }
 
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
 }