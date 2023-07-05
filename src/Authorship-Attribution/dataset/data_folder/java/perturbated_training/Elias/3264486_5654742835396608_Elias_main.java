import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.HashMap;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 
 
 public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
 
    public Main() throws FileNotFoundException {
        
        Scanner sc = new Scanner(new File("C-small-1-attempt0.in"));
        PrintWriter out = new PrintWriter(new File("C_small_1.out"));
 
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            long n = sc.nextLong();
            long k = sc.nextLong();
            PriorityQueue<Long> sizes = new PriorityQueue<Long>();
            HashMap<Long, Long> amounts = new HashMap<Long, Long>();
            sizes.add(-n);
            amounts.put(n, 1L);
            
            String min = "";
            String max = "";
            while (k > 0) {
                long size = -sizes.poll();
                long amount = amounts.get(size);
                k -= amount;
                long a = size/2;
                long b = (size-1)/2;
                if (k <= 0) {
                    max = "" + a;
                    min = "" + b;
                } else {
                    if (amounts.keySet().contains(a)) {
                        amounts.put(a, amount + amounts.get(a));
                    } else {
                        amounts.put(a, amount);
                        sizes.add(-a);
                    }
                    if (amounts.keySet().contains(b)) {
                        amounts.put(b, amount + amounts.get(b));
                    } else {
                        amounts.put(b, amount);
                        sizes.add(-b);
                    }
                }
            }
            
            String sol = max + " " + min;
            System.out.println("Case #" + t + ": " + sol);
            out.println("Case #" + t + ": " + sol);
        }
 
        out.close();
        sc.close();
    }
 }
