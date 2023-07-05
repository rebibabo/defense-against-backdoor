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
            String line = sc.next();
            int k = sc.nextInt();
            boolean[] pancakes = new boolean[line.length()];
            for (int i = 0; i < line.length(); i++) {
                pancakes[i] = (line.charAt(i) == '+');
            }
            
            int count = 0;
            for (int i = 0; i < line.length()-k+1; i++) {
                if (pancakes[i]) continue;
                else {
                    count++;
                    for (int j = i; j < i+k; j++) {
                        pancakes[j] = !pancakes[j];
                    }
                }
            }
            boolean poss = true;
            for (int i = 0; i < line.length(); i++) {
                if (!pancakes[i]) {
                    poss = false;
                }
            }
            String sol = "IMPOSSIBLE";
            if (poss) {
                sol = "" + count;
            }
            
            System.out.println("Case #" + t + ": " + sol);
            out.println("Case #" + t + ": " + sol);
        }
 
        out.close();
        sc.close();
    }
 }
