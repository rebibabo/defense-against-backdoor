package roud1A;
 
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class BRatatouille {
     public static void main (String[] args) {
         try (Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
             int tests = sc.nextInt();
             for (int t = 1; t <= tests; t++) {
                 int ingredientCount = sc.nextInt();
                 int packageCount = sc.nextInt();
                 int[] gramsPerServing = new int[ingredientCount];
                 for (int x = 0; x < ingredientCount; x++) gramsPerServing[x] = sc.nextInt();
                 int[][] packageSizes = new int[ingredientCount][packageCount];
                 for (int i = 0; i < ingredientCount; i++) {
                     for (int p = 0; p < packageCount; p++) {
                         packageSizes[i][p] = sc.nextInt();
                     }
                     Arrays.sort(packageSizes[i]);
                 }
                 int[] currentPackage = new int[ingredientCount];
                 int packetsProduced = 0;
                 int servings = 1;
                 outer:
                 while (true) {
                     for (int i = 0; i < ingredientCount; i++) {
                         while (tooLow(servings, gramsPerServing[i], packageSizes[i][currentPackage[i]])) {
                             currentPackage[i]++;
                             if (currentPackage[i] >= packageCount) break outer;
                         }
                     }
                     int originalServings = servings;
                     for (int i = 0; i < ingredientCount; i++) {
                         while (tooHigh(servings, gramsPerServing[i], packageSizes[i][currentPackage[i]])) {
                             servings++;
                         }
                     }
                     if (originalServings == servings) {
                         packetsProduced++;
                         for (int i = 0; i < ingredientCount; i++) {
                             currentPackage[i]++;
                             if (currentPackage[i] >= packageCount) break outer;
                         }
                     }
                 }
                 System.out.printf("Case #%d: %d%n", t, packetsProduced);
             }
         }
     }
     
     static boolean tooLow(int servings, int gramsPerServing, int packageSize) {
         int minPackageSize = (int)Math.ceil(servings * gramsPerServing * 0.9);
         return packageSize < minPackageSize;
     }
     
     static boolean tooHigh(int servings, int gramsPerServing, int packageSize) {
         int maxPackageSize = (int)Math.floor(servings * gramsPerServing * 1.1);
         return packageSize > maxPackageSize;
     }
 }
