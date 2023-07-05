import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class D {
    
    public void solve(Scanner scan, PrintWriter out) {
        int x = scan.nextInt();
        int r = scan.nextInt();
        int c = scan.nextInt();
        String result = ((r*c) % x != 0) || (tooWide(x, r, c)) ? "RICHARD" : "GABRIEL";
        if (x == 4 && (r == 2 || c == 2)) result = "RICHARD";
        System.out.println(result);
        out.println(result);
    }
    
    private boolean tooWide(int x, int r, int c) {
        int min = Math.min(r, c);
        for (int i = 0; i < x; i++) {
            int height = i+1;
            int width = x-i;
            if (min < height && min < width) return true;
        }
        return false;
    }
 
    public static void main(String[] args) throws Exception {
         Scanner scan = new Scanner(new FileReader("D-small-attempt2.in"));
         PrintWriter out = new PrintWriter("D-small-attempt2.out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new D().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }