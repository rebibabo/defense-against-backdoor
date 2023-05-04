import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class B {
    
    public void solve(Scanner scan, PrintWriter out) {
        int b = scan.nextInt();
        int n = scan.nextInt();
        int[] m = new int[b];
        int[] array = new int[b];
        long lcm = 1;
        for (int i = 0; i < b; i++) {
            m[i] = scan.nextInt();
            array[i] = m[i];
            lcm *= array[i];
        }
        int mod = 0;
        for (int i = 0; i < b; i++) {
            mod += lcm/array[i];
        }
        if (n <= b) {
            System.out.println(n);
            out.println(n);
            return;
        }
        int available = -1;
        int lim = n-b < mod ? n-b : (n-b)%mod;
        for (int i = 0; i < lim; i++) {
            int min = findLowest(array);
            boolean found = false;
            for (int j = 0; j < b; j++) {
                array[j] -= min;
                if (!found && array[j] == 0) {
                    available = j;
                    found = true;
                }
            }
            array[available] = m[available];
        }
        
        System.out.println(available+1);
        out.println(available+1);
    }
    
    private int findLowest(int[] array) {
        int min = 1000000;
        for (int i = 0; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }
 
    public static void main(String[] args) throws Exception {
        String filename = "B-small-attempt1";
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