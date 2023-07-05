import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class A {
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            int max = in.nextInt();
            String s = in.next();
            
            int stand=0, add=0;
            for (int i=0; i<=max; i++) {
                int digit = s.charAt(i) - '0';
                if (digit > 0) {
                    if (stand < i) {
                        add += i-stand;
                        stand = i;
                    }
                    stand+=digit;
                }
            }
            
            
            out.println("Case #" + test + ": " + add);
        }
    }
 }
