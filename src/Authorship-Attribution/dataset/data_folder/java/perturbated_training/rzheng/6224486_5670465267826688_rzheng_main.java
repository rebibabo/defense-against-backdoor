import java.util.Scanner;
 
 public class Main {
    private static int[][] multiply = { { 0, 1, 2, 3, 4, 5, 6, 7 }, { 1, 4, 3, 6, 5, 0, 7, 2 },
            { 2, 7, 4, 1, 6, 3, 0, 5 }, { 3, 2, 5, 4, 7, 6, 1, 0 }, { 4, 5, 6, 7, 0, 1, 2, 3 },
            { 5, 0, 7, 2, 1, 4, 3, 6 }, { 6, 3, 0, 5, 2, 7, 4, 1 }, { 7, 6, 1, 0, 3, 2, 5, 4 } };
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int L = sc.nextInt(), X = sc.nextInt(), result = 0;
            char[] str = sc.next().toCharArray();
            boolean i = false, j = false;
            for (int x = 0; x < X; x++) {
                for (char c : str) {
                    result = multiply[result][c - 'i' + 1];
                    if (result == 1) {
                        i = true;
                    } else if (result == 3 && i) {
                        j = true;
                    }
                }
            }
            System.out.println("Case #" + t + ": " + (j && result == 4 ? "YES" : "NO"));
        }
        sc.close();
    }
 }