import java.io.File;
 import java.io.PrintStream;
 import java.util.Locale;
 import java.util.Scanner;
 
 public class A {
    private static final String directions = "^>v<";
 
    private static boolean canMove(String[] cells, int i, int j, int r, int c,
            int direction) {
        while (true) {
            switch (direction) {
            case 0:
                i--;
                break;
            case 1:
                j++;
                break;
            case 2:
                i++;
                break;
            case 3:
                j--;
                break;
            }
 
            if (i < 0 || j < 0 || i >= r || j >= c) {
                return false;
            }
 
            if (cells[i].charAt(j) != '.') {
                return true;
            }
 
        }
    }
 
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
 
        Scanner in = new Scanner(new File("problem.in"));
        PrintStream out = new PrintStream(new File("problem.out"));
 
        int T = in.nextInt();
        for (int test = 1; test <= T; test++) {
            int r = in.nextInt();
            int c = in.nextInt();
 
            String[] cells = new String[r];
 
            for (int i = 0; i < r; i++) {
                cells[i] = in.next();
            }
 
            boolean impossible = false;
            int count = 0;
 
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    char s = cells[i].charAt(j);
                    if (s == '.') {
                        continue;
                    }
 
                    int direction = directions.indexOf(s);
                    if (direction == -1) {
                        continue;
                    }
 
                    boolean[] d = new boolean[4];
                    for (int k = 0; k < 4; k++) {
                        d[k] = canMove(cells, i, j, r, c, k);
                    }
                    if (!(d[0] || d[1] || d[2] || d[3])) {
                        impossible = true;
                        break;
                    }
                    if (!d[direction]) {
                        count++;
                    }
 
                }
                if (impossible) {
                    break;
                }
            }
 
            out.println("Case #" + test + ": "
                    + (impossible ? "IMPOSSIBLE" : count));
        }
 
        in.close();
        out.close();
    }
 
 }
