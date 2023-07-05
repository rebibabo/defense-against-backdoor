import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 public class Main {
    public static void main(String[] args) throws IOException {
        new Main();
    }
    
    public Main() throws IOException {
        
        Scanner sc = new Scanner(new File("A-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new File("A-small-attempt0.out"));
        
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            int amount = sc.nextInt();
            int[] vals = new int[amount];
            for (int i = 0; i < amount; i++) {
                vals[i] = sc.nextInt();
            }
            
            int firstTotal = 0;
            int biggestGap = 0;
            for (int i = 0; i < amount-1; i++) {
                if (vals[i] > vals[i+1]) firstTotal += (vals[i]-vals[i+1]);
                if ((vals[i]-vals[i+1]) > biggestGap) biggestGap = (vals[i]-vals[i+1]);
            }
            
            int secondTotal = 0;
            for (int i = 0; i < amount-1; i++) {
                secondTotal += Math.min(biggestGap, vals[i]);
            }
            
            String solution = "Case #" + task + ": " + firstTotal + " " + secondTotal;
            System.out.println(solution);
            out.println(solution);
        }
        
        out.close();
        sc.close();
    }
 }