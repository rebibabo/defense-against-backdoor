import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class D {
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            int x = in.nextInt();
            int r = in.nextInt();
            int c = in.nextInt();
 
            boolean possible = (r * c) % x == 0;
 
            if (possible) {
                if (x == 3) {
                    if ((r == 1) || (c == 1)) {
                        possible = false;
                    }
                } 
                if (x == 4) {
                    if ((r < 4) && (c < 4)) {
                        possible = false;
                    }
                    if ((r <= 2) || (c <= 2)) {
                        possible = false;
                    }
                }
            }
 
            out.println("Case #" + test + ": " + (possible ? "GABRIEL" : "RICHARD"));
            
        }
    }
 }
