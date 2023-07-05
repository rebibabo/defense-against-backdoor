package qualy;
 
 import java.util.Scanner;
 
 public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int N = sc.nextInt();
            String ans = "INSOMNIA";
            if (N > 0) {
                int cur = 0;
                boolean[] used = new boolean[10];
                int tot = 0;
                
                while (tot < 10) {
                    cur += N;
                    int tmp = cur;
                    while (tmp > 0) {
                        int digit = tmp % 10;
                        if (!used[digit]) {
                            used[digit] = true;
                            tot++;
                        }
                        tmp/=10;
                    }
                }
                ans = cur + "";
            }
            System.out.println("Case #" + caze + ": " + ans);
        }
    }
 
 }
