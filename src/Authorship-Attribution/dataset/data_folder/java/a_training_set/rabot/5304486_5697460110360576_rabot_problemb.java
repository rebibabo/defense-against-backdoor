
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class ProblemB {
 
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
 
        String path = ProblemB.class.getResource("").getPath();
        path = path.substring(0, path.lastIndexOf('/'));
        path = path.substring(0, path.lastIndexOf('/')) + "/src/";
 
        sc = new Scanner(new File(path + fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(path + outputFileName));
 
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            int P = sc.nextInt();
            int[] R = new int[N];
            for (int n = 0; n < N; n++) {
                R[n] = sc.nextInt();
            }
 
            int[][] Q = new int[N][P];
            double[][] dQ = new double[N][P];
            for (int n = 0; n < N; n++) {
                for (int p = 0; p < P; p++) {
                    Q[n][p] = sc.nextInt();
                    dQ[n][p] = ((double) Q[n][p]) / R[n];
                }
            }
 
            for (int n = 0; n < N; n++) {
                Arrays.sort(dQ[n]);
            }
 
            int[] idx = new int[N];
            Arrays.fill(idx, 0);
            int s = 1;
            int kit = 0;
            LOOP: while (true) {
                boolean flg = true;
                for (int n = 0; n < N; n++) {
                    if (dQ[n][idx[n]] < 0.9 * s) {
                        idx[n] += 1;
                        if (idx[n] >= P) {
                            break LOOP;
                        }
                        flg = false;
                        break;
                    }
                    if (dQ[n][idx[n]] > 1.1 * s) {
                        s = (int) Math.ceil(dQ[n][idx[n]] / 1.1);
                        flg = false;
                        break;
                    }
                }
                if (flg) {
                    kit++;
                    for (int n = 0; n < N; n++) {
                        idx[n]++;
                        if (idx[n] >= P) {
                            break LOOP;
                        }
                    }
                }
            }
 
            bw.write("Case #" + i + ": " + kit);
            bw.write("\r\n");
        }
        bw.close();
        sc.close();
    }
 }
