import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.InputStreamReader;
 import java.io.PrintStream;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 
 public class Codejam {
    
    public static void main(String[] args) {
        
        
        Scanner in = null;
        try {
            in = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e1) {
        }
        try {
            System.setOut(new PrintStream(new File("output.txt")));
        } catch (FileNotFoundException e) {
        }
        
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int n = in.nextInt();
            int r = in.nextInt();
            int c = in.nextInt();
            if (r > c) {
                r ^= c;
                c ^= r;
                r ^= c;
            }
            boolean rich = true;
            if ((r * c) % n == 0) {
                if (n <= 2) {
                    rich = false;
                } else if (r > 1) {
                    if (n == 3) {
                        rich = false;
                    } else {
                        if (c == 4 && r > 2) {
                            rich = false;
                        }
                    }
                }
            }
            System.out.println("Case #" + t + ": " + (rich ? "RICHARD" : "GABRIEL"));
        }
    }
 
 }
