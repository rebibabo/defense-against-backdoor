import java.util.Scanner;
 
 public class A {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            double D = sc.nextInt(), N = sc.nextInt(), min = Long.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                double K = sc.nextInt(), S = sc.nextInt();
                min = Math.min(min, D * S / (D - K));
            }
            System.out.printf("Case #" + i + ": %.6f\n", min);
        }
        sc.close();
    }
 }