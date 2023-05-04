package year2016.qualification;
 
 import java.io.File;
 import java.io.PrintWriter;
 import java.math.BigInteger;
 import java.util.Scanner;
 
 public class CoinJam {
     
     public static void main(String[] args) throws Exception {
         File inputFile = new File("C-small-attempt1.in");
         Scanner in = new Scanner(inputFile);
         File outputFile = new File("output.txt");
         PrintWriter out = new PrintWriter(outputFile);
 
         int T = in.nextInt();
         for (int t=0; t<T; t++) {
             int N = in.nextInt();
             int J = in.nextInt();
             out.println("Case #"+(t+1)+":");
             out.print(find(N, J));
         }
 
         out.close();
     }
 
     static String find(int digits, int amount) {
         int divisiorLimit = 1000000;
         BigInteger divisorLimitBig = BigInteger.valueOf(divisiorLimit);
         StringBuilder output = new StringBuilder();
         int limit = 1 << (digits-2);
         BigInteger[] proof = new BigInteger[11];
         for (int mask = 0; mask < limit; mask++) {
             StringBuilder sb = new StringBuilder();
             sb.append('1');
             for (int i=digits-3; i>=0; i--) {
                 sb.append((mask&(1 << i)) == 0 ? '0' : '1');
             }
             sb.append('1');
             String coin = sb.toString();
             boolean ok = true;
             for (int base=2; base<=10; base++) {
                 BigInteger number = new BigInteger(coin, base);
                 boolean found = false;
                 int dLimit = (divisorLimitBig.compareTo(number) < 0) ? divisiorLimit : (number.intValue()-1);
                 for (int i=2; i<=dLimit; i++) {
                     BigInteger divisor = BigInteger.valueOf(i);
                     if (number.remainder(divisor).equals(BigInteger.ZERO)) {
                         proof[base] = divisor;
                         found = true;
                         break;
                     }
                 }
                 if (!found) {
                     ok = false;
                     break;
                 }
             }
             if (ok) {
                 output.append(coin);
                 for (int base=2; base<=10; base++) {
                     output.append(' ').append(proof[base]);
                 }
                 output.append('\n');
                 if (--amount == 0) {
                     break;
                 }
             }
         }
         return output.toString();
     }
     
 }
