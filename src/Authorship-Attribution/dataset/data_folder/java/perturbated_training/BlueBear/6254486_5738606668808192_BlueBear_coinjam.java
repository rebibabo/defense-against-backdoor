import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.util.HashSet;
 import java.util.Random;
 import java.util.Scanner;
 import java.util.Set;
 
 public class CoinJam {
 
     public static long isPrime(long x) {
         for (long i = 2; i * i <= x; i++)
             if (x % i == 0) return i;
         return -1;
     }
 
     public static void main(String[] args) throws FileNotFoundException {
         Scanner cin = new Scanner(new File("C-small-attempt0.in"));
         PrintStream cout = new PrintStream("C-small-attempt0.out");
 
 
 
 
 
         Random rand = new Random();
         StringBuilder ans = new StringBuilder();
 
         Set<Integer> visited = new HashSet<>();
         int cnt = 0;
         int N = 16;
         int J = 50;
         while (cnt < J) {
             int hehe = rand.nextInt(1 << (N - 2));
             if (visited.contains(hehe)) continue;
             visited.add(hehe);
 
             int number = (((1 << (N - 2)) | hehe) << 1) | 1;
             boolean isok = true;
             StringBuilder line = new StringBuilder();
             line.append(Integer.toBinaryString(number));
 
             for (int i = 2; i <= 10; i++) {
                 long value = 0;
                 for (int j = N - 1; j >= 0; j--)
                     if ((number & (1 << j)) > 0) value = value * i + 1;
                     else value *= i;
 
                 long factor = isPrime(value);
                 if (factor == -1) {
                     isok = false;
                     break;
                 } else {
                     line.append(" ");
                     line.append(factor);
                 }
             }
 
             if (isok) {
                 ans.append(line.toString());
                 ans.append("\n");
                 cnt++;
             }
         }
 
         cout.printf("Case #1:%n%s", ans.toString());
 
         cin.close();
         cout.close();
     }
 }
