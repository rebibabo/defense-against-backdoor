import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            System.out.println(
                    "Case #" + i + ": " + (solve(sc.nextInt(), sc.nextInt(), sc.nextInt()) ? "GABRIEL" : "RICHARD"));
        }
        sc.close();
    }
 
    private static boolean solve(int X, int R, int C) {
        if (X == 1 || X == 2) {
            return R * C % X == 0;
        } else if (X == 3) {
            return R * C % 3 == 0 && R >= 2 && C >= 2;
        } else {
            return R == 3 && C == 4 || R == 4 && C == 3 || R == 4 && C == 4;
        }
    }
 }