import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int B = sc.nextInt(), M = sc.nextInt();
            int[][] matrix = new int[B][B];
            if (tryMatrix(matrix, 0, 1, M)) {
                System.out.println("Case #" + i + ": POSSIBLE");
                for (int j = 0; j < matrix.length; j++) {
                    for (int k = 0; k < matrix.length; k++) {
                        System.out.print(matrix[j][k]);
                    }
                    System.out.println();
                }
            } else {
                System.out.println("Case #" + i + ": IMPOSSIBLE");
            }
        }
        sc.close();
    }
 
    private static boolean tryMatrix(int[][] matrix, int i, int j, int M) {
        if (i == matrix.length - 1) {
            return fulfilled(matrix, M);
        }
        int newI = i;
        int newJ = j;
        if (++newJ >= matrix.length) {
            newJ = ++newI + 1;
        }
        if (tryMatrix(matrix, newI, newJ, M)) {
            return true;
        }
        matrix[i][j] = 1;
        if (tryMatrix(matrix, newI, newJ, M)) {
            return true;
        }
        matrix[i][j] = 0;
        return false;
    }
 
    private static boolean fulfilled(int[][] matrix, int M) {
        int[] num = new int[matrix.length];
        num[num.length - 1] = 1;
        for (int i = num.length - 2; i >= 0; i--) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    num[i] += num[j];
                }
            }
        }
        return num[0] == M;
    }
 }