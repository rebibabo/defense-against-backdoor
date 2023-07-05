import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class C {
    
    public void solve(Scanner scan, PrintWriter out) {
        System.out.println();
        out.println();
        int n = scan.nextInt();
        long[] xs = new long[n];
        long[] ys = new long[n];
        for (int i = 0; i < n; i++) {
            xs[i] = scan.nextInt();
            ys[i] = scan.nextInt();
        }
        if (n == 1) {
            System.out.println(0);
            out.println(0);
            return;
        }
        for (int i = 0; i < n; i++) {
            int min = 10000;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int l = 0;
                int r = 0;
                for (int k = 0; k < n; k++) {
                    if (i == k || j == k) continue;
                    long cross = (xs[j] - xs[i])*(ys[k] - ys[i]) - (ys[j] - ys[i])*(xs[k] - xs[i]);
                    if (cross > 0) l++;
                    if (cross < 0) r++;
                }
                min = Math.min(min, l);
                min = Math.min(min, r);
            }
            System.out.println(min);
            out.println(min);
        }
    }
    
    public static void main(String[] args) throws Exception {
        String filename = "C-small-attempt4";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new C().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }