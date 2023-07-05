package qualification;
 import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class zCNaive {
    
    public void solve(Scanner scan, PrintWriter out) {
        int i = scan.nextInt();
        int z = scan.nextInt();
 
        boolean[] array = new boolean[i+2];
        array[0] = true;
        array[i+1] = true;
 
         int best = 1;
         int bestMax = 0;
         int bestMin = 0;
         
        for (int j = 1; j <= z; j++) {
            best = 1;
            bestMax = 0;
            bestMin = 0;
            for (int k = 1; k <= i; k++) {
                int orig = k;
                if (!array[orig]) {
                    int current = k - 1;
                    int l = 0;
                    while (!array[current]) {
                        current--;
                        l++;
                    }
                    int r = 0;
                    current = k+1;
                    while (!array[current]) {
                        current++;
                        r++;
                    }
                    int max = Math.max(l, r);
                    int min = Math.min(l, r);
                    if (min > bestMin) {
                        best = k;
                        bestMax = max;
                        bestMin = min;
                    }
                    if (min == bestMin && max > bestMax) {
                        best = k;
                        bestMax = max;
                        bestMin = min;
                    }
                }
            }
            array[best] = true;
        }
         System.out.println(bestMax + " " + bestMin);
         out.println(bestMax + " " + bestMin);
    }
 
    public static void main(String[] args) throws Exception {
        String filename = "C-small-1-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new zCNaive().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }