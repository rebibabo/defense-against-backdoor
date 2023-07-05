import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
 
    private void solve(Scanner scan, PrintWriter out) {
        double d = scan.nextInt();
        int n = scan.nextInt();
        
        double[] pos = new double[n];
        double[] speed = new double[n];
        for (int i = 0; i < n; i++) {
            pos[i] = scan.nextDouble();
            speed[i] = scan.nextDouble();
        }
        
        double maxTime = 0;
        
        for (int i = 0; i < n; i++) {
            maxTime = Math.max(maxTime, (d-pos[i])/speed[i]);
        }
        
        double ans = d/maxTime;
        
        System.out.println(ans);
        out.println(ans);
    }
    
     public static void main(String[] args) throws Exception {
        String filename = "A-small-attempt0";
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
