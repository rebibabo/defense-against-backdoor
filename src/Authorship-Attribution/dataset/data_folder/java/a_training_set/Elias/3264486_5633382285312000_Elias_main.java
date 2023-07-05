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
 
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            String sol = sc.next();
            String s = "";
            while (!sol.equals(s)) {
                s = sol;
                sol = "";
                for (int i = 0; i < s.length(); i++) {
                    if (i == s.length() - 1) {
                        sol += s.charAt(i);
                    } else if (s.charAt(i) > s.charAt(i + 1)) {
                        sol += ((char) (s.charAt(i) - 1));
                        for (int j = i + 1; j < s.length(); j++) {
                            sol += '9';
                        }
                        break;
                    } else {
                        sol += s.charAt(i);
                        continue;
                    }
                }
            }
 
            long solution = Long.parseLong(sol);
 
            System.out.println("Case #" + t + ": " + solution);
            out.println("Case #" + t + ": " + solution);
        }
 
        out.close();
        sc.close();
    }
 }
