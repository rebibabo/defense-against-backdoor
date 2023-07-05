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
        
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int max = sc.nextInt();
            String s = sc.next();
            int sol = 0;
            int peopleStanding = 0;
            for (int i = 0; i <= max; i++) {
                char c = s.charAt(i);
                int v = c - '0';
                sol = Math.max(sol, i - peopleStanding);
                peopleStanding += v;
            }
            out.println("Case #" + t + ": " + sol);
        }
        
        out.close();
        sc.close();
    }
 }
