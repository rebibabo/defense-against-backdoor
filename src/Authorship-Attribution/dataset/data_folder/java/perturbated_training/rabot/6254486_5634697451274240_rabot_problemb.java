package qcj2016jr;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class ProblemB {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        ProblemB obj = new ProblemB();
        obj.solve(fileName);
    }
 
    public void solve(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName + ".out"));
 
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String S = br.readLine();
            int[][] wk = new int[2][S.length()];
            for (int j = 0 ; j < S.length() ; j++) {
                wk[0][j] = S.charAt(j) == '+' ? 1 : 0;
            }
            int ans = 0;
            int pos = S.length() - 1;
            while (pos >= 0) {
                if (wk[ans % 2][pos] == 1) {
                    pos--;
                    continue;
                }
                int last = 0;
                if (wk[ans % 2][0] == 1) {
                    last = 1;
                    while (last <= pos) {
                        if (wk[ans % 2][last] == 0) {
                            break;
                        }
                        last++;
                    }
                    last--;
                } else {
                    last = pos;
                }
                System.arraycopy(wk[ans % 2], 0, wk[(ans + 1) % 2], 0, S.length());
                for (int k = 0 ; k <= last ; k++) {
                    wk[(ans + 1) % 2][k] = 1 - wk[ans % 2][last - k];
                }
                ans++;
            }
 
            bw.write("Case #" + (i + 1) + ": " + ans);
            bw.write("\r\n");
        }
        bw.close();
        br.close();
    }
 }
