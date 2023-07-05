package gcj2015r2;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.text.DecimalFormat;
 import java.util.StringTokenizer;
 
 public class KiddlePool {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        KiddlePool obj = new KiddlePool();
        obj.solve(fileName);
    }
 
    DecimalFormat format = new DecimalFormat("0.000000000");
 
    public void solve(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName + ".out"));
 
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            StringTokenizer token = new StringTokenizer(str, " ");
            int N = Integer.parseInt(token.nextToken());
            double V = Double.parseDouble(token.nextToken());
            double X = Double.parseDouble(token.nextToken());
            double[] R = new double[N];
            double[] C = new double[N];
            for (int j = 0; j < N; j++) {
                str = br.readLine();
                token = new StringTokenizer(str, " ");
                R[j] = Double.parseDouble(token.nextToken());
                C[j] = Double.parseDouble(token.nextToken());
            }
 
            double ans = 0;
            if (N == 1) {
                if (C[0] == X) {
                    ans = V / R[0];
                } else {
                    ans = -1;
                }
 
            } else {
                if (C[0] == C[1]) {
                    if (C[0] == X) {
                        ans = Math.min(V / R[0], V / R[1]);
                    } else {
                        ans = -1;
                    }
                } else {
                    if (X < Math.min(C[0], C[1]) || X > Math.max(C[0], C[1])) {
                        ans = -1;
                    } else {
                        ans = Math.max(V * (X - C[1]) / (C[0] - C[1]) / R[0], V
                                * (X - C[0]) / (C[1] - C[0]) / R[1]);
                    }
                }
            }
 
            if (ans < 0) {
                bw.write("Case #" + (i + 1) + ": " + "IMPOSSIBLE");
            } else {
                bw.write("Case #" + (i + 1) + ": " + format.format(ans));
            }
            bw.write("\r\n");
        }
        bw.close();
        br.close();
    }
 }
