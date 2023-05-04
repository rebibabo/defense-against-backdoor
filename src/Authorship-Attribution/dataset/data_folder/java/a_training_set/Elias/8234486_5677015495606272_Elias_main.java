import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 public class Main {
    public static void main(String[] args) throws IOException {
        new Main();
    }
    
    public Main() throws IOException {
        
        Scanner sc = new Scanner(new File("D-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new File("D-small-attempt0.out"));
        
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            
            int sol = 0;
            if (r == 2) {
                sol = 1;
            } else if (r == 3) {
                sol = 2;
            } else if (r == 4) {
                sol = 1;
            } else if (r == 5) {
                sol = 1;
            } else if (r == 6) {
                sol = 2;
            }
            
            String solution = "Case #" + task + ": " + sol;
            System.out.println(solution);
            out.println(solution);
        }
        
        out.close();
        sc.close();
    }
 }