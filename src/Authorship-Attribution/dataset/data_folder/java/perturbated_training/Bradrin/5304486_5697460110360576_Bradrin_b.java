import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Scanner;
 
 public class B {
 
    private void solve(Scanner scan, PrintWriter out) {
        int n = scan.nextInt();
        int p = scan.nextInt();
        
        int[] grams = new int[n];
        for (int i = 0; i < n; i++) {
            grams[i] = scan.nextInt();
        }
        
        int[][] packages = new int[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                packages[i][j] = scan.nextInt();
            }
            Arrays.sort(packages[i]);
        }
        
        int[][][] intervals = new int[n][p][2];
        
        for (int i = 0; i < n; i++) {
            double low = grams[i] * 0.9;
            double high = grams[i] * 1.1;
            for (int j = 0; j < p; j++) {
                int min = Integer.MAX_VALUE;
                int max = 0;
                boolean done = false;
                for (int k = (int) (packages[i][j]/high); k <= packages[i][j] / low; k++) {
                    if (packages[i][j] >= k * low && packages[i][j] <= k * high) {
                        done = true;
                        min = Math.min(min, k);
                        max = Math.max(max, k);
                    }
                }
                if (done) {
                    intervals[i][j][0] = min;
                    intervals[i][j][1] = max;
                } else {
                    intervals[i][j][0] = -1;
                    intervals[i][j][1] = -1;
                }
            }
        }
        
 
 
 
 
 
 
        
        int packs = 0;
        
        for (int j = 0; j < p; j++) {
            int min = intervals[0][j][0];
            int max = intervals[0][j][1];
            if (min == -1) {
                continue;
            }
            int[] matches = new int[n];
            matches[0] = j;
            for (int k = min; k <= max; k++) {
                boolean foundMatch = true;
                for (int i = 1; i < n; i++) {
                    boolean foundMatchForIngredient = false;
                    for (int l = 0; l < p; l++) {
                        if (intervals[i][l][0] <= k && intervals[i][l][1] >= k) {
                            matches[i] = l;
                            foundMatchForIngredient = true;
                            break;
                        }
                    }
                    if (!foundMatchForIngredient) {
                        foundMatch = false;
                        break;
                    }
                }
                if (foundMatch) {
                    for (int i = 0; i < n; i++) {
                        intervals[i][matches[i]][0] = -1;
                        intervals[i][matches[i]][1] = -1;
                    }
                    packs++;
                    break;
                }
            }
        }
        
        System.out.println(packs);
        out.println(packs);
        
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
    }
    
     public static void main(String[] args) throws Exception {
        String filename = "B-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new B().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
 }
