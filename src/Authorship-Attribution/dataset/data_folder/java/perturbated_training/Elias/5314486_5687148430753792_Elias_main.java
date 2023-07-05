import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 
 public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
 
    public Main() throws FileNotFoundException {
        
        Scanner sc = new Scanner(new File("B-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new File("B_small.out"));
 
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            int n = sc.nextInt();
            int c = sc.nextInt();
            int m = sc.nextInt();
            int amount1 = 0;
            int amount2 = 0;
            int[] array = new int[n];
            for (int i = 0; i < m; i++) {
                int pos = sc.nextInt();
                int cust = sc.nextInt();
                array[pos-1]++;
                if (cust == 1) {
                    amount1++;
                } else {
                    amount2++;
                }
            }
            int[] sumArray = new int[n];
            sumArray[0] = array[0];
            for (int i = 1; i < n; i++) {
                sumArray[i] = sumArray[i-1] + array[i];
            }
            
            int min = (int) Math.max(amount1, amount2);
            for (int i = 0; i < n; i++) {
                min = (int) Math.max(min, sumArray[i]/(i+1));
            }
            
            int moves = 0;
            for (int i = 0; i < n; i++) {
                if (array[i] > min) {
                    moves += (array[i]- min);
                }
            }
            
            System.out.println("Case #" + task + ": " + min + " " + moves);
            out.println("Case #" + task + ": " + min + " " + moves);
        }
 
        out.close();
        sc.close();
    }
 }
