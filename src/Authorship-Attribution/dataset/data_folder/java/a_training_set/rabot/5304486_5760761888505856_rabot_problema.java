
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class ProblemA {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String fileName = sc.next();
        sc.close();
        int fileNamePos = fileName.indexOf('.');
        String outputFileName = null;
        if (fileNamePos >= 0) {
            outputFileName = fileName.substring(0, fileNamePos) + ".out.txt";
        } else {
            outputFileName = fileName + ".out.txt";
        }
 
        String path = ProblemA.class.getResource("").getPath();
        path = path.substring(0, path.lastIndexOf('/'));
        path = path.substring(0, path.lastIndexOf('/')) + "/src/";
 
        sc = new Scanner(new File(path + fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(path + outputFileName));
 
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int R = sc.nextInt();
            int C = sc.nextInt();
            char[][] Q = new char[R][];
            for (int r = 0; r < R; r++) {
                String line = sc.next();
                Q[r] = line.toCharArray();
            }
 
            char[][] ans = new char[R][C];
            for (int r = 0; r < R; r++) {
                Arrays.fill(ans[r], '?');
            }
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (ans[r][c] == '?') {
                        char ch = find(r, c, Q, ans);
                        fill(r, c, Q, ans, ch);
                    }
                }
            }
            bw.write("Case #" + i + ":");
            bw.write("\r\n");
            for (int r = 0; r < ans.length; r++) {
                bw.write(ans[r]);
                bw.write("\r\n");
            }
        }
        bw.close();
        sc.close();
    }
 
    static char find(int r0, int c0, char[][] Q, char[][] ans) {
        for (int i = r0 + c0; i < Q.length + Q[0].length - 1; i++) {
            for (int r = r0; r < Q.length; r++) {
                int c = i - r;
                if (c >= Q[r].length) {
                    continue;
                }
                if (c < c0) {
                    break;
                }
                if (ans[r][c] == '?' && Q[r][c] != '?') {
                    return Q[r][c];
                }
            }
        }
        return '?';
    }
 
    static void fill(int r0, int c0, char[][] Q, char[][] ans, char ch) {
        int C = Q[r0].length;
        for (int r = r0; r < Q.length; r++) {
            boolean flg = false;
            for (int c = c0; c < Q[r].length; c++) {
                if (Q[r][c] == ch) {
                    flg = true;
                    continue;
                }
                if (Q[r][c] == '?' && ans[r][c] == '?') {
                    continue;
                } else {
                    C = Math.min(C, c);
                }
            }
            if (flg) {
                break;
            }
        }
        for (int r = r0; r < Q.length; r++) {
            boolean flg = true;
            for (int c = c0; c < C; c++) {
                if (!((Q[r][c] == '?' || Q[r][c] == ch) && ans[r][c] == '?')) {
                    flg = false;
                    break;
                }
            }
            if (flg) {
                for (int c = c0; c < C; c++) {
                    ans[r][c] = ch;
                }
            } else {
                break;
            }
        }
    }
 }
