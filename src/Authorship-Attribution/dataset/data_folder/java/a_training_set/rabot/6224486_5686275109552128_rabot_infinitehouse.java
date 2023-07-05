package gcj2015qr;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.StringTokenizer;
 
 public class InfiniteHouse {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        InfiniteHouse obj = new InfiniteHouse();
        obj.solve(fileName);
    }
 
    public void solve(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName + ".out"));
 
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            Integer D = Integer.parseInt(br.readLine());
            String str = br.readLine();
            StringTokenizer token = new StringTokenizer(str, " ");
 
            int[] P = new int[D];
            int max = 0;
            for (int j = 0; j < D; j++) {
                P[j] = Integer.parseInt(token.nextToken());
                max = Math.max(max, P[j]);
            }
 
            int ans = 1000;
            for (int j = 1; j <= max; j++) {
                int special = 0;
                for (int k = 0; k < D; k++) {
                    special += ((P[k] + (j - 1)) / j) - 1;
                }
                ans = Math.min(ans, j + special);
            }
 
            bw.write("Case #" + (i + 1) + ": " + ans);
            bw.write("\r\n");
        }
        bw.close();
        br.close();
    }
 }
