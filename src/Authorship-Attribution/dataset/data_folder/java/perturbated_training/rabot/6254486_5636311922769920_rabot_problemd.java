package qcj2016jr;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.StringTokenizer;
 
 public class ProblemD {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        ProblemD obj = new ProblemD();
        obj.solve(fileName);
    }
 
    ArrayList<BigInteger> prime = null;
    public void solve(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName + ".out"));
        
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine(), " ");
            int K = Integer.parseInt(token.nextToken());
            int C = Integer.parseInt(token.nextToken());
            int S = Integer.parseInt(token.nextToken());
 
            ArrayList<Long> ans = new ArrayList<>();
            long cur = 0;
            long k = 0;
            int c = 0;
            while (k < K) {
                cur += k++;
                if ( ++c >= C) {
                    ans.add(cur);
                    c = 0;
                    cur = 0;
                } else {
                    cur *= K;
                }
            }
            if ( cur != 0 ) {
                ans.add(cur);
            }
            if ( ans.size() == 0 ) {
                ans.add(0L);
            }
            if ( ans.size() <= S ) {
                bw.write("Case #" + (i + 1) + ":");
                for (long a:ans) {
                    bw.write(" " + (a + 1));
                }
            } else {
                bw.write("Case #" + (i + 1) + ": " + "IMPOSSIBLE");
            }
            
            bw.write("\r\n");
        }
        bw.close();
        br.close();
    }
 }
