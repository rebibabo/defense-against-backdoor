package gcj2015qr;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.StringTokenizer;
 
 public class StandingOvation {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        StandingOvation obj = new StandingOvation();
        obj.solve(fileName);
    }
 
    public void solve(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName + ".out"));
 
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            StringTokenizer token = new StringTokenizer(str, " ");
 
            int smax = Integer.parseInt(token.nextToken());
            String digits = token.nextToken();
 
            int cnt = 0;
            int ans = 0;
            for (int j = 0 ; j < digits.length() ; j++) {
                if (cnt < j) {
                    ans += j - cnt;
                    cnt = j;
                }
                cnt += (digits.charAt(j) - '0');
            }
 
            bw.write("Case #" + (i + 1) + ": " + ans);
            bw.write("\r\n");
        }
        bw.close();
        br.close();
    }
 }
