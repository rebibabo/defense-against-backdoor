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
            String s = in.nextLine();
            int ret = 0;
            int cur = 0;
            for (int i = 0; i <= n; i++) {
                int c = s.charAt(i + 1) - '0';
                if (cur < i) {
                    ret += i - cur;
                    cur = i;
                }
                cur += c;
            }
            System.out.println("Case #" + t + ": " + ret);
        }
    }
 
 }
