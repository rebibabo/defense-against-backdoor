import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
    
    public void solve(Scanner scan, PrintWriter out) {
        int parties = scan.nextInt();
        int[] count = new int[parties];
        int current = 0;
        for (int i = 0; i < parties; i++) {
            count[i] = scan.nextInt();
            current += count[i];
        }
        if (parties == 2) {
            while (current % 2 != 0) {
                if (count[0] > count[1]) {
                    System.out.print(" A");
                    out.print(" A");
                    count[0]--;
                } else if (count[1] > count[0]) {
                    System.out.print(" B");
                    out.print(" B");
                    count[1]--;
                } else {
                    throw new RuntimeException();
                }
                current--;
            }
            while (current > 0) {
                System.out.print(" AB");
                out.print(" AB");
                current -= 2;
            }
            System.out.println();
            out.println();
            return;
        }
        while (current > 2) {
            int maxIndex = findMaxIndex(count);
            System.out.print(" " + ((char) ('A' + maxIndex)));
            out.print(" " + ((char) ('A' + maxIndex)));
            count[maxIndex]--;
            current--;
        }
        int a = findMaxIndex(count);
        count[a]--;
        int b = findMaxIndex(count);
        count[b]--;
        System.out.println(" " + ((char) ('A' + a)) + ((char) ('A' + b)));
        out.println(" " + ((char) ('A' + a)) + ((char) ('A' + b)));
    }
    
    private int findMaxIndex(int[] count) {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > max) {
                max = count[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
     public static void main(String[] args) throws Exception {
        String filename = "A-large";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ":");
             out.print("Case #" + (count+1) + ":");
             new A().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }