import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class C {
 
    private void solve(Scanner scan, PrintWriter out) {
        int hd = scan.nextInt();
        int ad = scan.nextInt();
        int hk = scan.nextInt();
        int ak = scan.nextInt();
        int b = scan.nextInt();
        int d = scan.nextInt();
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
        int maxD = 0;
        int maxB = 0;
        
        if (d > 0) {
            maxD = (ad / d) + 1000;
        }
        if (b > 0) {
            maxB = (hk / b) + 1000;
        }
        
        int best = Integer.MAX_VALUE;
        for (int i = 0; i <= maxD; i++) {
            for (int j = 0; j <= maxB; j++) {
                int turns = 0;
                int HD = hd;
                int AD = ad;
                int HK = hk;
                int AK = ak;
                int dRemaining = i;
                int bRemaining = j;
                while (dRemaining > 0) {
                    if (turns > 1000) {
                        break;
                    }
                    if (HD - AK + d <= 0) {
                        HD = hd;
                    } else {
                        AK -= d;
                    }
                    HD -= AK;
                    turns++;
                    dRemaining--;
                }
                while (bRemaining > 0) {
                    if (turns > 1000) {
                        break;
                    }
                    if (HD - AK <= 0) {
                        HD = hd;
                    } else {
                        AD += b;
                    }
                    HD -= AK;
                    turns++;
                    bRemaining--;
                }
                while (HK > AD) {
                    if (turns > 1000) {
                        break;
                    }
                    if (HD - AK <= 0) {
                        HD = hd;
                    } else {
                        HK -= AD;
                    }
                    HD -= AK;
                    turns++;
                }
 
                if (turns > 1000) {
                    continue;
                }
                turns++;
                best = Math.min(best, turns);
            }
        }
        
        if (best > 100000) {
            System.out.println("IMPOSSIBLE");
            out.println("IMPOSSIBLE");
            return;
        }
        System.out.println(best);
        out.println(best);
    }
    
     public static void main(String[] args) throws Exception {
        String filename = "C-small-attempt2";
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
