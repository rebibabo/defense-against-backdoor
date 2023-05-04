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
            int r = sc.nextInt();
            int c = sc.nextInt();
            int w = sc.nextInt();
            
            int total = c/w;
            if (c%w == 0) total--;
            total += w;
            
            String solution = "Case #" + task + ": " + total;
            System.out.println(solution);
            out.println(solution);
        }
        
        out.close();
        sc.close();
    }
 }