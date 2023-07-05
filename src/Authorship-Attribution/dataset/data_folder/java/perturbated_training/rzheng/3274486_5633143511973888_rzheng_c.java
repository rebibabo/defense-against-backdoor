import java.util.Scanner;
 
 public class C {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            double[] N = new double[sc.nextInt()];
            sc.nextInt();
            double U = sc.nextDouble();
            for (int j = 0; j < N.length; j++) {
                N[j] = sc.nextDouble();
                U += N[j];
            }
            double V = U / N.length;
            int O = N.length;
            for (double n : N) {
                if (n > V) {
                    U -= n;
                    O--;
                }
            }
            double p = 1;
            for (double n : N) {
                p *= O == 0 || n > U / O ? n : U / O;
            }
 
            System.out.printf("Case #" + i + ": %.9f\n", p);
        }
        sc.close();
    }
 }