package qualy;
 
 import java.util.Scanner;
 
 public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int D = sc.nextInt();
            int[] pan = new int[D];
            int max = 1;
            for (int i = 0; i < pan.length; i++) {
                pan[i] = sc.nextInt();
                max = Math.max(max, pan[i]);
            }
            int ans = max;
            for (int i = 1; i <= max; i++) {
                int val = i;
                for (int j = 0; j < pan.length; j++) {
                    val += (pan[j] - 1) / i;
                }
                ans = Math.min(ans, val);
            }
            
            System.out.println("Case #" + caze + ": " + ans);
        }
    }
 
 }
