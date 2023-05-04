package gcj.R1B_2015.B;
 
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class GCJ {
 
    public static void tr(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }
 
    public static void main(String[] args) throws Throwable {
        long start = System.currentTimeMillis();
 
        String pkg = new Object(){}.getClass().getEnclosingClass().getPackage().getName().replace('.', '/');
        String dir = "src/" + pkg;
 
        String filename = "";
 
 
        if (true) { filename = "B-small-attempt0.in"; }
 
 
        Scanner sc = null;
        try {
            sc = new Scanner(new File(dir + "/" + filename));
        } catch (FileNotFoundException e) {
            tr(e.getMessage());
            return;
        }
        PrintWriter fout = new PrintWriter(new File(dir + "/" + filename + ".res"));
 
        GCJ obj = new GCJ();
        int TNO = sc.nextInt();
        for (int tno = 0; tno < TNO; tno++) {
            fout.write(String.format("Case #%d: ", (tno + 1)));
            obj.solve(sc, fout);
            fout.flush();
        }
        fout.flush();
        fout.close();
        long end = System.currentTimeMillis();
        tr((end - start) + "ms");
    }
 
    final int[] dr = {0, 1, 0, -1};
    final int[] dc = {1, 0, -1, 0};
 
    int check(int R, int C, int p) {
        boolean[][] t = new boolean[R][C];
        for (int i = 0; i < R * C; i++) {
            if ((p >> i &1 ) == 1) {
                t[i / C][i % C] = true;
            }
        }
        int pt = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) if (t[r][c]) {
                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];
                    if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                        if (t[nr][nc]) pt++;
                    }
                }
            }
        }
        return pt / 2;
    }
 
    void solve(Scanner sc, PrintWriter fout) {
        int R = sc.nextInt();
        int C = sc.nextInt();
        int N = sc.nextInt();
 
        if ( N== 0) {
            fout.println(0);
            return;
        }
 
        final int RC = R * C;
        
        int p = (1 << N) - 1;
        int best = 9999999;
        do {
            int h = check(R, C, p);
            System.out.println(Integer.toBinaryString(p));
            best = Math.min(best, h);
        } while( (p = next_combination(p)) < (1 << RC) );
        fout.println(best);
    }
    static int next_combination(int p) {
        int lsb = p & -p;
        int rem = p + lsb;
        int rit = rem & ~p;
        return rem | (((rit / lsb) >> 1) - 1);
    }
 
 }
