import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            char[] S = sc.next().toCharArray();
            int K = sc.nextInt(), count = 0;
            for (int j = 0; j <= S.length - K; j++) {
                if (S[j] == '-') {
                    count++;
                    for (int k = j; k < j + K; k++) {
                        S[k] = S[k] == '-' ? '+' : '-';
                    }
                }
            }
            for (int j = S.length - K; j < S.length; j++) {
                if (S[j] == '-') {
                    count = -1;
                }
            }
            System.out.println("Case #" + i + ": " + (count == -1 ? "IMPOSSIBLE" : count));
        }
        sc.close();
    }
 }