import java.util.Arrays;
 import java.util.Scanner;
 
 public class B {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt(), P = sc.nextInt(), R[] = new int[N], Q[][] = new int[N][P];
            for (int j = 0; j < N; j++) {
                R[j] = sc.nextInt();
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < P; k++) {
                    Q[j][k] = sc.nextInt();
                }
                Arrays.sort(Q[j]);
            }
 
            int count = 0, index[] = new int[N];
            while (true) {
                double percentage[] = new double[N];
                for (int j = 0; j < N; j++) {
                    percentage[j] = (double) Q[j][index[j]] / R[j];
                }
                if (isValid(percentage)) {
                    count++;
                    for (int j = 0; j < N; j++) {
                        index[j]++;
                    }
                } else {
                    int minIndex = 0;
                    double min = percentage[0];
                    for (int j = 1; j < N; j++) {
                        if (percentage[j] < min) {
                            minIndex = j;
                            min = percentage[j];
                        }
                    }
                    index[minIndex]++;
                }
                boolean flag = false;
                for (int j = 0; j < N; j++) {
                    if (index[j] == P) {
                        flag = true;
                    }
                }
                if (flag) {
                    break;
                }
            }
 
            System.out.println("Case #" + i + ": " + count);
        }
        sc.close();
    }
 
    private static boolean isValid(double[] percentage) {
        for (double p1 : percentage) {
            int n = (int) (p1 * 10 / 9);
            boolean flag = true;
            for (double p2 : percentage) {
                if (p2 < n * 0.9 || p2 > n * 1.1) {
                    flag = false;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }
 }