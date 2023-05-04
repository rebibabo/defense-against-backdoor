
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
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
            String S = sc.next();
            int K = sc.nextInt();
 
            boolean[] SS = new boolean[S.length()];
            for (int j = 0; j < S.length(); j++) {
                SS[j] = (S.charAt(j) == '+');
            }
            int cnt = 0;
            for (int j = 0; j <= S.length() - K; j++) {
                if (!SS[j]) {
                    cnt++;
                    for (int k = 0; k < K; k++) {
                        SS[j + k] = !SS[j + k];
                    }
                }
            }
            boolean chk = true;
            for (int j = 0; j < S.length(); j++) {
                chk = chk && SS[j];
            }
            if (chk) {
                bw.write("Case #" + i + ": " + cnt);
            } else {
                bw.write("Case #" + i + ": " + "IMPOSSIBLE");
            }
            bw.write("\r\n");
        }
        bw.close();
        sc.close();
    }
 }
