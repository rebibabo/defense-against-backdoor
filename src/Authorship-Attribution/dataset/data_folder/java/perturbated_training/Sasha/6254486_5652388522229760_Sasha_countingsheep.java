import java.io.File;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class CountingSheep {
 
    private static String asleep(int n) {
        boolean[] f = new boolean[10];
        int d = 0;
 
        List<Integer> digits = new ArrayList<>();
        while (n != 0) {
            digits.add(n % 10);
            n /= 10;
        }
 
        List<Integer> last = new ArrayList<>();
        int count = 0;
        while (d != 10) {
            count++;
            last.clear();
 
            int p = 0;
            for (int i = 0; i < digits.size(); i++) {
                p += digits.get(i) * count;
                int c = p % 10;
                last.add(c);
                if (!f[c]) {
                    f[c] = true;
                    d++;
                }
                p /= 10;
            }
            while (p != 0) {
                int c = p % 10;
                last.add(c);
                if (!f[c]) {
                    f[c] = true;
                    d++;
                }
                p /= 10;
            }
        }
 
        StringBuilder b = new StringBuilder();
        for (int i = last.size() - 1; i >= 0; i--) {
            b.append(last.get(i));
        }
        return b.toString();
    }
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
 
        for (int test = 1; test <= T; test++) {
            int n = in.nextInt();
 
            out.printf("Case #%d: %s\n", test, n == 0 ? "INSOMNIA" : asleep(n));
        }
 
    }
 }
