import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int K = sc.nextInt(), C = sc.nextInt(), S = sc.nextInt();
            System.out.print("Case #" + i + ":");
            for (int j = 1; j <= K; j++) {
                System.out.print(" " + j);
            }
            System.out.println();
        }
        sc.close();
    }
 }