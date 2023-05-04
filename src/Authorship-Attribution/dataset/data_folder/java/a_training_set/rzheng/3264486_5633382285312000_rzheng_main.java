import java.math.BigInteger;
 import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            BigInteger N = BigInteger.valueOf(sc.nextLong());
            for (int j = 0; j < String.valueOf(N).length() - 1; j++) {
                BigInteger a = N.divide(BigInteger.TEN.pow(j)).mod(BigInteger.TEN);
                BigInteger b = N.divide(BigInteger.TEN.pow(j + 1)).mod(BigInteger.TEN);
                if (a.compareTo(b) < 0) {
                    N = N.subtract(N.mod(BigInteger.TEN.pow(j + 1)).add(BigInteger.ONE));
                }
            }
            System.out.println("Case #" + i + ": " + N);
        }
        sc.close();
    }
 }