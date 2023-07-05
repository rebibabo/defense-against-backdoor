package qcj2016jr;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 
 public class ProblemA {
    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        ProblemA obj = new ProblemA();
        obj.solve(fileName);
    }
 
    public void solve(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(fileName + ".out"));
 
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            Integer N = Integer.parseInt(br.readLine());
            String ans = "INSOMNIA";
            int chk = 0;
            int ALL = 0b1111111111;
            LOOP_j:for (int j = 1 ; j <= 100 ; j++) {
                int cur = N * j;
                int num = cur;
                while ( num > 0 ) {
                    int digit = num % 10;
                    chk = chk | (1 << digit);
                    if ( chk == ALL ) {
                        ans = Integer.toString(cur);
                        break LOOP_j;
                    }
                    num = num / 10;
                }
            }
 
            bw.write("Case #" + (i + 1) + ": " + ans);
            bw.write("\r\n");
        }
        bw.close();
        br.close();
    }
 }
