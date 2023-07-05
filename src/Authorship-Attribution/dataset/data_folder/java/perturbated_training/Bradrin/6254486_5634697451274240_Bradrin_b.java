import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class B {
    
    public void solve(Scanner scan, PrintWriter out) {
        String input = scan.next();
        char[] pancakes = input.toCharArray();
        
        int flips = 0;
        while (!isComplete(pancakes)) {
            for (int i = pancakes.length-1; i >=0; i--) {
                if (pancakes[i] == '-') {
                    if (pancakes[0] == '-') {
                        flip(pancakes, i+1);
                        flips++;
                    } else {
                        for (int j = 0; j <= i; j++) {
                            if (pancakes[j] == '-') {
                                flip(pancakes, j);
                                flips++;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
        
         System.out.println(flips);
         out.println(flips);
    }
    
    private void flip(char[] pancakes, int n) {
         for (int i = 0; i < n; i++) {
             pancakes[i] = flip(pancakes[i]);
         }
         for (int i = 0; i < n/2; i++) {
             char t = pancakes[i];
             pancakes[i] = pancakes[n-i-1];
             pancakes[n-i-1] = t;
         }
     }
    
    private char flip(char value) {
        return value == '+' ? '-' : '+';
    }
 
     private boolean isComplete(char[] pancakes) {
        for (int i = 0; i < pancakes.length; i++) {
            if (pancakes[i] == '-') {
                return false;
            }
        }
         return true;
     }
 
     public static void main(String[] args) throws Exception {
        String filename = "B-small-attempt0";
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