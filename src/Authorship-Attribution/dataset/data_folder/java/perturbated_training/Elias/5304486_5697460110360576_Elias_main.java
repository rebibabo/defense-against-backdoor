import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 
 public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
 
    public Main() throws FileNotFoundException {
        
        Scanner sc = new Scanner(new File("B-small-attempt1.in"));
        PrintWriter out = new PrintWriter(new File("B_small_1.out"));
 
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            int n = sc.nextInt();
            int p = sc.nextInt();
            int[] needed = new int[n];
            for (int i = 0; i < n; i++) {
                needed[i] = sc.nextInt();
            }
            int[][] packages = new int[n][p];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < p; j++) {
                    packages[i][j] = sc.nextInt();
                }
            }
            for (int i = 0; i < n; i++) {
                Arrays.sort(packages[i]);
            }
 
            int[] counters = new int[n];
 
            int sol = solve(n, p, counters, packages, needed, 0, -1, -1);
 
            System.out.println("Case #" + task + ": " + sol);
            out.println("Case #" + task + ": " + sol);
        }
 
        out.close();
        sc.close();
    }
 
    private int solve(int n, int p, int[] counters, int[][] packages, int[] needed, int layer, int min, int max) {
        if (layer == n)
            return 1;
        int counter = counters[layer];
        if (layer == 0) {
            int total = 0;
            while (counter < p) {
                int minAmount = packages[layer][counter] * 10 / 11;
                while (minAmount * 11 < packages[layer][counter] * 10) {
                    minAmount++;
                }
                int minAmount2 = minAmount / needed[layer];
                if (needed[layer] * minAmount2 < minAmount) {
                    minAmount2++;
                }
 
                int maxAmount = packages[layer][counter] * 10 / 9 + 9;
                while (maxAmount * 9 > packages[layer][counter] * 10) {
                    maxAmount--;
                }
                int maxAmount2 = maxAmount / needed[layer];
 
                if (minAmount2 <= maxAmount2) {
                    total += solve(n, p, counters, packages, needed, layer + 1, minAmount2, maxAmount2);
                }
                counter++;
            }
            return total;
        } else {
            while (counter < p) {
                int minAmount = packages[layer][counter] * 10 / 11;
                while (minAmount * 11 < packages[layer][counter] * 10) {
                    minAmount++;
                }
                int minAmount2 = minAmount / needed[layer];
                if (needed[layer] * minAmount2 < minAmount) {
                    minAmount2++;
                }
 
                int maxAmount = packages[layer][counter] * 10 / 9 + 9;
                while (maxAmount * 9 > packages[layer][counter] * 10) {
                    maxAmount--;
                }
                int maxAmount2 = maxAmount / needed[layer];
 
                int newMin = Math.max(min, minAmount2);
                int newMax = Math.min(max, maxAmount2);
                if (newMin <= newMax) {
                    int sol = solve(n, p, counters, packages, needed, layer + 1, newMin, newMax);
                    if (sol == 1) {
                        counters[layer] = counter + 1;
                        return 1;
                    }
                }
                counter++;
            }
            return 0;
        }
    }
 }
