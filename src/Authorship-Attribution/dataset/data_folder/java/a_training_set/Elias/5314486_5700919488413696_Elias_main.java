import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 
 public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
 
    public Main() throws FileNotFoundException {
        
        Scanner sc = new Scanner(new File("A-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new File("A_small.out"));
 
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            int n = sc.nextInt();
            int p = sc.nextInt();
            
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextInt();
            }
            
            int result = 0;
            if (p==2) {
                int amount0 = 0;
                int amount1 = 0;
                for (int i = 0; i < n; i++) {
                    if (array[i]%2 == 0) {
                        amount0++;
                    } else {
                        amount1++;
                    }
                }
                result = amount0 + (amount1+1)/2;
            } else if (p==3) {
                int amount0 = 0;
                int amount1 = 0;
                int amount2 = 0;
                for (int i = 0; i < n; i++) {
                    if (array[i]%3 == 0) {
                        amount0++;
                    } else if (array[i]%3 == 1){
                        amount1++;
                    } else {
                        amount2++;
                    }
                }
                int overlap = (int)Math.min(amount1, amount2);
                result = amount0 + overlap;
                amount1 -= overlap;
                amount2 -= overlap;
                result += (amount1+2)/3;
                result += (amount2+2)/3;
            } else {
                result = -1;
            }
            
            System.out.println("Case #" + task + ": " + result);
            out.println("Case #" + task + ": " + result);
        }
 
        out.close();
        sc.close();
    }
 }
