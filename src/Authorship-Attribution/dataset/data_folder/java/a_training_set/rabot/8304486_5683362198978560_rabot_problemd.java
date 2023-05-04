
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class ProblemD {
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
 
        String path = ProblemD.class.getResource("").getPath();
        path = path.substring(0, path.lastIndexOf('/'));
        path = path.substring(0, path.lastIndexOf('/')) + "/src/";
 
        sc = new Scanner(new File(path + fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(path + outputFileName));
 
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int R = sc.nextInt();
            int C = sc.nextInt();
            int N = sc.nextInt();
            long D = sc.nextLong();
 
            long[][] min = new long[R][C];
            long[][] max = new long[R][C];
            for (int r = 0; r < R; r++) {
                Arrays.fill(min[r], 1);
                Arrays.fill(max[r], Long.MAX_VALUE);
            }
 
            boolean flg = true;
            for (int n = 0; n < N; n++) {
                int Ri = sc.nextInt() - 1;
                int Ci = sc.nextInt() - 1;
                int Bi = sc.nextInt();
                if (flg == false) {
                    continue;
                }
                if (min[Ri][Ci] > Bi) {
                    flg = false;
                    continue;
                } else {
                    min[Ri][Ci] = Bi;
                }
                if (max[Ri][Ci] < Bi) {
                    flg = false;
                    continue;
                } else {
                    max[Ri][Ci] = Bi;
                }
                for (int r = Ri - 1; r >= 0; r--) {
                    min[r][Ci] = Math.max(min[r][Ci], min[r + 1][Ci] - D);
                    max[r][Ci] = Math.min(max[r][Ci], max[r + 1][Ci] + D);
                }
                for (int r = Ri + 1; r < R; r++) {
                    min[r][Ci] = Math.max(min[r][Ci], min[r - 1][Ci] - D);
                    max[r][Ci] = Math.min(max[r][Ci], max[r - 1][Ci] + D);
                }
                for (int c = Ci - 1; c >= 0; c--) {
                    for (int r = 0; r < R; r++) {
                        min[r][c] = Math.max(min[r][c], min[r][c + 1] - D);
                        max[r][c] = Math.min(max[r][c], max[r][c + 1] + D);
                    }
                }
                for (int c = Ci + 1; c < C; c++) {
                    for (int r = 0; r < R; r++) {
                        min[r][c] = Math.max(min[r][c], min[r][c - 1] - D);
                        max[r][c] = Math.min(max[r][c], max[r][c - 1] + D);
                    }
                }
            }
            long ans = 0;
            if (flg) {
                for (int r = 0; r < R; r++) {
                    for (int c = 0; c < C; c++) {
                        if (min[r][c] > max[r][c]) {
                            flg = false;
                            break;
                        }
                        ans = (ans + max[r][c]) % 1000000007;
                    }
                }
            }
 
            bw.write("Case #" + i + ": " + (flg ? ans : "IMPOSSIBLE"));
            bw.write("\r\n");
        }
        bw.close();
        sc.close();
    }
 }
