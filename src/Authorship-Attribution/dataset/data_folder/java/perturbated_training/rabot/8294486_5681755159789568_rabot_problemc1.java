
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.text.DecimalFormat;
 import java.util.Scanner;
 
 public class ProblemC1 {
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
 
        String path = ProblemC1.class.getResource("").getPath();
        path = path.substring(0, path.lastIndexOf('/'));
        path = path.substring(0, path.lastIndexOf('/')) + "/src/";
 
        sc = new Scanner(new File(path + fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(path + outputFileName));
 
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            int Q = sc.nextInt();
            int[] E = new int[N];
            int[] S = new int[N];
            for (int n = 0; n < N; n++) {
                E[n] = sc.nextInt();
                S[n] = sc.nextInt();
            }
 
            int[][] D = new int[N][N];
            for (int n0 = 0; n0 < N; n0++) {
                for (int n1 = 0; n1 < N; n1++) {
                    D[n0][n1] = sc.nextInt();
                }
            }
            int[] U = new int[Q];
            int[] V = new int[Q];
            for (int q = 0; q < Q; q++) {
                U[q] = sc.nextInt();
                V[q] = sc.nextInt();
            }
 
            
            double[][] dp = new double[N][2];
            for (int n = 0; n < N; n++) {
                dp[n][0] = -1;
                dp[n][1] = Long.MAX_VALUE;
            }
            dp[0][0] = E[0];
            dp[0][1] = 0;
            for (int n = 1; n < N; n++) {
                double min_t = Long.MAX_VALUE;
                for (int j = 0; j < N; j++) {
                    if (dp[j][0] >= 0) {
                        
                        dp[j][0] -= D[n - 1][n];
                        if (dp[j][0] < 0) {
                            dp[j][0] = -1;
                            dp[j][1] = Long.MAX_VALUE;
                        } else {
                            dp[j][1] += ((double) D[n - 1][n]) / S[j];
                            min_t = Math.min(min_t, dp[j][1]);
                        }
                    }
                }
                
                dp[n][0] = E[n];
                dp[n][1] = min_t;
            }
            DecimalFormat df = new DecimalFormat("#############.000000");
            bw.write("Case #" + i + ": " + df.format(dp[N - 1][1]));
            bw.write("\r\n");
        }
        bw.close();
        sc.close();
    }
 }
