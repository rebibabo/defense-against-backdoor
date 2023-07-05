import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.Set;
 
 public class B {
    
    public void solve(Scanner scan, PrintWriter out) {
        int n = scan.nextInt();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 2*n - 1; i++) {
            for (int j = 0; j < n; j++) {
                int current = scan.nextInt();
                if (!set.contains(current)) {
                    set.add(current);
                } else {
                    set.remove(current);
                }
            }
        }
        int[] array = new int[n];
        int i = 0;
        for (int element : set) {
            array[i] = element;
            i++;
        }
        Arrays.sort(array);
        for (int element : array) {
            System.out.print(" " + element);
            out.print(" " + element);
        }
        System.out.println();
        out.println();
    }
    
     public static void main(String[] args) throws Exception {
        String filename = "B-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ":");
             out.print("Case #" + (count+1) + ":");
             new B().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }