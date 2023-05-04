import java.io.*;
 import java.util.Scanner;
 
 public class Main {
 
     public static void main(String[] args) throws IOException {
         System.setOut(new PrintStream(new File("output.txt")));
         Scanner in = new Scanner(new FileInputStream(new File("input.txt")));
 
         int T = in.nextInt();
         in.nextLine();
         for (int t = 1; t <= T; t++) {
             long n = in.nextLong();
             long k = in.nextLong();
 
             long d = 1;
             while (d * 2L <= k)d *= 2L;
 
             k -= d;
 
             for (long x = d / 2L; x > 0; x /= 2L) {
                 if (k < x) {
                     n = n / 2L;
                 } else {
                     n = (n - 1L) / 2L;
                     k -= x;
                 }
             }
 
             long mi = (n - 1L) / 2L;
             long ma = n / 2L;
 
             System.out.println("Case #" + t + ": " + ma + " " + mi);
         }
     }
 }
