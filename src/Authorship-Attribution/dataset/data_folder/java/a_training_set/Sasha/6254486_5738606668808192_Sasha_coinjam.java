import java.io.File;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class CoinJam {
 
    private static void build(StringBuilder b, int pos, int n, int sum, int dif, List<String> res, int j) {
 
        if (res.size() == j) {
            return;
        }
 
        if (pos == n - 1) {
            if (sum % 2 == 0 && dif == 0) {
                b.append(1);
                res.add(b.toString());
            }
            return;
        }
 
        b.append(0);
        build(b, pos+1, n, sum, dif, res, j);
        b.setLength(pos);
 
        b.append(1);
        build(b, pos+1, n, sum + 1, pos % 2 == 0 ? dif -1 : dif + 1, res, j);
        b.setLength(pos);
    }
 
 
 
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
 
 
        int[] div = new int[] {3, 2, 5, 2, 7, 2, 3, 2, 11};
 
        for (int test = 1; test <= T; test++) {
            int n = in.nextInt();
            int j = in.nextInt();
 
 
 
            List<String> res = new ArrayList<>(j);
            StringBuilder b = new StringBuilder();
            b.append(1);
            build(b, 1, n, 0, 0, res, j);
 
            out.printf("Case #%d: \n", test);
            for (int i=0; i<j; i++) {
                out.print(res.get(i));
                for (int k=0; k<div.length; k++) {
                    out.printf(" %d", div[k]);
                }
                out.println();
            }
        }
 
    }
 }
