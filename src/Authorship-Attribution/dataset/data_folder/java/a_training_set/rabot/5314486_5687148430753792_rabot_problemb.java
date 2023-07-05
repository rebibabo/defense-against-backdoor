
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
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
            System.out.println(i);
            int N = sc.nextInt();
            int C = sc.nextInt();
            int M = sc.nextInt();
            int[] P = new int[M];
            int[] B = new int[M];
            int[] seat = new int[N];
            int[] cust = new int[C];
            for (int m = 0; m < M; m++) {
                P[m] = sc.nextInt() - 1;
                B[m] = sc.nextInt() - 1;
 
                seat[P[m]]++;
                cust[B[m]]++;
            }
 
            int[] seat_sum = new int[N];
            seat_sum[0] = seat[0];
            for (int n = 1; n < N; n++) {
                seat_sum[n] = seat_sum[n - 1] + seat[n];
            }
 
            int min = 0;
            for (int c = 0; c < C; c++) {
                min = Math.max(min, cust[c]);
            }
 
            int ans_y = 0;
            int ans_prom = 0;
            for (int y = min; y <= M; y++) {
                int prom = 0;
                int cur = 0;
                for (int p = N - 1; p >= 0; p--) {
                    if (seat[p] > y) {
                        prom += (seat[p] - y);
                        cur += (seat[p] - y);
                    } else if (cur > 0) {
                        cur -= (y - seat[p]);
                        if (cur < 0) {
                            cur = 0;
                        }
                    }
                }
                if (cur > 0) {
                    continue;
                } else {
                    ans_y = y;
                    ans_prom = prom;
                    break;
                }
            }
 
            bw.write("Case #" + i + ": " + ans_y + " " + ans_prom);
            bw.write("\r\n");
        }
        bw.close();
        sc.close();
    }
 }
