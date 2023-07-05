import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class D {
    
    public void solve(Scanner scan, PrintWriter out) {
        long k = scan.nextInt();
        long c = scan.nextInt();
        long s = scan.nextInt();
        
        int tiles = (int) Math.ceil((double) k / c);
        
        if (tiles > s) {
            System.out.println(" IMPOSSIBLE");
            out.println(" IMPOSSIBLE");
            return;
        }
        
        long count = 0;
        
        for (int i = 0; i < tiles; i++) {
            long index = 0;
            for (int j = 0; j < c; j++) {
                index += count * pow(k, c-j-1);
                count++;
                count = Math.min(k-1, count);
            }
            index++;
            System.out.print(" " + index);
            out.print(" " + index);
        }
        System.out.println();
        out.println();
    }
    
    public long pow(long a, long b) {
        long result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }
    
    public static void main(String[] args) throws Exception {
        String filename = "D-small-attempt1";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ":");
             out.print("Case #" + (count+1) + ":");
             new D().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }