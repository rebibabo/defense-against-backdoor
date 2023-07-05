package round1a;
 
 import java.util.Scanner;
 
 public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int N = sc.nextInt();
            long[] x = new long[N], y = new long[N];
            for (int i = 0; i < N; i++) {
                x[i] = sc.nextLong();
                y[i] = sc.nextLong();
            }
            int[] best = new int[N];
            for (int i = 0; i < N; i++) best[i] = Math.max(0, N-3);
            for (int i = 0; i < N; i++) for (int j = 0; j < i; j++) {
                int left = 0, right = 0;
                for (int k = 0; k < N; k++) {
                    long d = (x[k] - x[i]) * (y[j] - y[i]) - (y[k] - y[i]) * (x[j] - x[i]);
                    if (d < 0) left++;
                    if (d > 0) right++;
                }
                best[i] = Math.min(best[i], Math.min(left, right));
                best[j] = Math.min(best[j], Math.min(left, right));
            }
            
            System.out.println("Case #" + caze + ":");
            for (int i = 0; i < best.length; i++) {
                System.out.println(best[i]);
            }
        }
    }
 
 }
