import java.math.BigInteger;
 import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt(), J = sc.nextInt();
            long jamcoin = (1L << (N - 1)) + 1;
            System.out.println("Case #" + i + ":");
            for (int j = 0; j < J; jamcoin += 2) {
                BigInteger[] divisors = divisors(jamcoin);
                if (divisors != null) {
                    j++;
                    System.out.print(Long.toBinaryString(jamcoin));
                    for (BigInteger divisor : divisors) {
                        System.out.print(" " + divisor);
                    }
                    System.out.println();
                }
            }
        }
        sc.close();
    }
 
    private static BigInteger[] divisors(long jamcoin) {
        BigInteger[] divisors = new BigInteger[9];
        for (int k = 2; k <= 10; k++) {
            if ((divisors[k - 2] = divisor(new BigInteger(Long.toBinaryString(jamcoin), k))) == null) {
                return null;
            }
        }
        return divisors;
    }
 
    private static BigInteger divisor(BigInteger jamcoin) {
        for (BigInteger i = BigInteger.valueOf(3); i.multiply(i).compareTo(jamcoin) <= 0; i = i
                .add(BigInteger.valueOf(2))) {
            if (jamcoin.mod(i).equals(BigInteger.ZERO)) {
                return i;
            }
        }
        return null;
    }
 }