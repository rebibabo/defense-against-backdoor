import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.HashSet;
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
            int n = sc.nextInt();
            String sol = "INSOMNIA";
            if (n != 0) {
                HashSet<Character> set = new HashSet<Character>();
                int i = 1;
                while(true) {
                    int val = i*n;
                    String s= ""+val;
                    for (int j = 0; j < s.length(); j++) {
                        set.add(s.charAt(j));
                    }
                    if (set.size() == 10) {
                        sol = s;
                        break;
                    }
                    i++;
                }
            }
            System.out.println("Case #" + t + ": " + sol);
            out.println("Case #" + t + ": " + sol);
        }
        
        out.close();
        sc.close();
    }
 }
