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
            String s = in.next();
 
            String res = String.valueOf(s.charAt(0));
            for (int i=1; i<s.length(); i++) {
                char c = s.charAt(i);
                if (c < res.charAt(0)) {
                    res = res + c;
                } else {
                    res = c + res;
                }
            }
 
            out.printf("Case #%d: %s\n", test, res);
 
        }
 
    }
 }
