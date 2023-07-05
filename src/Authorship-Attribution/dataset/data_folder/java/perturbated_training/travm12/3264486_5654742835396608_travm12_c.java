import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class c {
    public static void main(String[] Args) throws Exception {
        
        Scanner sc = new Scanner(new File("C-small-1-attempt0.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("c.out"))));
        
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            
            long n = sc.nextLong();
            long pos = sc.nextLong();
 
            int dep = 0;
            long v = 0;
            while (pos > ((v << 1) | 1)) {
                v = ((v << 1) | 1);
                dep++;
            }
 
            
            n -= v;
            pos -= v + 1;
            v++;
            if (n % v > pos)
                n = n / v + 1;
            else
                n = n / v;
 
            
            out.printf("Case #%d: %d %d%n", ++cc, n >> 1, (n - 1) >> 1);
        }
        out.close();
    }
 }
