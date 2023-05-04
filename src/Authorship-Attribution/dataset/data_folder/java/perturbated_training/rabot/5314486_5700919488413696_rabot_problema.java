
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.text.DecimalFormat;
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
            int N = sc.nextInt();
            int P = sc.nextInt();
            int[] G = new int[N];
            for (int n = 0; n < N; n++) {
                G[n] = sc.nextInt();
            }
 
            int[] cnt = new int[P];
            for (int n = 0; n < N; n++) {
                cnt[G[n] % P]++;
            }
 
            int ans = cnt[0];
            switch (P) {
            case 2:
                ans += (cnt[1] + 1) / 2;
                break;
            case 3:
                if (cnt[2] <= cnt[1]) {
                    ans += cnt[2];
                    ans += (cnt[1] - cnt[2] + 2) / 3;
                } else {
                    ans += cnt[1];
                    ans += (cnt[2] - cnt[1] + 2) / 3;
                }
                break;
            case 4:
                if (cnt[3] <= cnt[1]) {
                    ans += cnt[3];
                    
                    ans += (cnt[2] / 2);
                    
                    if (cnt[2] % 2 == 0) {
                        ans += (cnt[1] - cnt[3] + 3) / 4;
                    } else {
                        ans += (cnt[1] - cnt[3] + 5) / 4;
                    }
                } else {
                    ans += cnt[1];
                    
                    ans += (cnt[2] / 2);
                    
                    if (cnt[2] % 2 == 0) {
                        ans += (cnt[3] - cnt[1] + 3) / 4;
                    } else {
                        ans += (cnt[3] - cnt[1] + 5) / 4;
                    }
                }
                break;
 
            default:
                break;
            }
            bw.write("Case #" + i + ": " + ans);
            bw.write("\r\n");
        }
        bw.close();
        sc.close();
    }
 }
