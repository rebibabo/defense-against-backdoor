import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
    
    public void solve(Scanner scan, PrintWriter out) {
        int r = scan.nextInt();
        int c = scan.nextInt();
        int w = scan.nextInt();
        int result = r*(c/w);
        if (c%w == 0) {
            result += w-1;
        } else {
            result += w;
        }
        System.out.println(result);
        out.println(result);
    }
    
    public static void main(String[] args) throws Exception {
        String filename = "A-small-attempt2";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new A().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }