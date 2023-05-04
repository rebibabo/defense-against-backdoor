import java.util.Scanner;
 
 public class Main {
    private static int[] f;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            f = new int[sc.nextInt()];
            for (int j = 0; j < f.length; j++) {
                f[j] = sc.nextInt() - 1;
            }
            int max = 0;
            for (int j = 0; j < f.length; j++) {
                boolean[] flag = new boolean[f.length];
                flag[j] = true;
                int value = j, count = 1, prev = j;
                while (!flag[f[value]]) {
                    flag[f[value]] = true;
                    count++;
                    prev = value;
                    value = f[value];
                }
                if (f[value] == prev) {
                    max = Math.max(max, count + next(j, value, flag));
                } else if (f[value] == j) {
                    max = Math.max(max, count);
                }
            }
            System.out.println("Case #" + i + ": " + max);
        }
        sc.close();
    }
 
    private static int next(int first, int last, boolean[] flag) {
        int max = 0;
        for (int i = 0; i < flag.length; i++) {
            if (!flag[i]) {
                boolean[] clone = flag.clone();
                clone[i] = true;
                int value = i, count = 1, prev = last;
                while (!clone[f[value]]) {
                    clone[f[value]] = true;
                    count++;
                    prev = value;
                    value = f[value];
                }
                if (f[value] == prev) {
                    max = Math.max(max, count + next(first, value, clone));
                } else if (f[value] == first) {
                    max = Math.max(max, count);
                }
            }
        }
        return max;
    }
 }