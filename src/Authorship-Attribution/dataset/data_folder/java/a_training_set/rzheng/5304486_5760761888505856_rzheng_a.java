import java.util.Scanner;
 
 public class A {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            char[][] grid = new char[sc.nextInt()][];
            sc.nextInt();
            for (int j = 0; j < grid.length; j++) {
                grid[j] = sc.next().toCharArray();
            }
            for (int j = 0; j < grid.length; j++) {
                char c = '?';
                for (int k = 0; k < grid[j].length; k++) {
                    if (grid[j][k] == '?') {
                        grid[j][k] = c;
                    } else {
                        c = grid[j][k];
                    }
                }
            }
            for (int j = 0; j < grid.length; j++) {
                char c = '?';
                for (int k = grid[j].length - 1; k >= 0; k--) {
                    if (grid[j][k] == '?') {
                        grid[j][k] = c;
                    } else {
                        c = grid[j][k];
                    }
                }
            }
            for (int j = 0; j < grid[0].length; j++) {
                char c = '?';
                for (int k = 0; k < grid.length; k++) {
                    if (grid[k][j] == '?') {
                        grid[k][j] = c;
                    } else {
                        c = grid[k][j];
                    }
                }
            }
            for (int j = 0; j < grid[0].length; j++) {
                char c = '?';
                for (int k = grid.length - 1; k >= 0; k--) {
                    if (grid[k][j] == '?') {
                        grid[k][j] = c;
                    } else {
                        c = grid[k][j];
                    }
                }
            }
            System.out.println("Case #" + i + ":");
            for (char[] row : grid) {
                for (char c : row) {
                    System.out.print(c);
                }
                System.out.println();
            }
        }
        sc.close();
    }
 }