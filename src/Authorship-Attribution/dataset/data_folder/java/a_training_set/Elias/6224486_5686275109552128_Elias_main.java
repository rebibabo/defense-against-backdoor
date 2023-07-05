import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Scanner;
 
 public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
 
    public Main() throws FileNotFoundException {
        
        Scanner sc = new Scanner(new File("B-small-attempt6.in"));
        PrintWriter out = new PrintWriter(new File("B_small.out"));
 
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int amount = sc.nextInt();
            int[] array = new int[amount];
            for (int i = 0; i < amount; i++) {
                array[i] = sc.nextInt();
            }
            int min = 1001;
            for (int i = 0; i < amount; i++) {
                min = Math.min(min, array[i]);
            }
            int max = 0;
            for (int i = 0; i < amount; i++) {
                max = Math.max(max, array[i]);
            }
            int sol = max;
            for (int i = 1; i < max; i++) {
                int subtotal = 0;
                for (int j = 0; j < amount; j++) {
                    int val = array[j];
                    if (array[j] > i) {
                        int subsubtotal = (array[j] - i) / i;
                        subsubtotal++;
                        if (array[j] % i == 0)
                            subsubtotal--;
                        subtotal += subsubtotal;
                    }
                }
                sol = Math.min(sol, subtotal + i);
            }
            System.out.println("Case #" + t + ": " + sol);
            out.println("Case #" + t + ": " + sol);
        }
 
        out.close();
        sc.close();
    }
 }
