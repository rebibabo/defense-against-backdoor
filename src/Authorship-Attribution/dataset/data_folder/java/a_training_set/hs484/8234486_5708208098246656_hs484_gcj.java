package gcj.R2_2015.A;
 
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
 
 
        if (true) { filename = "A-small-attempt1.in"; }
 
 
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
 
    int toi(char c) {
        if (c == '>') return 0;
        if (c == '^') return 1;
        if (c == '<') return 2;
        if (c == 'v') return 3;
        return 4;
    }
 
    boolean isdir(int x) {
        return 0 <= x && x <= 3;
    }
 
    void markClear() {
        for (int i = 0; i < mark.length; i++) Arrays.fill(mark[i], 0);
    }
 
    int[] dr = {0, -1, 0, 1};
    int[] dc = {1, 0, -1, 0};
 
    int[] s1 = new int[100 * 100 + 10];
    int[] s2 = new int[100 * 100 + 10];
    int[] s3 = new int[100 * 100 + 10];
 
    int R, C;
    int[][] table;
    int[][] mark;
    int[][] moved;
    void solve(Scanner sc, PrintWriter fout) {
        R = sc.nextInt();
        C = sc.nextInt();
        table = new int[R + 2][C + 2];
        mark = new int[R + 2][C + 2];
        moved = new int[R + 2][C + 2];
        for (int i = 0; i < R + 2; i++) for (int j = 0; j < C + 2; j++) table[i][j] = -1;
        for (int i = 0; i < R; i++) {
            char[] s = sc.next().toCharArray();
            for (int j = 0; j < C; j++) {
                table[i+1][j+1] = toi(s[j]);
            }
        }
 
        int countR[] = new int[R + 2];
        int countC[] = new int[C + 2];
 
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (isdir(table[i][j])) {
                    countR[i]++;
                    countC[j]++;
                }
            }
        }
 
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (isdir(table[i][j])) {
                    markClear();
                    if (countR[i] == 1 && countC[j] == 1) {
                        fout.println("IMPOSSIBLE");
                        return;
                    }
 
                    int d = table[i][j];
                    int r = i;
                    int c = j;
                    int step = 0;
 
                    int sp = 0;
                    for (;;) {
                        if (table[r][c] == -1) {
                            break;
                        }
                        if (mark[r][c] != 0) {
                            sp = -1; 
                            break;
                        }
                        mark[r][c] = (++step);
                        if (isdir(table[r][c])) {
                            d = table[r][c];
                            s1[sp] = r;
                            s2[sp] = c;
                            sp++;
                        }
                        r += dr[d];
                        c += dc[d];
                    }
                    if (sp == -1) {
                        
                    } else if (sp == 1) {
                        int rr = s1[sp-1];
                        int cc = s2[sp-1];
                        table[rr][cc] = (table[rr][cc] + 1) % 4;
                        j--;
                        moved[rr][cc] = 1;
                    } else {
                        table[s1[sp-1]][s2[sp-1]] = (table[s1[sp-2]][s2[sp-2]] + 2) % 4;
                        moved[s1[sp-1]][s2[sp-1]] = 1;
                    }
 
                }
            }
        }
 
        int ans = 0;
        for (int i = 0; i < R + 2; i++) for (int j = 0; j < C + 2; j++)
            ans += moved[i][j];
        fout.println(ans);
        return;
    }
 }
