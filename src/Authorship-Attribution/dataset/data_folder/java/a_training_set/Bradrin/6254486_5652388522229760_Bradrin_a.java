import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
    
    public void solve(Scanner scan, PrintWriter out) {
        int n = scan.nextInt();
        
        boolean[] numbers = new boolean[10];
        
        if (n == 0) {
            System.out.println("INSOMNIA");
            out.println("INSOMNIA");
            return;
        }
        
        int current = n;
 
         count(current, numbers);
        while (!asleep(numbers)) {
             current += n;
            count(current, numbers);
        }
        
        System.out.println(current);
        out.println(current);
    }
    
    private void count(int current, boolean[] numbers) {
        while (current > 0) {
            numbers[current%10] = true;
            current /= 10;
        }
     }
 
     private boolean asleep(boolean[] numbers) {
         for (int i = 0; i < numbers.length; i++) {
             if (!numbers[i]) {
                 return false;
             }
         }
         return true;
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